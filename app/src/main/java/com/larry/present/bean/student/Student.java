package com.larry.present.bean.student;

/**
 * @ClassName: Student
 * @Description: 数据库表student对应的entity
 */
public class Student {

    /**
     * 学生id
     */
    private String id;

    /**
     * 学生名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别
     */
    private String sexual;

    /**
     * 头像
     */
    private String portraitUrl;

    /**
     * 手机唯一标示
     */
    private String imel;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 学校id
     */
    private String schoolId;


    /**
     * 班级id
     */
    private String classId;


    /**
     * 班级职位
     */
    private String classPosition;

    public String getClassPosition() {
        return classPosition;
    }

    public void setClassPosition(String classPosition) {
        this.classPosition = classPosition;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取学生id
     *
     * @return id 学生id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置学生id
     *
     * @param id 学生id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取学生名称
     *
     * @return name 学生名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置学生名称
     *
     * @param name 学生名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     *
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号
     *
     * @return phone 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取性别
     *
     * @return sexual 性别
     */
    public String getSexual() {
        return sexual;
    }

    /**
     * 设置性别
     *
     * @param sexual 性别
     */
    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    /**
     * 获取头像
     *
     * @return portraitUrl 头像
     */
    public String getPortraitUrl() {
        return portraitUrl;
    }

    /**
     * 设置头像
     *
     * @param portraitUrl 头像
     */
    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    /**
     * 获取手机唯一标示
     *
     * @return imel 手机唯一标示
     */
    public String getImel() {
        return imel;
    }

    /**
     * 设置手机唯一标示
     *
     * @param imel 手机唯一标示
     */
    public void setImel(String imel) {
        this.imel = imel;
    }

    /**
     * 获取邮箱
     *
     * @return mail 邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 设置邮箱
     *
     * @param mail 邮箱
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * 获取学号
     *
     * @return studentNumber 学号
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * 设置学号
     *
     * @param studentNumber 学号
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}