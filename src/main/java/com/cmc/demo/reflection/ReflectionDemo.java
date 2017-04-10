package com.cmc.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Java Reflection 实例
 * 
 * @author Thomas Lee
 * @version 2017年2月16日 下午3:20:02
 */
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        new ReflectionDemo().printClass("com.cmc.model.user.User");
    }

    /**
     * 打印Class
     * 
     * @author Thomas Lee
     * @version 2017年2月16日 下午5:35:43
     */
    public void printClass(String clazzQualifier) throws ClassNotFoundException {

        /**
         * 第一种方式；通过类的方法forName()获取
         */
        Class<?> clazzUser = Class.forName(clazzQualifier);

        /**
         * 第二种方式：通过类直接获取
         * Class<?> clazzUser = User.class;
         */

        /**
         * 第三种方式：通过对象获取
         * User user = new User();
         * Class<?> clazzUser = user.getClass(); 
         */
        Field[] userFields = clazzUser.getDeclaredFields();

        StringBuilder sb = new StringBuilder();
        sb.append(Modifier.toString(clazzUser.getModifiers()) + " class " + clazzUser.getSimpleName() + " {\n");
        for (Field userField : userFields) {
            sb.append("\t" + Modifier.toString(userField.getModifiers()) + " " + userField.getType().getSimpleName() + " " + userField.getName() + ";\n");
        }
        sb.append("}");

        this.out(sb.toString());
    }

    private void out(String content) {
        System.out.print(content);
    }

}