package com.larry.present.bean.course;

/**
 * @ClassName: Course
 * @Description: 数据库表course对应的entity
 */
public class Course {
    /**
     * 课程id
     */

    private String id;

    /**
     * 课程名
     */
    private String courseName;


    /**
     * 获取课程id
     *
     * @return id 课程id
     */
    public String getId() {
        return id;
    }


    /**
     * 设置课程id
     *
     * @param id 课程id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取课程名
     *
     * @return courseName 课程名
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置课程名
     *
     * @param courseName 课程名
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}