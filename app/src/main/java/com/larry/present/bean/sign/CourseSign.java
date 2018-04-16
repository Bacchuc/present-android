/**
 * 北京红云融通技术有限公司
 * 日期：$$Date$$
 * 作者：$$Author$$
 * 版本：$$Rev$$
 * 版权：All rights reserved.
 */
package com.larry.present.bean.sign;


/**
 * @ClassName: CourseSign
 * @Description: 数据库表course_sign对应的entity
 */
public class CourseSign {
    /**
     * id
     */
    //@UUID
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 签到类型
     */
    private String signStartType;

    /**
     * 老师id
     */
    private String teacherId;

    /**
     * 数据状态
     */
    private String dataState;

    /**
     * 签到有效时间
     */
    private int validOfTime;

    /**
     * 获取id
     *
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取课程id
     *
     * @return courseId 课程id
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * 设置课程id
     *
     * @param courseId 课程id
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取创建时间
     *
     * @return createTime 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取签到类型
     *
     * @return signStartType 签到类型
     */
    public String getSignStartType() {
        return signStartType;
    }

    /**
     * 设置签到类型
     *
     * @param signStartType 签到类型
     */
    public void setSignStartType(String signStartType) {
        this.signStartType = signStartType;
    }

    /**
     * 获取老师id
     *
     * @return teacherId 老师id
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 设置老师id
     *
     * @param teacherId 老师id
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取数据状态
     *
     * @return dataState 数据状态
     */
    public String getDataState() {
        return dataState;
    }

    /**
     * 设置数据状态
     *
     * @param dataState 数据状态
     */
    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    /**
     * 获取签到有效时间
     *
     * @return validOfTime 签到有效时间
     */
    public int getValidOfTime() {
        return validOfTime;
    }

    /**
     * 设置签到有效时间
     *
     * @param validOfTime 签到有效时间
     */
    public void setValidOfTime(int validOfTime) {
        this.validOfTime = validOfTime;
    }
}