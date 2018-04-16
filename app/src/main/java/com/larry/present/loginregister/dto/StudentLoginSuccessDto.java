package com.larry.present.loginregister.dto;



/**
 * Created by Larry-sea on 2017/3/28.
 *
 * 学生登录成功返回的dto
 *
 */
public class StudentLoginSuccessDto {
    /**
     * 学生id
     */
    private String id;

    /**
     * token
     */
    private String token;

    /**
     * 学生名称
     */
    private String name;

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
     * 班级id
     */
    private String classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 班级职位
     */
    private String classPosition;


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClassPosition() {
        return classPosition;
    }

    public void setClassPosition(String classPosition) {
        this.classPosition = classPosition;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    private String schoolId;

    /**
     * 获取学生id
     * @return id 学生id
     */
    public String getId()
    {
        return id;
    }

    /**
     * 设置学生id
     * @param id 学生id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * 获取学生名称
     * @return name 学生名称
     */
    public String getName()
    {
        return name;
    }

    /**
     * 设置学生名称
     * @param name 学生名称
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 获取手机号
     * @return phone 手机号
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * 设置手机号
     * @param phone 手机号
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * 获取性别
     * @return sexual 性别
     */
    public String getSexual()
    {
        return sexual;
    }

    /**
     * 设置性别
     * @param sexual 性别
     */
    public void setSexual(String sexual)
    {
        this.sexual = sexual;
    }

    /**
     * 获取头像
     * @return portraitUrl 头像
     */
    public String getPortraitUrl()
    {
        return portraitUrl;
    }

    /**
     * 设置头像
     * @param portraitUrl 头像
     */
    public void setPortraitUrl(String portraitUrl)
    {
        this.portraitUrl = portraitUrl;
    }

    /**
     * 获取手机唯一标示
     * @return imel 手机唯一标示
     */
    public String getImel()
    {
        return imel;
    }

    /**
     * 设置手机唯一标示
     * @param imel 手机唯一标示
     */
    public void setImel(String imel)
    {
        this.imel = imel;
    }

    /**
     * 获取邮箱
     * @return mail 邮箱
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * 设置邮箱
     * @param mail 邮箱
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    /**
     * 获取学号
     * @return studentNumber 学号
     */
    public String getStudentNumber()
    {
        return studentNumber;
    }

    /**
     * 设置学号
     * @param studentNumber 学号
     */
    public void setStudentNumber(String studentNumber)
    {
        this.studentNumber = studentNumber;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
