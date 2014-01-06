package com.rover12421.shaka.apktool.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by rover12421 on 1/5/14.
 * 反射静态库
 */
public class ReflectUtil {

    private static Class[] getObjesType(Object[] objects) {
        Class[] clazzes = new Class[objects.length];
        for (int i = 0; i < clazzes.length; i++) {
            clazzes[i] = objects[i].getClass();
        }
        return clazzes;
    }

    public static Object newInstance(Class<?> clazz, Object ... objects) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = clazz.getDeclaredConstructor(getObjesType(objects));
        constructor.setAccessible(true);
        return constructor.newInstance(objects);
    }

    public static Field getField(Class<?> clazz, String name) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static Field getField(Object obj, String name) throws NoSuchFieldException {
        return getField(obj.getClass(), name);
    }

    public static void setFieldValue(Object obj, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(obj, name);
        field.set(obj, value);
    }

    /**
     * 设置类中静态字段值
     * @param clazz
     * @param name
     * @param value
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setFieldValue(Class<?> clazz, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(clazz, name);
        field.set(clazz, value);
    }

    public static Object getFieldValue(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(obj, name);
        return field.get(obj);
    }

    /**
     * 获取类中静态字段的值
     * @param clazz
     * @param name
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Class<?> clazz, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(clazz, name);
        return field.get(clazz);
    }

    public static Method getMethod(Class<?> clazz, String name, Class ... classes) throws NoSuchMethodException {
        Method method = clazz.getDeclaredMethod(name, classes);
        method.setAccessible(true);
        return method;
    }

    public static Method getMethod(Class<?> clazz, String name, Object ... objects) throws NoSuchMethodException {
        return getMethod(clazz, name, getObjesType(objects));
    }

    public static Method getMethod(Object obj, String name, Class ... classes) throws NoSuchMethodException {
        return getMethod(obj.getClass(), name, classes);
    }

    public static Method getMethod(Object obj, String name, Object ... objects) throws NoSuchMethodException {
        return getMethod(obj.getClass(), name, objects);
    }

    public static Object invokeMethod(Object obj, String name, Object ... objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(obj, name, objects);
        return method.invoke(obj, objects);
    }

    public static Object invokeMethod(Class<?> clazz, String name, Object ... objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(clazz, name, objects);
        return method.invoke(null, objects);
    }
}
