package com.treasure.bean;

import java.util.List;

import com.treasure.model.Member;

public class InviteResult extends Result{
	private List<Member> members;

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
