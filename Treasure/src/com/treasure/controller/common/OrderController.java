package com.treasure.controller.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.internal.util.AlipaySignature;
import com.treasure.bean.OrderResult;
import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.bean.SignResult;
import com.treasure.constant.StaticConstants;
import com.treasure.constant.SysConstants;
import com.treasure.model.Member;
import com.treasure.model.Order;
import com.treasure.model.PayRecord;
import com.treasure.model.Setting;
import com.treasure.service.MemberService;
import com.treasure.service.OrderService;
import com.treasure.service.SettingService;

/**
 * @author Administrator 订单管理
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
	@Autowired
	@Qualifier(value = "orderServiceImpl")
	protected OrderService service;
	@Autowired
	@Qualifier(value = "memberServiceImpl")
	protected MemberService memberService;
	@Autowired
	@Qualifier(value = "settingServiceImpl")
	protected SettingService settingService;

	@RequestMapping(value = "/mgr/orderList")
	public String orderList() {
		return "business/order/orderList";
	}

	@RequestMapping(value = "/mgr/orderform")
	public String orderform() {
		return "business/order/orderform";
	}

	@RequestMapping(value = "/mgr/pay")
	public String pay() {
		return "business/pay/pay";
	}

	@RequestMapping(value = "/mgr/pay1")
	public String pay1() {
		return "business/pay/pay1";
	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/mgr/deleteOrder", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult deleteOrder(@RequestParam(required = false, value = "keys") String keys) {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			List<Long> keyes = new ArrayList<Long>();
			String[] objs = keys.split(",");
			for (String k : objs) {
				keyes.add(Long.valueOf(k));
			}
			num = service.deleteByKeys(keyes);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("成功删除" + num + "条数据");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("删除失败!");
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	@RequestMapping(value = "/sign")
	public @ResponseBody SignResult sign(String signStr) {
		SignResult result = new SignResult();
		try {
			// 公钥：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
			String sign = AlipaySignature
					.rsaSign(
							signStr,
							"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxImYDFtQUvik2S9/hTZi/8H5q3s4XUxNLgeYqrOSw/H9/PsKlJQ9t4HLKYw+8AKr0/CkB53oDjRQ3aMpnZexUS5X95EHxoKvWDlyYSLaLFc4Uxtgh6jUbrhb0XVdaQuU9S+j1zKYC2BwNmj9M49qd76pxKbylxB04QstI6gzBs2h6zy4/JFofDcjsUNftbjncus1EttdtWb+Hha+n1K+CXQrLNYTK476zdkCLbdoiE/CPuRywFPSlUKKg0kpblG3RdIInuXYQ8JVfWB96gOFe6bXDaNxH1U3TycV5yDA5+0qc0fXoKDQqwI2lmtDjCH7tyk/jAiPc2IjzrdQs8hIRAgMBAAECggEAQpmdY3H04auyESEsFyk20owY7BPDx09MBOrdYJRLFCSLx4sQYklx6K0oW+ItK1HQ9XXN8VJYNPeZb0/uMjxHRFZ7Gc/132S61yvlMri8McVMC9HLt8y4lSS6qrxhuKVITei5uZltncolmhOx0zYkn5daLVw0+tqm5d1vU4X0r+slh+iB7dk53eeEwMX1HxKtxN0ZnUDv8id6p1zw7H72MYuitlhwScVMD0w4RSMIYcM+rxPy/azIfIbxog+7Z41y/l67nAoMX+2hDcEYt/DRj3XVDqivGuJMz+DL+0xFuh3VMKCb2h/j1uRRNLUagLbKsB+9ZoDwzTAxZH9dXVWztQKBgQDeY0j2Kyr2N7poSlqJJPjLigv8gzjL1X0AVcI4JDJsQZaiRkMX61ZaTgI5OI2AIGWcoNhxzua4jcZ9AUyOk56iL5AYy4i/kt+hILBfmPyGKqCc8jYczRrZdJtFprsFenjl5TSqKnaW6luHBeZflOmfSa7K/8rc4Ghg8QdvWFThWwKBgQDL6CYxh3VzVIhoTqd8bs1V5HHOH6olM5zS/MhrZVcUUd31OGXaXNxSEvB8NXN3riKOtjgCObWQCIsnLnP748tW7BBBEBUoC+jnaP1Z/mddf1KTr10DfdSbeZBYnVjaGmiv+MRiqY1F9BWcImh0MfvOUBCdieAxIuN4YmONH3eqAwKBgQCfcfgB53lqBxKza3v2EHgBFvz5EuxJ2MtCzjnuHqW4UF48ArxgDe9f18ZoWE+7qX6xCfGlx35Mm/nP7nqBTv3RsuBRprxo/lkwIUvTftCvMSju3gpGJBg4CQ66pYw5pVHBItDboI5eLbFp4aUGqrHF7wJPsY41RwAPYUC/aEmTOQKBgGWZrAY717L6qOlIX73pu9iTdn1dW7sjp32dxQN2QFh4nl8ElgOjB4jBtSA+xeUZWFDBzNCXDrfQ+f0w8UvOalBKWaMBr3j/zjIYJz0DPV/iBDRgSblmplw9y0XMSSyLwiQEkV4T27fCck4U+vUCvtRDIrRMse/p0Wv3omD8D/UfAoGBAMN3r4POG5wFlPEVVoaPIGs54hTF5v74LBch8EnS9cBHweudv7l8kwVoPqoDQh/VQTnHpzeyyAm1Vj5A6KXDHW9FX6tQOj+OA1d2Qi0r2iijK5IFI4r63zlr+ZAdBJJasommRnT7JBWeMCf0of/iGz/v+NdEdwYNdzjTFiLIG5O3",
							"utf-8", "RSA2");
			result.setCode(1);
			result.setMsg("签名成功。");
			result.setSign(sign);
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E3001");
			result.setMsg("签名异常！");
		}
		return result;
	}

	@RequestMapping(value = "/signOrder")
	public @ResponseBody SignResult signOrder(String signStr, String orderNo) {
		SignResult result = new SignResult();
		if (StringUtils.isBlank(signStr)) {
			result.setCode(0);
			result.setErrorCode("E3001");
			result.setMsg("签名参数不能为空！");
			return result;
		}
		if (StringUtils.isBlank(orderNo)) {
			result.setCode(0);
			result.setErrorCode("E3002");
			result.setMsg("订单号不能为空！");
			return result;
		}
		System.out.println(signStr);
		System.out.println(orderNo);
		try {
			// 公钥：MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqNKnjp+2vcQWg+SJnPdk1uc+39RHIWjrDmGBZj/0gl0NooyzbPZ+YR91G7NQfRULFyPfiYr5GEy+byNelzMBj1wjeXFRw+jaQgo1j1/bAkPzbXmiUnJxOJddtSvgY0ABfI+HVoMhFu9eiTIJu82UKOWSjFdumBoKt9DzzdWLReciE3zP3eMUHv1IQhiY2Fchau2rajhwBm7w/hGVf6BLsNCyi5pWPZgiWe1MboklK6Z8GcVqT7Lj9EYL84+L9hb3ZMkWvyG2WUNSXgWDrXBvZFQAE2KvWeHR0xaYjhHXcukW2xltjDbFtIw6w5DRJNxXxE0h0C9INJq7H2P+r5+rQwIDAQAB
			if (StringUtils.isBlank(signStr) || !signStr.contains("【total_amount】") || !signStr.contains("【timestamp】") || !signStr.contains(orderNo)) {
				result.setCode(0);
				result.setErrorCode("E3003");
				result.setMsg("签名参数错误！");
				return result;
			}
			Order order = service.selectByOrderNo(orderNo);
			if (null == order || order.getTotalAmount() <= 0) {
				result.setCode(0);
				result.setErrorCode("E3004");
				result.setMsg("订单信息错误！");
				return result;
			}
			String time = StaticConstants.format.format(new Date());
			String amount = order.getTotalAmount() + "";
			signStr = signStr.replaceAll("【total_amount】", amount).replaceAll("【timestamp】", time);
			System.out.println(signStr);
			//MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCo0qeOn7a9xBaD5Imc92TW5z7f1EchaOsOYYFmP/SCXQ2ijLNs9n5hH3Ubs1B9FQsXI9+JivkYTL5vI16XMwGPXCN5cVHD6NpCCjWPX9sCQ/NteaJScnE4l121K+BjQAF8j4dWgyEW716JMgm7zZQo5ZKMV26YGgq30PPN1YtF5yITfM/d4xQe/UhCGJjYVyFq7atqOHAGbvD+EZV/oEuw0LKLmlY9mCJZ7UxuiSUrpnwZxWpPsuP0Rgvzj4v2FvdkyRa/IbZZQ1JeBYOtcG9kVAATYq9Z4dHTFpiOEddy6RbbGW2MNsW0jDrDkNEk3FfETSHQL0g0mrsfY/6vn6tDAgMBAAECggEAS9BmDMeusiO4FLzyC7qR32xzsOLMmrVaMdgZ20hHTq/pKqFTBlDfyk/tMGZjc/iaRWcHwOXXHVGBh7lzhiUSw0WWbLg2Buuv8E5TZTKf0LGhRP2Fps7cB5T6MR0mJIwe3sg59+DlYD5YzvXYpy0YJxGJsVqmQMoZHBlLE5mddOMrvYXkCI0gn6YU48rdBbMvYsw12bchADNi9tKJcCUDA5uomHF422q0Eh2TT2mhpRfEObuYJTJOy4qxZ3uW+bPyVBmpvdkJAZa7595pvCuiYZVJZMSr7l/xTFioWbp1/eczXsLK/QZ55qCDLUyptiCctQ4CW7QF9ZnVHtWdkmNLQQKBgQD+gXvObx6hCYQcjqScEhTknLfKzISSsVDBP9PMsy3+AQDN3Q3NULCnzrP4yhseF9AkMgdofH4J/6EJzjotT3fl/+Ieqnce5CPC034sF74n/lK49UY9nQs2/CssKzxUfXVQHGqUfpDs2d5DJ1gwtWozgNzZJ995l1YXUf12TQNZYQKBgQCp0GQ0eq7DXM+MiByNt9x5qLcuTSFchC5dKfT1uC8o6eKF9wx+PHQ/d2vDuFuuskPGKSM/chg1OnP1heb1RK3WYC3aXOPw3K3O/673ZMzLlbWchGTcP4Fj7LEfCY5jTW8aXK+4SdGXbu4TUx86P9iMXiLdgWagLWRqziIrUDBTIwKBgQDX2L1FwRw6+7Ud+yREcVli4j9GMdomB1gz7YwVyckx6WvjLJ1uCgco6rfcQpxpE00lxYBe72Z92F2d0Vep4gIQAMqns28/tcFWlMQnZSOTC7DgyLR/wNcoFQoQdcK+7YA3J0KFGiqTldtZHN9AwDuH3rxvN8/icaVENfc/A+XZQQKBgFD7lq4IS8vTOQ+V4bgcFMLYGPO1yNEsIEhBuBZuzd6/G04z8PtKrU2PEA94nhCD5xpbh/m72ZuY0rAmOWyCYjy4BWX7oqC9PQ5YlQJaSehU7xVDdi4rAvO6bwWUMxlcVj3GMe67FX8YYeVJYL0QsZ7PTeVAzsZYn2joAReS2FjtAoGATvmf3dkdWJvJwaHQTG91qxWmhkC18EZWpVeQ9Wz6y05WI2U/Qn6GTkAkQVZ4lWsFIuJIwVSa9AnGmJ/2/641cfKjO/e1ZMakWI3dXuHGUNSL1dJZWovd0QL5SdcJcTsvsBMQMU2znloFGyx4Ean3ndFT4xHfATwFK0ZnFrZmYKA=
			String sign = AlipaySignature
					.rsaSign(
							signStr,
							"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxImYDFtQUvik2S9/hTZi/8H5q3s4XUxNLgeYqrOSw/H9/PsKlJQ9t4HLKYw+8AKr0/CkB53oDjRQ3aMpnZexUS5X95EHxoKvWDlyYSLaLFc4Uxtgh6jUbrhb0XVdaQuU9S+j1zKYC2BwNmj9M49qd76pxKbylxB04QstI6gzBs2h6zy4/JFofDcjsUNftbjncus1EttdtWb+Hha+n1K+CXQrLNYTK476zdkCLbdoiE/CPuRywFPSlUKKg0kpblG3RdIInuXYQ8JVfWB96gOFe6bXDaNxH1U3TycV5yDA5+0qc0fXoKDQqwI2lmtDjCH7tyk/jAiPc2IjzrdQs8hIRAgMBAAECggEAQpmdY3H04auyESEsFyk20owY7BPDx09MBOrdYJRLFCSLx4sQYklx6K0oW+ItK1HQ9XXN8VJYNPeZb0/uMjxHRFZ7Gc/132S61yvlMri8McVMC9HLt8y4lSS6qrxhuKVITei5uZltncolmhOx0zYkn5daLVw0+tqm5d1vU4X0r+slh+iB7dk53eeEwMX1HxKtxN0ZnUDv8id6p1zw7H72MYuitlhwScVMD0w4RSMIYcM+rxPy/azIfIbxog+7Z41y/l67nAoMX+2hDcEYt/DRj3XVDqivGuJMz+DL+0xFuh3VMKCb2h/j1uRRNLUagLbKsB+9ZoDwzTAxZH9dXVWztQKBgQDeY0j2Kyr2N7poSlqJJPjLigv8gzjL1X0AVcI4JDJsQZaiRkMX61ZaTgI5OI2AIGWcoNhxzua4jcZ9AUyOk56iL5AYy4i/kt+hILBfmPyGKqCc8jYczRrZdJtFprsFenjl5TSqKnaW6luHBeZflOmfSa7K/8rc4Ghg8QdvWFThWwKBgQDL6CYxh3VzVIhoTqd8bs1V5HHOH6olM5zS/MhrZVcUUd31OGXaXNxSEvB8NXN3riKOtjgCObWQCIsnLnP748tW7BBBEBUoC+jnaP1Z/mddf1KTr10DfdSbeZBYnVjaGmiv+MRiqY1F9BWcImh0MfvOUBCdieAxIuN4YmONH3eqAwKBgQCfcfgB53lqBxKza3v2EHgBFvz5EuxJ2MtCzjnuHqW4UF48ArxgDe9f18ZoWE+7qX6xCfGlx35Mm/nP7nqBTv3RsuBRprxo/lkwIUvTftCvMSju3gpGJBg4CQ66pYw5pVHBItDboI5eLbFp4aUGqrHF7wJPsY41RwAPYUC/aEmTOQKBgGWZrAY717L6qOlIX73pu9iTdn1dW7sjp32dxQN2QFh4nl8ElgOjB4jBtSA+xeUZWFDBzNCXDrfQ+f0w8UvOalBKWaMBr3j/zjIYJz0DPV/iBDRgSblmplw9y0XMSSyLwiQEkV4T27fCck4U+vUCvtRDIrRMse/p0Wv3omD8D/UfAoGBAMN3r4POG5wFlPEVVoaPIGs54hTF5v74LBch8EnS9cBHweudv7l8kwVoPqoDQh/VQTnHpzeyyAm1Vj5A6KXDHW9FX6tQOj+OA1d2Qi0r2iijK5IFI4r63zlr+ZAdBJJasommRnT7JBWeMCf0of/iGz/v+NdEdwYNdzjTFiLIG5O3",
							"utf-8", "RSA2");
			order.setSysTime(time);
			order.setAmount(amount);
			result.setCode(1);
			result.setMsg("签名成功。");
			result.setSign(sign);
			result.setOrder(order);
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E3005");
			result.setMsg("签名异常！");
		}
		return result;
	}

	@RequestMapping(value = "/buy")
	public @ResponseBody OrderResult buy(Order order) {
		System.out.println("order ========== "+order);
		OrderResult result = new OrderResult();
		try {
			service.buy(order, result);
			// if (result.isSuccess() && 1 == order.getPayType()) {//微信支付
			//
			// }
		} catch (DuplicateKeyException e) {
			result.setCode(0);
			result.setErrorCode("E0513");
			result.setMsg("该支付单号已被使用！");
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E0511");
			result.setMsg("生成购买记录异常！");
		}
		return result;
	}

	/**
	 * 更新支付单号
	 * 
	 * @param id
	 * @param payOrderNo
	 * @return
	 */
	@RequestMapping(value = "/update")
	public @ResponseBody OrderResult buy(Long id, String payOrderNo) {
		OrderResult result = new OrderResult();
		try {
			if (null == id) {
				result.setCode(0);
				result.setErrorCode("E2401");
				result.setMsg("订单主键不能为空！");
				return result;
			}
			if (StringUtils.isBlank(payOrderNo)) {
				result.setCode(0);
				result.setErrorCode("E2402");
				result.setMsg("支付单号不能为空！");
				return result;
			}
			Order order = new Order();
			order.setId(id);
			order.setPayOrderNo(payOrderNo);
			int n = service.updatePayOrderNo(order);
			if (1 == n) {
				result.setCode(1);
				result.setMsg("更新订单支付单号成功。");
			} else {
				result.setCode(0);
				result.setErrorCode("E2403");
				result.setMsg("更新订单支付单号失败！");
			}
		} catch (DuplicateKeyException e) {
			result.setCode(0);
			result.setErrorCode("E2405");
			result.setMsg("该支付单号已被使用！");
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E2404");
			result.setMsg("更新订单支付单号异常！");
		}
		return result;
	}

	@RequestMapping(value = "/mgr/showUpdate", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showUpdate(@RequestParam(required = false, value = "key") Long id) {
		ResponseResult result = new ResponseResult();
		try {
			if (id != null) {
				Order order = service.selectByKey(id);
				/*if (null != order) {
					order.setPayType1(1 == order.getPayType() ? "微信支付" : "支付宝支付");
				}*/
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(order);
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}

	// 显示所有订单
	@RequestMapping(value = "/mgr/showList")
	public @ResponseBody ResponseResult showList(Order order, PageInfo pageInfo) {
		ResponseResult result = new ResponseResult();
		try {
			if (pageInfo == null) {
				pageInfo = new PageInfo();
			}
			List<Order> list = service.selectList(order, pageInfo);// 查询符合条件的数据
			int totalRecord = service.selectCount(order);
			pageInfo.setTotalRecord(totalRecord);
			result.setCode(SysConstants.STATUS_TRUE);
			result.setMsg("分页查询订单列表成功。");
			result.setList(list);
			result.setPageInfo(pageInfo);
		} catch (Exception e) {// 异常处理
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("分页查询订单列表异常！");
		}
		return result;
	}

	// 审核
	@RequestMapping(value = "/mgr/check")
	public @ResponseBody ResponseResult check(Order order) {
		System.out.println("order ======== "+order);
		ResponseResult result = new ResponseResult();
		try {
			order.setCheckTime(new Date());
			order.setStatus((byte) 1);
			Setting setting = settingService.querySettingInfo();
			if (1 == order.getPayStatus()) {
				order.setIntegral(order.getQuantity() / setting.getIntegralPrice());
			}
			int n = service.update(order);
			//修改会员账户信息
			/*Member me = memberService.selectById(order.getMemberId());
			//me.setId(memberId);
			me.setMoney(me.getMoney()-order.getQuantity());
			me.setConsumeBean(me.getConsumeBean()+order.getQuantity() * setting.getConsumeBeanRate());
			memberService.update(me);*/
			
			if (n > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("审核订单成功！");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("审核订单失败！");
			}
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("审核订单异常！");
		}
		return result;
	}

	// 订单历史
	@RequestMapping(value = "/orderHis")
	public @ResponseBody OrderResult orderHis(Order order, PageInfo pageInfo) {
		OrderResult result = new OrderResult();
		try {
			if (null == order || null == order.getMemberId()) {
				result.setCode(0);
				result.setErrorCode("E2701");
				result.setMsg("用户主键不能为空！");
				return result;
			}
			if (pageInfo == null) {
				pageInfo = new PageInfo();
			}
			List<Order> list = service.selectList(order, pageInfo);// 查询符合条件的数据
			int totalRecord = service.selectCount(order);
			pageInfo.setTotalRecord(totalRecord);
			result.setCode(1);
			result.setMsg("分页查询订单列表成功。");
			result.setOrders(list);
			result.setPageInfo(pageInfo);
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E2702");
			result.setMsg("分页查询订单列表异常！");
		}
		return result;
	}

	// 支付结果回调
	@RequestMapping(value = "/payResult")
	public @ResponseBody String payResult(String out_trade_no, String trade_no, Double total_amount, String seller_id, String timestamp) {
		try {
			PayRecord record = new PayRecord();
			record.setOrderNo(out_trade_no);
			record.setTradeNo(trade_no);
			record.setTotalAmount(total_amount);
			record.setSellerId(seller_id);
			record.setTimestamp(timestamp);
			service.addPayRecord(record);
		} catch (Exception e) {
		}
		return "success";
	}
}
