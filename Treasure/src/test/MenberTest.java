package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.treasure.model.Member;

public class MenberTest {
	@Test
	public void consult() throws SQLException{
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring-beans-common.xml");
		DataSource consult =(DataSource) context.getBean("dataSource");
		//MemberController consult=(MemberController) context.getBean("memberController");
		Member consults = new Member();
		
		consults.setFullName("刘国旗");
		
//		consults.setConId(5L);
//		consults.setConContent("测试要成功");
//		consults.setConAuthor("测试");
//		consults.setStatus(1);
//		consults.setConHeadline("爸爸是爱你的");
//		consults.setConDate("2017-12-18 16:28:40");
	//	ResponseResult result=consult.showList(consults,info);
//		ResponseResult result=consult.addConsult(consults);
//		修改为成功
//		ResponseResult result=consult.addConsult(consults);

//		ResponseResult result=consult.deleteConsult("1");
//		ResponseResult result=consult.showUpdate(2L);
	//	MemberResult result=consult.signin(consults);
//		ResponseResult result=consult.showList() ;	System.out.println("+++++++++++++++++++++"+result.getObject().toString());
	//	System.out.println("+++++++++++++++++++++"+result.getList());
	//	System.out.println("+++++++++++++++++++++"+result.getCode());
	//	System.out.println("---------------->"+result.getMember());
	//	System.out.println("++++++++++"+result.getMsg());
	}
}
