package com.larry.present.loginregister.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larry.present.R;
import com.larry.present.common.basetemplate.BaseFragment;
import com.larry.present.bean.school.School;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
*    
* 项目名称： present
* 类描述：  选择学校fragment
* 创建人：Larry-sea   
* 创建时间：2017/4/7 14:03   
* 修改人：Larry-sea  
* 修改时间：2017/4/7 14:03   
* 修改备注：   
* @version    
*    
*/
public class SelectSchoolFragment extends BaseFragment {


    @BindView(R.id.rv_bases)
    RecyclerView rvBases;
    Unbinder unbinder;
    /*
    * 学校数据
    * */
    WeakReference<List<School>> schoolWeakReference;

    /**
     * 学校list
     */
    List<School> schoolList;

    @Override
    public int getLayoutId() {
        return R.layout.base_recyclerview;
    }

    @Override
    public void initViews() {
        rvBases.setAdapter(new CommonAdapter<School>(getContext(), android.R.layout.simple_list_item_1, schoolList) {
            @Override
            protected void convert(ViewHolder holder, School school, int position) {
                holder.setText(android.R.id.text1, school.getSchoolName());
            }
        });

    }


    @Override
    public void initDatas() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
