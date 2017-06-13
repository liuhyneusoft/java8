package com.lhy.test.java8.chapter3;

import java.util.function.IntConsumer;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Demo30 {
    /**
     * @FunctionalInterface interface IntConsumer
     */
    public void repeat(int repeatTimes, IntConsumer com){
        for (int i=0;i<repeatTimes;i++){
            com.accept(i); // 执行一个lambda表达式
        }
    }

    public static void main(String a[]){
        Demo30 demo30 = new Demo30();
        demo30.repeat(3,(x)->{int j = x*2; System.out.println(j);});
    }


}
