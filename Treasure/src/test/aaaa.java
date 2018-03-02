package test;

import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.sf.json.JSONObject;

public class aaaa {
	public static void main(String[] args) {
		
		String code = RandomStringUtils.randomNumeric(6);
		Document doc = null;
		try {
			doc = Jsoup.connect("http://v.juhe.cn/sms/send").timeout(1000 * 20)//
					.ignoreContentType(true)//
					.data("mobile", "18574375543")//
					.data("tpl_id", "57305")//49986
					.data("tpl_value", "#code#=" + code)//
					.data("key", "9f01e583269b4a1dd95aa393bc493105")//bd7d1ae1a54e70c6106ccd16333315a0
					.data("dtype", "json")//
					.get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(doc.body().text());
		System.out.println(json);
	}
}
