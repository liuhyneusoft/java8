package com.lhy.test.java8.chapter1;

import com.lhy.test.java8.data.GreateList;
import com.lhy.test.java8.data.User8;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Demo11 extends  SuperDemo1{
    public static void main(String a[]){
        Demo11 demo = new Demo11();
        demo.construct8();
    }

    //构造器引用,User8类要有一个构造函数  User(String s)
    public void construct8(){
        List<String> list = GreateList.getStringList();
        List<User8> l8 = list.stream().map(User8::new).collect(Collectors.toList());
        l8.stream().forEach(v->System.out.println(v.getName()));
    }


}
