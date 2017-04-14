package com.cmc.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import com.cmc.user.facade.entity.User;

/**
 * java reflection demo.
 * @author Thomas Lee
 * @version 2017年2月16日 下午3:20:02
 */
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        String clazzQualifier = "com.cmc.user.facade.entity.User";
        // new ReflectionDemo().printFields("com.cmc.user.facade.entity.User");
        // new ReflectionDemo().printClazz(clazzQualifier);
        new ReflectionDemo().printInstance(clazzQualifier);
    }

    /**
     * 打印类对象.
     * @param clazzQualifier
     * @throws ClassNotFoundException
     * @author Thomas Lee
     * @version 2017年4月14日 下午3:41:19
     */
    public void printClazz(String clazzQualifier) throws ClassNotFoundException {
        // 方法一；通过类的方法forName()获取
        Class<?> clazz = Class.forName(clazzQualifier);

        // 方法二：通过类直接获取
        // Class<?> clazz = User.class;

        // 方法三：通过对象获取
        // User user = new User();
        // Class<?> clazz = user.getClass();
        this.print(clazz.toString());
    }

    /**
     * 打印类的实例
     * @param clazzQualifier
     * @throws ClassNotFoundException
     * @author Thomas Lee
     * @version 2017年4月14日 下午3:44:50
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     */
    public void printInstance(String clazzQualifier) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // 方法一：通过类对象调用newInstance()方法
        Class<?> clazz = Class.forName(clazzQualifier);
        @SuppressWarnings("unused")
        User user = (User) clazz.newInstance();
        this.print(clazz.newInstance().toString());

        // 方法二：通过类对象的getConstructor()或getDeclaredConstructor()方法获得构造器（Constructor）对象并调用其newInstance()方法创建对象
        this.print(String.class.getConstructor(String.class).newInstance("hello"));
        this.print(String.class.getDeclaredConstructor(String.class).newInstance("hello"));

    }

    /**
     * 打印类的属性.
     * <p>
     * Class: Instances of the class Class represent classes and interfaces in a running Java application.
     * </p>
     * @param clazzQualifier 限定名称
     * @throws ClassNotFoundException
     * @author Thomas Lee
     * @version 2017年2月16日 下午5:35:43
     * @version 2017年4月14日 下午3:17:51
     */
    public void printFields(String clazzQualifier) throws ClassNotFoundException {
        // 第一种方式；通过类的方法forName()获取
        Class<?> clazz = Class.forName(clazzQualifier);

        // 第二种方式：通过类直接获取
        // Class<?> clazzUser = User.class;

        // 第三种方式：通过对象获取
        // User user = new User();
        // Class<?> clazzUser = user.getClass();

        Field[] classFields = clazz.getDeclaredFields();

        StringBuilder sb = new StringBuilder();
        sb.append(Modifier.toString(clazz.getModifiers()) + " class " + clazz.getSimpleName() + " {\n");
        for (Field userField : classFields) {
            sb.append("\t" + Modifier.toString(userField.getModifiers()) + " " + userField.getType().getSimpleName() + " " + userField.getName() + ";\n");
        }
        sb.append("}");

        this.print(sb.toString());
    }

    /**
     * 打印内容.
     * @param content
     * @author Thomas Lee
     * @version 2017年4月14日 下午3:12:48
     */
    private void print(String content) {
        System.out.println(content);
    }

}