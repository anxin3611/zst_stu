package com.zst.spring.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 10:36
 * @description 简化toString方法
 * 1、ToStringBuilder、HashCodeBuilder、EqualsBuilder、ToStringStyle、ReflectionToStringBuilder、
 * CompareToBuilder等这些类都是位于commons-lang.jar下面的，所以要使用这些类一定要导入commons-lang.jar。
 * 2、为什么要使用ToStringBuilder？
 * 系统中一般都要打印日志的，因为所有实体的toString()方法 都用的是简单的"+"，因为每"＋" 一个就会 new 一个 String 对象，这样如果系统内存小的话会暴内存（前提系统实体比较多）。
 * 使用ToStringBuilder就可以避免暴 内存这种问题的。
 */
public class BaseDO implements Serializable {
    private static final long serialVersionUID = -930075348621129577L;

    @Override
    public String toString() {
        /*ReflectionToStringBuilder类是用来实现类中的toString()方法的类，
        它采用Java反射机制（Reflection），通过reflection包中的AccessibleObject类
        绕过访问控制而直接存取对象的私有成员。因此在使用该类时，要注意运行环境的安全策略。*/
        return ToStringBuilder.reflectionToString(this);
    }
}
