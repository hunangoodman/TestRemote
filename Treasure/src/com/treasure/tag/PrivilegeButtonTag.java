package com.treasure.tag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.treasure.constant.SysConstants;
import com.treasure.model.Function;
import com.treasure.model.RoleModule;
import com.treasure.model.User;

/**
 * @project Fusion-Common
 * @package com.fusionzoom.common.tag
 * @class PlatPrivilegeButtonTag.java
 * @author jiagui E-mail:<zhujiagui@zkingsoft.com>
 * @date 2015年9月10日 下午4:40:20
 * @description 平台按钮权限检查标签
 */
public class PrivilegeButtonTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String btnname;

	public void setBtnname(String btnname) {
		this.btnname = btnname;
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		HttpSession session = pageContext.getSession();
		// 获取用户登录信息
		User user = (User) session.getAttribute(SysConstants.LOGIN_KEY);
		// 超级用户
		if (user != null && SysConstants.SUPERMAN.equals(user.getUserName())) {
			return SKIP_PAGE;
		}
		// 获取登录后存储的角色资源信息
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		// 请求资源ID值
		String reqResourceID = request.getParameter("sourceId");
		if (StringUtils.isNotBlank(btnname)) {
			Map<String, RoleModule> roleResMap = (Map<String, RoleModule>) session.getAttribute(SysConstants.ROLE_RESOURCE_KEY);
			if (StringUtils.isNotBlank(reqResourceID)) {
				if (roleResMap != null && roleResMap.containsKey(Integer.valueOf(reqResourceID))) {
					RoleModule roleSource = roleResMap.get(Integer.valueOf(reqResourceID));
					if (roleSource == null || roleSource.getFlist() == null || roleSource.getFlist().size() == 0) {
						return SKIP_BODY;
					}
					synchronized (this) {
						if (roleSource.getFlist() != null) {
							for (Function funtion : roleSource.getFlist()) {
								if (funtion != null && btnname.equals(funtion.getCode())) {
									return SKIP_PAGE;
								}
							}
						}
					}
				}
			}
		}
		return SKIP_BODY;
	}
}
