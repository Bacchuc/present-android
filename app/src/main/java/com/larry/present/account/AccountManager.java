package com.larry.present.account;

import com.larry.present.bean.student.Student;
import com.larry.present.bean.teacher.Teacher;
import com.larry.present.config.Constants;

/*
*    
* 项目名称：present-android      
* 类描述： 账户管理类,在登录成功时需要实例化这个类
* 创建人：Larry-sea   
* 创建时间：2017/5/7 11:32   
* 修改人：Larry-sea  
* 修改时间：2017/5/7 11:32   
* 修改备注：   
* @version    
*    
*/
public class AccountManager {
    private static Teacher teacher;
    private static Student student;


    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher teacher) {
        AccountManager.teacher = teacher;
    }

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        AccountManager.student = student;
    }


    /**
     * 返回登录的用户类型
     *
     * @return
     */
    public static int returnLoginUserType() {
        if (teacher == null) {
            return Constants.STUDENT_TYPE;
        } else if (student == null) {
            return Constants.TEACHER_TYPE;
        }
        return -1;
    }
}
