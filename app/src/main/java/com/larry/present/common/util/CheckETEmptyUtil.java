package com.larry.present.common.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/*
*    
* 项目名称：Here      
* 类描述：检查EditText是否有值工具类
* 创建人：Larry-sea   
* 创建时间：2016/12/3 16:02   
* 修改人：Larry-sea  
* 修改时间：2016/12/3 16:02   
* 修改备注：重载addTip方法，修改check方法，添加context
* @version
*
* usage example CheckEtEmptyUtil checkemptyUtil.addView().addTip().addView().checkHasWifiHost();
*    
*/
public class CheckETEmptyUtil {

    private List<EditText> mViewList;                  //视图保存的viewList
    private List<String> mTipList;                     //视图是否为空建议list
    Builder mBuilder;                                  //构造者
    private WeakReference<List<EditText>> mWRViewList; //view的弱引用
    private WeakReference<List<String>> mWRTipList;    //tip意见的弱引用
    private WeakReference<Builder> mWRBuilder;         //构造者的弱引用
    private Context mContext;                          //设备上下文

    /**
     * @return 如果其中有那个EditText 内容为空则返回true 否则返回false
     * @example 使用这个方法之前一定的先用addView 添加相应的editText 否则会报出异常
     */
    public boolean isEmpty() {

        this.mViewList = mBuilder.mViewList;
        this.mTipList = mBuilder.mTipList;
        if (mViewList == null) {
            throw new IllegalArgumentException("please add the EditText first ");
        }

        for (int position = 0; position < mViewList.size(); position++) {
            if (mViewList.get(position).getText().toString().isEmpty()) {
                if (mTipList.get(position) != null) {
                    Toast.makeText(mContext, mTipList.get(position), Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        mWRBuilder = null;
        mWRTipList = null;
        mWRViewList = null;
        return false;
    }


    /**
     * @param editText 添加待检查的editeText
     */
    public CheckETEmptyUtil addView(final EditText editText) {
        if (mBuilder == null) {
            mBuilder = new Builder();
            mWRBuilder = new WeakReference<>(mBuilder);
        }
        if (mWRBuilder.get() != null) {
            mWRBuilder.get().addView(editText);
        }
        return this;
    }


    /**
     * @param tip 添加对应的editeText的建议
     */
    public CheckETEmptyUtil addTip(final String tip) {
        if (mWRBuilder == null) {
            throw new IllegalArgumentException("please add EditText first");
        }
        mWRBuilder.get().addTip(tip);
        return this;
    }

    /**
     * @param tipId 对应的string 资源id
     */
    public CheckETEmptyUtil addTip(final int tipId) {
        if (mWRBuilder == null) {
            throw new IllegalArgumentException("please add EditText first");
        }

        mWRBuilder.get().addTip(mContext.getResources().getString(tipId));
        return this;
    }

    public CheckETEmptyUtil(Context context) {
        this.mContext = context;
    }

    /*
        *
        * 构造者
        * */
    private class Builder {

        List<EditText> mViewList;     //视图保存的viewList
        List<String> mTipList;        //视图是否为空建议list

        /**
         * 添加view
         *
         * @param editText
         * @return
         */
        public Builder addView(EditText editText) {
            if (editText == null) {
                throw new NullPointerException("parm cant be null,checkHasWifiHost you parm ");
            }
            if (mViewList == null || mTipList == null) {
                mViewList = new ArrayList<>();
                mTipList = new ArrayList<>();
                mWRViewList = new WeakReference<>(mViewList);
                mWRTipList = new WeakReference<>(mTipList);
            }
            mWRViewList.get().add(editText);
            return this;
        }


        /**
         * 添加view相对应的tip
         *
         * @return builder
         */
        public Builder addTip(String tip) {

            if (tip == null) {
                throw new NullPointerException("tip cant be null,checkHasWifiHost you parm ");
            }

            if (mWRViewList == null || mWRTipList == null) {
                throw new IllegalArgumentException("please add EditeTextFirst and then add the tip");
            }
            /*
            * 连续添加view 而没有添加tip时 添加空tip这样达到提示信息不回错乱
            *
            * */
            if (mWRViewList.get().size() - mWRTipList.get().size() > 1) {
                int diffValue = mWRViewList.get().size() - mWRTipList.get().size();
                for (int dValue = 1; dValue < diffValue; dValue++) {
                    mWRTipList.get().add(null);
                }
                mWRTipList.get().add(tip);
            } else {
                mWRTipList.get().add(tip);
            }
            return this;
        }
    }


    /**
     * 当长时间没有停留在界面时需要判断是否被回收掉，如果被回收掉
     * 则需要重新生成对象
     *
     * @return
     */
    public boolean isRecycler() {
        if (mWRViewList == null || mWRViewList.get() == null) {
            return true;
        }
        if (mWRTipList == null || mWRTipList.get() == null) {
            return true;
        }
        if (mWRBuilder == null || mWRBuilder.get() == null) {
            return true;
        }
        return false;
    }
}


