package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.web.system.service.MutiLangServiceI;

public class ChooseNewTag extends TagSupport
{
  protected String hiddenName;
  protected String textname;
  protected String icon;
  protected String title;
  protected String url;
  protected String top;
  protected String left;
  protected String width;
  protected String height;
  protected String name;
  protected String hiddenid;
  protected Boolean isclear = Boolean.valueOf(false);
  protected String fun;
  protected String inputTextname;
  protected String langArg;
  protected String choseButName;
  protected String clearButName;
  protected Boolean isInit = Boolean.valueOf(false);

  public int doStartTag() throws JspTagException {
    return 6;
  }

  public int doEndTag() throws JspTagException {
    try {
      this.title = MutiLangUtil.doMutiLang(this.title, this.langArg);
      JspWriter out = this.pageContext.getOut();
      out.print(end().toString());
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 6;
  }

  public StringBuffer end() {
    String confirm = MutiLangUtil.getMutiLangInstance().getLang("common.confirm");
    String cancel = MutiLangUtil.getMutiLangInstance().getLang("common.cancel");
    String methodname = UUIDGenerator.generate().replaceAll("-", "");
    StringBuffer sb = new StringBuffer();
    sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + this.icon + "\" onClick=\"choose_" + methodname + StringUtil.replace("()\">{0}</a>", "{0}", this.choseButName == null ? "选择" : this.choseButName));
    if ((this.isclear.booleanValue()) && (StringUtil.isNotEmpty(this.textname))) {
      sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll_" + methodname + StringUtil.replace("();\">{0}</a>", "{0}", this.clearButName == null ? "清空" : this.clearButName));
    }
    sb.append("<script type=\"text/javascript\">");
    sb.append("var windowapi = frameElement.api, W = windowapi.opener;");
    sb.append("function choose_" + methodname + "(){");

    sb.append("var carrierid = document.getElementById(\"carrier\").value;").append("var url = ").append("'").append(this.url).append("'+carrierid;");
    if (this.isInit.booleanValue()) {
      sb.append("var initValue = ").append("$('#" + this.hiddenName + "').val();");
      sb.append("url += ").append("'&ids='+initValue;");
    }

    sb.append("if(typeof(windowapi) == 'undefined'){");
    sb.append("$.dialog({");
    sb.append("content: 'url:'+url,");
    sb.append("zIndex: 2100,");
    if (this.title != null) {
      sb.append("title: '" + this.title + "',");
    }
    sb.append("lock : true,");
    if (this.width != null)
      sb.append("width :'" + this.width + "',");
    else {
      sb.append("width :600,");
    }
    if (this.height != null)
      sb.append("height :'" + this.height + "',");
    else {
      sb.append("height :550,");
    }
    if (this.left != null)
      sb.append("left :'" + this.left + "',");
    else {
      sb.append("left :'50%',");
    }
    if (this.top != null)
      sb.append("top :'" + this.top + "',");
    else {
      sb.append("top :'50%',");
    }
    sb.append("opacity : 0.4,");
    sb.append("button : [ {");
    sb.append(StringUtil.replace("name : '{0}',", "{0}", confirm));
    sb.append("callback : clickcallback_" + methodname + ",");
    sb.append("focus : true");
    sb.append("}, {");
    sb.append(StringUtil.replace("name : '{0}',", "{0}", cancel));
    sb.append("callback : function() {");
    sb.append("}");
    sb.append("} ]");
    sb.append("});");
    sb.append("}else{");
    sb.append("$.dialog({");
    sb.append("content: 'url:'+url,");
    sb.append("zIndex: 2100,");
    if (this.title != null) {
      sb.append("title: '" + this.title + "',");
    }
    sb.append("lock : true,");
    sb.append("parent:windowapi,");
    if (this.width != null)
      sb.append("width :'" + this.width + "',");
    else {
      sb.append("width :600,");
    }
    if (this.height != null)
      sb.append("height :'" + this.height + "',");
    else {
      sb.append("height :550,");
    }
    if (this.left != null)
      sb.append("left :'" + this.left + "',");
    else {
      sb.append("left :'50%',");
    }
    if (this.top != null)
      sb.append("top :'" + this.top + "',");
    else {
      sb.append("top :'50%',");
    }
    sb.append("opacity : 0.4,");
    sb.append("button : [ {");
    sb.append(StringUtil.replace("name : '{0}',", "{0}", confirm));
    sb.append("callback : clickcallback_" + methodname + ",");
    sb.append("focus : true");
    sb.append("}, {");
    sb.append(StringUtil.replace("name : '{0}',", "{0}", cancel));
    sb.append("callback : function() {");
    sb.append("}");
    sb.append("} ]");
    sb.append("});");
    sb.append("}");
    sb.append("}");
    clearAll(sb, methodname);
    callback(sb, methodname);
    sb.append("</script>");
    return sb;
  }

  private void clearAll(StringBuffer sb, String methodname)
  {
    String[] textnames = (String[])null;
    String[] inputTextnames = (String[])null;

    if (!StringUtil.isEmpty(this.textname)) {
      textnames = this.textname.split(",");
    }

    if (StringUtil.isNotEmpty(this.inputTextname))
      inputTextnames = this.inputTextname.split(",");
    else {
      inputTextnames = textnames;
    }
    if ((this.isclear.booleanValue()) && (StringUtil.isNotEmpty(this.textname))) {
      sb.append("function clearAll_" + methodname + "(){");
      for (int i = 0; i < textnames.length; i++) {
        inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
        sb.append("if($('#" + inputTextnames[i] + "').length>=1){");
        sb.append("$('#" + inputTextnames[i] + "').val('');");
        sb.append("$('#" + inputTextnames[i] + "').blur();");
        sb.append("}");
        sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
        sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val('');");
        sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
        sb.append("}");
      }
      sb.append("$('#" + this.hiddenName + "').val(\"\");");
      sb.append("}");
    }
  }

  private void callback(StringBuffer sb, String methodname)
  {
    sb.append("function clickcallback_" + methodname + "(){");
    sb.append("iframe = this.iframe.contentWindow;");
    String[] textnames = (String[])null;
    String[] inputTextnames = (String[])null;
    if (StringUtil.isNotEmpty(this.textname))
    {
      textnames = this.textname.split(",");
      if (StringUtil.isNotEmpty(this.inputTextname))
        inputTextnames = this.inputTextname.split(",");
      else {
        inputTextnames = textnames;
      }
      for (int i = 0; i < textnames.length; i++) {
        inputTextnames[i] = inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
        sb.append("var " + textnames[i] + "=iframe.get" + this.name + "Selections('" + textnames[i] + "');\t");
        sb.append("if($('#" + inputTextnames[i] + "').length>=1){");
        sb.append("$('#" + inputTextnames[i] + "').val(" + textnames[i] + ");");
        sb.append("$('#" + inputTextnames[i] + "').blur();");
        sb.append("}");
        sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
        sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val(" + textnames[i] + ");");
        sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
        sb.append("}");
      }
    }
    if (StringUtil.isNotEmpty(this.hiddenName)) {
      sb.append("var id =iframe.get" + this.name + "Selections('" + this.hiddenid + "');");
      sb.append("if (id!== undefined &&id!=\"\"){");
      sb.append("$('#" + this.hiddenName + "').val(id);");
      sb.append("}");
    }
    if (StringUtil.isNotEmpty(this.fun))
    {
      sb.append(this.fun + "();");
    }
    sb.append("}");
  }

  public void setHiddenName(String hiddenName)
  {
    this.hiddenName = hiddenName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public void setTextname(String textname) {
    this.textname = textname;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setTop(String top) {
    this.top = top;
  }

  public void setLeft(String left) {
    this.left = left;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public void setIsclear(Boolean isclear) {
    this.isclear = isclear;
  }

  public void setHiddenid(String hiddenid) {
    this.hiddenid = hiddenid;
  }
  public void setFun(String fun) {
    this.fun = fun;
  }

  public String getInputTextname() {
    return this.inputTextname;
  }

  public void setInputTextname(String inputTextname) {
    this.inputTextname = inputTextname;
  }

  public String getLangArg() {
    return this.langArg;
  }

  public void setLangArg(String langArg) {
    this.langArg = langArg;
  }

  public void setIsInit(Boolean isInit) {
    this.isInit = isInit;
  }

  public String getChoseButName() {
    return this.choseButName;
  }

  public void setChoseButName(String choseButName) {
    this.choseButName = choseButName;
  }

  public String getClearButName() {
    return this.clearButName;
  }

  public void setClearButName(String clearButName) {
    this.clearButName = clearButName;
  }
}