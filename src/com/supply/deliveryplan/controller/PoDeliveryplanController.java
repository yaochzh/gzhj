package com.supply.deliveryplan.controller;

import com.alibaba.fastjson.JSONArray;
import com.supply.common.Identify;
import com.supply.common.SupplyConstants;
import com.supply.deliveryplan.entity.PoDeliveryplanEntity;
import com.supply.deliveryplan.page.PoDeliveryplanPage;
import com.supply.deliveryplan.service.PoDeliveryplanServiceI;
import com.supply.deliveryplan.service.PoDeliveryplanU8ServiceI;
import com.supply.deliveryplanb.entity.PoDeliveryplanBEntity;
import com.supply.vehicle.entity.VehicleEntity;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
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
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.JeecgSqlUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
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
@RequestMapping({"/poDeliveryplanController"})
public class PoDeliveryplanController extends BaseController
{
  private static final Logger logger = Logger.getLogger(PoDeliveryplanController.class);

  @Autowired
  private PoDeliveryplanServiceI poDeliveryplanService;

  @Autowired
  private PoDeliveryplanU8ServiceI poDeliveryplanU8Service;

  @Autowired
  private SystemService systemService;

  @Autowired
  private Validator validator;

  @Autowired
  private CommonService commonService;

  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request) { return new ModelAndView("com/supply/deliveryplan/poDeliveryplanList");
  }

  @RequestMapping(params={"datagrid"})
  public void datagrid(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class, 
      dataGrid);

    HqlGenerateUtil.installHql(cq, 
      poDeliveryplan);
    try
    {
      String query_createDate_begin = request
        .getParameter("createDate_begin");
      String query_createDate_end = request
        .getParameter("createDate_end");

      if (StringUtil.isNotEmpty(query_createDate_begin)) {
        cq.ge("createDate", new SimpleDateFormat("yyyy-MM-dd")
          .parse(query_createDate_begin));
      }
      if (StringUtil.isNotEmpty(query_createDate_end)) {
        cq.le("createDate", new SimpleDateFormat("yyyy-MM-dd")
          .parse(query_createDate_end));
      }
      String query_expiredate_begin = request
        .getParameter("expiredate_begin");
      String query_expiredate_end = request
        .getParameter("expiredate_end");
      if (StringUtil.isNotEmpty(query_expiredate_begin)) {
        cq.ge("expiredate", new SimpleDateFormat("yyyy-MM-dd")
          .parse(query_expiredate_begin));
      }
      if (StringUtil.isNotEmpty(query_expiredate_end))
        cq.le("expiredate", new SimpleDateFormat("yyyy-MM-dd")
          .parse(query_expiredate_end));
    }
    catch (Exception e) {
      throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.poDeliveryplanService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    poDeliveryplan = (PoDeliveryplanEntity)this.systemService.getEntity(PoDeliveryplanEntity.class, 
      poDeliveryplan.getId());
    String message = "送货计划主表删除成功";
    try {
      if (SupplyConstants.BILLSTATUS_APPROVE.equals(poDeliveryplan.getBillstatus())) {
        message = "单据已审核，不能进行删除操作！";
        throw new BusinessException(message);
      }
      this.poDeliveryplanService.delMain(poDeliveryplan);
      this.systemService.addLog(message, Globals.Log_Type_DEL, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      message = "送货计划主表删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"doBatchDel"})
  @ResponseBody
  public AjaxJson doBatchDel(String ids, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    String message = "送货计划主表删除成功";
    try {
      for (String id : ids.split(",")) {
        PoDeliveryplanEntity poDeliveryplan = (PoDeliveryplanEntity)this.systemService.getEntity(
          PoDeliveryplanEntity.class, id);
        this.poDeliveryplanService.delMain(poDeliveryplan);
        this.systemService.addLog(message, Globals.Log_Type_DEL, 
          Globals.Log_Leavel_INFO);
      }
    } catch (Exception e) {
      e.printStackTrace();
      message = "送货计划主表删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(PoDeliveryplanEntity poDeliveryplan, PoDeliveryplanPage poDeliveryplanPage, HttpServletRequest request)
  {
    List poDeliveryplanBList = poDeliveryplanPage
      .getPoDeliveryplanBList();
    AjaxJson j = new AjaxJson();
    String message = "添加成功";
    try {
      poDeliveryplan.setDeliveryplancode(
        Identify.getIdent(SupplyConstants.SHJH));
      this.poDeliveryplanService.addMain(poDeliveryplan, poDeliveryplanBList);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      message = "送货计划主表添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  private void updateCar(List<PoDeliveryplanBEntity> poDeliveryplanBList, String string) {
    String sql = "update bd_vehicle set bpm_status = '" + string + "' where ";
    List carid = new ArrayList();
    for (PoDeliveryplanBEntity b : poDeliveryplanBList) {
      if (!StringUtil.isEmpty(b.getVehiclelicense())) {
        carid.add(b.getVehiclelicense());
      }
    }
    if ((carid != null) && (carid.size() > 0)) {
      sql = sql + JeecgSqlUtil.buildSqlForIn("id", (String[])carid.toArray(new String[0]));
      this.systemService.updateBySqlString(sql);
    }
  }

  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(PoDeliveryplanEntity poDeliveryplan, PoDeliveryplanPage poDeliveryplanPage, HttpServletRequest request)
  {
    List poDeliveryplanBList = poDeliveryplanPage
      .getPoDeliveryplanBList();
    AjaxJson j = new AjaxJson();
    String message = "更新成功";
    try {
      this.poDeliveryplanService.updateMain(poDeliveryplan, 
        poDeliveryplanBList);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      message = "更新送货计划主表失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(poDeliveryplan.getId())) {
      List list = null;
      try {
        list = this.poDeliveryplanU8Service
          .queryU8PoOrderByOrdercode(poDeliveryplan.getId());
      }
      catch (Exception e) {
        e.printStackTrace();
      }

      if (list == null)
        throw new BusinessException("订单数据查询为空！");
      if ((list != null) && (list.size() > 1))
        throw new BusinessException("订单数据多条，请检查！");
      if ((list != null) && (list.size() == 1)) {
        poDeliveryplan
          .setPoordercode(((Map)list.get(0)).get("POID") == null ? "" : 
          ((Map)list.get(0)).get("POID").toString());
        poDeliveryplan
          .setMaterialcode(((Map)list.get(0)).get("CINVCODE") == null ? "" : 
          ((Map)list.get(0)).get("CINVCODE").toString());
        poDeliveryplan
          .setMaterialname(((Map)list.get(0)).get("CINVNAME") == null ? "" : 
          ((Map)list.get(0)).get("CINVNAME").toString());
        poDeliveryplan.setSpec(((Map)list.get(0)).get("CINVSTD") == null ? "" : 
          ((Map)list.get(0)).get("CINVSTD").toString());
        poDeliveryplan
          .setSupplercode(((Map)list.get(0)).get("CVENCODE") == null ? "" : 
          ((Map)list.get(0)).get("CVENCODE").toString());
        poDeliveryplan
          .setSupplername(((Map)list.get(0)).get("CVENNAME") == null ? "" : 
          ((Map)list.get(0)).get("CVENNAME").toString());
        poDeliveryplan
          .setPoordernum(((Map)list.get(0)).get("IQUANTITY") == null ? BigDecimal.ZERO : 
          BigDecimal.valueOf(Double.parseDouble(
          ((Map)list
          .get(0)).get("IQUANTITY").toString())));
        poDeliveryplan
          .setSurplusnum(((Map)list.get(0)).get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO : 
          BigDecimal.valueOf(Double.parseDouble(
          ((Map)list
          .get(0)).get("SURPLUSQUANTITY")
          .toString())));
      }
    }
    req.setAttribute("poDeliveryplanPage", poDeliveryplan);
    return new ModelAndView("com/supply/deliveryplan/poDeliveryplan-add");
  }

  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest req)
  {
    String roleCodes = ResourceUtil.getSessionRoleCode();
    if (StringUtil.isNotEmpty(poDeliveryplan.getId())) {
      if ((poDeliveryplan.getBillstatus() != null) && (!SupplyConstants.BILLSTATUS_SAVE.equals(poDeliveryplan.getBillstatus()))) {
        throw new BusinessException("单据已审核，不能进行更新操作！");
      }
      poDeliveryplan = (PoDeliveryplanEntity)this.poDeliveryplanService.getEntity(
        PoDeliveryplanEntity.class, poDeliveryplan.getId());

      req.setAttribute("poDeliveryplanPage", poDeliveryplan);
    }
    if ((roleCodes.contains("admin")) || (roleCodes.contains("supper"))) {
      return new ModelAndView("com/supply/deliveryplan/poDeliveryplan-update");
    }
    return new ModelAndView("com/supply/deliveryplan/poDeliveryplan-update-new");
  }

  @RequestMapping(params={"poDeliveryplanBList"})
  public ModelAndView poDeliveryplanBList(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest req)
  {
    Object id0 = poDeliveryplan.getId();

    String hql0 = "from PoDeliveryplanBEntity where 1 = 1 AND fK_ID = ? ";
    try {
      List poDeliveryplanBEntityList = this.systemService
        .findHql(hql0, new Object[] { id0 });
      req.setAttribute("poDeliveryplanBList", poDeliveryplanBEntityList);
      List poDeliveryplana = this.systemService.findByProperty(PoDeliveryplanEntity.class, "id", id0);
      if (!poDeliveryplana.isEmpty())
        req.setAttribute("carrierb", ((PoDeliveryplanEntity)poDeliveryplana.get(0)).getCarrier());
    }
    catch (Exception e) {
      logger.info(e.getMessage());
    }
    return new ModelAndView("com/supply/deliveryplanb/poDeliveryplanBList");
  }
  /**
   * 导出excel
   * 
   * @param request
   * @param response
   */
  @RequestMapping(params = "exportXls")
  public String exportXls(PoDeliveryplanEntity poDeliveryplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid, ModelMap map) {
	CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class,
		dataGrid);
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		poDeliveryplan);
	try {
	    // 自定义追加查询条件
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
	cq.add();
	List<PoDeliveryplanEntity> list = this.poDeliveryplanService
		.getListByCriteriaQuery(cq, false);
	List<PoDeliveryplanPage> pageList = new ArrayList<PoDeliveryplanPage>();
	if (list != null && list.size() > 0) {
	    for (PoDeliveryplanEntity entity : list) {
		try {
		    PoDeliveryplanPage page = new PoDeliveryplanPage();
		    MyBeanUtils.copyBeanNotNull2Bean(entity, page);
		    Object id0 = entity.getId();
		    String hql0 = "from PoDeliveryplanBEntity where 1 = 1 AND fK_ID = ? ";
		    List<PoDeliveryplanBEntity> poDeliveryplanBEntityList = systemService
			    .findHql(hql0, id0);
		    page.setPoDeliveryplanBList(poDeliveryplanBEntityList);
		    pageList.add(page);
		} catch (Exception e) {
		    logger.info(e.getMessage());
		}
	    }
	}
	map.put(NormalExcelConstants.FILE_NAME, "送货计划主表");
	map.put(NormalExcelConstants.CLASS, PoDeliveryplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("送货计划主表列表",
		"导出人:Jeecg", "导出信息"));
	map.put(NormalExcelConstants.DATA_LIST, pageList);
	return NormalExcelConstants.JEECG_EXCEL_VIEW;
  }

  /**
   * 通过excel导入数据
   * 
   * @param request
   * @param
   * @return
   */
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
	    params.setHeadRows(2);
	    params.setNeedSave(true);
	    try {
		List<PoDeliveryplanPage> list = ExcelImportUtil
			.importExcel(file.getInputStream(),
				PoDeliveryplanPage.class, params);
		PoDeliveryplanEntity entity1 = null;
		for (PoDeliveryplanPage page : list) {
		    page.setDeliveryplancode(Identify
			    .getIdent(SupplyConstants.SHJH));
		    entity1 = new PoDeliveryplanEntity();
		    MyBeanUtils.copyBeanNotNull2Bean(page, entity1);
		    poDeliveryplanService.addMain(entity1,
			    page.getPoDeliveryplanBList());
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

  /**
   * 导出excel 使模板
   */
  @RequestMapping(params = "exportXlsByT")
  public String exportXlsByT(ModelMap map) {
	map.put(NormalExcelConstants.FILE_NAME, "送货计划主表");
	map.put(NormalExcelConstants.CLASS, PoDeliveryplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("送货计划主表列表",
		"导出人:" + ResourceUtil.getSessionUserName().getRealName(),
		"导出信息"));
	map.put(NormalExcelConstants.DATA_LIST, new ArrayList());
	return NormalExcelConstants.JEECG_EXCEL_VIEW;
  }

  /**
   * 导入功能跳转
   * 
   * @return
   */
  @RequestMapping(params = "upload")
  public ModelAndView upload(HttpServletRequest req) {
	req.setAttribute("controller_name", "poDeliveryplanController");
	return new ModelAndView("common/upload/pub_excel_upload");
  }

  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<PoDeliveryplanEntity> list() { List listPoDeliveryplans = this.poDeliveryplanService
      .getList(PoDeliveryplanEntity.class);
    return listPoDeliveryplans; } 
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public ResponseEntity<?> get(@PathVariable("id") String id) {
    PoDeliveryplanEntity task = (PoDeliveryplanEntity)this.poDeliveryplanService.get(
      PoDeliveryplanEntity.class, id);
    if (task == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(task, HttpStatus.OK);
  }

  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody PoDeliveryplanPage poDeliveryplanPage, UriComponentsBuilder uriBuilder)
  {
    Set failures = this.validator
      .validate(poDeliveryplanPage, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(
        BeanValidators.extractPropertyAndMessage(failures), 
        HttpStatus.BAD_REQUEST);
    }

    List poDeliveryplanBList = poDeliveryplanPage
      .getPoDeliveryplanBList();

    PoDeliveryplanEntity poDeliveryplan = new PoDeliveryplanEntity();
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(poDeliveryplan, poDeliveryplanPage);
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    this.poDeliveryplanService.addMain(poDeliveryplan, poDeliveryplanBList);

    String id = poDeliveryplanPage.getId();
    URI uri = uriBuilder.path("/rest/poDeliveryplanController/" + id)
      .build().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uri);

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  public ResponseEntity<?> update(@RequestBody PoDeliveryplanPage poDeliveryplanPage)
  {
    Set failures = this.validator
      .validate(poDeliveryplanPage, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(
        BeanValidators.extractPropertyAndMessage(failures), 
        HttpStatus.BAD_REQUEST);
    }

    List poDeliveryplanBList = poDeliveryplanPage
      .getPoDeliveryplanBList();

    PoDeliveryplanEntity poDeliveryplan = new PoDeliveryplanEntity();
    try
    {
      MyBeanUtils.copyBeanNotNull2Bean(poDeliveryplan, poDeliveryplanPage);
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    this.poDeliveryplanService.updateMain(poDeliveryplan, poDeliveryplanBList);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) { PoDeliveryplanEntity poDeliveryplan = (PoDeliveryplanEntity)this.poDeliveryplanService.get(
      PoDeliveryplanEntity.class, id);
    this.poDeliveryplanService.delMain(poDeliveryplan);
  }

  @RequestMapping(params={"datagridU8"})
  public void datagridU8(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
    throws Exception
  {
    String createBy = poDeliveryplan.getSupplername() == null ? "" : poDeliveryplan.getSupplername().toString();
    String createByName = poDeliveryplan.getSupplercode() == null ? "" : poDeliveryplan.getSupplercode().toString();
    if ((StringUtil.isEmpty(createBy)) && (StringUtil.isEmpty(createByName))) {
      return;
    }
    List<Map<String, Object>> list = this.poDeliveryplanU8Service
      .queryU8PoOrder(createBy.toString(), createByName.toString());
    CriteriaQuery cq = new CriteriaQuery(PoDeliveryplanEntity.class, 
      dataGrid);
    ArrayList results = new ArrayList();
    for (Map<String,Object> map : list) {
      PoDeliveryplanEntity entity = new PoDeliveryplanEntity();
      entity.setId(map.get("POID") == null ? "" : map.get("POID")
        .toString());
      entity.setPoordercode(map.get("POID") == null ? "" : map
        .get("POID").toString());
      entity.setSysOrgCode(map.get("DPODATE") == null ? "" : map.get(
        "DPODATE").toString());
      entity.setSupplercode(map.get("CVENCODE") == null ? "" : map.get(
        "CVENCODE").toString());
      entity.setSupplername(map.get("CVENNAME") == null ? "" : map.get(
        "CVENNAME").toString());
      entity.setMaterialcode(map.get("CINVCODE") == null ? "" : map.get(
        "CINVCODE").toString());
      entity.setMaterialname(map.get("CINVNAME") == null ? "" : map.get(
        "CINVNAME").toString());
      entity.setSpec(map.get("CINVSTD") == null ? "" : map.get("CINVSTD")
        .toString());
      entity.setStore(map.get("IQUANTITY") == null ? "" : map.get(
        "IQUANTITY").toString());
      entity.setSurplusnum(map.get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO : 
        BigDecimal.valueOf(Double.parseDouble(map
        .get("SURPLUSQUANTITY").toString())));
      entity.setUnloadplace(map.get("FPOARRQUANTITY") == null ? "" : map
        .get("FPOARRQUANTITY").toString());
      entity.setVnote(map.get("ITAXPRICE") == null ? "" : map.get(
        "ITAXPRICE").toString());
      results.add(entity);
    }

    HqlGenerateUtil.installHql(cq, 
      poDeliveryplan);

    cq.add();

    this.poDeliveryplanService.getDataGridReturn(cq, true);
    dataGrid.setResults(results);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params={"querySrc"})
  public ModelAndView querySrc(PoDeliveryplanEntity poDeliveryplan, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(poDeliveryplan.getId())) {
      poDeliveryplan = (PoDeliveryplanEntity)this.poDeliveryplanService.getEntity(
        PoDeliveryplanEntity.class, poDeliveryplan.getId());
      req.setAttribute("poDeliveryplanPage", poDeliveryplan);
    }
    return new ModelAndView("com/supply/deliveryplan/poDeliveryplanU8List");
  }

  @RequestMapping(params={"queryVehicle"})
  @ResponseBody
  public List<VehicleEntity> queryVehicle(HttpServletRequest request, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(VehicleEntity.class);

    List ls = this.commonService
      .getListByCriteriaQuery(cq, Boolean.valueOf(false));
    return ls;
  }

  @RequestMapping(params={"doApprove"})
  @ResponseBody
  public AjaxJson doApprove(String ids, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    String message = "送货计划审核成功";
    try {
      for (String id : ids.split(",")) {
        PoDeliveryplanEntity poDeliveryplan = (PoDeliveryplanEntity)this.systemService.getEntity(
          PoDeliveryplanEntity.class, id);

        if (SupplyConstants.BILLSTATUS_APPROVE.equals(poDeliveryplan
          .getBillstatus())) {
          throw new BusinessException("已审核单据，不能再次审核");
        }
        if ((SupplyConstants.BILLSTATUS_SAVE
          .equals(poDeliveryplan.getBillstatus())) || 
          (StringUtil.isEmpty(poDeliveryplan.getBillstatus()))) {
          this.poDeliveryplanService
            .updateBySqlString("update po_deliveryplan set billstatus = '" + 
            SupplyConstants.BILLSTATUS_APPROVE + 
            "' where id = '" + id + "'");
        }
        this.systemService.addLog(message, Globals.Log_Type_APPROVE, 
          Globals.Log_Leavel_INFO);
      }
    } catch (Exception e) {
      e.printStackTrace();
      message = "送货计划审核失败" + e.getMessage();
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"queryByCode"})
  @ResponseBody
  public AjaxJson queryByCode(HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    Map jsonObject = new HashMap();
    String code = request.getParameter("code") == null ? "" : request.getParameter("code").toString();
    if (!StringUtil.isEmpty(code))
    {
      VehicleEntity vehicle = (VehicleEntity)this.systemService.getEntity(VehicleEntity.class, code);
      if (vehicle != null) {
        jsonObject.put("vehicletype", vehicle.getCvehicletype() == null ? "" : vehicle.getCvehicletype().toString());
        jsonObject.put("drivername", vehicle.getCdriver() == null ? "" : vehicle.getCdriver().toString());
        jsonObject.put("idcard", vehicle.getVlicensenumber() == null ? "" : vehicle.getVlicensenumber().toString());
        jsonObject.put("phone", vehicle.getVdrivermobile() == null ? "" : vehicle.getVdrivermobile().toString());
        jsonObject.put("loadnum", vehicle.getNload() == null ? "" : vehicle.getNload().toString());
        j.setAttributes(jsonObject);
      }
    }
    return j;
  }

  @RequestMapping(params={"getVehicleTree"})
  @ResponseBody
  public String getVehicleTree(HttpServletRequest request)
  {
    List vehicleList = this.systemService.findByQueryString("from VehicleEntity order by vehiclelicense");
    List a = new ArrayList();
    Map b = new HashMap();
    b.put("id", "1");
    b.put("value", "条目1");
    a.add(b);
    b.put("id", "2");
    b.put("value", "条目2");
    a.add(b);
    b.put("id", "3");
    b.put("value", "条目3");
    a.add(b);

    return JSONArray.toJSONString(a);
  }

  @RequestMapping(params={"queryByCodes"})
  @ResponseBody
  public AjaxJson queryByCodes(HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    List obj = new ArrayList();
    String code = request.getParameter("codes") == null ? "" : request.getParameter("codes").toString();
    if (!StringUtil.isEmpty(code))
    {
      String[] ids = code.split(",");
      String where = StringUtil.buildSqlForIn("ID", ids);
      String hql = "select ID,CVEHICLETYPE,VEHICLELICENSE,CDRIVER,VLICENSENUMBER,VDRIVERMOBILE,NLOAD from bd_vehicle where " + 
        where;
      List VehicleEntitys = this.systemService
        .findObjForJdbc(hql, 1, 10000, VehicleEntity.class);
      if ((VehicleEntitys != null) && (VehicleEntitys.size() > 0)) {
        for (int i = 0; i < VehicleEntitys.size(); i++) {
          Map jsonObject = new HashMap();
          jsonObject.put("vehicletype", ((VehicleEntity)VehicleEntitys.get(i)).getCvehicletype() == null ? "" : ((VehicleEntity)VehicleEntitys.get(i)).getCvehicletype().toString());
          jsonObject.put("drivername", ((VehicleEntity)VehicleEntitys.get(i)).getCdriver() == null ? "" : ((VehicleEntity)VehicleEntitys.get(i)).getCdriver().toString());
          jsonObject.put("idcard", ((VehicleEntity)VehicleEntitys.get(i)).getVlicensenumber() == null ? "" : ((VehicleEntity)VehicleEntitys.get(i)).getVlicensenumber().toString());
          jsonObject.put("phone", ((VehicleEntity)VehicleEntitys.get(i)).getVdrivermobile() == null ? "" : ((VehicleEntity)VehicleEntitys.get(i)).getVdrivermobile().toString());
          jsonObject.put("loadnum", ((VehicleEntity)VehicleEntitys.get(i)).getNload() == null ? "" : ((VehicleEntity)VehicleEntitys.get(i)).getNload().toString());
          jsonObject.put("vehiclelicense", ((VehicleEntity)VehicleEntitys.get(i)).getVehiclelicense() == null ? "" : ((VehicleEntity)VehicleEntitys.get(i)).getVehiclelicense().toString());
          obj.add(jsonObject);
        }

        j.setObj(obj.toArray(new Map[0]));
      }
    }
    return j;
  }
}