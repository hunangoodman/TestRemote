package com.treasure.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.dao.UserMapper;
import com.treasure.dao.UserRoleMapper;
import com.treasure.model.User;
import com.treasure.model.UserRole;
import com.treasure.service.UserService;
import com.treasure.utils.AesUtil;

/**
 * @project House-Intro-Web-Service
 * @package com.xinhai.houseintro.web.service.impl
 * @class UserServiceImpl.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2015年10月25日 下午5:27:52
 * @description 用户模块service层实现类
 */
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	public List<User> selectUserList(User user, PageInfo pageVo) {
		return userMapper.selectList(user, pageVo);
	}

	public int selectTotalRecord(User user) {
		return userMapper.selectTotalRecord(user);
	}

	public int addUser(User user) {
		int result = userMapper.insertSelective(user);
		if (StringUtils.isNotBlank(user.getRoleIds())) {
			String[] rids = user.getRoleIds().split(",");
			List<UserRole> list = new ArrayList<UserRole>();
			UserRole ur = null;
			for (String rid : rids) {
				ur = new UserRole();
				ur.setRoleId(Long.valueOf(rid));
				ur.setUserId(user.getUserId());
				list.add(ur);
			}
			result += userRoleMapper.insertList(list);
		}
		return result;
	}

	public int updateUser(User user) {
		int result = userMapper.updateByPrimaryKeySelective(user);
		if (StringUtils.isNotBlank(user.getRoleIds())) {
			String[] rids = user.getRoleIds().split(",");
			List<UserRole> list = new ArrayList<UserRole>();
			UserRole ur = null;
			for (String rid : rids) {
				ur = new UserRole();
				ur.setRoleId(Long.valueOf(rid));
				ur.setUserId(user.getUserId());
				list.add(ur);
			}
			result += userRoleMapper.deleteByUserId(user.getUserId());
			result += userRoleMapper.insertList(list);
		} else {
			result += userRoleMapper.deleteByUserId(user.getUserId());
		}
		return result;
	}

	public User selectUserByKey(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	public int deleteUserByKey(List<?> keys) {
		userRoleMapper.deleteUserRoleByKey(keys);
		return userMapper.deleteUserByKey(keys);
	}

	public User doLogin(User user) throws Exception {
		User u = null;
		String msg = "";
		if (user == null || user.getUserName() == null || user.getUserName().equals("")) {
			u = new User();
			msg = "请输入正确的用户信息！";
			u.setMsg(msg);
			return u;
		}
		List<User> ulist = userMapper.selectUserByName(user.getUserName());
		if (ulist == null || ulist.size() < 1) { // 用户不存在
			u = new User();
			msg = "您输入的用户不存在！";
		} else {
			u = ulist.get(0);
			if (u.getStatus() != null && u.getStatus() == 1) { // 用户锁定
				msg = "该用户已锁定";
			} else if (!(u.getPassword()).equals(AesUtil.encrypt(user.getPassword(), SysConstants.AES_KEY))) { // 密码错误
				msg = "您输入的密码错误！";
			} else {
				msg = "true"; // 登录成功
			}
		}
		// 登录成功后，获取登录成功后的用户对象属性
		u.setMsg(msg);
		return u;
	}

	public ResponseResult updatePwd(User user) {
		ResponseResult result = new ResponseResult();
		try {
			User u = userMapper.selectByPrimaryKey(user.getUserId());
			if (u == null) { // 用户不存在
				result.setCode("false");
				result.setMsg("用户不存在!");
			} else {
				if (!(u.getPassword()).equals(AesUtil.encrypt(user.getOldpwd(), SysConstants.AES_KEY))) {
					result.setCode("false");
					result.setMsg("原始密码错误!");
				} else {
					user.setPassword(AesUtil.encrypt(user.getPassword(), SysConstants.AES_KEY));
					int num = userMapper.updateByPrimaryKeySelective(user);
					if (num > 0) {
						result.setCode("true");
						result.setMsg("密码修改成功，请使用新密码登录！");
					} else {
						result.setCode("false");
						result.setMsg("密码修改失败!");
					}
				}
			}
		} catch (Exception e) {
			result.setCode("error");
			result.setMsg("系统异常!");
		}
		return result;
	}

	public int updateUserLock(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public String selectRoleIdsByKey(Long nid) {
		return userRoleMapper.selectRoleIdsByKey(nid);
	}

	@Override
	public String selectRoleNamesByKey(Long nid) {
		return userRoleMapper.selectRoleNamesByKey(nid);
	}
}
