package com.larry.present.bean.sign;

/**
 * Created by Larry-sea on 2017/3/23.
 *
 *
 * 学生某门课程的一学期的签到记录汇总
 *
 *
 */
public class StudentSignInfoOfTermDto {


    /**
     * 学生学号
     */
    String studentNumber;
    /*
    * 学生姓名
    * */
    String name;
    /*
    * 签到次数
    * */
    String sign;
    /*
    *病假
    * */
    String sickLeave;
    /*
    * 缺勤
    *
    * */
    String absence;


    String signState;

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(String sickLeave) {
        this.sickLeave = sickLeave;
    }

    public String getAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }
}
