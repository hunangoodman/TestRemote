package com.treasure.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PayUtils {
	public static void main(String[] args) {
		weChatPay();
	}

	public static String weChatPay() {
		try {
			Map<String, String> data = new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("appid", "wx2421b1c4370ec43b");
					put("body", "银哆宝");
					put("mch_id", "1451012502");
					put("nonce_str", UUIDUtil.get32UUID());
					put("notify_url", "http://119.29.15.225:8090/Treasure/order/payResult");
					put("out_trade_no", "YDB0111111");
					put("scene_info", "{'h5_info': {'type':'wap','wap_name':'银哆宝','wap_url':'http://ydb.xfnrsc.com'}}");
					put("spbill_create_ip", "180.175.106.80");
					put("total_fee", "0.01");
					put("trade_type", "MWEB");
				}
			};
			data = sortMapByKey(data);
			String param = "";
			String value = "";
			Document document = new Document("");
			Element xml = document.appendElement("xml");
			for (String key : data.keySet()) {
				value = data.get(key);
				xml.appendElement(key).text(value);
				param += "&" + key + "=" + value;
			}
			String sign = MyMd5Utils.encodeByMd5(param.substring(1, param.length()));
			xml.appendElement("sign").text(sign.toUpperCase());
			// System.out.println(xml.outerHtml());
			Document doc = Jsoup.connect("https://api.mch.weixin.qq.com/pay/unifiedorder")//
					.timeout(1000 * 20).ignoreContentType(true)//
					.requestBody(xml.outerHtml())//
					.post();
			System.out.println(doc.body().html());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String gotIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
}
