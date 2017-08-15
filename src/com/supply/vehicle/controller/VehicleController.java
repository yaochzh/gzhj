package com.supply.vehicle.controller;

import com.supply.vehicle.entity.VehicleEntity;
import com.supply.vehicle.service.VehicleServiceI;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.CriterionList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
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
@RequestMapping({"/vehicleController"})
public class VehicleController extends BaseController
{
  private static final Logger logger = Logger.getLogger(VehicleController.class);

  @Autowired
  private VehicleServiceI vehicleService;

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
    return new ModelAndView("com/supply/vehicle/vehicleList");
  }

  @RequestMapping(params={"datagrid"})
  public void datagrid(VehicleEntity vehicle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {
    CriteriaQuery cq = new CriteriaQuery(VehicleEntity.class, dataGrid);

    HqlGenerateUtil.installHql(cq, vehicle, request.getParameterMap());

    cq.add();
    this.vehicleService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params={"doDel"})
  @ResponseBody
  public AjaxJson doDel(VehicleEntity vehicle, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    vehicle = (VehicleEntity)this.systemService.getEntity(VehicleEntity.class, vehicle.getId());
    this.message = "车辆档案删除成功";
    try {
      this.vehicleService.delete(vehicle);
      this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "车辆档案删除失败";
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
    this.message = "车辆档案删除成功";
    try {
      for (String id : ids.split(",")) {
        VehicleEntity vehicle = (VehicleEntity)this.systemService.getEntity(VehicleEntity.class, 
          id);

        this.vehicleService.delete(vehicle);
        this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "车辆档案删除失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"doAdd"})
  @ResponseBody
  public AjaxJson doAdd(VehicleEntity vehicle, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    this.message = "车辆档案添加成功";
    Object cusCode = ResourceUtil.getSessionUserName().getCurrentDepart().getDescription();
    Object cusName = ResourceUtil.getSessionUserName().getCurrentDepart().getDepartname();
    Object createby = ResourceUtil.getSessionUserName().getUserName();
    try
    {
      String hql = "select id\n  from bd_vehicle\n where vehiclelicense = '" + 
        vehicle.getVehiclelicense() + "' and  ccarrier = '" + vehicle.getCcarrier() + "'";
      List list = this.systemService.findListbySql(hql);
      if ((list != null) && (list.size() > 0)) {
        this.message = "车牌号已存在！";
        throw new BusinessException(this.message);
      }
      if ((vehicle.getBsealflag() == null) || ("".equals(vehicle.getBsealflag()))) {
        vehicle.setBsealflag("N");
      }
      vehicle.setDef1(cusName == null ? "" : cusName.toString());
      vehicle.setDef2(cusCode == null ? "" : cusCode.toString());
      vehicle.setVehiclelicense(vehicle.getVehiclelicense().toUpperCase());
      this.vehicleService.save(vehicle);
      this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "车辆档案添加失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"doUpdate"})
  @ResponseBody
  public AjaxJson doUpdate(VehicleEntity vehicle, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    this.message = "车辆档案更新成功";
    VehicleEntity t = (VehicleEntity)this.vehicleService.get(VehicleEntity.class, vehicle.getId());
    try {
      Object cusCode = ResourceUtil.getSessionUserName().getCurrentDepart().getDescription();
      Object cusName = ResourceUtil.getSessionUserName().getCurrentDepart().getDepartname();
      Object createby = ResourceUtil.getSessionUserName().getUserName();

      String hql = "select id\n  from bd_vehicle\n where vehiclelicense = '" + 
        vehicle.getVehiclelicense() + "' and ccarrier = '" + vehicle.getCcarrier() + "' and id <> '" + vehicle.getId() + "'";
      List list = this.systemService.findListbySql(hql);
      if ((list != null) && (list.size() > 0)) {
        this.message = "车牌号已存在！";
        throw new BusinessException(this.message);
      }

      MyBeanUtils.copyBeanNotNull2Bean(vehicle, t);
      if (t.getBsealflag() == null) {
        t.setBsealflag("N");
      }
      t.setVehiclelicense(t.getVehiclelicense().toUpperCase());
      this.vehicleService.saveOrUpdate(t);
      this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } catch (Exception e) {
      e.printStackTrace();
      this.message = "车辆档案更新失败";
      throw new BusinessException(e.getMessage());
    }
    j.setMsg(this.message);
    return j;
  }

  @RequestMapping(params={"goAdd"})
  public ModelAndView goAdd(VehicleEntity vehicle, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(vehicle.getId())) {
      vehicle = (VehicleEntity)this.vehicleService.getEntity(VehicleEntity.class, vehicle.getId());
      req.setAttribute("vehiclePage", vehicle);
    }
    return new ModelAndView("com/supply/vehicle/vehicle-add");
  }

  @RequestMapping(params={"goUpdate"})
  public ModelAndView goUpdate(VehicleEntity vehicle, HttpServletRequest req)
  {
    if (StringUtil.isNotEmpty(vehicle.getId())) {
      vehicle = (VehicleEntity)this.vehicleService.getEntity(VehicleEntity.class, vehicle.getId());
      req.setAttribute("vehiclePage", vehicle);
    }
    return new ModelAndView("com/supply/vehicle/vehicle-update");
  }

  @RequestMapping(params={"upload"})
  public ModelAndView upload(HttpServletRequest req)
  {
    req.setAttribute("controller_name", "vehicleController");
    return new ModelAndView("common/upload/pub_excel_upload");
  }

  @RequestMapping(params={"exportXls"})
  public String exportXls(VehicleEntity vehicle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    CriteriaQuery cq = new CriteriaQuery(VehicleEntity.class, dataGrid);
    HqlGenerateUtil.installHql(cq, vehicle, request.getParameterMap());
    List vehicles = this.vehicleService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
    modelMap.put("fileName", "车辆档案");
    modelMap.put("entity", VehicleEntity.class);
    modelMap.put("params", 
      new ExportParams("车辆档案列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), 
      "导出信息"));
    modelMap.put("data", vehicles);
    return "jeecgExcelView";
  }

  @RequestMapping(params={"exportXlsByT"})
  public String exportXlsByT(VehicleEntity vehicle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap)
  {
    modelMap.put("fileName", "车辆档案");
    modelMap.put("entity", VehicleEntity.class);
    modelMap.put("params", 
      new ExportParams("车辆档案列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), 
      "导出信息"));
    modelMap.put("data", new ArrayList());
    return "jeecgExcelView";
  }

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
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
				List<VehicleEntity> listVehicleEntitys = ExcelImportUtil.importExcel(file.getInputStream(),VehicleEntity.class,params);
				for (VehicleEntity vehicle : listVehicleEntitys) {
					vehicleService.save(vehicle);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
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
  public List<VehicleEntity> list() { List listVehicles = this.vehicleService.getList(VehicleEntity.class);
    return listVehicles; } 
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public ResponseEntity<?> get(@PathVariable("id") String id) {
    VehicleEntity task = (VehicleEntity)this.vehicleService.get(VehicleEntity.class, id);
    if (task == null) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(task, HttpStatus.OK);
  }
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  @ResponseBody
  public ResponseEntity<?> create(@RequestBody VehicleEntity vehicle, UriComponentsBuilder uriBuilder) {
    Set failures = this.validator.validate(vehicle, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
    }

    this.vehicleService.save(vehicle);

    String id = vehicle.getId();
    URI uri = uriBuilder.path("/rest/vehicleController/" + id).build().toUri();
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uri);

    return new ResponseEntity(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes={"application/json"})
  public ResponseEntity<?> update(@RequestBody VehicleEntity vehicle) {
    Set failures = this.validator.validate(vehicle, new Class[0]);
    if (!failures.isEmpty()) {
      return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
    }

    this.vehicleService.saveOrUpdate(vehicle);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
  @RequestMapping(value={"/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.DELETE})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) { this.vehicleService.deleteEntityById(VehicleEntity.class, id);
  }

  @RequestMapping(params={"vehicles"})
  public ModelAndView vehicles(HttpServletRequest request)
  {
    ModelAndView mv = new ModelAndView("com/supply/vehicle/vehicleChooseList");
    String carrier = oConvertUtils.getString(request.getParameter("carrier"));
    mv.addObject("ccarrier", carrier);
    return mv;
  }

  @RequestMapping(params={"datagridVehicle"})
  public void datagridVehicle(VehicleEntity vehicle, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
  {

      String ccarrier = request.getParameter("ccarrier") == null ? "" : request.getParameter("ccarrier").toString();

      if (ccarrier.isEmpty()) {
        return;
      }
    CriteriaQuery cq = new CriteriaQuery(VehicleEntity.class, dataGrid);
    String queryVehicle = 

"select bd_vehicle.vehiclelicense\n" +
"  from bd_vehicle\n" + 
" where bd_vehicle.vehiclelicense not in\n" + 
"       (select so_pickupplan_b.vehiclelicense\n" + 
"          from so_pickupplan\n" + 
"          left join so_pickupplan_b\n" + 
"            on so_pickupplan.id = so_pickupplan_b.fk_id\n" + 
"         where so_pickupplan.expiredate > trunc(sysdate)\n" + 
"           and so_pickupplan_b.vehiclelicense is not null\n" + 
"           and so_pickupplan_b.vehiclelicense in\n" + 
"               (select bd_vehicle.vehiclelicense\n" + 
"                  from bd_vehicle\n" + 
"                 where  ccarrier = '"+ccarrier+"'))\n" + 
"   and bd_vehicle.vehiclelicense not in\n" + 
"       (select po_deliveryplan_b.vehiclelicense\n" + 
"          from po_deliveryplan\n" + 
"          left join po_deliveryplan_b\n" + 
"            on po_deliveryplan.id = po_deliveryplan_b.fk_id\n" + 
"         where po_deliveryplan.expiredate > trunc(sysdate)\n" + 
"           and po_deliveryplan_b.vehiclelicense is not null\n" + 
"           and po_deliveryplan_b.vehiclelicense in\n" + 
"               (select bd_vehicle.vehiclelicense\n" + 
"                  from bd_vehicle\n" + 
"                 where ccarrier = '"+ccarrier+"'))\n" + 
"   and bsealflag <> 'Y'\n" + 
"   and ccarrier = '"+ccarrier+"'";

    List vehicleEntitys = this.systemService
      .findObjForJdbc(queryVehicle, 1, 10000, VehicleEntity.class);
    if ((!vehicleEntitys.isEmpty()) && (vehicleEntitys.size() > 0)) {
      List lss = new ArrayList();
      lss.add(((VehicleEntity)vehicleEntitys.get(0)).getVehiclelicense());
      Criterion criterion = Restrictions.in("vehiclelicense", lss);
      for (int i = 1; i < vehicleEntitys.size(); i++) {
        List ls = new ArrayList();
        ls.add(((VehicleEntity)vehicleEntitys.get(i)).getVehiclelicense());
        criterion = Restrictions.or(criterion, Restrictions.in("vehiclelicense", ls));
      }
      cq.getCriterionList().addPara(criterion);
    }
    vehicle.setCcarrier(ccarrier);

    HqlGenerateUtil.installHql(cq, vehicle);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
}