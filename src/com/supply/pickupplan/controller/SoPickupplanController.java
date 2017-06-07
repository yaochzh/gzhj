package com.supply.pickupplan.controller;

import com.supply.common.Identify;
import com.supply.common.SupplyConstants;
import com.supply.deliveryplan.entity.PoDeliveryplanEntity;
import com.supply.pickupplan.entity.SoPickupplanEntity;
import com.supply.pickupplan.page.SoPickupplanPage;
import com.supply.pickupplan.service.SoPickupplanServiceI;
import com.supply.pickupplan.service.SoPickupplanU8ServiceI;
import com.supply.pickupplanb.entity.SoPickupplanBEntity;
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
@RequestMapping({"/soPickupplanController"})
public class SoPickupplanController extends BaseController
{
  private static final Logger logger = Logger.getLogger(SoPickupplanController.class);

  @Autowired
  private SoPickupplanServiceI soPickupplanService;

  @Autowired
  private SoPickupplanU8ServiceI soPickupplanU8Service;

  @Autowired
  private SystemService systemService;

  @Autowired
  private Validator validator;

  @Autowired
  private CommonService commonService;

  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request) { return new ModelAndView("com/supply/pickupplan/soPickupplanList");
  }

  @RequestMapping(params={"datagrid"})
  public void datagrid(SoPickupplanEntity soPickupplan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(SoPickupplanEntity.class, dataGrid);

    HqlGenerateUtil.installHql(cq, 
      soPickupplan);
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
    this.soPickupplanService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(SoPickupplanEntity soPickupplan, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    soPickupplan = (SoPickupplanEntity)this.systemService.getEntity(SoPickupplanEntity.class, 
      soPickupplan.getId());
    String message = "提货计划主表删除成功";
    try {
      if (SupplyConstants.BILLSTATUS_APPROVE.equals(soPickupplan
        .getBillstatus())) {
        message = "单据已审核，不能进行删除操作！";
        throw new BusinessException(message);
      }
      this.soPickupplanService.delMain(soPickupplan);
      this.systemService.addLog(message, Globals.Log_Type_DEL, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      message = "提货计划主表删除失败";
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
    String message = "提货计划主表删除成功";
    try {
      for (String id : ids.split(",")) {
        SoPickupplanEntity soPickupplan = (SoPickupplanEntity)this.systemService.getEntity(
          SoPickupplanEntity.class, id);
        this.soPickupplanService.delMain(soPickupplan);
        this.systemService.addLog(message, Globals.Log_Type_DEL, 
          Globals.Log_Leavel_INFO);
      }
    } catch (Exception e) {
      e.printStackTrace();
      message = "提货计划主表删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(SoPickupplanEntity soPickupplan, SoPickupplanPage soPickupplanPage, HttpServletRequest request)
  {
    List soPickupplanBList = soPickupplanPage
      .getSoPickupplanBList();
    AjaxJson j = new AjaxJson();
    String message = "添加成功";
    try {
      soPickupplan.setPickupplancode(
        Identify.getIdent(SupplyConstants.THJH));
      this.soPickupplanService.addMain(soPickupplan, soPickupplanBList);
      this.systemService.addLog(message, Globals.Log_Type_INSERT, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      message = "提货计划主表添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(SoPickupplanEntity soPickupplan, SoPickupplanPage soPickupplanPage, HttpServletRequest request)
  {
    List soPickupplanBList = soPickupplanPage
      .getSoPickupplanBList();
    AjaxJson j = new AjaxJson();
    String message = "更新成功";
    try {
      this.soPickupplanService.updateMain(soPickupplan, soPickupplanBList);
      this.systemService.addLog(message, Globals.Log_Type_UPDATE, 
        Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      message = "更新提货计划主表失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
  }

  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(SoPickupplanEntity soPickupplan, HttpServletRequest req)
    throws Exception
  {
    if (StringUtil.isNotEmpty(soPickupplan.getId())) {
      List list = this.soPickupplanU8Service
        .queryU8SoOrderByOrdercode(soPickupplan.getId());
      if (list == null)
        throw new BusinessException("订单数据查询为空！");
      if ((list != null) && (list.size() > 1))
        throw new BusinessException("订单数据多条，请检查！");
      if ((list != null) && (list.size() == 1))
      {
        List<SoPickupplanEntity> soPickupplanEntitys = this.soPickupplanService
          .findByProperty(SoPickupplanEntity.class, 
          "soordercode", soPickupplan.getId());
        soPickupplan
          .setSoordercode(((Map)list.get(0)).get("cSOCode") == null ? "" : 
          ((Map)list.get(0)).get("cSOCode").toString());
        soPickupplan
          .setMaterialcode(((Map)list.get(0)).get("DPODATE") == null ? "" : 
          ((Map)list.get(0)).get("DPODATE").toString());
        soPickupplan
          .setMaterialcode(((Map)list.get(0)).get("cInvCode") == null ? "" : 
          ((Map)list.get(0)).get("cInvCode").toString());
        soPickupplan
          .setMaterialname(((Map)list.get(0)).get("CINVNAME") == null ? "" : 
          ((Map)list.get(0)).get("CINVNAME").toString());
        soPickupplan.setSpec(((Map)list.get(0)).get("CINVSTD") == null ? "" : 
          ((Map)list.get(0)).get("CINVSTD").toString());
        soPickupplan
          .setCustomercode(((Map)list.get(0)).get("cCusCode") == null ? "" : 
          ((Map)list.get(0)).get("cCusCode").toString());
        soPickupplan
          .setCustomername(((Map)list.get(0)).get("cCusName") == null ? "" : 
          ((Map)list.get(0)).get("cCusName").toString());
        soPickupplan
          .setSoordernum(((Map)list.get(0)).get("IQUANTITY") == null ? BigDecimal.ZERO : 
          BigDecimal.valueOf(Double.parseDouble(
          ((Map)list
          .get(0)).get("IQUANTITY").toString())));
        if ((soPickupplanEntitys != null) && 
          (soPickupplanEntitys.size() > 0)) {
          BigDecimal orderNum = ((SoPickupplanEntity)soPickupplanEntitys.get(0))
            .getSoordernum();
          BigDecimal planassNums = BigDecimal.ZERO;
          for (SoPickupplanEntity entity : soPickupplanEntitys) {
            planassNums = planassNums.add(entity.getPlanassnum() == null ? BigDecimal.ZERO : entity.getPlanassnum());
          }
          soPickupplan.setSurplusnum(orderNum.subtract(planassNums));
          soPickupplan.setPlanassnum(orderNum.subtract(planassNums));
        } else {
          soPickupplan
            .setSurplusnum(((Map)list.get(0)).get("IQUANTITY") == null ? BigDecimal.ZERO : 
            BigDecimal.valueOf(
            Double.parseDouble(((Map)list.get(0)).get(
            "IQUANTITY").toString())));
          soPickupplan
            .setPlanassnum(((Map)list.get(0)).get("IQUANTITY") == null ? BigDecimal.ZERO : 
            BigDecimal.valueOf(
            Double.parseDouble(((Map)list.get(0)).get(
            "IQUANTITY").toString())));
        }
      }
    }
    req.setAttribute("soPickupplanPage", soPickupplan);
    return new ModelAndView("com/supply/pickupplan/soPickupplan-add");
  }

  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(SoPickupplanEntity soPickupplan, HttpServletRequest req)
  {
    String roleCodes = ResourceUtil.getSessionRoleCode();
    if (StringUtil.isNotEmpty(soPickupplan.getId())) {
      soPickupplan = (SoPickupplanEntity)this.soPickupplanService.getEntity(
        SoPickupplanEntity.class, soPickupplan.getId());
      req.setAttribute("soPickupplanPage", soPickupplan);
    }
    if ((roleCodes.contains("admin")) || (roleCodes.contains("supper"))) {
      return new ModelAndView("com/supply/pickupplan/soPickupplan-update");
    }
    return new ModelAndView(
      "com/supply/pickupplan/soPickupplan-update-new");
  }

  @RequestMapping(params={"soPickupplanBList"})
  public ModelAndView soPickupplanBList(SoPickupplanEntity soPickupplan, HttpServletRequest req)
  {
    Object id0 = soPickupplan.getId();

    String hql0 = "from SoPickupplanBEntity where 1 = 1 AND fK_ID = ? ";
    try {
      List soPickupplanBEntityList = this.systemService
        .findHql(hql0, new Object[] { id0 });
      req.setAttribute("soPickupplanBList", soPickupplanBEntityList);
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    return new ModelAndView("com/supply/pickupplanb/soPickupplanBList");
  }
  /**
   * 导出excel
   * 
   * @param request
   * @param response
   */
  @RequestMapping(params = "exportXls")
  public String exportXls(SoPickupplanEntity soPickupplan,
	    HttpServletRequest request, HttpServletResponse response,
	    DataGrid dataGrid, ModelMap map) {
	CriteriaQuery cq = new CriteriaQuery(SoPickupplanEntity.class, dataGrid);
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
		soPickupplan);
	try {
	    // 自定义追加查询条件
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
	cq.add();
	List<SoPickupplanEntity> list = this.soPickupplanService
		.getListByCriteriaQuery(cq, false);
	List<SoPickupplanPage> pageList = new ArrayList<SoPickupplanPage>();
	if (list != null && list.size() > 0) {
	    for (SoPickupplanEntity entity : list) {
		try {
		    SoPickupplanPage page = new SoPickupplanPage();
		    MyBeanUtils.copyBeanNotNull2Bean(entity, page);
		    Object id0 = entity.getId();
		    String hql0 = "from SoPickupplanBEntity where 1 = 1 AND fK_ID = ? ";
		    List<SoPickupplanBEntity> soPickupplanBEntityList = systemService
			    .findHql(hql0, id0);
		    page.setSoPickupplanBList(soPickupplanBEntityList);
		    pageList.add(page);
		} catch (Exception e) {
		    logger.info(e.getMessage());
		}
	    }
	}
	map.put(NormalExcelConstants.FILE_NAME, "提货计划主表");
	map.put(NormalExcelConstants.CLASS, SoPickupplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("提货计划主表列表",
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
		List<SoPickupplanPage> list = ExcelImportUtil.importExcel(
			file.getInputStream(), SoPickupplanPage.class, params);
		SoPickupplanEntity entity1 = null;
		for (SoPickupplanPage page : list) {
		    page.setPickupplancode(Identify
			    .getIdent(SupplyConstants.THJH));
		    entity1 = new SoPickupplanEntity();
		    MyBeanUtils.copyBeanNotNull2Bean(page, entity1);
		    soPickupplanService.addMain(entity1,
			    page.getSoPickupplanBList());
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
	map.put(NormalExcelConstants.FILE_NAME, "提货计划主表");
	map.put(NormalExcelConstants.CLASS, SoPickupplanPage.class);
	map.put(NormalExcelConstants.PARAMS, new ExportParams("提货计划主表列表",
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
	req.setAttribute("controller_name", "soPickupplanController");
	return new ModelAndView("common/upload/pub_excel_upload");
  }
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<SoPickupplanEntity> list() { List listSoPickupplans = this.soPickupplanService
      .getList(SoPickupplanEntity.class);
    return listSoPickupplans; } 
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public ResponseEntity<?> get(@PathVariable("id") String id) {
    SoPickupplanEntity task = (SoPickupplanEntity)this.soPickupplanService.get(
      SoPickupplanEntity.class, id);
    if (task == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(task, HttpStatus.OK);
  }

  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody SoPickupplanPage soPickupplanPage, UriComponentsBuilder uriBuilder)
  {
    Set failures = this.validator
      .validate(soPickupplanPage, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(
        BeanValidators.extractPropertyAndMessage(failures), 
        HttpStatus.BAD_REQUEST);
    }

    List soPickupplanBList = soPickupplanPage
      .getSoPickupplanBList();

    SoPickupplanEntity soPickupplan = new SoPickupplanEntity();
    try {
      MyBeanUtils.copyBeanNotNull2Bean(soPickupplan, soPickupplanPage);
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    this.soPickupplanService.addMain(soPickupplan, soPickupplanBList);

    String id = soPickupplanPage.getId();
    URI uri = uriBuilder.path("/rest/soPickupplanController/" + id).build()
      .toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uri);

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  public ResponseEntity<?> update(@RequestBody SoPickupplanPage soPickupplanPage)
  {
    Set failures = this.validator
      .validate(soPickupplanPage, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(
        BeanValidators.extractPropertyAndMessage(failures), 
        HttpStatus.BAD_REQUEST);
    }

    List soPickupplanBList = soPickupplanPage
      .getSoPickupplanBList();

    SoPickupplanEntity soPickupplan = new SoPickupplanEntity();
    try {
      MyBeanUtils.copyBeanNotNull2Bean(soPickupplan, soPickupplanPage);
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
    this.soPickupplanService.updateMain(soPickupplan, soPickupplanBList);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) { SoPickupplanEntity soPickupplan = (SoPickupplanEntity)this.soPickupplanService.get(
      SoPickupplanEntity.class, id);
    this.soPickupplanService.delMain(soPickupplan);
  }

  @RequestMapping(params={"datagridU8"})
  public void datagridU8(SoPickupplanEntity poDeliveryplan, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
    throws Exception
  {
    String createBy = poDeliveryplan.getCustomername() == null ? "" : 
      poDeliveryplan.getCustomername().toString();
    String createByName = poDeliveryplan.getCustomercode() == null ? "" : 
      poDeliveryplan.getCustomercode().toString();
    if ((StringUtil.isEmpty(createBy)) && (StringUtil.isEmpty(createByName))) {
      return;
    }
    List<Map<String, Object>> list = this.soPickupplanU8Service.queryU8SoOrder(
      createBy.toString(), createByName.toString());
    CriteriaQuery cq = new CriteriaQuery(SoPickupplanEntity.class, dataGrid);
    ArrayList results = new ArrayList();
    for (Map<String, Object> map : list) {
      SoPickupplanEntity entity = new SoPickupplanEntity();
      entity.setId(map.get("cSOCode") == null ? "" : map.get("cSOCode")
        .toString());
      entity.setSoordercode(map.get("cSOCode") == null ? "" : map.get(
        "cSOCode").toString());
      entity.setSysOrgCode(map.get("DPODATE") == null ? "" : map.get(
        "DPODATE").toString());
      entity.setCustomercode(map.get("cCusCode") == null ? "" : map.get(
        "cCusCode").toString());
      entity.setCustomername(map.get("cCusName") == null ? "" : map.get(
        "cCusName").toString());
      entity.setMaterialcode(map.get("cInvCode") == null ? "" : map.get(
        "cInvCode").toString());
      entity.setMaterialname(map.get("CINVNAME") == null ? "" : map.get(
        "CINVNAME").toString());
      entity.setSpec(map.get("CINVSTD") == null ? "" : map.get("CINVSTD")
        .toString());
      entity.setStore(map.get("IQUANTITY") == null ? "" : map.get(
        "IQUANTITY").toString());
      entity.setSurplusnum(map.get("SURPLUSQUANTITY") == null ? BigDecimal.ZERO : 
        BigDecimal.valueOf(Double.parseDouble(map
        .get("SURPLUSQUANTITY").toString())));
      entity.setUnloadplace(map.get("FOUTQUANTITY") == null ? "" : map
        .get("FOUTQUANTITY").toString());
      entity.setVnote(map.get("ITAXPRICE") == null ? "" : map.get(
        "ITAXPRICE").toString());
      results.add(entity);
    }

    HqlGenerateUtil.installHql(cq, 
      poDeliveryplan);

    cq.add();
    this.soPickupplanService.getDataGridReturn(cq, true);
    dataGrid.setResults(results);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params={"querySrc"})
  public ModelAndView querySrc(SoPickupplanBEntity soPickupplan, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(soPickupplan.getId())) {
      soPickupplan = (SoPickupplanBEntity)this.soPickupplanService.getEntity(
        PoDeliveryplanEntity.class, soPickupplan.getId());
      req.setAttribute("soPickupplanPage", soPickupplan);
    }
    return new ModelAndView("com/supply/pickupplan/soPickupplanU8List");
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
    String message = "提货计划审核成功";
    try {
      for (String id : ids.split(",")) {
        SoPickupplanEntity soPickupplan = (SoPickupplanEntity)this.systemService.getEntity(
          SoPickupplanEntity.class, id);

        if (SupplyConstants.BILLSTATUS_APPROVE.equals(soPickupplan
          .getBillstatus()))
          throw new BusinessException("已审核单据，不能再次审核");
        if ((SupplyConstants.BILLSTATUS_SAVE.equals(soPickupplan
          .getBillstatus())) || 
          (StringUtil.isEmpty(soPickupplan.getBillstatus()))) {
          this.soPickupplanService
            .updateBySqlString("update so_pickupplan set billstatus = '" + 
            SupplyConstants.BILLSTATUS_APPROVE + 
            "' where id = '" + id + "'");
        }
        this.systemService.addLog(message, Globals.Log_Type_APPROVE, 
          Globals.Log_Leavel_INFO);
      }
    } catch (Exception e) {
      e.printStackTrace();
      message = "提货计划审核失败" + e.getMessage();
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
    String code = request.getParameter("code") == null ? "" : request
      .getParameter("code").toString();
    if (!StringUtil.isEmpty(code))
    {
      VehicleEntity vehicle = (VehicleEntity)this.systemService.getEntity(
        VehicleEntity.class, code);
      if (vehicle != null) {
        jsonObject.put("vehicletype", 
          vehicle.getCvehicletype() == null ? "" : vehicle
          .getCvehicletype().toString());
        jsonObject.put("drivername", vehicle.getCdriver() == null ? "" : 
          vehicle.getCdriver().toString());
        jsonObject.put("idcard", 
          vehicle.getVlicensenumber() == null ? "" : vehicle
          .getVlicensenumber().toString());
        jsonObject.put("phone", vehicle.getVdrivermobile() == null ? "" : 
          vehicle.getVdrivermobile().toString());
        jsonObject.put("loadnum", vehicle.getNload() == null ? "" : 
          vehicle.getNload().toString());
        j.setAttributes(jsonObject);
      }
    }
    return j;
  }

  private void updateCar(List<SoPickupplanBEntity> poDeliveryplanBList, String status)
  {
    String sql = "update bd_vehicle set bpm_status = '" + status + 
      "' where ";
    List carid = new ArrayList();
    for (SoPickupplanBEntity b : poDeliveryplanBList) {
      carid.add(b.getVehiclelicense());
    }
    sql = sql + JeecgSqlUtil.buildSqlForIn("id", (String[])carid.toArray(new String[0]));
    this.systemService.updateBySqlString(sql);
  }

  @RequestMapping(params={"queryByCarrier"})
  @ResponseBody
  public AjaxJson queryByCarrier(HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    Map jsonObject = new HashMap();
    String code = request.getParameter("code") == null ? "" : request
      .getParameter("code").toString();
    String hql = "select ID, VEHICLELICENSE\n  from bd_vehicle\n where bd_vehicle.vehiclelicense not in\n       (select bd_vehicle.vehiclelicense\n          from so_pickupplan\n          left join so_pickupplan_b\n            on so_pickupplan.id = so_pickupplan_b.fk_id\n          inner join bd_vehicle\n            on so_pickupplan_b.vehiclelicense = bd_vehicle.id\n         where so_pickupplan.expiredate <= trunc(sysdate)\n           )\n   and bd_vehicle.vehiclelicense not in\n       (select bd_vehicle.vehiclelicense\n          from po_deliveryplan\n          left join po_deliveryplan_b\n            on po_deliveryplan.id = po_deliveryplan_b.fk_id\n          inner join bd_vehicle\n            on po_deliveryplan_b.vehiclelicense = bd_vehicle.id\n         where po_deliveryplan.expiredate <= trunc(sysdate)\n           ) and bsealflag <> 'Y' and ccarrier = '" + 
      code + "'";
    if (!StringUtil.isEmpty(code))
    {
      List vehicles = this.systemService.findObjForJdbc(hql, 1, 
        10000, VehicleEntity.class);
      if (vehicles != null) {
        for (int i = 0; i < vehicles.size(); i++) {
          jsonObject.put(((VehicleEntity)vehicles.get(i)).getId() == null ? "" : 
            ((VehicleEntity)vehicles.get(i)).getId().toString(), 
            ((VehicleEntity)vehicles.get(i)).getVehiclelicense() == null ? "" : 
            ((VehicleEntity)vehicles.get(i)).getVehiclelicense()
            .toString());
        }
        j.setAttributes(jsonObject);
      }
    }
    return j;
  }

  @RequestMapping(params={"checkPlanassnum"})
  @ResponseBody
  public AjaxJson checkPlanassnum(HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    Map jsonObject = new HashMap();
    jsonObject.put("isOK", "0");
    String planassnum = request.getParameter("planassnum") == null ? "0" : 
      request.getParameter("planassnum").toString();
    String soordercode = request.getParameter("soordercode") == null ? "" : 
      request.getParameter("soordercode").toString();
    String id = request.getParameter("id") == null ? "~" : request
      .getParameter("id").toString();
    String soordernum = request.getParameter("soordernum") == null ? "0" : request
      .getParameter("soordernum").toString();

    BigDecimal surplusnumOld = BigDecimal.ZERO;
    BigDecimal planassnumOld = BigDecimal.ZERO;
    BigDecimal surplusnumNew = BigDecimal.ZERO;
    if (StringUtil.isEmpty(planassnum)) {
      jsonObject.put("isOK", "1");
    }
    else {
      String hql = "select SURPLUSNUM,PLANASSNUM,ID from so_pickupplan where soordercode = '" + 
        soordercode + "'";
      List<SoPickupplanEntity> soPickupplanEntitys = this.systemService
        .findObjForJdbc(hql, 1, 10000, SoPickupplanEntity.class);
      if (!soPickupplanEntitys.isEmpty()) {
        BigDecimal planassnums = BigDecimal.ZERO;
        BigDecimal ordernum = BigDecimal.valueOf(Double.parseDouble(soordernum));
        for (SoPickupplanEntity entity : soPickupplanEntitys) {
          if (entity.getId().equals(id)) {
            surplusnumOld = entity.getSurplusnum() == null ? BigDecimal.ZERO : entity.getSurplusnum();
            planassnumOld = entity.getPlanassnum() == null ? BigDecimal.ZERO : entity.getPlanassnum();
            surplusnumNew = surplusnumOld.add(BigDecimal.valueOf(Double.parseDouble(planassnum)).subtract(planassnumOld));
          } else {
            planassnums = planassnums.add(entity.getPlanassnum() == null ? BigDecimal.ZERO : entity.getPlanassnum());
          }
        }

        if ((BigDecimal.valueOf(Double.parseDouble(planassnum)).compareTo(ordernum.subtract(planassnums)) > 0) || (surplusnumNew.compareTo(BigDecimal.ZERO) < 0)) {
          jsonObject.put("isOK", "1");
        }
        jsonObject.put("surplusnumNew", surplusnumNew);
      }
      else if (BigDecimal.valueOf(Double.parseDouble(planassnum)).compareTo(BigDecimal.valueOf(Double.parseDouble(soordernum))) > 0) {
        jsonObject.put("isOK", "1");
      }
    }

    j.setAttributes(jsonObject);
    return j;
  }
}