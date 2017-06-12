package com.lhy.test.java8.chapter2;

import com.lhy.test.java8.data.GreateList;
import com.lhy.test.java8.data.User8;
import com.lhy.test.java8.data.User9;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo22 {

    public void intSrem(){
        IntStream intStream = IntStream.of(1,2,3,4,5,6);

        //IntStream intStream1 = IntStream.rangeClosed(1,100);  //  1--100的整数
        IntStream intStream1 = IntStream.range(1,100); //2-99的整数
       // intStream1.forEach(System.out::println);

        //对象流--原始流
        List<String> lists = Arrays.asList("aaa","ccc","ddd","4","dgadaf");
        IntStream intStream2 = lists.stream().mapToInt(value -> value.length());
        //原始流---对象流
        Stream<Integer> stream = intStream2.boxed();
    }

    /**
     * 并行流要返回和串行流相同的结果。
     * 并行流里不要有竞争，并行时处理共享对象。
     */
    public void parallStream(){
        //并行方式1:
       //Stream<String> stream =  list.parallelStream();
        //并行方式2:
       // list.stream().parallel().filter();
    }

    public void functionMethod(){
        /** 阅读文档
         * 1. Stream<T> filter(Predicate<? super T> predicate);
         * 2.  重要的是返回值，boolean，只要lambda表达式返回boolean就可以了。方法名test是无所谓的。
         * @FunctionalInterface
           public interface Predicate<T> {
                boolean test(T t);

         */
    }



    public static void main(String a[]){
        Demo22 demo21 = new Demo22();
        demo21.intSrem();
    }
}
