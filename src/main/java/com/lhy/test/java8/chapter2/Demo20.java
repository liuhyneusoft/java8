package com.lhy.test.java8.chapter2;

import com.lhy.test.java8.data.GreateList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/6/10.
 */
public class Demo20 {


    /**
     * stream 不会存储元素
     * stream 不改变原对象，产生一个新的stream
     * stream可能是延迟的，需要结果的时候才执行
     *
     * s使用：
     *1.创建stream
     * 2.将初始stream转成另一个stream的中间操作
     * 3.使用终止操作产生结果，强制延迟操作立即执行，之后stream就没用了。
     */
    public void d1(){
        List<String> str = GreateList.getStringList();
        System.out.println(str.stream().filter(v->v.length()>4).count());
        /**
         * .stream() 创建stream
         * filter 进行转换，转成一个新的stream，
         * count，collect 终止操作。
         */
        //List<String> strNew =  str.stream().filter(v->v.length()>4).collect(Collectors.toList());
        List<String> strNew =  str.stream().filter(v->v.indexOf("w")>-1).collect(Collectors.toList());

        Stream<String> s = str.stream().filter(v->v.indexOf("w")<0);
        List<String> strNew1 = s.collect(Collectors.toList());

        List<String> strNew2 =  str.parallelStream().filter(v->v.indexOf("w")>-1).collect(Collectors.toList());
        //strNew.forEach(System.out::println);
        strNew1.forEach(System.err::println);
        strNew2.forEach(System.out::println);
    }

    public void createStream(){
        //创建一个流
        Stream<String> stream = Stream.of("adadf","bgfdg","cfdsgf");
        //创建一个流
        String arr[] = {"adadf","bgfdg","cfdsgf"};
        Stream<String> stream1 = Arrays.stream(arr,1,2);
        //创建一个流
        Stream<String> echos = Stream.generate(()->"dddd");//无限stream,无数个ddd
        //echos.collect(Collectors.toList()).forEach(System.out::println);//OutOfMemoryError
        Stream<String> echos1 = Stream.generate(()->"dddd").limit(10); //截取前10个
        //echos1.collect(Collectors.toList()).forEach(System.out::println);

        //limit取前10个，skip丢掉前3个。
        Stream<Double> random =  Stream.generate(Math::random).limit(10).skip(3);
        //random.collect(Collectors.toList()).forEach(System.out::println);

        //concat 合并两个流
        Stream<Double> random1 =  Stream.generate(Math::random).limit(3);
        Stream<Double> contact = Stream.concat(random,random1);
        //contact.collect(Collectors.toList()).forEach(System.out::println);

        //distinct返回不改变原始流的顺序，取消了重复元素的新流。
        Stream<String> distinct = Stream.generate(()->"dddd").limit(10).distinct(); //截取前10个,因为都是重复的，所以distinct之后最终返回1个
        //distinct.collect(Collectors.toList()).forEach(System.out::println);

        //sort
        List<String> list = GreateList.getStringList();

        //Stream<String> sort= list.stream().sorted(Comparator.comparing((String v)->v.length()).reversed());
        Stream<String> sort= list.stream().sorted(Comparator.comparing( String::length).reversed());
        sort.collect(Collectors.toList()).forEach(System.out::println);
        //升序排序--按照对象的一个字段排序，排序的是string存的是数值。
        // List<VAssignGrid> list = grid.stream().sorted(Comparator.comparing(v->Integer.parseInt(v.getBonusAmt()))).collect(Collectors.toList());
        //倒序排序
        // List<VAssignGrid> list1 = grid.stream().sorted(Comparator.comparing((VAssignGrid v)->Integer.parseInt(v.getBonusAmt())).reversed()).collect(Collectors.toList());
    
    }
    /**
     * flatMap
     */
    public void filterMap(){
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("a1","a2","a3"));
        lists.add(Arrays.asList("b1","b2","b3"));
        lists.add(Arrays.asList("c1","c2","cc3"));
        List<Stream<String>> ls = lists.stream().map(v->v.stream()).collect(Collectors.toList());
        //flatMap 把多个stream合并成一个流
        List<String> l = lists.stream().flatMap(v->v.stream()).collect(Collectors.toList());
        ls.stream().forEach((Stream<String> v)->{List<String> lis = v.collect(Collectors.toList());lis.forEach(System.out::println);});
        l.stream().forEach(System.err::println);
    }
/**
* list to map
*/
    public void list2map(){
            // Student的id可以唯一确定一个Student
          Map<String, Student> studentMap = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));
          Map<String, String> studentMap1 = list.stream().collect(Collectors.toMap(Student::getId,Student::getName));
        
        List<Student> listValues = new ArrayList<Student>(studentMap.values()); //map values
        List<String> listKeys = new ArrayList<String>(studentMap.keySet()); //map keys
    }

    /**
        list to set
    */
    public void list2set(){
        Set<String> set = new HashSet<String>();
        set.add("123");
        set.add("456");
        List<String> setToList = new ArrayList<String>(set); //Set To List

        Set<String> listToSet = new HashSet<String>(setToList);
    }
    
    /**
        map to set
    */
    public void map2set(){
        Set<String> mapToSetKeys = new HashSet<String>(studentMap.keySet()); //map keys
        Set<Student> mapToSetValues = new HashSet<Student>(studentMap.values()); //map values

    }

    public static void main(String arg[]){
        Demo20 d=new Demo20();
        d.filterMap();
    }

}
