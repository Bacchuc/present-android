
package com.larry.present.bean.teacher;

/**
 * @ClassName: Teacher
 * @Description: 数据库表teacher对应的entity
 */
public class Teacher {
    /**
     * 用户的唯一id
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 学校id
     */
    private String schoolId;

    /**
     * 邮箱
     */
    private String mail;


    /*
    * 手机号
    *
    * */

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户的唯一id
     *
     * @return id 用户的唯一id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置用户的唯一id
     *
     * @param Id 用户的唯一id
     */
    public void setId(String Id) {
        this.id = Id;
    }

    /**
     * 获取用户名
     *
     * @return name 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户密码
     *
     * @return password 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取学校id
     *
     * @return schoolId 学校id
     */
    public String getSchoolId() {
        return schoolId;
    }

    /**
     * 设置学校id
     *
     * @param schoolId 学校id
     */
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
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
}