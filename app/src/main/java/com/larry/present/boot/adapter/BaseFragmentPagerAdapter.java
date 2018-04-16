package com.larry.present.boot.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.larry.present.R;
import com.larry.present.common.context.AppContext;

import java.util.List;

/*  
*    
* 项目名称：Present      
* 类描述： 所有页面的基础fragmentPagerAdapter
* 创建人：Larry-sea   
* 创建时间：2017/4/4 21:34   
* 修改人：Larry-sea  
* 修改时间：2017/4/4 21:34   
* 修改备注：   
* @version    
*    
*/
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {


    /**
     * 存放fragment的集合
     */
    private List<Fragment> mFragments;

    private static Context mContext;


    public List<Fragment> getmFragments() {
        return mFragments;
    }

    public void setmFragments(List<Fragment> mFragments) {
        if (mFragments == null || mFragments.isEmpty())
            return;
        this.mFragments = mFragments;
    }


    /**
     * 标题数组
     */
    private String[] mTitles = new String[]{AppContext.getContext().getResources().getString(R.string.sign),
            AppContext.getContext().getResources().getString(R.string.history)};

    /**
     * @param context    设备上下文
     * @param fm         fragmentManager
     * @param mFragments 待添加的fragmentlist
     */
    public BaseFragmentPagerAdapter(Context context, FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
        this.mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
