package com.lhy.test.java8.chapter1;

import com.lhy.test.java8.data.GreateList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Demo10 extends  SuperDemo1{
    public static void main(String a[]){
        Demo10 demo = new Demo10();
        demo.compareable8();
    }

    //常用匿名类方式
    public void runnables(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("haha");
            }
        });
        thread.start();
    }

    public void sysout(){System.out.println("sysout woqule");}

    //lambda方式
    public void runnables8(){
        //Thread thread = new Thread(()->{ System.out.println("woqule");});
        //Thread thread = new Thread(this::sysout);     //方法引用
        Thread thread = new Thread(super::sysout);     //方法引用
        thread.start();
    }

    //lambda方式

    /** 如果使用{} 那么要有；分号， 有返回值的要有return
     * x->System.out.println(x)  ----- (x)->{System.out.println(x);}
     * (String v1,String v2) ->  v1.compareTo(v2)  ----  (String v1,String v2) -> {return v1.compareTo(v2);}
     * String::compareTo --- ( v1, v2) -> {return v1.compareTo(v2);}
     */
    public void compareable8(){
        List<String> list = GreateList.getStringList();
        list.stream().forEach(x->System.out.println(x));
        //Collections.sort(list,(String v1,String v2) ->  v1.compareTo(v2));
        //Collections.sort(list,(String v1,String v2) -> {return v1.compareTo(v2);});
        //Collections.sort(list,( v1, v2) -> {return v1.compareTo(v2);});
        //Collections.sort(list,String::compareTo); //方法引用，第一个参数会成为执行方法的对象
        Collections.sort(list, Comparator.comparing(String::length));//接口加入了静态方法
        list.stream().forEach((x)->{System.out.println(x);});
        list.stream().forEach( System.err::println );//方法引用
    }

}
