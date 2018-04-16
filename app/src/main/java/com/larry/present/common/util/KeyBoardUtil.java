package com.larry.present.common.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/*
*    
* 项目名称：Here      
* 类描述：  键盘打开关闭的工具类
* 创建人：Larry-sea   
* 创建时间：2016/12/3 20:48   
* 修改人：Larry-sea  
* 修改时间：2016/12/3 20:48   
* 修改备注：   
* @version    
*    
*/
public class KeyBoardUtil {

    /**
     * 打开软键盘
     *
     * @param mEditText    editText
     *
     * @param mContext     context设备上下文
     *
     */
    public static void openKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText  editText
     *
     * @param mContext   设备上下文
     *
     */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }



    /**
     * @param rootView 监听的根view
     * @return true代表键盘已经弹起  false则未弹起
     */
    public static boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }
}
