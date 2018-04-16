package com.larry.present.bean.user;

/*  
*    
* 项目名称：Here      
* 类描述： 基础的用户类保存用户userId和SessionId  当登录时初始化这个
* 创建人：Larry-sea   
* 创建时间：2017/1/8 11:25   
* 修改人：Larry-sea  
* 修改时间：2017/1/8 11:25   
* 修改备注：   
* @version    
*    
*/
public class User {
    private static User INSTANCE;      //用户实例
    String userId;                     //用户id
    String sessionId;                  //sessionId
    boolean isTeacher =false;         //是否是老师用户标记 如果为true  否则为false

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    private User() {

    }


    /**
     *
     * 获取单用户实例
     *
     * @return
     */
    public static User getInstance() {
        if (INSTANCE == null) {
            synchronized (User.class) {
                if (INSTANCE == null) {
                    INSTANCE = new User();
                }
            }
        }
        return INSTANCE;
    }


}
