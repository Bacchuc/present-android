package com.larry.present.common.basetemplate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;

/**
 * 所有类继承的基础fragment
 */
public abstract class BaseFragment extends Fragment  {

    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;
    protected Subscription subscription;
    //fragment的视图
    View convertView;


    Unbinder unbinder;


    //初始化view
    public abstract void initViews();

    //初始化数据
    public abstract void initDatas();

    //获取布局id
    public abstract int getLayoutId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        isInitView = true;
        lazyLoad();
        convertView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, convertView);
        return convertView;
    }


    private void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            //不加载数据
            return;
        }
        //加载数据


        isFirstLoad = false;

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
        unbinder.unbind();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
