package com.larry.present.loginregister.dto;

/**
 * Created by Larry-sea on 2017/3/16.
 *
 *
 * 老师登录成功的返回数据dto
 *
 */
public class TeacherLoginSuccessDto {

    /**
     * 用户鉴权token
     */
    String token;
    /**
     * 用户id
     */
    String userId;
    /**
     * 用户姓名
     */
    String name;
    /**
     * 学校id
     */
    String schoolId;
    /**
     * 用户邮箱号
     */
    String mail;
    /**
     * 用户手机号
     */
    String phone;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
