package studydemo.www.doloop.com.studtydemo.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaonan on 17/1/10.
 */

public class Student {
    private String name;
    private List<Course> courseList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }


    public static Student genTestStudent1() {
        Student student = new Student();
        student.setName("学生1");

        List<Course> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
            c.setName("学生1的课程" + (i + 1));
            list.add(c);
        }
        student.setCourseList(list);
        return student;
    }

    public static Student genTestStudent2() {
        Student student = new Student();
        student.setName("学生2");

        List<Course> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Course c = new Course();
            c.setName("学生2的课程" + (i + 1));
            list.add(c);
        }
        student.setCourseList(list);
        return student;
    }

}
