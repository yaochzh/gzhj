package com.supply.carrier.controller;

import com.supply.carrier.entity.BdCarrierEntity;
import com.supply.carrier.service.BdCarrierServiceI;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Scope("prototype")
@Controller
@RequestMapping({"/bdCarrierController"})
public class BdCarrierController extends BaseController
{
  private static final Logger logger = Logger.getLogger(BdCarrierController.class);

  @Autowired
  private BdCarrierServiceI bdCarrierService;

  @Autowired
  private SystemService systemService;

  @Autowired
  private Validator validator;
  private String message;

  public String getMessage() { return this.message; }

  public void setMessage(String message)
  {
    this.message = message;
  }

  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request)
  {
    return new ModelAndView("com/supply/carrier/bdCarrierList");
  }

  @RequestMapping(params={"datagrid"})
  public void datagrid(BdCarrierEntity bdCarrier, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(BdCarrierEntity.class, dataGrid);

    HqlGenerateUtil.installHql(cq, 
      bdCarrier, request.getParameterMap());

    cq.add();
    this.bdCarrierService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(BdCarrierEntity bdCarrier, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    bdCarrier = (BdCarrierEntity)this.systemService.getEntity(BdCarrierEntity.class, 
      bdCarrier.getId());
    this.message = "承运单位删除成功";
    try {
      this.bdCarrierService.delete(bdCarrier);
      this.systemService.addLog(this.message, Globals.Log_Type_DEL, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "承运单位删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"doBatchDel"})
  @ResponseBody
  public AjaxJson doBatchDel(String ids, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    this.message = "承运单位删除成功";
    try {
      for (String id : ids.split(",")) {
        BdCarrierEntity bdCarrier = (BdCarrierEntity)this.systemService.getEntity(
          BdCarrierEntity.class, id);
        this.bdCarrierService.delete(bdCarrier);
        this.systemService.addLog(this.message, Globals.Log_Type_DEL, 
          Globals.Log_Leavel_INFO);
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "承运单位删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(BdCarrierEntity bdCarrier, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    TSUser user = ResourceUtil.getSessionUserName();
    String userName = user.getUserName();
    this.message = "承运单位添加成功";
    try
    {
      String hql = "select id\n  from bd_carrier\n where (carriercode = '" + 
        bdCarrier.getCarriercode() + 
        "' or carriername = '" + bdCarrier.getCarriername() + 
        "')";
      if ((bdCarrier.getBsealflag() == null) || ("".equals(bdCarrier.getBsealflag()))) {
        bdCarrier.setBsealflag("N");
      }
      List list = this.systemService.findListbySql(hql);
      if ((list != null) && (list.size() > 0)) {
        this.message = "承运单位编码或名称已存在！";
        throw new BusinessException(this.message);
      }
      this.bdCarrierService.save(bdCarrier);

      this.systemService.addLog(this.message, Globals.Log_Type_INSERT, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "承运单位添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(BdCarrierEntity bdCarrier, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    this.message = "承运单位更新成功";
    String hql = "select id\n  from bd_carrier\n where  (carriercode = '" + 
      bdCarrier.getCarriercode() + 
      "' or carriername = '" + bdCarrier.getCarriername() + 
      "') and id <> '" + bdCarrier.getId() + "'";

    List list = this.systemService.findListbySql(hql);
    if ((list != null) && (list.size() > 0)) {
      this.message = "承运单位编码或名称已存在！";
      throw new BusinessException(this.message);
    }
    BdCarrierEntity t = (BdCarrierEntity)this.bdCarrierService.get(BdCarrierEntity.class, 
      bdCarrier.getId());
    String updatesql = "";
    try {
      MyBeanUtils.copyBeanNotNull2Bean(bdCarrier, t);
      if ((t.getBsealflag() == null) || ("".equals(t.getBsealflag()))) {
        t.setBsealflag("N");
      }
      if ((t.getBsealflag() != null) && (t.getBsealflag().equals("Y")))
        updatesql = "update bd_vehicle set bsealflag = 'Y' where ccarrier = '" + bdCarrier.getId() + "'";
      else {
        updatesql = "update bd_vehicle set bsealflag = 'N' where ccarrier = '" + bdCarrier.getId() + "'";
      }
      this.systemService.updateBySqlString(updatesql);
      this.bdCarrierService.saveOrUpdate(t);
      this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "承运单位更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(BdCarrierEntity bdCarrier, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(bdCarrier.getId())) {
      bdCarrier = (BdCarrierEntity)this.bdCarrierService.getEntity(BdCarrierEntity.class, 
        bdCarrier.getId());
      req.setAttribute("bdCarrierPage", bdCarrier);
    }
    return new ModelAndView("com/supply/carrier/bdCarrier-add");
  }

  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(BdCarrierEntity bdCarrier, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(bdCarrier.getId())) {
      bdCarrier = (BdCarrierEntity)this.bdCarrierService.getEntity(BdCarrierEntity.class, 
        bdCarrier.getId());
      req.setAttribute("bdCarrierPage", bdCarrier);
    }
    return new ModelAndView("com/supply/carrier/bdCarrier-update");
  }

  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "bdCarrierController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }

  @RequestMapping(params={"exportXls"})
  public String exportXls(BdCarrierEntity bdCarrier, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(BdCarrierEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, 
      bdCarrier, request.getParameterMap());
    List bdCarriers = this.bdCarrierService
      .getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "承运单位");
    modelMap.put("entity", BdCarrierEntity.class);
    modelMap.put("params", 
      new ExportParams("承运单位列表", 
      "导出人:" + ResourceUtil.getSessionUserName().getRealName(), 
      "导出信息"));
    modelMap.put("data", bdCarriers);
    return "jeecgExcelView";
  }

  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(BdCarrierEntity bdCarrier, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "承运单位");
    modelMap.put("entity", BdCarrierEntity.class);
    modelMap.put("params", 
      new ExportParams("承运单位列表", 
      "导出人:" + ResourceUtil.getSessionUserName().getRealName(), 
      "导出信息"));
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(params = "importExcel", method = RequestMethod.POST)
  @ResponseBody
  public AjaxJson importExcel(HttpServletRequest request,
	    HttpServletResponse response) {
	AjaxJson j = new AjaxJson();

	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
	    MultipartFile file = entity.getValue();// 获取上传文件对象
	    ImportParams params = new ImportParams();
	    params.setTitleRows(2);
	    params.setHeadRows(1);
	    params.setNeedSave(true);
	    try {
		List<BdCarrierEntity> listBdCarrierEntitys = ExcelImportUtil
			.importExcel(file.getInputStream(),
				BdCarrierEntity.class, params);
		for (BdCarrierEntity bdCarrier : listBdCarrierEntitys) {
		    bdCarrierService.save(bdCarrier);
		}
		j.setMsg("文件导入成功！");
	    } catch (Exception e) {
		j.setMsg("文件导入失败！");
		logger.error(ExceptionUtil.getExceptionMessage(e));
	    } finally {
		try {
		    file.getInputStream().close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return j;
  }
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<BdCarrierEntity> list() { List listBdCarriers = this.bdCarrierService
      .getList(BdCarrierEntity.class);
    return listBdCarriers; } 
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public ResponseEntity<?> get(@PathVariable("id") String id) {
    BdCarrierEntity task = (BdCarrierEntity)this.bdCarrierService.get(BdCarrierEntity.class, id);
    if (task == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(task, HttpStatus.OK);
  }

  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody BdCarrierEntity bdCarrier, UriComponentsBuilder uriBuilder) {
    Set failures = this.validator
      .validate(bdCarrier, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(
        BeanValidators.extractPropertyAndMessage(failures), 
        HttpStatus.BAD_REQUEST);
    }

    this.bdCarrierService.save(bdCarrier);

    String id = bdCarrier.getId();
    URI uri = uriBuilder.path("/rest/bdCarrierController/" + id).build()
      .toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uri);

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  public ResponseEntity<?> update(@RequestBody BdCarrierEntity bdCarrier) {
    Set failures = this.validator
      .validate(bdCarrier, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(
        BeanValidators.extractPropertyAndMessage(failures), 
        HttpStatus.BAD_REQUEST);
    }

    this.bdCarrierService.saveOrUpdate(bdCarrier);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) { this.bdCarrierService.deleteEntityById(BdCarrierEntity.class, id);
  }

  @RequestMapping(params={"queryCarrierCodeById"})
  @ResponseBody
  public AjaxJson queryCarrierCodeById(HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    Map jsonObject = new HashMap();
    String id = request.getParameter("carrierid") == null ? "~" : request
      .getParameter("carrierid").toString();
    List carriers = this.systemService.findByProperty(BdCarrierEntity.class, "id", id);
    if (!carriers.isEmpty()) {
      jsonObject.put("carriercode", ((BdCarrierEntity)carriers.get(0)).getCarriercode());
    }
    j.setAttributes(jsonObject);
    return j;
  }
}