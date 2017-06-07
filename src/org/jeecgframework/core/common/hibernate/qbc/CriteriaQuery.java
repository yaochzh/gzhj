package org.jeecgframework.core.common.hibernate.qbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.datatable.SortInfo;

public class CriteriaQuery
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int curPage = 1;
  private int pageSize = 10;
  private String myAction;
  private String myForm;
  private CriterionList criterionList = new CriterionList();
  private CriterionList jqcriterionList = new CriterionList();
  private int isUseimage = 0;
  private DetachedCriteria detachedCriteria;
  private static Map<String, Object> map;
  private static Map<String, Object> ordermap;
  private boolean flag = true;
  private String field = "";
  private Class entityClass;
  private List results;
  private int total;
  private List<String> alias = new ArrayList();
  private DataGrid dataGrid;
  private DataTables dataTables;

  public CriteriaQuery()
  {
  }

  public List getResults()
  {
    return this.results;
  }

  public void setResults(List results) {
    this.results = results;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public DataTables getDataTables()
  {
    return this.dataTables;
  }

  public void setDataTables(DataTables dataTables) {
    this.dataTables = dataTables;
  }

  public DataGrid getDataGrid() {
    return this.dataGrid;
  }

  public void setDataGrid(DataGrid dataGrid) {
    this.dataGrid = dataGrid;
  }

  public Class getEntityClass() {
    return this.entityClass;
  }

  public void setEntityClass(Class entityClass) {
    this.entityClass = entityClass;
  }
  public CriterionList getJqcriterionList() {
    return this.jqcriterionList;
  }

  public void setJqcriterionList(CriterionList jqcriterionList) {
    this.jqcriterionList = jqcriterionList;
  }

  public CriteriaQuery(Class c) {
    this.detachedCriteria = DetachedCriteria.forClass(c);
    map = new HashMap();
    ordermap = new HashMap();
  }

  public CriteriaQuery(Class c, int curPage, String myAction, String myForm) {
    this.curPage = curPage;
    this.myAction = myAction;
    this.myForm = myForm;
    this.detachedCriteria = DetachedCriteria.forClass(c);
  }

  public CriteriaQuery(Class c, int curPage, String myAction) {
    this.myAction = myAction;
    this.curPage = curPage;
    this.detachedCriteria = DetachedCriteria.forClass(c);
    map = new HashMap();
    ordermap = new HashMap();
  }

  public CriteriaQuery(Class entityClass, int curPage) {
    this.curPage = curPage;
    this.detachedCriteria = DetachedCriteria.forClass(entityClass);
    map = new HashMap();
  }
  public CriteriaQuery(Class entityClass, DataGrid dg) {
    this.curPage = dg.getPage();

    this.detachedCriteria = DetachedCriteria.forClass(entityClass);

    this.field = dg.getField();
    this.entityClass = entityClass;
    this.dataGrid = dg;
    this.pageSize = dg.getRows();
    map = new HashMap();
    ordermap = new HashMap();
  }
  public CriteriaQuery(Class entityClass, DataTables dataTables) {
    this.curPage = dataTables.getDisplayStart();
    String[] fieldstring = dataTables.getsColumns().split(",");
    this.detachedCriteria = 
      DetachedCriteriaUtil.createDetachedCriteria(entityClass, "start", "_table", fieldstring);

    this.field = dataTables.getsColumns();
    this.entityClass = entityClass;
    this.dataTables = dataTables;
    this.pageSize = dataTables.getDisplayLength();
    map = new HashMap();
    ordermap = new HashMap();
    addJqCriteria(dataTables);
  }

  public CriteriaQuery(Class c, int pageSize, int curPage, String myAction, String myForm)
  {
    this.pageSize = pageSize;
    this.curPage = curPage;
    this.myAction = myAction;
    this.myForm = myForm;
    this.detachedCriteria = DetachedCriteria.forClass(c);
  }

  public void add(Criterion c)
  {
    this.detachedCriteria.add(c);
  }

  public void add()
  {
    for (int i = 0; i < getCriterionList().size(); i++) {
      add(getCriterionList().getParas(i));
    }
    getCriterionList().removeAll(getCriterionList());
  }

  public void addJqCriteria(DataTables dataTables)
  {
    String search = dataTables.getSearch();
    SortInfo[] sortInfo = dataTables.getSortColumns();
    String[] sColumns = dataTables.getsColumns().split(",");
    if (StringUtil.isNotEmpty(search))
    {
      for (String string : sColumns) {
        if (string.indexOf("_") != -1)
          continue;
        this.jqcriterionList.addPara(Restrictions.like(string, "%" + search + 
          "%"));
      }

      add(getOrCriterion(this.jqcriterionList));
    }

    if (sortInfo.length > 0)
    {
      for (SortInfo sortInfo2 : sortInfo)
        addOrder(sColumns[sortInfo2.getColumnId().intValue()], sortInfo2.getSortOrder());
    }
  }

  public void createCriteria(String name)
  {
    this.detachedCriteria.createCriteria(name);
  }

  public void createCriteria(String name, String value) {
    this.detachedCriteria.createCriteria(name, value);
  }

  public void createAlias(String name, String value)
  {
    if (!this.alias.contains(name)) {
      this.detachedCriteria.createAlias(name, value);
      this.alias.add(name);
    }
  }

  public void setResultTransformer(Class class1) {
    this.detachedCriteria.setResultTransformer(Transformers.aliasToBean(class1));
  }

  public void setProjection(Property property) {
    this.detachedCriteria.setProjection(property);
  }

  public Criterion and(CriteriaQuery query, int source, int dest)
  {
    return Restrictions.and(query.getCriterionList().getParas(source), 
      query.getCriterionList().getParas(dest));
  }

  public Criterion and(Criterion c, CriteriaQuery query, int souce)
  {
    return Restrictions.and(c, query.getCriterionList().getParas(souce));
  }

  public Criterion getOrCriterion(CriterionList list)
  {
    Criterion c1 = null;
    Criterion c2 = null;
    Criterion c3 = null;
    c1 = list.getParas(0);
    for (int i = 1; i < list.size(); i++) {
      c2 = list.getParas(i);
      c3 = getor(c1, c2);
      c1 = c3;
    }
    return c3;
  }

  public Criterion getor(Criterion c1, Criterion c2)
  {
    return Restrictions.or(c1, c2);
  }

  public Criterion and(Criterion c1, Criterion c2)
  {
    return Restrictions.and(c1, c2);
  }

  public Criterion or(CriteriaQuery query, int source, int dest)
  {
    return Restrictions.or(query.getCriterionList().getParas(source), query
      .getCriterionList().getParas(dest));
  }

  public Criterion or(Criterion c, CriteriaQuery query, int source)
  {
    return Restrictions.or(c, query.getCriterionList().getParas(source));
  }

  public void or(Criterion c1, Criterion c2)
  {
    this.detachedCriteria.add(Restrictions.or(c1, c2));
  }

  public void addOrder(String ordername, SortDirection ordervalue)
  {
    ordermap.put(ordername, ordervalue);
  }

  public void setOrder(Map<String, Object> map)
  {
    for (Map.Entry entry : map.entrySet()) {
      judgecreateAlias((String)entry.getKey());
      if (SortDirection.asc.equals(entry.getValue()))
        this.detachedCriteria.addOrder(Order.asc((String)entry.getKey()));
      else
        this.detachedCriteria.addOrder(Order.desc((String)entry.getKey()));
    }
  }

  public void judgecreateAlias(String entitys)
  {
    String[] aliass = entitys.split("\\.");
    for (int i = 0; i < aliass.length - 1; i++)
      createAlias(aliass[i], aliass[i]);
  }

  public static Map<String, Object> getOrdermap()
  {
    return ordermap;
  }

  public static void setOrdermap(Map<String, Object> ordermap) {
    ordermap = ordermap;
  }

  public void eq(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != "")) {
      this.criterionList.addPara(Restrictions.eq(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void notEq(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != "")) {
      this.criterionList.addPara(Restrictions.ne(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void like(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != ""))
    {
      this.criterionList.addPara(Restrictions.like(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void gt(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != "")) {
      this.criterionList.addPara(Restrictions.gt(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void lt(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != "")) {
      this.criterionList.addPara(Restrictions.lt(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void le(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != "")) {
      this.criterionList.addPara(Restrictions.le(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void ge(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != "")) {
      this.criterionList.addPara(Restrictions.ge(keyname, keyvalue));
      if (this.flag) {
        put(keyname, keyvalue);
      }
      this.flag = true;
    }
  }

  public void in(String keyname, Object[] keyvalue)
  {
    if ((keyvalue != null) && (keyvalue.length > 0) && (keyvalue[0] != ""))
      this.criterionList.addPara(Restrictions.in(keyname, keyvalue));
  }

  public void notin(String keyname, Object[] keyvalue)
  {
    if ((keyvalue != null) && (keyvalue.length > 0) && (keyvalue[0] != ""))
      this.criterionList.addPara(Restrictions.not(Restrictions.in(keyname, keyvalue)));
  }

  public void isNull(String keyname)
  {
    this.criterionList.addPara(Restrictions.isNull(keyname));
  }

  public void isNotNull(String keyname)
  {
    this.criterionList.addPara(Restrictions.isNotNull(keyname));
  }

  public void put(String keyname, Object keyvalue)
  {
    if ((keyvalue != null) && (keyvalue != ""))
      map.put(keyname, keyvalue);
  }

  public void between(String keyname, Object keyvalue1, Object keyvalue2)
  {
    Criterion c = null;

    if ((!keyvalue1.equals(null)) && (!keyvalue2.equals(null)))
      c = Restrictions.between(keyname, keyvalue1, keyvalue2);
    else if (!keyvalue1.equals(null))
      c = Restrictions.ge(keyname, keyvalue1);
    else if (!keyvalue2.equals(null)) {
      c = Restrictions.le(keyname, keyvalue2);
    }
    this.criterionList.add(c);
  }

  public void sql(String sql) {
    Restrictions.sqlRestriction(sql);
  }

  public void sql(String sql, Object[] objects, Type[] type) {
    Restrictions.sqlRestriction(sql, objects, type);
  }

  public void sql(String sql, Object objects, Type type) {
    Restrictions.sqlRestriction(sql, objects, type);
  }

  public Integer getCurPage() {
    return Integer.valueOf(this.curPage);
  }

  public void setCurPage(Integer curPage) {
    this.curPage = curPage.intValue();
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public String getMyAction() {
    return this.myAction;
  }

  public void setMyAction(String myAction) {
    this.myAction = myAction;
  }

  public String getMyForm() {
    return this.myForm;
  }

  public void setMyForm(String myForm) {
    this.myForm = myForm;
  }

  public CriterionList getCriterionList() {
    return this.criterionList;
  }

  public void setCriterionList(CriterionList criterionList) {
    this.criterionList = criterionList;
  }

  public DetachedCriteria getDetachedCriteria() {
    return this.detachedCriteria;
  }

  public String getField() {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
    this.detachedCriteria = detachedCriteria;
  }

  public int getIsUseimage() {
    return this.isUseimage;
  }

  public void setIsUseimage(int isUseimage)
  {
    this.isUseimage = isUseimage;
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    map = map;
  }

  public boolean isFlag() {
    return this.flag;
  }

  public void setFlag(boolean flag)
  {
    this.flag = flag;
  }
}