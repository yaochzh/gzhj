package org.jeecgframework.tag.core.easyui;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class DictSelectTag extends TagSupport
{
  private static final long serialVersionUID = 1L;
  private String typeGroupCode;
  private String field;
  private String id;
  private String defaultVal;
  private String divClass;
  private String labelClass;
  private String title;
  private boolean hasLabel = true;
  private String type;
  private String dictTable;
  private String dictField;
  private String dictText;
  private String extendJson;
  private String readonly;
  private String dictCondition;
  private String datatype;

  @Autowired
  private static SystemService systemService;

  public String getReadonly()
  {
    return this.readonly;
  }
  public void setReadonly(String readonly) {
    this.readonly = readonly;
  }

  public String getDictCondition()
  {
    return this.dictCondition;
  }

  public void setDictCondition(String dicCondition) {
    this.dictCondition = dicCondition;
  }

  public String getDatatype()
  {
    return this.datatype;
  }

  public void setDatatype(String datatype) {
    this.datatype = datatype;
  }

  public int doStartTag()
    throws JspTagException
  {
    return 6;
  }

  public int doEndTag() throws JspTagException {
    try {
      JspWriter out = this.pageContext.getOut();
      out.print(end().toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 6;
  }

  public StringBuffer end()
  {
    StringBuffer sb = new StringBuffer();
    if (StringUtils.isBlank(this.divClass)) {
      this.divClass = "form";
    }
    if (StringUtils.isBlank(this.labelClass))
      this.labelClass = "Validform_label";
    if (this.dictTable != null) {
      List<Map<String, Object>> list = queryDic();
	if ("radio".equals(type)) {
		for (Map<String, Object> map : list) {
			radio(map.get("text").toString(), map.get("field")
					.toString(), sb);
		}
	} else if ("checkbox".equals(type)) {
		for (Map<String, Object> map : list) {
			checkbox(map.get("text").toString(), map.get("field")
					.toString(), sb);
		}
	} else if("text".equals(type)){
		for (Map<String, Object> map : list) {
			text(map.get("text").toString(), map.get("field")
					.toString(), sb);
		}
	}
      else {
        sb.append("<select name=\"" + this.field + "\"");

        readonly(sb);

        if (!StringUtils.isBlank(this.extendJson)) {
            Gson gson = new Gson();
		Map<String, String> mp = gson.fromJson(extendJson, Map.class);
		for(Map.Entry<String, String> entry: mp.entrySet()) { 
			sb.append(entry.getKey()+"=\"" + entry.getValue() + "\"");
			} 
        }
        if (!StringUtils.isBlank(this.id)) {
          sb.append(" id=\"" + this.id + "\"");

          if (this.id.contains("vehiclelicense")) {
            sb.append(" onchange= \"myChange(this.value,id)\"  onclick= \"myclick2(id)\"");
          }
          if (this.id.contains("carrier")) {
            sb.append(" onchange= \"myclick(id)\" ");
          }
        }

        sb.append(">");

        select("common.please.select", "", sb);

        for (Object mp = list.iterator(); ((Iterator)mp).hasNext(); ) { Map map = (Map)((Iterator)mp).next();
          select(map.get("text").toString(), map.get("field").toString(), sb);
        }
        sb.append("</select>");
      }
    } else {
      TSTypegroup typeGroup = (TSTypegroup)TSTypegroup.allTypeGroups.get(this.typeGroupCode.toLowerCase());
      List<TSType> types = TSTypegroup.allTypes.get(this.typeGroupCode.toLowerCase());
      if (this.hasLabel) {
        sb.append("<div class=\"" + this.divClass + "\">");
        sb.append("<label class=\"" + this.labelClass + "\" >");
      }
      if (typeGroup != null) {
        if (this.hasLabel) {
          if (StringUtils.isBlank(this.title)) {
            this.title = MutiLangUtil.getMutiLangInstance().getLang(typeGroup.getTypegroupname());
          }
          sb.append(this.title + ":");
          sb.append("</label>");
        }
        if ("radio".equals(type)) {
		for (TSType type : types) {
			radio(type.getTypename(), type.getTypecode(), sb);
		}
	} else if ("checkbox".equals(type)) {
		for (TSType type : types) {
			checkbox(type.getTypename(), type.getTypecode(), sb);
		}
	}else if ("text".equals(type)) {
		for (TSType type : types) {
			text(type.getTypename(), type.getTypecode(), sb);
		}
	}
        else {
          sb.append("<select name=\"" + this.field + "\"");

          readonly(sb);
          if (!StringUtils.isBlank(this.extendJson)) {
            Gson gson = new Gson();
            Map<String, String> mp = gson.fromJson(extendJson, Map.class);
		for(Map.Entry<String, String> entry: mp.entrySet()) { 
			sb.append(" "+entry.getKey()+"=\"" + entry.getValue() + "\"");
			} 
          }
          if (!StringUtils.isBlank(this.id)) {
            sb.append(" id=\"" + this.id + "\"");
          }
          datatype(sb);
          sb.append(">");

          select("common.please.select", "", sb);

          for (TSType type : types) {
            select(type.getTypename(), type.getTypecode(), sb);
          }
          sb.append("</select>");
        }
        if (this.hasLabel) {
          sb.append("</div>");
        }
      }
    }

    return (StringBuffer)sb;
  }

  private void text(String name, String code, StringBuffer sb)
  {
    if (code.equals(this.defaultVal))
      sb.append("<input name='" + this.field + "'" + " id='" + this.id + "' value='" + MutiLangUtil.getMutiLangInstance().getLang(name) + "' readOnly = 'readOnly' />");
  }

  private void radio(String name, String code, StringBuffer sb)
  {
    if (code.equals(this.defaultVal)) {
      sb.append("<input type=\"radio\" name=\"" + this.field + 
        "\" checked=\"checked\" value=\"" + code + "\"");
      if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + this.id + "\"");
      }

      readonly(sb);

      datatype(sb);
      sb.append(" />");
    } else {
      sb.append("<input type=\"radio\" name=\"" + this.field + "\" value=\"" + 
        code + "\"");
      if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + this.id + "\"");
      }

      readonly(sb);

      datatype(sb);
      sb.append(" />");
    }
    sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
  }

  private void checkbox(String name, String code, StringBuffer sb)
  {
    String[] values = this.defaultVal.split(",");
    Boolean checked = Boolean.valueOf(false);
    for (int i = 0; i < values.length; i++) {
      String value = values[i];
      if (code.equals(value)) {
        checked = Boolean.valueOf(true);
        break;
      }
      checked = Boolean.valueOf(false);
    }
    if (checked.booleanValue()) {
      sb.append("<input type=\"checkbox\" name=\"" + this.field + 
        "\" checked=\"checked\" value=\"" + code + "\"");
      if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + this.id + "\"");
      }

      readonly(sb);

      datatype(sb);
      sb.append(" />");
    } else {
      sb.append("<input type=\"checkbox\" name=\"" + this.field + 
        "\" value=\"" + code + "\"");
      if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + this.id + "\"");
      }

      readonly(sb);

      datatype(sb);
      sb.append(" />");
    }
    sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
  }

  private void select(String name, String code, StringBuffer sb)
  {
    if (code.equals(this.defaultVal))
      sb.append(" <option value=\"" + code + "\" selected=\"selected\">");
    else {
      sb.append(" <option value=\"" + code + "\">");
    }
    sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
    sb.append(" </option>");
  }

  private List<Map<String, Object>> queryDic()
  {
    String createBy = ResourceUtil.getSessionUserName().getUserName();
    String wheresql = "";

    if ((this.dictTable.equals("bd_vehicle")) || (this.dictTable.equals("bd_carrier")))
    {
      wheresql = " where  bsealflag <> 'Y'";
    } else if (this.dictTable.equals("t_s_depart"))
      wheresql = " where org_code like 'A05A%'";
    else {
      wheresql = " where create_By = '" + createBy + "'";
    }
    String sql = "select " + this.dictField + " as field," + this.dictText + 
      " as text from " + this.dictTable;

    if (!StringUtil.isEmpty(createBy)) {
      sql = sql + wheresql;
    }

    systemService = (SystemService)ApplicationContextUtil.getContext().getBean(
      SystemService.class);
    List list = systemService.findForJdbc(sql, new Object[0]);
    return list;
  }

  private StringBuffer datatype(StringBuffer sb)
  {
    if (!StringUtils.isBlank(this.datatype) ) {
      sb.append(" datatype=\"" + this.datatype + "\"");
    }
    return sb;
  }

  private StringBuffer readonly(StringBuffer sb)
  {
    if ((!StringUtils.isBlank(this.readonly)) && (this.readonly.equals("readonly"))) {
      if ("radio".equals(this.type)) {
        sb.append(" disable= \"disabled\" disabled=\"disabled\" ");
      }
      else if ("checkbox".equals(this.type)) {
        sb.append(" disable= \"disabled\" disabled=\"disabled\" ");
      }
      else if (!"text".equals(this.type))
      {
        sb.append(" disable= \"disabled\" disabled=\"disabled\" ");
      }
    }
    return sb;
  }

  public String getTypeGroupCode() {
    return this.typeGroupCode;
  }

  public void setTypeGroupCode(String typeGroupCode) {
    this.typeGroupCode = typeGroupCode;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDefaultVal() {
    return this.defaultVal;
  }

  public void setDefaultVal(String defaultVal) {
    this.defaultVal = defaultVal;
  }

  public String getDivClass() {
    return this.divClass;
  }

  public void setDivClass(String divClass) {
    this.divClass = divClass;
  }

  public String getLabelClass() {
    return this.labelClass;
  }

  public void setLabelClass(String labelClass) {
    this.labelClass = labelClass;
  }

  public String getField() {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isHasLabel() {
    return this.hasLabel;
  }

  public void setHasLabel(boolean hasLabel) {
    this.hasLabel = hasLabel;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDictTable() {
    return this.dictTable;
  }

  public void setDictTable(String dictTable) {
    this.dictTable = dictTable;
  }

  public String getDictField() {
    return this.dictField;
  }

  public void setDictField(String dictField) {
    this.dictField = dictField;
  }

  public String getDictText() {
    return this.dictText;
  }

  public void setDictText(String dictText) {
    this.dictText = dictText;
  }
  public String getExtendJson() {
    return this.extendJson;
  }

  public void setExtendJson(String extendJson) {
    this.extendJson = extendJson;
  }
}