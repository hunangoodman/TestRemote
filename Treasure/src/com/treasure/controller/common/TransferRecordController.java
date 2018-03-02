package com.treasure.controller.common;
 
import java.util.List;
 
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.treasure.bean.PageInfo;
import com.treasure.bean.ResponseResult;
import com.treasure.constant.SysConstants;
import com.treasure.model.MallRecord;
import com.treasure.model.TransferRecord;
import com.treasure.service.MallRecordService;
import com.treasure.service.TransferRecordService;
/**
 *     @name 吴健
 *     @date 2017-1-26
 * */
@Controller
@RequestMapping(value ="transfer")
public class TransferRecordController extends BaseController {
   
   @Autowired
   @Qualifier(value = "transferRecordServiceImpl")
   private TransferRecordService transferRecordService;
   
   @Autowired
   @Qualifier(value = "mallRecordServiceImpl")
   private MallRecordService mallRecordService;
   
   private static final Logger log = Logger.getLogger(TransferRecord.class);
   
   // 进入页面
       @RequestMapping(value = "/mgr/transferlist")
       public String transferlist() {
           log.info("进入明细模块页面...");
           System.out.println("进入转出明细页面...");
           return "purview/details/transferRecordList";
       }
       // 进入页面
       @RequestMapping(value = "/mgr/transferlist1")
       public String transferlist1() {
           log.info("进入明细模块页面...");
           System.out.println("进入转入明细页面...");
           return "purview/details/transferRecordList1";
       }
       
       // 进入商城页面
               @RequestMapping(value = "/mgr/malltransferlist")
               public String malltransferlist() {
                   log.info("进入商城明细模块页面...");
                   System.out.println("进入商城转入明细页面...");
                   return "purview/details/mallRecordList";
               }
       
       
       /**
        * 后台显示页面
        * */
       @RequestMapping(value = "/mgr/showList")
       public @ResponseBody ResponseResult showList(TransferRecord transferRecord, PageInfo pageInfo) {
           ResponseResult result = new ResponseResult();
           
           try {
               if (pageInfo == null) {
                   pageInfo = new PageInfo();
               }
               List<TransferRecord> list=transferRecordService.findInPage(transferRecord, pageInfo);
               int totalRecord =transferRecordService.findTotal(transferRecord);
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
        * 后台显示页面
        * */
       @RequestMapping(value = "/mgr/showList1")
       public @ResponseBody ResponseResult showList1(TransferRecord transferRecord, PageInfo pageInfo) {
           ResponseResult result = new ResponseResult();
           
           try {
               if (pageInfo == null) {
                   pageInfo = new PageInfo();
               }
               List<TransferRecord> list=transferRecordService.findInPage1(transferRecord, pageInfo);
               int totalRecord =transferRecordService.findTotal1(transferRecord);
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
        * 商城后台显示
        * */
       @RequestMapping(value = "/mgr/showList2")
       public @ResponseBody ResponseResult showList2(MallRecord mallRecord, PageInfo pageInfo) {
           ResponseResult result = new ResponseResult();
           
           try {
               if (pageInfo == null) {
                   pageInfo = new PageInfo();
               }
               List<MallRecord> list=mallRecordService.selectList(mallRecord, pageInfo);
               int totalRecord =mallRecordService.findTotal1(mallRecord);
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
       
}