package org.jeecgframework.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class MyBeanUtils extends PropertyUtilsBean
{
  private static void convert(Object dest, Object orig)
    throws IllegalAccessException, InvocationTargetException
  {
    if (dest == null) {
      throw new IllegalArgumentException(
        "No destination bean specified");
    }
    if (orig == null) {
      throw new IllegalArgumentException("No origin bean specified");
    }

    if ((orig instanceof DynaBean)) {
      DynaProperty[] origDescriptors = 
        ((DynaBean)orig).getDynaClass().getDynaProperties();
      for (int i = 0; i < origDescriptors.length; i++) {
        String name = origDescriptors[i].getName();
        if (PropertyUtils.isWriteable(dest, name)) {
          Object value = ((DynaBean)orig).get(name);
          try {
            getInstance().setSimpleProperty(dest, name, value);
          }
          catch (Exception localException)
          {
          }
        }
      }

    }
    else if ((orig instanceof Map)) {
      Iterator names = ((Map)orig).keySet().iterator();
      while (names.hasNext()) {
        String name = (String)names.next();
        if (PropertyUtils.isWriteable(dest, name)) {
          Object value = ((Map)orig).get(name);
          try {
            getInstance().setSimpleProperty(dest, name, value);
          }
          catch (Exception localException1)
          {
          }
        }

      }

    }
    else
    {
      PropertyDescriptor[] origDescriptors = 
        PropertyUtils.getPropertyDescriptors(orig);
      for (int i = 0; i < origDescriptors.length; i++) {
        String name = origDescriptors[i].getName();

        if ("class".equals(name)) {
          continue;
        }
        if ((!PropertyUtils.isReadable(orig, name)) || 
          (!PropertyUtils.isWriteable(dest, name))) continue;
        try {
          Object value = PropertyUtils.getSimpleProperty(orig, name);
          getInstance().setSimpleProperty(dest, name, value);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
        }
        catch (Exception localException2)
        {
        }
      }
    }
  }

  public static void copyBeanNotNull2Bean(Object databean, Object tobean)
    throws Exception
  {
    PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(databean);
    for (int i = 0; i < origDescriptors.length; i++) {
      String name = origDescriptors[i].getName();

      if ("class".equals(name)) {
        continue;
      }
      if ((!PropertyUtils.isReadable(databean, name)) || (!PropertyUtils.isWriteable(tobean, name))) continue;
      try {
        Object value = PropertyUtils.getSimpleProperty(databean, name);
        if (value != null)
          getInstance().setSimpleProperty(tobean, name, value);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
      }
      catch (Exception localException)
      {
      }
    }
  }

  public static void copyBean2Bean(Object dest, Object orig)
    throws Exception
  {
    convert(dest, orig);
  }

  public static void copyBean2Map(Map map, Object bean) {
    PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
    for (int i = 0; i < pds.length; i++)
    {
      PropertyDescriptor pd = pds[i];
      String propname = pd.getName();
      try {
        Object propvalue = PropertyUtils.getSimpleProperty(bean, propname);
        map.put(propname, propvalue);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
    }
  }

  public static void copyMap2Bean(Object bean, Map properties)
    throws IllegalAccessException, InvocationTargetException
  {
    if ((bean == null) || (properties == null)) {
      return;
    }

    Iterator names = properties.keySet().iterator();
    while (names.hasNext()) {
      String name = (String)names.next();

      if (name == null) {
        continue;
      }
      Object value = properties.get(name);
      try {
        Class clazz = PropertyUtils.getPropertyType(bean, name);
        if (clazz == null) {
          continue;
        }
        String className = clazz.getName();
        if ((className.equalsIgnoreCase("java.sql.Timestamp")) && (
          (value == null) || (value.equals(""))))
        {
          continue;
        }
        getInstance().setSimpleProperty(bean, name, value);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
    }
  }

  public static void copyMap2Bean_Nobig(Object bean, Map properties)
    throws IllegalAccessException, InvocationTargetException
  {
    if ((bean == null) || (properties == null)) {
      return;
    }

    Iterator names = properties.keySet().iterator();
    while (names.hasNext()) {
      String name = (String)names.next();

      if (name == null) {
        continue;
      }
      Object value = properties.get(name);

      name = name.toLowerCase();
      try {
        if (value == null) {
          continue;
        }
        Class clazz = PropertyUtils.getPropertyType(bean, name);
        if (clazz == null) {
          continue;
        }
        String className = clazz.getName();

        if (className.equalsIgnoreCase("java.util.Date")) {
          value = new Date(((Timestamp)value).getTime());
        }
        if (className.equalsIgnoreCase("java.lang.Double")) {
          value = new Double(value.toString());
        }

        getInstance().setSimpleProperty(bean, name, value);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
    }
  }

  public static void copyMap2Bean(Object bean, Map properties, String defaultValue)
    throws IllegalAccessException, InvocationTargetException
  {
    if ((bean == null) || (properties == null)) {
      return;
    }

    Iterator names = properties.keySet().iterator();
    while (names.hasNext()) {
      String name = (String)names.next();

      if (name == null) {
        continue;
      }
      Object value = properties.get(name);
      try {
        Class clazz = PropertyUtils.getPropertyType(bean, name);
        if (clazz == null) {
          continue;
        }
        String className = clazz.getName();
        if ((className.equalsIgnoreCase("java.sql.Timestamp")) && (
          (value == null) || (value.equals(""))))
        {
          continue;
        }
        if ((className.equalsIgnoreCase("java.lang.String")) && 
          (value == null)) {
          value = defaultValue;
        }

        getInstance().setSimpleProperty(bean, name, value);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
    }
  }
}