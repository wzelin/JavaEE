package com.xxx.javaee.jdbc;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectUtil {

    /**
     * 通过反射遍历ResultSet中的值
     * 并将值设置到对应的JavaBean对象中
     * 返回JavaBean的对象
     * 注意：不要在企业环境中使用（此代码仅供学习使用）
     *
     * @param rs
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T getInstance(ResultSet rs, Class<T> clazz){
        T t = null;
        try {
            t = (T)clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field f : fields){
                String name = f.getName();
                String colName = StrUtil.toSqlField(name);
                String setMethod = "set" + StrUtil.firstUpper(name);
                Class type = f.getType();
                Method method = clazz.getMethod(setMethod,type);

                Object value = getValue(rs,colName,type);

                method.invoke(t,value);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }


    public static Object getValue(ResultSet rs,String colName,Class type) throws SQLException {

        if(int.class == type || Integer.class == type){
            return rs.getInt(colName);
        }else if(short.class == type || Short.class == type){
            return rs.getShort(colName);
        }else if(byte.class == type || Byte.class == type){
            return rs.getByte(colName);
        }else if(long.class == type || Long.class == type){
            return rs.getLong(colName);
        }else if(float.class == type || Float.class == type){
            return rs.getFloat(colName);
        }else if(double.class == type || Double.class == type){
            return rs.getDouble(colName);
        }else if(String.class == type){
            return rs.getString(colName);
        }else if(java.sql.Date.class == type){
            return rs.getDate(colName);
        }else if(java.util.Date.class == type){
            return new java.util.Date(rs.getDate(colName).getTime());
        }

        return null;
    }

}
