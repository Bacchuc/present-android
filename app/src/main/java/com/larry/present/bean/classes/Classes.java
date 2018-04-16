package com.larry.present.bean.classes;


/**
 *
 * @ClassName: Classes
 * @Description: 数据库表classes对应的entity
 */
public class Classes
{
    /**
     * 班级id
     */
    private String id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学校id
     */
    private String schoolId;

    /**
     * 获取班级id
     * @return id 班级id
     */
    public String getId()
    {
         return id;
    }

    /**
     * 设置班级id
     * @param id 班级id
     */
    public void setId(String id)
    {
         this.id = id;
    }

    /**
     * 获取班级名称
     * @return className 班级名称
     */
    public String getClassName()
    {
         return className;
    }

    /**
     * 设置班级名称
     * @param className 班级名称
     */
    public void setClassName(String className)
    {
         this.className = className;
    }

    /**
     * 获取学校id
     * @return schoolId 学校id
     */
    public String getSchoolId()
    {
         return schoolId;
    }

    /**
     * 设置学校id
     * @param schoolId 学校id
     */
    public void setSchoolId(String schoolId)
    {
         this.schoolId = schoolId;
    }
}