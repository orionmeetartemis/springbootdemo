package com.example.demo;

import com.example.demo.util.PageUtils;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewTest {
    public static void main(String[] args) {
        B b1 = new B();
        A a = new A();
        a.setS1("s1");
        a.setL(12345L);

        BeanUtils.copyProperties(a,b1);

        System.err.println(a);
        Long l = 1234567890L;
        ArrayList<Long> list11 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list11.add(1111111111111L);
        list11.add(211111111111L);
        list2 = (ArrayList<String>) list11.clone();
        System.err.println(list2);

        String s = l.toString();
        System.err.println(s);
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int year1 = localDate.get(ChronoField.YEAR);
        Month month = localDate.getMonth();
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        int day = localDate.getDayOfMonth();
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK);

        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6,7,8,9,10);
        PageUtils.Page<Integer> integerPage = PageUtils.buildByPageNum(list, 1, 3);
        list.stream().filter(integer -> {
            boolean b = integer > 3;
            return b;
        }).forEach(System.out::println);

        List<User> userList = Lists.newArrayList(
                new User().setId("A").setName("张三"),
                new User().setId("B").setName("李四"),
                new User().setId("C").setName("王五")
        );
        Map<String, String> map1 = userList.stream().collect(Collectors.toMap(User::getId, User::getName));
        Map<String, User> map2 = userList.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        Map<String, User> map3 = userList.stream().collect(Collectors.toMap(User::getId, t -> t));
        Map<String, String> map4 = userList.stream().collect(Collectors.toMap(User::getId,User::getName,(v1,v2) -> v1 + v2));


        Optional<String> strOptional = Optional.of("jay@huaxiao");
        strOptional.ifPresentOrElse(System.out::println, () -> System.out.println("Null"));

        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
        addList(integers);
        System.out.println(integers);

        Stream<String> stream = Stream.of("hello", "felord.cn");
        List<String> list1 = stream.peek(System.out::println).collect(Collectors.toList());

    }

    public static void addList(List<Integer> list){
        list.add(111);
    }
}

@Data
@Accessors(chain = true)//使用chain属性，setter方法返回当前对象
class User {
    private String id;
    private String name;

}

@Data
class A{
    Long l;
    String s1;
}

@Data
class B{
    String l;
    String s1;
}
