package com.treasure.model;

import java.util.Date;
import java.util.List;

public class User{

	private Long userId;

	private String fullName;

	private String userName;

	private String jobNo;

	private String password;

	private Integer userType;

	private Long parentId;

	private Long companyId;

	private String mobilePhone;

	private String email;

	private Date addTime;

	private Integer status;

	private String description;
	// 无映射属性
	private String oldpwd; // 旧密码
	private List<UserRole> urlist;// 用户角色集合
	private List<Role> rlist;// 角色集合
	private String roleIds;
	private String msg;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public List<UserRole> getUrlist() {
		return urlist;
	}

	public void setUrlist(List<UserRole> urlist) {
		this.urlist = urlist;
	}

	public List<Role> getRlist() {
		return rlist;
	}

	public void setRlist(List<Role> rlist) {
		this.rlist = rlist;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName
				+ ", userName=" + userName + ", jobNo=" + jobNo + ", password="
				+ password + ", userType=" + userType + ", parentId="
				+ parentId + ", companyId=" + companyId + ", mobilePhone="
				+ mobilePhone + ", email=" + email + ", addTime=" + addTime
				+ ", status=" + status + ", description=" + description
				+ ", oldpwd=" + oldpwd + ", urlist=" + urlist + ", rlist="
				+ rlist + ", roleIds=" + roleIds + ", msg=" + msg + "]";
	}

}