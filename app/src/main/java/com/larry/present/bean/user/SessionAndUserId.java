package com.larry.present.bean.user;

/*  
*    
* 项目名称：Here      
* 类描述：session 和userid的存储模型
* 创建人：Larry-sea   
* 创建时间：2016/11/30 14:51   
* 修改人：Larry-sea  
* 修改时间：2016/11/30 14:51   
* 修改备注：   
* @version    
*    
*/
public class SessionAndUserId {

    String userId;         //用户id
    String sessionId;      //sessionId

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


    /**
     * @param userId    用户id
     * @param sessionId sessionId
     */
    public SessionAndUserId(String userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }
}
