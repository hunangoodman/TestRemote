package com.treasure.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.treasure.bean.TokenBean;
import com.treasure.constant.StaticConstants;
import com.treasure.utils.DateUtils;

public class MemberLoginFilter implements Filter {
	private List<String> noFilterURL = new ArrayList<String>();

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String token = request.getParameter("token");
		String requestURL = request.getRequestURL().toString();
		if (isNotFilter(requestURL) || requestURL.contains("mgr") || requestURL.contains("sendCode") || requestURL.contains("signin") || requestURL.contains("member/login") || requestURL.contains("checkToken")) {
			chain.doFilter(request, resp);
			return;
		}
		String memberId = null;
		if (requestURL.contains("verified") || requestURL.contains("updatePassword") || requestURL.contains("updateHead") || requestURL.contains("memberInfo")) {
			memberId = request.getParameter("id");
		} else if (requestURL.contains("myInvite") || requestURL.contains("order") || requestURL.contains("tradeHis") || requestURL.contains("withdraw")) {
			memberId = request.getParameter("memberId");
		} else if (requestURL.contains("sale") || requestURL.contains("send")) {
			memberId = request.getParameter("sellerMemberId");
		}
		Integer type = 0;
		if (StringUtils.isBlank(token)) {
			type = 1;
		} else if (StaticConstants.tokenMap.containsKey(token)) {
			TokenBean bean = StaticConstants.tokenMap.get(token);
			if (null == bean) {
				type = 4;
			} else {
				if (StringUtils.isNotBlank(memberId) && !bean.getMemberId().toString().equals(memberId)) {
					type = 4;
				} else {
					int hour = DateUtils.compareTime(bean.getDate(), new Date());
					if (hour > 24) {
						type = 3;
					} else {
						bean.setDate(new Date());
						StaticConstants.tokenMap.put(token, bean);
						chain.doFilter(request, resp);
						return;
					}
				}
			}
		} else {
			type = 2;
		}
		// 根路径
		HttpServletResponse response = (HttpServletResponse) resp;
		response.addHeader("pragma", "no-cache");
		response.addHeader("cache-control", "no-cache");
		response.addDateHeader("expires", 0);
		// 重定向到登录页面
		String path = request.getContextPath() + "/member/checkToken?type=" + type;
		response.sendRedirect(path);
	}

	public void destroy() {

	}

	/**
	 * 判断是否进行过滤 true 不过滤 false 过滤
	 * 
	 * @return
	 */
	private boolean isNotFilter(String requestURL) {
		for (int i = 0; i < noFilterURL.size(); i++) {
			if (requestURL.indexOf(noFilterURL.get(i)) != -1) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {
		// 获取不需要过滤资源
		String strUrl = config.getInitParameter("noFilterURL");
		if (strUrl != null) {
			String[] method = strUrl.split(",");
			for (int i = 0; i < method.length; i++) {
				noFilterURL.add(method[i]);
			}
		}
	}
}
