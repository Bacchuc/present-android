package com.larry.present.bean.sign;

/**
 * Created by Larry-sea on 2017/3/29.
 * <p>
 * <p>
 * 学生某堂课签到的dto
 */
public class StudentCourseSignDto {


    /**
     * 学生学号
     */
    String studentNumber;

    /**
     * 学生姓名
     */
    String name;


    public String getStudentId() {
        return studentId;
    }


    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 签到状态
     */
    String signState;

    //学生id
    String studentId;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }
}
