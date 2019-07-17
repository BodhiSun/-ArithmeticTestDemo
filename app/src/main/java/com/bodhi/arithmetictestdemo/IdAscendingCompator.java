package com.bodhi.arithmetictestdemo;

import com.bodhi.arithmetictestdemo.bean.Student;

import java.util.Comparator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/17 11:38
 * desc :
 */
public class IdAscendingCompator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getAge()==o2.getAge()?0:o1.getAge()>o2.getAge()?1:-1;
    }
}
