package com.autumn.clever.book.Java8实战;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;

/**
 * java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象。
 * 如果你需要定义一个Lambda，将输入对象的信息映射到输出，就可以使用这个接口（比如提取苹果的重量，或把字符串映射为它的长度）。
 *
 * @author zhangqiuying
 * @description
 * @date 2021/6/25 3:08 下午
 */
public class FunctionDemo {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("", "秋颖", "小猫", " ", "小秋秋");
        Function<String, Integer> function = (String s) -> s.length();
        List<Integer> resultList = map(list, function);
        System.out.println(resultList.toString());

        Student s1 = new Student("秋颖", 1);
        Student s2 = new Student("小猫", 2);
        List<Student> students = Lists.newArrayList(s1, s2);
        students.forEach(s -> System.out.println(s.toString()));
        Function<Student, Teacher> studentTeacherFunction = (Student s) -> {
            Teacher teacher = new Teacher();
            teacher.setName(s.getName());
            teacher.setAge(s.getAge());
            return teacher;
        };
        List<Teacher> teachers = map(students, studentTeacherFunction);
        teachers.forEach(t -> System.out.println(t.toString()));
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> resultList = Lists.newArrayList();
        for (T l : list) {
            R result = function.apply(l);
            resultList.add(result);
        }
        return resultList;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {
    private String name;
    private Integer age;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Teacher {
    private String name;
    private Integer age;
}
