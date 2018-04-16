package com.larry.present.listener;

import android.view.KeyEvent;

/*
*    
* 项目名称：present-android      
* 类描述： 监听返回的回调事假
* 创建人：Larry-sea   
* 创建时间：2017/5/16 21:17   
* 修改人：Larry-sea  
* 修改时间：2017/5/16 21:17   
* 修改备注：   
* @version    
*    
*/
public interface onBackPressedClickListener {

    boolean onBackPressed(int keyCode, KeyEvent event);

}
