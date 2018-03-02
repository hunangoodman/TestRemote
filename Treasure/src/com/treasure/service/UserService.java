package com.treasure.service;

import java.util.List;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.model.User;

public interface UserService {
	int selectTotalRecord(User user);

	List<User> selectUserList(User user, PageInfo pageVo);

	int addUser(User user) throws Exception;

	int updateUser(User user) throws Exception;

	int updateUserLock(User user) throws Exception;

	int deleteUserByKey(List<?> keys);

	User selectUserByKey(Long userId);

	/**
	 * 登录验证
	 * 
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public User doLogin(User user) throws Exception;

	// 更新密码
	ResponseResult updatePwd(User user);

	String selectRoleIdsByKey(Long nid);

	String selectRoleNamesByKey(Long nid);
}
