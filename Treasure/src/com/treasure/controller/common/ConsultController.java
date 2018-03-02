package com.treasure.controller.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.BtoolsConsult;
import com.treasure.model.User;
import com.treasure.service.ConsultService;
/**
 * 	@name 吴健
 * 	@date 2017-12-17
 * */
@Controller
@RequestMapping(value = "consult")
public class ConsultController extends BaseController{
	@Autowired
	@Qualifier(value = "consultServiceImpl")
	private ConsultService consultService;
		
	private static final Logger log = Logger.getLogger(ConsultController.class);
	
	// 进入页面
		@RequestMapping(value = "/consultlist")
		public String consultlist() {
			log.info("进入咨询模块页面...");
			System.out.println("进入咨询模块页面...");
			return "purview/consult/consultlist";
		}
		
		@RequestMapping(value = "/consultform")
		public String moduleform() {
			return "purview/consult/consultform";
		}
		
		@RequestMapping(value = "/upload")
		public String upload() {
			return "purview/consult/upload";
		}
		
		/**
		 * 后台显示页面
		 * */
		@RequestMapping(value = "/showList")
		public @ResponseBody ResponseResult showList(BtoolsConsult btoolsConsult, PageInfo pageInfo) {
			ResponseResult result = new ResponseResult();
			try {
				if (pageInfo == null) {
					pageInfo = new PageInfo();
				}
				List<BtoolsConsult> list=consultService.findInPage(btoolsConsult, pageInfo);
				int totalRecord =consultService.findTotal(btoolsConsult);
				pageInfo.setTotalRecord(totalRecord);
				result.setCode(SysConstants.STATUS_TRUE);
				result.setMsg("分页查询列表成功。");
				result.setList(list);
				result.setPageInfo(pageInfo);
				}catch (Exception e) {// 异常处理
				e.printStackTrace();
				result.setCode(SysConstants.STATUS_ERROR);
				result.setMsg("分页查询列表异常！");
			}
				return result;
		}
		
		
		
		/**
		 * 增加和修改
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/addConsult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult addConsult(BtoolsConsult btoolsConsult) {
			int num = 0;
			ResponseResult result = new ResponseResult();
			try {
				User user = this.getSessionUser();
				System.out.println("用户："+user);
				if (btoolsConsult.getConId()== null) {
					btoolsConsult.setConDate(new Date());
					btoolsConsult.setConAuthor(user.getUserName());
					btoolsConsult.setStatus(2);
					num = consultService.add(btoolsConsult);
					if (num > 0) {
						result.setCode(SysConstants.STATUS_TRUE);
						result.setMsg("添加成功~");
					} else {
						result.setCode(SysConstants.STATUS_FALSE);
						result.setMsg("添加失败！");
					}
				} else {
					num = consultService.modifyByModel(btoolsConsult);
					if (num > 0) {
						result.setCode(SysConstants.STATUS_TRUE);
						result.setMsg("修改成功~");
					} else {
						result.setCode(SysConstants.STATUS_FALSE);
						result.setMsg("修改失败！");
					}
				}
			} catch (Exception e) {
				result.setCode(SysConstants.STATUS_ERROR);
				result.setMsg(e.getMessage());
				result.setObject(btoolsConsult);
			}
			return result;
			
		}

		
		/**
		 * 咨询模块修改页面
		 * 
		 * @return
		 * @throws Exception
		 */
		
		@RequestMapping(value = "/showUpdate", produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult showUpdate(
				@RequestParam(required = false, value = "key") Long consultId) {
			ResponseResult result = new ResponseResult();
			try {
				if (consultId != null) {
					BtoolsConsult consult = consultService.findById(consultId);
					result.setCode(SysConstants.STATUS_TRUE);
					result.setObject(consult);
				}
			} catch (Exception e) {
				result.setCode(SysConstants.STATUS_ERROR);
				result.setMsg(e.getMessage());
				result.setObject(null);
			}
			return result;
		}
		
		/**
		 * 咨询模块发布点击
		 * 
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/issue")
		public @ResponseBody ResponseResult issue(BtoolsConsult btoolsConsult) {
			ResponseResult result = new ResponseResult();
			try {
				btoolsConsult.setConDate(new Date());
				btoolsConsult.setStatus(2);
				int n = consultService.modifyByModel(btoolsConsult);
				if (n > 0) {
					result.setCode(SysConstants.STATUS_TRUE);
					result.setMsg("发布成功！");
				} else {
					result.setCode(SysConstants.STATUS_FALSE);
					result.setMsg("发布失败！");
				}
			} catch (Exception e) {// 异常处理
				e.printStackTrace();
				result.setCode(SysConstants.STATUS_ERROR);
				result.setMsg("发布异常！");
			}
			return result;
		}
		
		
		
		
		
		/**
		 * 批量删除
		 * 
		 * @param keys
		 * @return
		 */
		@RequestMapping(value = "/deleteConsult", produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult deleteConsult(
				@RequestParam(required = false, value = "keys") String keys) {
			log.info("主键：" + keys);
			ResponseResult result = new ResponseResult();
			try {
				if (StringUtils.hasText(keys)) {
					List<Long> keyes = new ArrayList<Long>();
					String[] objs = keys.split(",");
					
					for (String k : objs) {
						
						keyes.add(Long.parseLong(k));
					}
					int num = consultService.remove(keyes);
					if (num > 0) {
						result.setCode(SysConstants.STATUS_TRUE);
						result.setMsg("成功刪除" + num + "条数据");
					} else {
						result.setCode(SysConstants.STATUS_FALSE);
						result.setMsg("删除失败！");
					}
				}
			} catch (Exception e) {
				result.setCode(SysConstants.STATUS_ERROR);
				result.setMsg(e.getMessage());
			}
			return result;
		}
		
		
		/** 发布后前台查询咨询模块列表
		 * 
		 * 
		 * @return
		 */
		@RequestMapping(value = "/issue/showList", produces = "application/json;charset=utf-8")
		public @ResponseBody ResponseResult showList() {
			log.info("查询consult数据");
			ResponseResult result = new ResponseResult();
			try {
				List<BtoolsConsult> list = consultService.selectAllList();// 查询符合条件的数据
				result.setCode(SysConstants.STATUS_TRUE);
				result.setList(list);
			} catch (Exception e) {// 异常处理
				e.printStackTrace();
				log.info(e.getMessage());
				result.setCode(SysConstants.STATUS_ERROR);
				result.setMsg(e.getMessage());
			}
			return result;
		}
		
}
