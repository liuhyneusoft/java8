package com.lhy.test.java8.chapter9;

import java.util.Objects;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Demo90 {
    public static void main(String a[]){
        String str1 = null;
        String str2 = "";
        String str3 = null;
        String str4 = "test";
        System.out.println(Objects.isNull(str1)); //判断 null
        System.out.println(Objects.isNull(str2));
        System.out.println(Objects.equals(str1,str2)); //equals 比较
        System.out.println(Objects.equals(str1,str3));
        System.out.println(Objects.hashCode(str4));     //计算 hash
        System.out.println(Objects.hash(str4,"hehe","gaga"));
        System.out.println(Integer.compare(123,4));     //比较大小
        System.out.println(Integer.compare(123,4666));
    }
}
