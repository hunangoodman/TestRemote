package test;

import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.Member;
import com.treasure.service.MemberService;
import com.treasure.service.impl.MemberServiceImpl;
import com.treasure.utils.AesUtil;
import com.treasure.utils.MyMd5Utils;

public class reutr {
	public static void main(String[] args) {
		ResponseResult result = new ResponseResult();
		MemberService service = new MemberServiceImpl();
		Member member = new Member();
		member.setId(1216L);
		member.setPasswordTwo("123456");
		Member mem=service.selectById(member.getId());
		try {
			if (!(mem.getPasswordTwo()).equals(AesUtil.encrypt(member.getPasswordTwo(), SysConstants.AES_KEY))){
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("二级密码错误！");
				System.out.println(result.getObject());
			//	return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(mem.getSilver()<member.getSilver()){
			result.setCode(SysConstants.STATUS_FALSE);
			result.setMsg("智联币数量不足！");
		//	return result;
		}
		StringBuffer s1= new StringBuffer();
		s1.append("11111111111111");
		s1.append("12930289");
		s1.append("888");
		String sign = null;
		try {
			sign=MyMd5Utils.encodeByMd5(s1.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setObject(sign);
		System.out.println(result.getObject());
	}
}
