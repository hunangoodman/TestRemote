
package com.treasure.controller.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import test.MenberTest;

import com.treasure.bean.AjaxResult;
import com.treasure.bean.InviteResult;
import com.treasure.bean.MallRecordResult;
import com.treasure.bean.MemberResult;
import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.bean.Result;
import com.treasure.bean.TokenBean;
import com.treasure.bean.UploadResult;
import com.treasure.constant.StaticConstants;
import com.treasure.constant.SysConstants;
import com.treasure.model.Card;
import com.treasure.model.CoinRecord;
import com.treasure.model.IncomeRecord;
import com.treasure.model.InestmentIncome;
import com.treasure.model.MallRecord;
import com.treasure.model.MarketRecords;
import com.treasure.model.Member;
import com.treasure.model.MemberExample;
import com.treasure.model.MobileCode;
import com.treasure.model.MoneyManagement;
import com.treasure.model.Setting;
import com.treasure.service.CardService;
import com.treasure.service.CoinRecordService;
import com.treasure.service.IncomeService;
import com.treasure.service.MallRecordService;
import com.treasure.service.MemberService;
import com.treasure.service.MoneyManagementService;
import com.treasure.service.SettingService;
import com.treasure.service.impl.IncomeServiceImpl;
import com.treasure.utils.AesUtil;
import com.treasure.utils.CodeUtil;
import com.treasure.utils.DateUtils;
import com.treasure.utils.FileUtils;
import com.treasure.utils.MyMd5Utils;
import com.treasure.utils.PhoneFormatCheckUtils;
import com.treasure.utils.UUIDUtil;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
/**
 * @project Treasure
 * @package com.treasure.controller.common
 * @class MemberController.java
 * @author jiagui E-mail:1257896208@qq.com
 * @date 2017年9月28日 下午11:18:40
 * @description 用户管理模块
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController {
	@Autowired
	@Qualifier(value = "memberServiceImpl")
	protected MemberService service;
	@Autowired
	@Qualifier(value = "settingServiceImpl")
	protected SettingService settingService;
	
	@Autowired
	@Qualifier(value = "coinRecordServiceImpl")
	private CoinRecordService coinservice;
	
	@Autowired
	@Qualifier(value = "mallRecordServiceImpl")
	private MallRecordService mallRecordService;
	
	@Autowired
	@Qualifier(value = "moneyManagementServiceImpl")
	private MoneyManagementService moneyManagementService;
	
	@Autowired
	@Qualifier(value = "incomeServiceImpl")
	private IncomeService incomeService;
	
	@Autowired
	@Qualifier(value = "cardServiceImpl")
	private CardService cardservice;
	@Value("${member.img.save.path}")
	private String savePath;
	@Value("${member.img.read.path}")
	private String readPath;

	@Value("${member.img.path.separator}")
	
	private String separator;
	private static final Logger log = Logger.getLogger(MemberController.class);

	@RequestMapping(value = "/mgr/memberlist")
	public String memberlist() {
		return "business/member/memberlist";
	}

	@RequestMapping(value = "/mgr/memberform")
	public String memberform() {
		return "business/member/memberform";
	}

	@RequestMapping(value = "/mgr/rechargeform")
	public String rechargeform() {
		return "business/member/rechargeform";
	}
	
	@RequestMapping(value = "/mgr/updatepassword")
	public String updatepassword() {
		return "business/member/updatepassword";
	}
	
	@RequestMapping(value = "/mgr/img")
	public String img() {
		return "business/member/img";
	}
	
	@RequestMapping(value = "/mgr/coinlist")
	public String showPage() {
		System.out.println("member...");	
		return "business/coinrecode/coinrecodelist";
	}
	/*
	 * 添加银行卡【前台】
	 */
	@RequestMapping(value = "/addCard")
	public @ResponseBody ResponseResult addCard(Card card){
		ResponseResult result  = new ResponseResult();
		/*try {
			String bankName = new String(card.getCardInfo().getBytes("ISO8859-1"),"utf-8");
			String FullName = new String(card.getFullName().getBytes("ISO8859-1"),"utf-8");
			card.setCardInfo(bankName);
			card.setFullName(FullName);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			System.out.println("转码错误");
		}*/
		
		System.out.println(card.getCardInfo());
		System.out.println(card.getFullName());
		if(cardservice.addCard(card)!=1){
			result.setCode("0");
			result.setMsg("只能拥有一张银行卡 !");
			return result;
		 }

		service.updateTrueName(card);

		result.setCode("1");
		result.setMsg("添加成功!");
		return result;
	}
	/*
	 * 查询银行卡【前台】
	 */
	@RequestMapping(value = "/showCard",produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showRecord(Long id) {
		ResponseResult result = new ResponseResult();
		List<Card>  list=cardservice.findAll(id);
		if(list.size()==0){
			result.setCode("0");
			result.setMsg("您尚未绑定银行卡");
			return result;
		}
		result.setList(cardservice.findAll(id));
		result.setCode(SysConstants.STATUS_TRUE);
		return result;
	}
	/*
	 * 删除银行卡【前台】
	 */
	@RequestMapping(value = "/delCard",produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult delCard(long id) {
		ResponseResult result = new ResponseResult();
		System.out.println("银行卡"+id);
		if(cardservice.delete(id)>0){
			result.setCode(SysConstants.STATUS_TRUE);
			result.setMsg("删除成功");
			return result;
		}
		result.setCode(SysConstants.STATUS_FALSE);
		result.setMsg("删除失败");
		return result;
	}

	/**
	 * 后台修改密码
	 * */
	@RequestMapping(value = "/mgr/updatepass", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult updatepass(Member member) {
		return service.updatePwd(member);
	}
	
	/**
	 * 查询拨币记录
	 */
	@RequestMapping(value = "/mgr/showRecord",produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showRecord(CoinRecord cord, PageInfo pageVo) {
		if (pageVo == null) {
			pageVo = new PageInfo();
		}
		
		List<CoinRecord> list = coinservice.findAll(cord,pageVo);// 查询符合条件的数据
		System.out.println(list.get(0).getUserName());
		int totalRecord = coinservice.selectCount(cord);
		pageVo.setTotalRecord(totalRecord);
		
		ResponseResult result = new ResponseResult();
		result.setList(list);
		result.setPageInfo(pageVo);
		result.setCode(SysConstants.STATUS_TRUE);
		return result;
	}
	/**
	 * 获取修改信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mgr/showUpdate", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showUpdate(@RequestParam(required = false, value = "key") Long id) {
		ResponseResult result = new ResponseResult();
		try {
			if (id != null) {
				Member member = service.selectById(id);
				result.setCode(SysConstants.STATUS_TRUE);
				result.setObject(member);
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}

	/**
	 * 充值
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mgr/recharge", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult recharge(Member member) {
		ResponseResult result = new ResponseResult();
		try {
			if (member != null) {
				int n = service.recharge(member);
				if (n > 0) {
					CoinRecord cord=new CoinRecord();
					//cord.setFullName(member.getUserName());
					cord.setCoin(member.getRate());
					cord.setMemberId(member.getId());
					//cord.setMobile(member.getMobile());
					cord.setRecordDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
					//cord.setMenber(member.getFullName());
					coinservice.addCoinRecode(cord);
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("充值成功");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("充值失败。");
				}
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}

	/**
	 * 修改会员信息【后台】
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mgr/updateMember", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult updateMgrMember(Member member) {
		ResponseResult result = new ResponseResult();
		try {
 			int n = service.update(member);
			if (n > 0){
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("修改会员信息成功。");
			}else{
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("修改会员信息失败。");
			}
		} catch (Exception e){
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
			result.setObject(null);
		}
		return result;
	}
	
	
	/**
	 * 修改真实姓名
	 * */
	@RequestMapping(value = "/updateFullName", produces = "application/json;charset=utf-8")
	public @ResponseBody MemberResult updateFullName(Member member){
		MemberResult result = new MemberResult();
		Member m = new Member();
		if (null == member || null == member.getId()) {
			result.setCode(0);
			result.setErrorCode("E3101");
			result.setMsg("用户信息错误！");
			return result;
		}
		m.setId(member.getId());
		if (StringUtils.isNotBlank(member.getFullName())) {
			m.setFullName(member.getFullName());
		}
		int n=service.update(m);
		if(n>1){
			result.setCode(1);
			result.setErrorCode("E3101");
			result.setMsg("修改成功！");
		}
		return result;
	}
	
	/**
	 * 修改会员信息【前台】
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMember", produces = "application/json;charset=utf-8")
	public @ResponseBody MemberResult updateMember(Member member){
		MemberResult result = new MemberResult();
		try {
			if (null == member || null == member.getId()) {
				result.setCode(0);
				result.setErrorCode("E3101");
				result.setMsg("用户信息错误！");
				return result;
			}
			
			Long id = member.getId();
			/*MobileCode mobileCode = service.selectByMemberId(id);
			String inCode = member.getCode();
			if (StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode.getCode())) {
				result.setCode(0);
				result.setErrorCode("E3102");
				result.setMsg("短信验证码输入错误！");
				return result;
			}
			if (0 == mobileCode.getStatus()) {
				result.setCode(0);
				result.setErrorCode("E3103");
				result.setMsg("短信验证码已失效，请重新发送！");
				return result;
			}*/
			Member m = new Member();
			m.setId(id);
			if (StringUtils.isNotBlank(member.getAddress())) {
				m.setAddress(member.getAddress());
			}
			if (StringUtils.isNotBlank(member.getCardNumber())) {
				m.setCardNumber(member.getCardNumber());
			}
			if (StringUtils.isNotBlank(member.getIdCard())) {
				m.setIdCard(member.getIdCard());
			}
			if (StringUtils.isNotBlank(member.getBank())) {
				m.setBank(member.getBank());
			}
			if (StringUtils.isNotBlank(member.getPassword())) {
				m.setPassword(member.getPassword());
			}
			if (StringUtils.isNotBlank(member.getFullName())) {
				m.setFullName(member.getFullName());
			}
			Integer type = member.getType();
			if(type!=null){
				if(type==1){
					String password = member.getPassword();
					String passwordAgain = member.getPasswordAgain();
					
					if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能为空！");
						return result;
					}
					if(!password.equals(passwordAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的密码不一致！");
						return result;
					}
					if(password.length()<6){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码长度不能小于6位！");
						return result;
					}
					m.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
				}else if(type==2){
					String passwordTwo = member.getPasswordTwo();
					String passwordTwoAgain = member.getPasswordTwoAgain();
					
					if (StringUtils.isBlank(passwordTwo)||StringUtils.isBlank(passwordTwoAgain)) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("二级密码不能为空！");
						return result;
					}
					if(!passwordTwo.equals(passwordTwoAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的二级密码不一致！");
						return result;
					}
					if(passwordTwo.length()<6){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码长度不能小于6位！");
						return result;
					}
					m.setPasswordTwo(AesUtil.encrypt(passwordTwo, SysConstants.AES_KEY));
				}else{
					String password = member.getPassword();
					String passwordAgain = member.getPasswordAgain();
					String fullName = member.getFullName();
					
					if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能为空！");
						return result;
					}
					if(!password.equals(passwordAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的密码不一致！");
						return result;
					}
					if(password.length()<6){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码长度不能小于6位！");
						return result;
					}
					m.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
					
					String passwordTwo = member.getPasswordTwo();
					String passwordTwoAgain = member.getPasswordTwoAgain();
					if (StringUtils.isBlank(passwordTwo)||StringUtils.isBlank(passwordTwoAgain)) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("二级密码不能为空！");
						return result;
					}
					if(!passwordTwo.equals(passwordTwoAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的二级密码不一致！");
						return result;
					}
					if(passwordTwo.length()<6){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码长度不能小于6位！");
						return result;
					}
					m.setPasswordTwo(AesUtil.encrypt(passwordTwo, SysConstants.AES_KEY));	
				}
			}
			MobileCode mobileCode = new MobileCode();
			mobileCode.setStatus((byte) 0);
			m.setVerifiedStatus((byte)1);
			int n = service.update(m, mobileCode);
			Card card = new Card();
			card.setMemberId(m.getId());
			card.setFullName(m.getFullName());
			card.setCardNumber(m.getCardNumber());
			card.setCardInfo(m.getBank());
			int flg=cardservice.addCard(card);
			int fg=0;
			if(flg==0){
				if(card.getCardInfo()==null&&card.getCardNumber()==null&&card.getFullName()==null) {
					fg=1;
				}else {
				fg=cardservice.updataCard(card);
				}
			}
			if (n >0) {
				result.setCode(1);
				result.setMsg("修改会员信息成功!");
			} else {
				result.setCode(0);
				result.setErrorCode("E3104");
				result.setMsg("修改会员信息失败!");
			}
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode(0);
				result.setErrorCode("E3105");
				result.setMsg("修改会员信息异常!");
			}
		return result;
	}

	/*
	 * 邀请二维码
	 */
	@RequestMapping(value = "/getEncode")
	@Transactional
	public @ResponseBody Result getEncode(String memberid,HttpServletRequest request){
		String filePath="http://www.jjstar.cc:8225/";
		Result result = new Result();
		if(memberid!=null){
			String encode=service.findEncodeById(memberid);
			if(encode==null){
				String desc="http://120.24.7.81:8080/check.html?yqm=ZLZC"+memberid;
				String path=FileUtils.getFolder(savePath); 
				try {
					CodeUtil.encode(desc,path);//10.0.0.123:8080/Treasure
					String name=CodeUtil.getFileName();
					if(name!=null){
						System.out.println("name:"+name);	
						service.addEncode(name,memberid);
						result.setCode(1);
						result.setMsg(filePath+name);
						return result;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else
				filePath=filePath+encode;
		}
		result.setCode(1);
		result.setMsg(filePath);
		return result;
	}
	/*
	 * 钱包二维码
	 */
	@RequestMapping(value = "/getMoneyEncode")
	@Transactional
	public @ResponseBody Result getMoneyEncode(String memberid,String monyAddress){
		String filePath="http://www.jjstar.cc:8225/";
		Result result = new Result();
		if(memberid!=null){
			String encode=service.findMoneyEncodeById(memberid);
			if(encode==null){
				String desc="http://120.24.7.81:8080/html/trans1.html?monyAddress="+monyAddress;//钱包地址
				String path=FileUtils.getFolder(savePath); 
				try {
					CodeUtil.encode(desc,path);//10.0.0.123:8080/Treasure
					String name=CodeUtil.getFileName();
					if(name!=null){
						System.out.println("name:"+name);
						service.addMoneyEncode(name,memberid);
						result.setCode(1);
						result.setMsg(filePath+name);
						return result;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else
				filePath=filePath+encode;
		}
		result.setCode(1);
		result.setMsg(filePath);
		return result;
		
	}
	/**
	 * 锁定、解锁用户
	 * 
	 * @param response
	 * @param userId
	 * @throws Exception
	 */
	@RequestMapping(value = "/mgr/updateMemberLock", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult updateUserLock(@RequestParam(required = false, value = "status") Byte status, @RequestParam(required = false, value = "key") Long id) throws Exception {
		int num = 0;
		ResponseResult result = new ResponseResult();
		try {
			Member member = new Member();
			member.setId(id);
			member.setStatus(status);
			num = service.update(member);
			if (num > 0) {
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("操作成功！");
			} else {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("操作失败！");
			}
		} catch (Exception e) {
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}

	@RequestMapping(value = "/mgr/showList", produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult showList(Member member, PageInfo pageVo) {
		log.info("查询User数据");
		ResponseResult result = new ResponseResult();
		try {
			if(pageVo == null){
				pageVo = new PageInfo();
			}
			List<Member> list = service.selectList(member, pageVo);// 查询符合条件的数据
			int totalRecord = service.selectCount(member);
			pageVo.setTotalRecord(totalRecord);
			result.setCode(SysConstants.STATUS_TRUE);
			result.setList(list);
			result.setPageInfo(pageVo);
		} catch (Exception e) {// 异常处理
			log.info(e.getMessage());
			result.setCode(SysConstants.STATUS_ERROR);
			result.setMsg("系统错误!");
		}
		return result;
	}
	@RequestMapping(value = "/checksTokens")
	public @ResponseBody Result checksToken(String token) {
		Result result = new Result();
		if (StringUtils.isBlank(token)){
			result.setCode(0);
			result.setErrorCode("E0001");
			result.setMsg("请求token不能为空！");
			return result;
		} else if (StaticConstants.tokenMap.containsKey(token)) {
			TokenBean bean = StaticConstants.tokenMap.get(token);
			if (null == bean) {
				result.setCode(0);
				result.setErrorCode("E0004");
				result.setMsg("您无权进行该操作！");
				return result;
			} else {	
				result.setCode(1);
				result.setMsg("成功");
				return result;
				}
		}
		return result;
	}
	
	@RequestMapping(value = "/checkToken")
	public @ResponseBody Result check(Integer type) {
		Result result = new Result();
		switch (type) {
		case 1:
			result.setCode(0);
			result.setErrorCode("E0001");
			result.setMsg("请求token不能为空！");
			break;
		case 2:
			result.setCode(0);
			result.setErrorCode("E0002");
			result.setMsg("请求token不存在！");
			break;
		case 3:
			result.setCode(0);
			result.setErrorCode("E0003");
			result.setMsg("请求token已过期，请重新登陆！");
			break;
		case 4:
			result.setCode(0);
			result.setErrorCode("E0004");
			result.setMsg("您无权进行该操作！");
			break;
		default:
			result.setCode(0);
			result.setErrorCode("E0000");
			result.setMsg("请求出错！");
			break;
		}
		return result;
	}
	
	//验证码绑定用户名与手机号
	@RequestMapping(value = "/sendCode" , produces = "application/json;charset=utf-8")
	public @ResponseBody Result sendCode(String mobile,String username) {
		Result result = new Result();
		try{
			if (StringUtils.isBlank(mobile) || !PhoneFormatCheckUtils.isPhoneLegal(mobile)) {
				result.setCode(0);
				result.setErrorCode("E0101");
				result.setMsg("手机号码输入有误！");
				return result;
			}
			// username= new String(username.getBytes("ISO8859-1"),"utf-8");
			//根据用户名查询
			Member  name=service.selectFullNmae(username);
			if(name!=null){
				result.setCode(0);
				result.setErrorCode("E0201");
				result.setMsg("用户名已注册");
				return result;
			}
			//查询次数
			try {
				int tpMem = service.selectMemberByMobile1(mobile);
				if(tpMem>=5){
					result.setCode(0);
					result.setErrorCode("E0206");
					result.setMsg("该手机号只能绑定5个用户！");   
					return result;
				} 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			String code = RandomStringUtils.randomNumeric(6);
			log.info("mobile:" + mobile + "=>code:" + code);
			MobileCode mobileCode = new MobileCode();
			mobileCode.setMobile(mobile);
			mobileCode.setUserName(username);
			mobileCode.setStatus((byte) 1);
			mobileCode.setCode(code);
			int n = service.addCode(mobileCode);
			if (1 == n){
				Document doc = Jsoup.connect("http://v.juhe.cn/sms/send").timeout(1000 * 20)//
						.ignoreContentType(true)//
						.data("mobile", mobile)//
						.data("tpl_id", "57305")//49986
						.data("tpl_value", "#code#=" + code)//
						.data("key", "9f01e583269b4a1dd95aa393bc493105")//bd7d1ae1a54e70c6106ccd16333315a0
						.data("dtype", "json")//
						.get();
				JSONObject json = JSONObject.fromObject(doc.body().text());
				if (null != json && 0 == json.getInt("error_code")) {
					result.setCode(1);
					result.setMsg("短信验证码发送成功。");
				} else {
					result.setCode(0);
					result.setErrorCode("E0102");
					result.setMsg("短信验证码发送失败。");
				}
			} else {
				result.setCode(0);
				result.setErrorCode("E0102");
				result.setMsg("短信验证码发送失败。");
			}
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E0103");
			result.setMsg("验证码发送异常！");
		}
		return result;
	}
	
	/**
	 * 注册
	 * */
	@RequestMapping(value = "/signin",produces = "application/json;charset=utf-8" )
	public @ResponseBody MemberResult signin(Member member) {
		MemberResult result = new MemberResult();
		try { 
			if (null == member) {
				result.setCode(0);
				result.setErrorCode("E0201");
				result.setMsg("用户信息不能为空！");
				return result;
			}
		//	String Name= new String(member.getUserName().getBytes("ISO8859-1"),"utf-8");
		//	member.setUserName(Name);
		//	System.out.println(Name);
			String  fullName= member.getUserName();
			//根据用户名查询
			Member  name=service.selectFullNmae(fullName);
			if(name!=null){
				result.setCode(0);
				result.setErrorCode("E0201");
				result.setMsg("用户名已注册");
				return result;
			}
			
			String mobile = member.getMobile();
			if (StringUtils.isBlank(mobile) || !PhoneFormatCheckUtils.isPhoneLegal(mobile)){
				result.setCode(0);
				result.setErrorCode("E0202");
				result.setMsg("手机号码输入有误！");
				return result;
			}
			//短信验证码
			MobileCode mobileCode = service.selectByMobile(mobile,member.getUserName());
			String inCode = member.getCode();
			log.info("mobile:" + mobile + "=>code:" + inCode);
			if (null == mobileCode || StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode.getCode())) {
				result.setCode(0);
				result.setErrorCode("E0203");
				result.setMsg("短信验证码输入错误！");
				return result;
			}
			if (0 == mobileCode.getStatus()) {
				result.setCode(0);
				result.setErrorCode("E0210");
				result.setMsg("短信验证码已失效，请重新发送！");
				return result;
			}
			String adCode = member.getAdCode();
			if (StringUtils.isBlank(adCode)) {
				result.setCode(0);
				result.setErrorCode("E0204");
				result.setMsg("推荐码不能为空！");
				return result;
			}
			
			if (!"ZLZC100".equals(adCode)) {
				MemberExample example = new MemberExample();
				Long id = null;
				try {
					id = Long.valueOf(adCode.split("ZLZC")[1]);
				} catch (Exception e) {
					result.setCode(0);
					result.setErrorCode("E0205");
					result.setMsg("推荐码不存在！");
					return result;
				}
				example.createCriteria().andIdEqualTo(id);
				int count = service.countByExample(example);
				if (0 == count) {
					result.setCode(0);
					result.setErrorCode("E0205");
					result.setMsg("推荐码不存在！");
					return result;
				}
			}
			
			/*String connect = member.getConnection();
			if (StringUtils.isBlank(connect)) {
				result.setCode(0);
				result.setErrorCode("E0204");
				result.setMsg("节点人推荐码不能为空！");
				return result;
			}*/
			/*if (!"ZLZC100".equals(connect)) {
				MemberExample example = new MemberExample();
				Long id = null;
				try {
					id = Long.valueOf(connect.split("ZLZC")[1]);
				} catch (Exception e) {
					result.setCode(0);
					result.setErrorCode("E0205");
					result.setMsg("节点人推荐码不存在！");
					return result;
				}
				example.createCriteria().andIdEqualTo(id);
				int count = service.countByExample(example);
				if (0 == count) {
					result.setCode(0);
					result.setErrorCode("E0205");
					result.setMsg("节点人推荐码不存在！");
					return result;
				}
			}*/
			String password = member.getPassword();
			String passwordAgain = member.getPasswordAgain();
			if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)) {
				result.setCode(0);
				result.setErrorCode("E0206");
				result.setMsg("密码不能为空！");
				return result;
			}
			if(!password.equals(passwordAgain)){
				result.setCode(0);
				result.setErrorCode("E0206");
				result.setMsg("两次输入的密码不一致！");
				return result;
			}
			String passwordTwo = member.getPasswordTwo();
			String passwordTwoAgain = member.getPasswordTwoAgain();
			if (StringUtils.isBlank(passwordTwo)||StringUtils.isBlank(passwordTwoAgain)) {
				result.setCode(0);
				result.setErrorCode("E0206");
				result.setMsg("二级密码不能为空！");
				return result;
			}
			if(!passwordTwo.equals(passwordTwoAgain)){
				result.setCode(0);
				result.setErrorCode("E0206");
				result.setMsg("两次输入的二级密码不一致！");
				return result;
			}
			Member mem = new Member();
			mem.setMobile(member.getMobile());
			int tpMem = service.selectMemberByMobile1(member.getMobile());
			System.out.println(tpMem);
			if(tpMem>=5){
				result.setCode(0);
				result.setErrorCode("E0206");
				result.setMsg("该手机号只能绑定5个用户！");   
				return result;
			} 
			/*int aNum = service.selectNumByCode(connect, "A");
			}
			int bNum = service.selectNumByCode(connect, "B");
			if(aNum>0){
				if(bNum>0){
					Long id = Long.valueOf(connect.split("ZLZC")[1]);
					Member m = service.selectById(id);
					member.setZone(m.getZone());
				}else{
					member.setZone("B");
				}
			}else{
				member.setZone("A");
			}*/
			//智能区块会自动形成两个链接区,小区持币总量按照相应算力匹配所得币量
			int aCount = service.selectCountByCode(adCode, "A");
			int bCount = service.selectCountByCode(adCode, "B");
			//A|B两个区块人数
			int aNum = service.selectNumByCode(adCode, "A");
			int bNum = service.selectNumByCode(adCode, "B");
			
			if(aNum>bNum){
				member.setZone("B");
			}else if(aNum<bNum){
				member.setZone("A");
			}else{
				if(aCount>bCount){
					member.setZone("B");
				}else{
					member.setZone("A");
				}
			}
			/*
			if(aCount>bCount){
				member.setZone("B");
			}else{
				if(aNum>bNum){
					member.setZone("B");
				}
				member.setZone("A");
			}*/
			
			member.setUuid(UUIDUtil.get32UUID());
			member.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
			member.setPasswordTwo(AesUtil.encrypt(passwordTwo, SysConstants.AES_KEY));
//			/MobileCode mobileCode = new MobileCode();
			mobileCode.setStatus((byte) 0);
			int n = service.add(member, mobileCode);
			if (1 == n) {
				result.setCode(1);
				result.setMember(member);
				result.setMsg("注册成功！");
			} else {
				result.setCode(0);
				result.setErrorCode("E0207");
				result.setMsg("注册失败！");
			}
		} catch (DuplicateKeyException e) {
			result.setCode(0);
			result.setErrorCode("E0208");
			result.setMsg("手机号码已被注册！");
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E0209");
			result.setMsg("该验证码已失效!");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
	public @ResponseBody MemberResult login(Member member) {
		MemberResult result = new MemberResult();
		try {	
			if (null == member) {
				result.setCode(0);
				result.setErrorCode("E0301");
				result.setMsg("用户信息不能为空！");
				return result;
			}
//			String uName = null;
			
//			uName = new String(member.getUserName().getBytes("ISO8859-1"),"utf-8");
//			member.setUserName(uName);
			String name = member.getUserName(); 
			if (StringUtils.isBlank(name)) {
				result.setCode(0);
				result.setErrorCode("E0302");
				result.setMsg("用户名不能为空！");
				return result;
			}
			service.login(member, result);
		} catch (Exception e) {// 异常处理

			result.setCode(0);
			result.setErrorCode("E0306");
			result.setMsg("登录异常！");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/verified" ,produces = "application/json;charset=utf-8")
	public @ResponseBody MemberResult verified(Member member) {
		MemberResult result = new MemberResult();
		try {
			if (null == member) {
				result.setCode(0);
				result.setErrorCode("E0401");
				result.setMsg("用户信息不能为空！");
				return result;
			}
			Long id = member.getId();
			if (null == id) {
				result.setCode(0);
				result.setErrorCode("E0402");
				result.setMsg("用户主键不能为空！");
				return result;
			}
			Member user = service.selectById(id);
			if (1 == user.getVerifiedStatus()) {
				result.setCode(0);
				result.setErrorCode("E0410");
				result.setMsg("用户已通过实名认证，不可重复认证！");
				return result;
			}
			String mobile = user.getMobile();
			//MobileCode mobileCode = service.selectByMobile(mobile);
			/*String inCode = member.getCode();
			if (StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode.getCode())) {
				result.setCode(0);
				result.setErrorCode("E0403");
				result.setMsg("短信验证码输入错误！");
				return result;
			}
			if (0 == mobileCode.getStatus()) {
				result.setCode(0);
				result.setErrorCode("E0410");
				result.setMsg("短信验证码已失效，请重新发送！");
				return result;
			}*/
			MobileCode mobileCode = new MobileCode();
			mobileCode.setStatus((byte) 0);
			service.verified(member, result, mobileCode);
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E0409");
			result.setMsg("实名认证异常！");
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/updatePassword",produces = "application/json;charset=utf-8")
	public @ResponseBody MemberResult updatePassword(Member member) {
		MemberResult result = new MemberResult();
		try {
			if (null == member || StringUtils.isBlank(member.getMobile())) {
				result.setCode(0);
				result.setErrorCode("E0601");
				result.setMsg("手机号码不能为空！");
				return result;
			}
			String mobile = member.getMobile();
			if (!PhoneFormatCheckUtils.isPhoneLegal(mobile)) {
				result.setCode(0);
				result.setErrorCode("E0602");
				result.setMsg("手机号码输入有误！");
				return result;
			}
			Member m = service.selectMemberByMobile(mobile);
			if (null == m) {
				result.setCode(0);
				result.setErrorCode("E0608");
				result.setMsg("电话号码未注册！");
				return result;
			}
			/*MobileCode mobileCode = service.selectByMobile(mobile);
			String inCode = member.getCode();
			log.info("mobile:" + mobile + "=>code:" + inCode);
			if (null == mobileCode || StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode.getCode())) {
				result.setCode(0);
				result.setErrorCode("E0603");
				result.setMsg("短信验证码输入错误！");
				return result;
			}
			if (0 == mobileCode.getStatus()) {
				result.setCode(0);
				result.setErrorCode("E0604");
				result.setMsg("短信验证码已失效，请重新发送！");
				return result;
			}*/
			MobileCode mobileCode = new MobileCode();
			mobileCode.setStatus((byte) 0);
			Integer type = member.getType();
			if(type==null){
				result.setCode(0);
				result.setErrorCode("E0605");
				result.setMsg("请选择需要修改的密码！");
				return result;
			}
			if(type==1){
				String password = member.getPassword();
				String passwordAgain = member.getPasswordAgain();
				if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)) {
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("密码不能为空！");
					return result;
				}
				if(!password.equals(passwordAgain)){
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("两次输入的密码不一致！");
					return result;
				}
				m.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
			}else if(type==2){
				String passwordTwo = member.getPasswordTwo();
				String passwordTwoAgain = member.getPasswordTwoAgain();
				if (StringUtils.isBlank(passwordTwo)||StringUtils.isBlank(passwordTwoAgain)) {
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("密码不能为空！");
					return result;
				}
				if(!passwordTwo.equals(passwordTwoAgain)){
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("两次输入的密码不一致！");
					return result;
				}
				m.setPasswordTwo(AesUtil.encrypt(passwordTwo, SysConstants.AES_KEY));
			}else{
				String password = member.getPassword();
				String passwordAgain = member.getPasswordAgain();
				if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)) {
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("密码不能为空！");
					return result;
				}
				if(!password.equals(passwordAgain)){
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("两次输入的密码不一致！");
					return result;
				}
				m.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
				
				String passwordTwo = member.getPasswordTwo();
				String passwordTwoAgain = member.getPasswordTwoAgain();
				if (StringUtils.isBlank(passwordTwo)||StringUtils.isBlank(passwordTwoAgain)) {
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("密码不能为空！");
					return result;
				}
				if(!passwordTwo.equals(passwordTwoAgain)){
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("两次输入的密码不一致！");
					return result;
				}
				m.setPasswordTwo(AesUtil.encrypt(passwordTwo, SysConstants.AES_KEY));
			}
			int n = service.update(m, mobileCode);
			if (1 == n) {
				result.setCode(1);
				result.setMember(m);
				result.setMsg("密码修改成功！");
			} else {
				result.setCode(0);
				result.setErrorCode("E0606");
				result.setMsg("密码修改失败！");
			}
		} catch (Exception e) {// 异常处理
			result.setCode(0);
			result.setErrorCode("E0607");
			result.setMsg("密码修改异常！");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/upload")
	public @ResponseBody UploadResult upload(String src) {
		UploadResult result = new UploadResult();
		try {
			if (StringUtils.isBlank(src)) {
				result.setCode(0);
				result.setErrorCode("E2001");
				result.setMsg("图片资源不能为空！");
				return result;
			}
			if (StringUtils.isNotBlank(src) && src.indexOf(";base64,") >= 0) {
				String[] datas = src.split(";base64,");
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] b = decoder.decodeBuffer(datas[1]);
				String path = FileUtils.getFolder(savePath) + UUIDUtil.get32UUID() + ".jpg";
				OutputStream out = new FileOutputStream(path);
				out.write(b);
				out.flush();
				out.close();
				result.setCode(1);
				result.setMsg("上传图片成功！");
				result.setUrl(FileUtils.getReadPath(path, readPath, separator));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2002");
			result.setMsg("上传图片异常！");
		}
		return result;
	}

	@RequestMapping(value = "/uploadImg")
	public @ResponseBody UploadResult uploadImg(@RequestParam(value = "file") File file) {
		UploadResult result = new UploadResult();
		try {
			if (null == file) {
				result.setCode(0);
				result.setErrorCode("E2001");
				result.setMsg("图片资源不能为空！");
				return result;
			}
			String path = FileUtils.getFolder(savePath) + UUIDUtil.get32UUID() + ".jpg";
			OutputStream out = new FileOutputStream(path);
			InputStream inputStream = new FileInputStream(file);
			int len;
			byte[] bs = new byte[1024];
			while ((len = inputStream.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			out.flush();
			out.close();
			inputStream.close();
			result.setCode(1);
			result.setMsg("上传图片成功！");
			result.setUrl(FileUtils.getReadPath(path, readPath, separator));
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2002");
			result.setMsg("上传图片异常！");
		}
		return result;
	}

	@RequestMapping(value = "/uploadTest")
	public @ResponseBody UploadResult uploadTest(HttpServletResponse response, @RequestParam(value = "fileData", required = false) MultipartFile[] files) {
		UploadResult result = new UploadResult();
		try {
			if (null == files) {
				result.setCode(0);
				result.setErrorCode("E2001");
				result.setMsg("图片资源不能为空！");
				return result;
			}
			response.setHeader("Access-Control-Allow-Origin", "*");// 允许跨域访问
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
			for (MultipartFile file : files) {
				String path = FileUtils.getFolder(savePath) + UUIDUtil.get32UUID()+ ".jpg";
				OutputStream out = new FileOutputStream(path);
				InputStream inputStream = file.getInputStream();
				int len;
				byte[] bs = new byte[1024];
				while ((len = inputStream.read(bs)) != -1) {
					out.write(bs, 0, len);
				}
				out.flush();
				out.close();
				inputStream.close();
				result.setCode(1);
				result.setMsg("上传图片成功！");
				result.setUrl(FileUtils.getReadPath(path, readPath, separator));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2002");
			result.setMsg("上传图片异常！");
		}
		return result;
	}

	@RequestMapping(value = "/myInvite")
	public @ResponseBody InviteResult myInvite(Long memberId) {
		InviteResult result = new InviteResult();
		try {
			if (null == memberId) {
				result.setCode(0);
				result.setErrorCode("E2201");
				result.setMsg("用户主键不能为空！");
				return result;
			}
			List<Member> members = service.selectInviteByAdCode("ZLZC" + memberId);
			result.setCode(1);
			result.setMembers(members);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2202");
			result.setMsg("查询异常！");
		}
		return result;
	}

	@RequestMapping(value = "/updateHead")
	public @ResponseBody MemberResult updateHead(Member member) {
		MemberResult result = new MemberResult();
		try {
			if (null == member || null == member.getId()) {
				result.setCode(0);
				result.setErrorCode("E2301");
				result.setMsg("用户主键不能为空！");
				return result;
			}
			if (StringUtils.isBlank(member.getHead())) {
				result.setCode(0);
				result.setErrorCode("E2302");
				result.setMsg("用户头像地址不能为空！");
				return result;
			}
			Member m = new Member();
			m.setId(member.getId());
			m.setHead(member.getHead());
			int n = service.update(m);
			if (1 == n) {
				result.setCode(1);
				result.setMsg("更新用户头像成功。");
				result.setMember(member);
			} else {
				result.setCode(1);
				result.setErrorCode("E2303");
				result.setMsg("更新用户头像失败！");
				result.setMember(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2304");
			result.setMsg("更新用户头像异常！");
		}
		return result;
	}
	
	@RequestMapping(value = "/cInfo")
	public @ResponseBody ResponseResult cInfo(String id) {
		ResponseResult result = new ResponseResult();
		try {
			if (StringUtils.isBlank(id)) {
				result.setCode("0");
				result.setMsg("用户主键不能为空!");
				return result;
			}
			Long idl=Long.parseLong(id);
			Card member = cardservice.selectCardId(idl);
			if (null == member) {
				result.setCode("0");
				result.setMsg("查询失败！");
				return result;
			}
			result.setObject(result);
			result.setCode("1");
			result.setMsg("查询成功!");
		}catch (Exception e) {
			e.printStackTrace();
			result.setCode("0");
			result.setMsg("查询异常！");
			return result;
		}
		return result;
	}
	@RequestMapping(value = "/memberInfo")
	public @ResponseBody Result income(String id) {
		MemberResult result = new MemberResult();
		try {
			if (StringUtils.isBlank(id)) {
				result.setCode(0);
				result.setErrorCode("E2601");
				result.setMsg("用户主键不能为空!");
				return result;
			}
			Member member = service.selectByZone(id);
			if (null == member) {
				result.setCode(0);
				result.setErrorCode("E2602");
				result.setMsg("查询失败！");
				return result;
			}
			//查询A区所有直属下级
			List<Member> aList = service.selectNumByZone(id,"A");
			//Double aCount = member.getaCount();//A区用户持币数（购买币个数）
			//Double bCount = member.getbCount();//B区用户持币数（购买币个数）
			double aCount = 0;
			double bCount = 0;
			Integer aNum = 0;
			Integer bNum = 0;
			//计算A区业绩
			if(aList!=null&&aList.size()>0){
				for(int i = 0;i<aList.size();i++){
					List<Member> tList = service.clearsSons(aList.get(i).getId()+"");
					if(tList!=null&&tList.size()>0){
						aNum+=tList.size();
						for(int j = 0;j<tList.size();j++){
							aCount = aCount+tList.get(j).getIntegral();
						}
					}
				}
			}
			member.setaCount(aCount);
			member.setaNum(aNum);
			//查询B区所有直属下级
			List<Member> bList = service.selectNumByZone(id,"B");
			//计算B区业绩
			if(bList!=null&&bList.size()>0){
				for(int i = 0;i<bList.size();i++){
					List<Member> tList = service.clearsSons(bList.get(i).getId()+"");
					if(tList!=null&&tList.size()>0){
						bNum+=tList.size();
						for(int j = 0;j<tList.size();j++){
							bCount = bCount+tList.get(j).getIntegral();
						}
					}
				}
			}
			member.setbCount(bCount);
			member.setbNum(bNum);
			//Member member = service.selectById(id);
			//Member member = service.selectByZone(id);
			Setting setting = settingService.querySettingInfo();
			//用户静态持币算力
			double stateCount = member.getIntegral();//用户持币数（购买币个数）
			Double statRate = (double)0;
			Double dynRate = (double)0;
			if(stateCount>=0&&stateCount<300){
				statRate = setting.getRate1();
			}else if(stateCount>=300&&stateCount<1000){
				statRate = setting.getRate2();
			}else if(stateCount>=1000&&stateCount<5000){
				statRate = setting.getRate3();
			}else{
				statRate = setting.getRate4();
			}
			//动态持币数
			//Integer dynCount = member.getDyTotal();//用户持币数（购买币个数）
			member.setStaIncome(stateCount*statRate);
			//查询A区人数、B区人数、a区收益、b区收益
			/*if(aCount>=0&&aCount<500){
				dynRate = (double)0;
			}else if(aCount>=500&&aCount<1000){
				dynRate = setting.getRate5();
			}else if(aCount>=1000&&aCount<5000){
				dynRate = setting.getRate6();
			}else{
				dynRate = setting.getRate7();
			}*/
			//member.setaCount(aCount*dynRate);
			/*Double aCount = member.getaCount();//A区用户持币数（购买币个数）
			member.setaCount(aCount);
			Double bCount = member.getbCount();//B区用户持币数（购买币个数）
*/			if(aCount>bCount){
				if(bCount>=0&&bCount<500){
					dynRate = (double)0;
				}else if(bCount>=500&&bCount<1000){
					dynRate = setting.getRate5();
				}else if(bCount>=1000&&bCount<5000){
					dynRate = setting.getRate6();
				}else{
					dynRate = setting.getRate7();
				}
				double rCount = bCount*dynRate;
				if(rCount>5000){
					member.setDyIncome((double)5000);
				}else{
					if(rCount>member.getIntegral()*0.5){
						member.setDyIncome(member.getIntegral()*0.5);
					}else{
						member.setDyIncome(rCount);
					}
				}
			}else{
				if(aCount>=0&&aCount<500){
					dynRate = (double)0;
				}else if(aCount>=500&&aCount<1000){
					dynRate = setting.getRate5();
				}else if(aCount>=1000&&aCount<5000){
					dynRate = setting.getRate6();
				}else{
					dynRate = setting.getRate7();
				}
				double rCount = aCount*dynRate;
				if(rCount>5000){
					member.setDyIncome((double)5000);
				}else{
					if(rCount>member.getIntegral()*0.5){
						member.setDyIncome(member.getIntegral()*0.5);
					}else{
						member.setDyIncome(rCount);
					}
				}
			}
			
			/*if(bCount>=0&&bCount<500){
				dynRate = (double)0;
			}else if(bCount>=500&&bCount<1000){
				dynRate = setting.getRate5();
			}else if(bCount>=1000&&bCount<5000){
				dynRate = setting.getRate6();
			}else{
				dynRate = setting.getRate7();
			}*/
			//member.setbCount(bCount);
			
			result.setCode(1);
			result.setMsg("查询成功！");
			result.setMember(member);
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2603");
			result.setMsg("查询异常！");
		}
		return result;
	}
	/*
	 * 查里历史记录
	 */
	@RequestMapping(value = "/hisoty")
	public @ResponseBody ResponseResult  history(Long id) {
		ResponseResult result=new ResponseResult();
		List<IncomeRecord> income=service.findHisotry(id);
		for(IncomeRecord re:income){
			DecimalFormat  df  =new DecimalFormat("#.0000"); 
			re.setIncome(Double.parseDouble(df.format(re.getIncome())));
			re.setIntegral(Double.parseDouble(df.format(re.getIntegral())));
		}
		result.setList(service.findHisotry(id));
		result.setCode("1");
		result.setMsg("查询成功");
		return result;
	}
	/**
	 * 查询指定用户的业绩
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/score")
	public @ResponseBody Result score(String id) {
		MemberResult result = new MemberResult();
		try {
			if (StringUtils.isBlank(id)) {
				result.setCode(0);
				result.setErrorCode("E2601");
				result.setMsg("用户主键不能为空!");
				return result;
			}
			Member member = new Member();
			//查询A区所有直属下级
			List<Member> aList = service.selectNumByZone(id,"A");
			double aCount = 0;
			double bCount = 0;
			Integer aNum = 0;
			Integer bNum = 0;
			//计算A区业绩
			if(aList!=null&&aList.size()>0){
				for(int i = 0;i<aList.size();i++){
					List<Member> tList = service.clearsSons(aList.get(i).getId()+"");
					if(tList!=null&&tList.size()>0){
						aNum+=tList.size();
						for(int j = 0;j<tList.size();j++){
							aCount = aCount+tList.get(j).getIntegral();
						}
					}
				}
			}
			member.setaCount(aCount);
			member.setaNum(aNum);
			//查询B区所有直属下级
			List<Member> bList = service.selectNumByZone(id,"B");
			//计算B区业绩
			if(bList!=null&&bList.size()>0){
				for(int i = 0;i<bList.size();i++){
					List<Member> tList = service.clearsSons(bList.get(i).getId()+"");
					if(tList!=null&&tList.size()>0){
						bNum+=tList.size();
						for(int j = 0;j<tList.size();j++){
							bCount = bCount+tList.get(j).getIntegral();
						}
					}
				}
			}
			member.setbCount(bCount);
			member.setbNum(bNum);
			//Member member = service.selectById(id);
			//Member member = service.selectByZone(id);
			Setting setting = settingService.querySettingInfo();
			//用户静态持币算力
			double stateCount = member.getIntegral();//用户持币数（购买币个数）
			Double statRate = (double)0;
			Double dynRate = (double)0;
			if(stateCount>=0&&stateCount<300){
				statRate = setting.getRate1();
			}else if(stateCount>=300&&stateCount<1000){
				statRate = setting.getRate2();
			}else if(stateCount>=1000&&stateCount<5000){
				statRate = setting.getRate3();
			}else{
				statRate = setting.getRate4();
			}
			//动态持币数
			//Integer dynCount = member.getDyTotal();//用户持币数（购买币个数）
			member.setStaIncome(stateCount*statRate);
			//查询A区人数、B区人数、a区收益、b区收益
			/*if(aCount>=0&&aCount<500){
				dynRate = (double)0;
			}else if(aCount>=500&&aCount<1000){
				dynRate = setting.getRate5();
			}else if(aCount>=1000&&aCount<5000){
				dynRate = setting.getRate6();
			}else{
				dynRate = setting.getRate7();
			}*/
			//member.setaCount(aCount*dynRate);
			/*Double aCount = member.getaCount();//A区用户持币数（购买币个数）
			member.setaCount(aCount);
			Double bCount = member.getbCount();//B区用户持币数（购买币个数）
*/			if(aCount>bCount){
				if(bCount>=0&&bCount<500){
					dynRate = (double)0;
				}else if(bCount>=500&&bCount<1000){
					dynRate = setting.getRate5();
				}else if(bCount>=1000&&bCount<5000){
					dynRate = setting.getRate6();
				}else{
					dynRate = setting.getRate7();
				}
				double rCount = bCount*dynRate;
				if(rCount>5000){
					member.setDyIncome((double)5000);
				}else{
					if(rCount>member.getIntegral()*0.5){
						member.setDyIncome(member.getIntegral()*0.5);
					}else{
						member.setDyIncome(rCount);
					}
				}
			}else{
				if(aCount>=0&&aCount<500){
					dynRate = (double)0;
				}else if(aCount>=500&&aCount<1000){
					dynRate = setting.getRate5();
				}else if(aCount>=1000&&aCount<5000){
					dynRate = setting.getRate6();
				}else{
					dynRate = setting.getRate7();
				}
				double rCount = aCount*dynRate;
				if(rCount>5000){
					member.setDyIncome((double)5000);
				}else{
					if(rCount>member.getIntegral()*0.5){
						member.setDyIncome(member.getIntegral()*0.5);
					}else{
						member.setDyIncome(rCount);
					}
				}
			}
			
			/*if(bCount>=0&&bCount<500){
				dynRate = (double)0;
			}else if(bCount>=500&&bCount<1000){
				dynRate = setting.getRate5();
			}else if(bCount>=1000&&bCount<5000){
				dynRate = setting.getRate6();
			}else{
				dynRate = setting.getRate7();
			}*/
			//member.setbCount(bCount);
			
			result.setCode(1);
			result.setMsg("查询成功！");
			result.setMember(member);
		} catch (Exception e) {// 异常处理
			e.printStackTrace();
			result.setCode(0);
			result.setErrorCode("E2603");
			result.setMsg("查询异常！");
		}
		return result;
	}
	
	@RequestMapping(value = "/recharge")
	public @ResponseBody Result recharge(String code,String coin,String sign) {
		Result result = new Result();
		MallRecord mall = new MallRecord();
		if(code==null||"".equals(code)){
			result.setCode(0);
			result.setErrorCode("E2204");
			result.setMsg("参数错误！");
			return result;
		}
		if(coin==null||"".equals(coin)){
			result.setCode(0);
			result.setErrorCode("E2204");
			result.setMsg("参数错误！");
			return result;
		}
		if(sign==null||"".equals(sign)){
			result.setCode(0);
			result.setErrorCode("E2204");
			result.setMsg("参数错误！");
			return result;
		}
		String url = "http://www.wuasset.com?code="+code+"&coin="+coin;
		//String url = "http://120.24.7.81:8080?code="+code+"&coin="+coin;
		String md5;
		try {
			md5 = MyMd5Utils.encodeByMd5(url);
			System.out.println("md5 = "+md5);
			if(!sign.equalsIgnoreCase(md5)){
				result.setCode(0);
				result.setErrorCode("E2203");
				result.setMsg("签名错误！");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Member member = service.selectMemberByUuid(code);
		
		if(member==null){
			result.setCode(0);
			result.setErrorCode("E2205");
			result.setMsg("用户不存在！");
			return result;
		}
		member.setIntegral(member.getIntegral()+Double.parseDouble(coin));
		mall.setMemberId(member.getId());
		mall.setMallIntegral(Double.parseDouble(coin));
		mall.setMallAddress(code);
		mall.setMallStatus("1");
		service.update(member);
		result.setCode(1);
		mallRecordService.inser(mall);
		result.setErrorCode("T2001");
		result.setMsg("操作成功！");
		return result;
	}
	
	/**
	 * 交易接口
	 * */
	@RequestMapping(value = "/marketPlace")
	public @ResponseBody ResponseResult marketPlace(Member member) {
		ResponseResult result = new ResponseResult();
		MallRecord mallRecord = new MallRecord();
		String requestID = UUIDUtil.get32UUID();
		Member mem=service.selectById(member.getId());
		try {
			if (!(mem.getPasswordTwo()).equals(AesUtil.encrypt(member.getPasswordTwo(), SysConstants.AES_KEY))) {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("二级密码错误！");
				return result;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mem.getVerifiedStatus()==0) {
			result.setCode("0");
			result.setMsg("还未实名认证请先认证");
			return result;
		}
		double s =member.getIntegral();
		if(mem.getIntegral()<s){
			result.setCode("0");
			result.setMsg("智联币数量不足！");
			return result;
		}
		String s1= new String();
		s1=s1+requestID+member.getUuid()+member.getIntegral();
		String sign = null;
		try {
			sign=MyMd5Utils.encodeByMd5(s1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> mall = new ArrayList<String>();
		mall.add(sign);
		mall.add(requestID);
		result.setCode("1");
		result.setList(mall);
		return result;
	}
	
	/**
	 * 商城接口
	 * */
	@RequestMapping(value = "/Recharge",produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult Recharge(String passTwo,String silver,String id,String address) {
		ResponseResult result = new ResponseResult();
		MallRecord mallRecord = new MallRecord();
		String requestID = UUIDUtil.get32UUID();
		Member mem=service.selectById(id);
		try {
			if (!(mem.getPasswordTwo()).equals(AesUtil.encrypt(passTwo, SysConstants.AES_KEY))) {
				result.setCode(SysConstants.STATUS_FALSE);
				result.setMsg("二级密码错误！");
				return result;
			}
		
		if(mem.getVerifiedStatus()==0) {
			result.setCode("0");
			result.setMsg("还未实名认证请先认证");
			return result;
		}
		double s =Double.parseDouble(silver);
		if(mem.getIntegral()<s){
			result.setCode("0");
			result.setMsg("智联币数量不足！");
			return result;
		}
		String s1= new String();
		s1=s1+requestID+address+silver;
		
		String sign = null;
		try {
			sign=MyMd5Utils.encodeByMd5(s1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mem.setIntegral(mem.getIntegral()-s);
//		int flg=service.update(mem);
		
		List<String> mall = new ArrayList<String>();
			mall.add(sign);
			mall.add(requestID);
			result.setCode("1");
			result.setList(mall);
		}catch (Exception e) {
			e.printStackTrace();
		}
			return result;
	}
	
	/**
	 * 交易市场
	 * */
	@RequestMapping(value = "/transactionRecord")
	public @ResponseBody ResponseResult transactionRecord(Member member){
		ResponseResult result = new ResponseResult();
		member.setId(7329L);
		member.setIntegral(30d);
    	Member mem=service.selectById(member.getId());
		mem.setIntegral(mem.getIntegral()-member.getIntegral());
		int flg = service.update(mem);
		MarketRecords marketRecord = new MarketRecords();
		marketRecord.setMemberId(mem.getId());
		marketRecord.setMarketIntegral(member.getIntegral());
		marketRecord.setMarketAddress(member.getUuid());
		marketRecord.setUserName(mem.getFullName());
		if(flg==1) { 
			result.setCode("1");
			result.setMsg("转出成功");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value = "/transaction",produces = "application/json;charset=utf-8")
	public @ResponseBody ResponseResult transaction(String silver,String id,String address,String msg){
		ResponseResult result = new ResponseResult();
		Member mem=service.selectById(id);
		double s =Double.parseDouble(silver);
		mem.setIntegral(mem.getIntegral()-s);
		int flg=service.update(mem);
		MallRecord mallrecord = new MallRecord();
		mallrecord.setMallAddress(address);
		mallrecord.setMemberId(mem.getId());
		mallrecord.setMallIntegral(s);
		mallrecord.setMallStatus("0");
		if(flg==1) {
			mallRecordService.inser(mallrecord);
			result.setCode("1");
			result.setMsg("转出成功");
			return result;
		}
		return result;
	}
	
	/**
	 * 商城转出成功
	 * */
	@RequestMapping(value = "/transactionrecording")
	public @ResponseBody ResponseResult transactionrecording(String id,String msg,PageInfo pageInfo) {
		ResponseResult mallRecordResult = new ResponseResult();
		MallRecord mallrecord = new MallRecord();
		long sid = Long.parseLong(id);
		mallrecord.setMemberId(sid);
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		int totalRecord =mallRecordService.findTotal(mallrecord);
		pageInfo.setTotalRecord(totalRecord);
		mallrecord.setMallStatus(msg);
		List<MallRecord> mall=null;
		 
		 try {
			 mall=mallRecordService.selectMallRecord(mallrecord,pageInfo); 
		 }catch (Exception e) {
			e.printStackTrace();
		}
		if(mall!=null){
		 mallRecordResult.setList(mall);
		 mallRecordResult.setCode("1");
		 mallRecordResult.setPageInfo(pageInfo);
		}else{
			 mallRecordResult.setCode("0");
		}
		
		return mallRecordResult;
	}
	
	@RequestMapping(value = "/rate")
	public @ResponseBody AjaxResult rate(double coin) {
		AjaxResult result = new AjaxResult();
		if(coin==0){
			result.setCode(0);
			result.setErrorCode("E2204");
			result.setMsg("参数错误！");
			return result;
		}
		Setting setting = settingService.querySettingInfo();
		double price = setting.getIntegralPrice();
		double num = (double)coin/price;
		//service.update(member);
		result.setCode(1);
		result.setErrorCode("T2001");
		result.setMsg("操作成功！");
		result.setCount(num);
		return result;
	}
	
	/**
	 * 找回密码
	 * */
		@RequestMapping(value = "/retrievePassword",produces = "application/json;charset=utf-8")
		public @ResponseBody MemberResult retrievePassword(Member member){
				MemberResult result = new MemberResult();
//				Member member =new Member();
//				member.setUserName("13618427354");
//				member.setCode(code);
//				member.setMobile("13618427354");
//				member.setPassword("123456");
//				member.setPasswordAgain("123456");
//				member.setType(3);
				if(null == member || StringUtils.isBlank(member.getMobile())){
					result.setCode(0);
					result.setErrorCode("E0601");
					result.setMsg("手机号码不能为空！");
					return result;
				}
				String mobile = member.getMobile();
				if (!PhoneFormatCheckUtils.isPhoneLegal(mobile)) {
					result.setCode(0);
					result.setErrorCode("E0602");
					result.setMsg("手机号码输入有误！");
					return result;
				}
			//	String uname = Transcoding(member.getUserName());
			//	member.setUserName(uname);
			//	System.out.println(member.getUserName());
				Member m  = null;
				try { 
					m = service.selectMemberByMobile(member.getUserName());
				}catch (Exception e){
					e.printStackTrace();
				}
				MobileCode mobileCode1 = service.selectByMobile(mobile,member.getUserName());
				String inCode = member.getCode();
				log.info("mobile:" + mobile + "=>code:" + inCode);
				if (null == mobileCode1 || StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode1.getCode())) {
					result.setCode(0);
					result.setErrorCode("E0603");
					result.setMsg("短信验证码输入错误！");
					return result;
				}
				if(!m.getMobile().equals(member.getMobile())){
					result.setCode(0);
					result.setErrorCode("E0603");
					result.setMsg("用户名输入错误！");
					return result;
				}
				if (0 == mobileCode1.getStatus()){
					result.setCode(0);
					result.setErrorCode("E0604");
					result.setMsg("短信验证码已失效，请重新发送！");
					return result;
				}
				mobileCode1.setStatus((byte) 0);
				Integer type = member.getType();
				if(type==null){
					result.setCode(0);
					result.setErrorCode("E0605");
					result.setMsg("请选择需要修改的密码！");
					return result;
				}
				if(type==1){
					String password = member.getPassword();
					String passwordAgain = member.getPasswordAgain();
					if(password.length()<6) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能小于6位！");
						return result;
					}
					if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能为空！");
						return result;
					}
					if(!password.equals(passwordAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的密码不一致！");
						return result;
					}
					try {
						m.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
					} catch (Exception e){
						e.printStackTrace();
					}
				}else if(type==2){
					String passwordTwo = member.getPasswordTwo();
					String passwordTwoAgain = member.getPasswordTwoAgain();
					
					if(StringUtils.isBlank(passwordTwo)||StringUtils.isBlank(passwordTwoAgain)) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能为空！");
						return result;
					}
					if(passwordTwo.length()<6) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能小于6位！");
						return result;
					}
					if(!passwordTwo.equals(passwordTwoAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的密码不一致！");
						return result;
					}
					try{
						m.setPasswordTwo(AesUtil.encrypt(passwordTwo, SysConstants.AES_KEY));
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					String password = member.getPassword();
					String passwordAgain = member.getPasswordAgain();
					
					if(password.length()<6) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能小于6位！");
						return result;
					}
					if (StringUtils.isBlank(password)||StringUtils.isBlank(passwordAgain)) {
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("密码不能为空！");
						return result;
					}
					if(!password.equals(passwordAgain)){
						result.setCode(0);
						result.setErrorCode("E0605");
						result.setMsg("两次输入的密码不一致！");
						return result;
					}
					try {
						m.setPassword(AesUtil.encrypt(password, SysConstants.AES_KEY));
					//	int n = service.update(m, mobileCode);
//						if (1 == n) {
//							result.setCode(1);
//							result.setMember(member);
//							result.setMsg("密码重置成功！");
//							return result;
//						} else {
//							result.setCode(0);
//							result.setErrorCode("E0606");
//							result.setMsg("密码重置失败！");
//							return result;
//						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
				
				int n = service.update(m, mobileCode1);
				if (1 == n) {
					result.setCode(1);
					result.setMember(member);
					result.setMsg("密码重置成功！");
				} else {
					result.setCode(0);
					result.setErrorCode("E0606");
					result.setMsg("密码重置失败！");
				}
				
				
			return result;
			}
		
		
		//密码修改获取验证码
		@RequestMapping(value = "/obtainCode",produces = "application/json;charset=utf-8")
		public @ResponseBody Result obtainCode(String mobile,String username) {
			Result result = new Result();
			MobileCode mobilecode = new MobileCode();
			mobilecode.setMobile(mobile);
		//	String uname = new String(username.getBytes("ISO8859-1","utf-8"));
			log.info("查询User数据"+username);
			mobilecode.setUserName(username);
		//	mobilecode.setUserName(Transcoding(username));
			System.out.println(mobilecode.getUserName());
			mobilecode.setStatus((byte)0);
			int n1 = service.update1(mobilecode);
			try {
				if (StringUtils.isBlank(mobile) || !PhoneFormatCheckUtils.isPhoneLegal(mobile)) {
					result.setCode(0);
					result.setErrorCode("E0101");
					result.setMsg("手机号码输入有误！");
					return result;
				}
				//根据用户名查询
				Member  name=service.selectFullNmae(mobilecode.getUserName());
				if(name==null){
					result.setCode(0);
					result.setErrorCode("E0201");
					result.setMsg("用户名没有找到");
					return result;
				}
				String code = RandomStringUtils.randomNumeric(6);
				log.info("mobile:" + mobile + "=>code:" + code);
				MobileCode mobileCode = new MobileCode();
				mobileCode.setMobile(mobile);
				mobileCode.setUserName(mobilecode.getUserName());
				mobileCode.setStatus((byte) 1);
				mobileCode.setCode(code);
				int n = service.addCode(mobileCode);
				if (1 == n){
					Document doc = Jsoup.connect("http://v.juhe.cn/sms/send").timeout(1000 * 20)//
							.ignoreContentType(true)//
							.data("mobile", mobile)//
							.data("tpl_id", "57305")
							.data("tpl_value", "#code#=" + code)//
							.data("key", "9f01e583269b4a1dd95aa393bc493105")
							.data("dtype", "json")//
							.get();
					JSONObject json = JSONObject.fromObject(doc.body().text());
					if (null != json && 0 == json.getInt("error_code")) {
						result.setCode(1);
						result.setMsg("短信验证码发送成功。");
					} else {
						result.setCode(0);
						result.setErrorCode("E0102");
						result.setMsg("短信验证码发送失败。");
					}
				} else {
					result.setCode(0);
					result.setErrorCode("E0102");
					result.setMsg("短信验证码发送失败。");
				}
			} catch (Exception e) {// 异常处理
				result.setCode(0);
				result.setErrorCode("E0103");
				result.setMsg("验证码发送异常！");
			}
			return result;
		}
		
	/**
	 * 单独绑定手机号
	 * */
		@RequestMapping(value = "/bindingPhone",produces = "application/json;charset=utf-8")
		public @ResponseBody Result bindingPhone(Member member) {
			MemberResult result = new MemberResult();
			try {
			Member m = service.selectMemberByMobile(member.getUserName());
			if(null == member || StringUtils.isBlank(member.getMobile())){
				result.setCode(0);
				result.setErrorCode("E0601");
				result.setMsg("手机号码不能为空！");
				return result;
			}
			String mobile = member.getMobile();
			if (!PhoneFormatCheckUtils.isPhoneLegal(mobile)){
				result.setCode(0);
				result.setErrorCode("E0602");
				result.setMsg("手机号码输入有误！");
				return result;
			}
			MobileCode mobileCode1 = service.selectByMobile(mobile,member.getUserName());
			String inCode = member.getCode();
			log.info("mobile:" + mobile + "=>code:" + inCode);
			if (null == mobileCode1 || StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode1.getCode())) {
				result.setCode(0);
				result.setErrorCode("E0603");
				result.setMsg("短信验证码输入错误！");
				return result;
			}
			
			if (0 == mobileCode1.getStatus()){
				result.setCode(0);
				result.setErrorCode("E0604");
				result.setMsg("短信验证码已失效，请重新发送！");
				return result;
			}
			MobileCode mobileCode = new MobileCode();
			mobileCode.setStatus((byte) 0);
			int n = service.update(member, mobileCode);
			if(1 == n){
				result.setCode(1);
				result.setMember(member);
				result.setMsg("手机号绑定成功！");
			} else {
				result.setCode(0);
				result.setErrorCode("E0606");
				result.setMsg("手机号绑定失败！");
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 *	 修改身份证
		 **/
		@RequestMapping(value = "/idCard",produces = "application/json;charset=utf-8")
		public @ResponseBody Result idCard(Member member){ 
			MemberResult result = new MemberResult();
			Member m = service.selectMemberByMobile(member.getUserName());
		/*	if(null == member || StringUtils.isBlank(member.getMobile())){
				result.setCode(0);
				result.setErrorCode("E0601");
				result.setMsg("手机号码不能为空！");
				return result;
			}
			String mobile = m.getMobile();
			if (!PhoneFormatCheckUtils.isPhoneLegal(mobile)){
				result.setCode(0);
				result.setErrorCode("E0602");
				result.setMsg("手机号码输入有误！");
				return result;
			}
			MobileCode mobileCode1 = service.selectByMobile(mobile,member.getUserName());
			String inCode = member.getCode();
			log.info("mobile:" + mobile + "=>code:" + inCode);
			if (null == mobileCode1 || StringUtils.isBlank(inCode) || 6 != inCode.length() || !inCode.equals(mobileCode1.getCode())) {
				result.setCode(0);
				result.setErrorCode("E0603");
				result.setMsg("短信验证码输入错误！");
				return result;
			}
			
			if (0 == mobileCode1.getStatus()){
				result.setCode(0);
				result.setErrorCode("E0604");
				result.setMsg("短信验证码已失效，请重新发送！");
				return result;
			}*/
			MobileCode mobileCode = new MobileCode();
			mobileCode.setStatus((byte) 0);
			int n = service.update(member, mobileCode);
			if(1 == n){
				result.setCode(1);
				result.setMember(member);
				result.setMsg("修改成功！");
			} else {
				result.setCode(0);
				result.setErrorCode("E0606");
				result.setMsg("修改失败！");
			}
			
			return result;
		}
		
		/**
		 * 理财展示
		 * */
		@RequestMapping(value = "/moneyManagementShow",produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult  moneyManagementShow(MoneyManagement moneyManagement,PageInfo pageInfo){
			log.info("查询投资记录数据");
			ResponseResult result = new ResponseResult();
			try {
			if (pageInfo == null){
				pageInfo=new PageInfo();
			}
			int totalRecord =moneyManagementService.selectTurnintoTotal(moneyManagement);
			pageInfo.setTotalRecord(totalRecord);
			List<MoneyManagement> mall=null;
			 try {
				 mall=moneyManagementService.selectTurninto(moneyManagement,pageInfo); 
			 }catch (Exception e) {
				e.printStackTrace();
			}

				//InvestMentperiod: 0 30 天 1：60 天 2：；90天 
			 	
			if(mall!=null){
				result.setList(mall);
				result.setCode("1");
				result.setPageInfo(pageInfo);
			}else{
				result.setCode("0");
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * 理财提现
		 * */
		@RequestMapping(value = "/withdraw",produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult withdraw(Member member){
			ResponseResult result = new ResponseResult();
			InestmentIncome inestmentIncome = new InestmentIncome();
			Member mem=service.selectById(member.getId());
			try{
				//验证密码
				if (!(mem.getPasswordTwo()).equals(AesUtil.encrypt(member.getPasswordTwo(), SysConstants.AES_KEY))) {
					result.setCode("0");
					result.setMsg("二级密码错误！");
					return result;
				}
				
				
				
				//根据ID和类型查询投资记录
				List<InestmentIncome> Income =null;
				if(Income.size()>0){
					double totalMoney = Income.get(Income.size()-1).getTotalMoney();
				}
			}catch (Exception e){
				result.setCode("0");
			}
			return result;
		}
		
		/**
		 * 理财投资
		 * */
		@RequestMapping(value = "/moneyManagement",produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult  moneyManagement(Member member){
			MoneyManagement moneyManagement = new MoneyManagement();
			InestmentIncome inestmentIncome = new InestmentIncome();
			Member mem=service.selectById(member.getId());
			ResponseResult result = new ResponseResult();
			
			try{
				if (!(mem.getPasswordTwo()).equals(AesUtil.encrypt(member.getPasswordTwo(), SysConstants.AES_KEY))) {
					result.setCode("0");
					result.setMsg("二级密码错误！");
					return result;
				}
				if(mem.getVerifiedStatus()==0) {
					result.setCode("0");
					result.setMsg("还未实名认证请先认证");
					return result;
				}
				if(mem.getIntegral()<member.getMoney()){
					result.setCode("0");
					result.setMsg("智联币数量不足！");
					return result;
				}
				if("0".equals(member.getFinancialManagement())){ 
					mem.setIntegral(mem.getIntegral()-member.getMoney());
					int flg = service.update(mem);
					if(flg==1){ 
						//FinancialManagement: 0 活期理财 1:定期理财
						//InvestMentperiod: 0 30 天 1：60 天 2：；90天 
						moneyManagement.setInvestMentperiod(member.getInvestMentperiod());
						moneyManagement.setMemberId(mem.getId());
						moneyManagement.setFinancialManagement(member.getFinancialManagement());
						moneyManagement.setMoney(member.getMoney());
						moneyManagement.setType("1");
						inestmentIncome.setIncomeId(mem.getId());
						inestmentIncome.setIncomeType("2");
						inestmentIncome.setMemberId(mem.getId());
						inestmentIncome.setFinancialmanagement("0");
						inestmentIncome.setTotalMoney(member.getMoney());
						double toMoney =inestmentIncome.getTotalMoney();
						moneyManagementService.insert(moneyManagement);
						List<InestmentIncome> selectIncome = incomeService.selectimcomeId(inestmentIncome);
						if(selectIncome.size()>0){
							inestmentIncome.setTotalMoney(toMoney+selectIncome.get(selectIncome.size()-1).getTotalMoney());
							incomeService.insertIncome(inestmentIncome);
						}
//						if(selectIncome.size()>0){
//							System.out.println(selectIncome.get(0).getTotalMoney());
//							inestmentIncome.setTotalMoney(selectIncome.get(0).getTotalMoney()+moneyManagement.getMoney());
//							incomeService.updateIncome(inestmentIncome);
//						}else{
//						incomeService.insertIncome(inestmentIncome);
//						}
						result.setCode("1");
						result.setMsg("投资成功");
						return result;
					}
					
				}else if("1".equals(member.getFinancialManagement())){ 
					mem.setIntegral(mem.getIntegral()-member.getMoney());
					int flg = service.update(mem);
					if(flg==1){ 
							//FinancialManagement: 0 活期理财 1:定期理财
							//InvestMentperiod: 0 30 天 1：60 天 2：；90天 
							moneyManagement.setInvestMentperiod(member.getInvestMentperiod());
							moneyManagement.setMemberId(mem.getId());
							moneyManagement.setFinancialManagement(member.getFinancialManagement());
							moneyManagement.setMoney(member.getMoney());
							moneyManagement.setType("1");
							inestmentIncome.setIncomeId(mem.getId());
							inestmentIncome.setIncomeType("0");
							inestmentIncome.setTotalMoney(member.getMoney());
							if(moneyManagement.getInvestMentperiod().equals("0")) {
								moneyManagement.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()+30));
								moneyManagementService.insert(moneyManagement);
							}
							if(moneyManagement.getInvestMentperiod().equals("1")) {
								moneyManagement.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()+60));
								moneyManagementService.insert(moneyManagement);
							}
							if(moneyManagement.getInvestMentperiod().equals("2")) {
								moneyManagement.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()+90));
								moneyManagementService.insert(moneyManagement);
							}
							incomeService.insertIncome(inestmentIncome);
							result.setCode("1");
							result.setMsg("投资成功");
							return result;
					}
					
					}
				}catch (Exception e){
					result.setCode("0");
					result.setMsg("投资异常！");
					e.printStackTrace();
				}
			
			return result;
		}
		
		@RequestMapping(value = "/interestRate",produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult interestRate(){
			ResponseResult result = new ResponseResult();
			try {
			Setting setting = settingService.querySettingInfo();
			result.setCode("1");
			result.setObject(setting);
			result.setMsg("查询成功");
			}catch (Exception e) {
				result.setCode("0");
				result.setMsg("查询出错");
			}
			return result;
		}
		
		
		/*@RequestMapping(value = "/encode",produces = "application/json;charset=utf-8")
		public String encode(Member member){
			Member mem=service.selectById(member.getId());
			String A=readPath+"/check.html?yqm"+mem.getAdCode();
			String b=null;
			try {
				CodeUtil.encode(A,b);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return b;
		}*/
		
		//转码
		public String Transcoding(String nume){
			String name  =null ;
			try {
				 name = new String(nume.getBytes("ISO8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return name;
		}
}
