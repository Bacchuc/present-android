package com.larry.present.bean.course;

/**
 * Created by Larry-sea on 2017/5/13.
 *
 * 老师和课程的信息
 *
 */
public class CourseAndTeacherDto  {

    //课程名
    String courseName;
    //老师姓名
    String teacherName;
    //老师手机号
    String teacherPhone;
    //老师邮箱
    String teacherMail;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherMail() {
        return teacherMail;
    }

    public void setTeacherMail(String teacherMail) {
        this.teacherMail = teacherMail;
    }
}
