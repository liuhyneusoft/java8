package com.lhy.test.java8.chapter2;

import com.lhy.test.java8.data.GreateList;
import com.lhy.test.java8.data.User8;
import com.lhy.test.java8.data.User9;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo21 {
    /**
     * 聚合方法都是终止操作，一个流执行了终止操作不能再执行其他操作了。
     * count，min,max
     */
    public void aggregate(){
        List<String> list = GreateList.getStringList();
        //聚合函数返回Optional，可以有值也可能没有。
        //Optional<String> op = list.stream().max((v1, v2)->v1.compareToIgnoreCase(v2));
        Optional<String> op = list.stream().max(String::compareToIgnoreCase);
        if(op.isPresent()){
            //System.out.println(op.get());
        }
        //findFirst 匹配找到的第一个
        Optional<String> op1 = list.stream().filter(s->s.startsWith("g")).findFirst();
        if(op1.isPresent()){
            ///System.err.println(op1.get());
        }

        //findAny 任意一个
        Optional<String> op2 = list.stream().parallel().filter(s->s.indexOf("n")>-1).findAny();
        if(op2.isPresent()){
            System.err.println(op2.get());
        }
        //anyMatch
        boolean b = list.stream().parallel().anyMatch(s -> s.startsWith("l"));
        System.err.println(b);
    }

    /**
     * optional<t> 对T类型的封装。
     * ifPresent 可以接收一个函数
     */
    public void optional(){
        List<String> ret = new ArrayList<>();
        List<String> list = GreateList.getStringList();
        Optional<String> op1 = list.stream().filter(s->s.startsWith("g")).findFirst();
        op1.ifPresent(v->ret.add(v));  ///这里不能更改原始的集合list，只能修改另一个结合
        op1.ifPresent(ret::add);///这里不能更改原始的集合list，只能修改另一个结合
        //ret.forEach(System.out::println);


        //.orElse 用于默认值, orElseGet用默认函数返回默认值,orElseThrow没有值的时候不再是默认值而是抛异常。
        Optional<String> op2 = list.stream().parallel().filter(s->s.indexOf("ff")>-1).findAny();
        //String string = op2.orElse("xxx");
        String string = op2.orElseGet(()->{return "hahahah";});
        //String stringE = op2.orElseThrow(RuntimeException::new);
        System.out.println(string);

        //创建Optional<T>使用Optional.empty() or Optional.of(T)
        Optional<String> optional = "a".equals("a")? Optional.empty():Optional.of("gaga");
        if(optional.isPresent()){
            System.out.println(optional.get());
        }

    }

    public void reduce(){
        List<Integer> list = Arrays.asList(1,3,5,7,8,9);
        //x+y这个操作起点是0
        Optional<Integer> optional= list.stream().reduce((x,y)->x+y);
        optional.ifPresent(System.out::println);
        //x+y这个操作起点是3， 3+（x+y）开始
        Integer integer= list.stream().reduce(3,(x,y)->x+y);
        System.out.println(integer);


        List<String> lists = Arrays.asList("aaa","ccc","ddd","4","dgadaf");
        Integer integer1 = lists.stream().reduce(0,(total,str)->total+str.length(),(t1,t2)->t1+t2);
        System.out.println(integer1);

        List<User8> user8s = new ArrayList<>();
        user8s.add(new User8("zhangsan","23"));
        user8s.add(new User8("aaa","23"));
        user8s.add(new User8("bbb","23"));
        user8s.stream().map(x->x.getName().length()). //通过map把List<User8> 转成 List<Integer>
                reduce((y,z)->y+z)          //聚合函数求List<Integer>的合计的 Optional<Integer>对象
                .ifPresent(System.out::println);

    }

    /**
     * collect 收集结果
     *
     */
    public  void collectResult(){
        List<String> lists = Arrays.asList("aaa","ccc","ddd","4","dgadaf");
        //收集到hashSet中
        HashSet<String> set = lists.stream().filter(x->x.length()>2).collect(HashSet::new,HashSet::add,HashSet::addAll);
        set.forEach(System.out::println);

        //收集到list
        List<String> list1 = lists.stream().filter(x->x.length()>2).collect(Collectors.toList());
        //收集到set
        Set<String> set1 = lists.stream().filter(x->x.length()>2).collect(Collectors.toSet());
       //收集到到set的类型
        TreeSet<String> set2 = lists.stream().filter(x->x.length()>2).collect(Collectors.toCollection(TreeSet::new));
        //收集到一个String
        String result = lists.stream().filter(x->x.length()>2).collect(Collectors.joining(","));
        System.out.println(result);
    }

    /**
     * 流的结果聚合为 总和，最大，最小，平均值
     */
    public void sumavg(){
        List<Integer> list = Arrays.asList(1,3,5,7,8,9);
        IntSummaryStatistics summary = list.stream().collect(Collectors.summarizingInt(v->v));
        double i = summary.getMax();
        double j = summary.getMin();
        double z = summary.getAverage();
        double y = summary.getSum();
        System.out.println(i+"-"+j+"-"+z+"-"+y);

    }

    /**
     * 集合转成map
     */
    public void maps(){
        List<User8> user8s = new ArrayList<>();
        user8s.add(new User8("zhangsan","12"));
        user8s.add(new User8("aaa","23"));
        user8s.add(new User8("bbb","34"));
        //  List<User8> ---- Map<String,String>
        Map<String,String> map = user8s.stream().collect(Collectors.toMap(User8::getName,User8::getAge));
        map.forEach((k,v)->{System.out.println(k+"-"+v);});
        //  List<User8> ----  Map<String,User8>
        Map<String,User8> map1 = user8s.stream().collect(Collectors.toMap(User8::getName, Function.identity()));
        map1.forEach((k,v)->{System.out.println(k+"-"+v.getName());});
    }

    // 用User8的age属性分组 得到 List<User8> -- Map<String,List<User8>>
    public   void groups(){
        List<User8> user8s = new ArrayList<>();
        user8s.add(new User8("zhangsan","12"));
        user8s.add(new User8("aaa","23"));
        user8s.add(new User8("bbb","12"));

        Map<String,List<User8>> map = user8s.stream().collect(Collectors.groupingBy(User8::getAge));
        //map.forEach((k,v)->{System.out.println(k+"-"+v.size());});

        Map<Boolean,List<User8>> map1 = user8s.stream().collect(Collectors.groupingBy(u->Integer.parseInt(u.getAge())>20));
        //map1.forEach((k,v)->{System.out.println(k+"-"+v.size());});
        //不返回list返回set
        Map<String,Set<User8>> map2 = user8s.stream().collect(Collectors.groupingBy(User8::getAge,Collectors.toSet()));
        //返回集合个数
        Map<String,Long> map3 = user8s.stream().collect(Collectors.groupingBy(User8::getAge,Collectors.counting()));

        List<User9> user9s = new ArrayList<>();
        user9s.add(new User9("zhangsan",12));
        user9s.add(new User9("aaa",23));
        user9s.add(new User9("aaa",43));

        Map<String,Set<User9>> ma4 = user9s.stream().collect(Collectors.groupingBy(User9::getName,Collectors.toSet()));
        //ma4.forEach((k,v)->{System.out.println(k+"-"+v);});

        Map<String,IntSummaryStatistics> ma5 = user9s.stream().collect(Collectors.groupingBy(User9::getName,Collectors.summarizingInt(User9::getAge)));
        //用name分组，并且每个组只保留年龄最大的。 minBy最小的
        Map<String,Optional<User9>> ma6 =   user9s.stream().collect(Collectors.groupingBy(User9::getName, Collectors.maxBy(Comparator.comparing(User9::getAge))));

System.out.println();
       // Map<String,Optional<User9>> ma6 =   user9s.stream().collect(Collectors.groupingBy(User9::getName, Collectors.maxBy(Comparator.comparing(User9::getAge))));
    }

    public static void main(String a[]){
        Demo21 demo21 = new Demo21();
        demo21.groups();
    }
}
