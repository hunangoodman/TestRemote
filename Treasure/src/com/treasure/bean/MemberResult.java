package com.treasure.bean;

import com.treasure.model.Member;

public class MemberResult extends Result{
	private Member member;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
