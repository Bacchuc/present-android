package com.larry.present.common.basetemplate;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;


/**
 *
 * 所有activity继承的基础activity
 *
 *
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {

    private SparseArray<View> mViews;

    public abstract int getLayoutId();

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initData();

    public abstract void processClick(View v);

    public void onClick(View v) {
        processClick(v);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        initViews();
        initListener();
        initData();
    }

    public <E extends View> E findView(int viewId) {
        E view = (E) mViews.get(viewId);
        if (view == null) {
            view = (E) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }
}
