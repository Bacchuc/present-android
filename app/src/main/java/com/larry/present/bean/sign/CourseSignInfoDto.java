
package com.larry.present.bean.sign;

import java.io.Serializable;

/**
 * Created by Larry-sea on 2017/3/24.
 *
 * 某次课程签到信息的dto，其中包括签到状态和签到时间
 *
 * 代表的是学生某次课程的签到记录信息
 *
 */
public class CourseSignInfoDto  implements Serializable{

    /**
     * 学生的签到状态
     */
    String signState;


    /**
     *
     * 签到时间
     *
     */
    String signTime;


    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }
}
