package com.larry.present.sign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larry.present.R;
import com.larry.present.account.AccountManager;
import com.larry.present.bean.course.Course;
import com.larry.present.common.context.AppContext;
import com.larry.present.common.subscribers.ProgressSubscriber;
import com.larry.present.common.subscribers.SubscriberOnNextListener;
import com.larry.present.common.util.DividerItemDecoration;
import com.larry.present.course.activity.CheckCourseActivity;
import com.larry.present.network.base.ApiService;
import com.larry.present.network.course.CourseApi;
import com.larry.present.sign.activity.StudentCheckCourseSignInfoActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
*    
* 项目名称：present-android      
* 类描述： 学生检查课程fragment
* 创建人：Larry-sea   
* 创建时间：2017/5/18 15:38   
* 修改人：Larry-sea  
* 修改时间：2017/5/18 15:38   
* 修改备注：   
* @version    
*    
*/
public class StudentCheckCourseFragment extends Fragment {


    @BindView(R.id.rv_bases)
    RecyclerView rvBases;
    Unbinder unbinder;

    CourseApi courseApi;


    SubscriberOnNextListener<List<Course>> getCourseListener;

    //查看签到信息intent
    Intent checkCourseSignInfo;

    //查看课程信息intent
    Intent checkCourseInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_recyclerview, container, false);
        AppContext.log(getClass().getSimpleName()+"            onCreateView     ");
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void initData() {

        courseApi = new CourseApi(ApiService.getInstance(getActivity()).getmRetrofit());
        getCourseListener = new SubscriberOnNextListener<List<Course>>() {
            @Override
            public void onNext(List<Course> courseList) {
                rvBases.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvBases.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                rvBases.setAdapter(new CommonAdapter<Course>(getActivity(), R.layout.course_item, courseList) {
                    @Override
                    protected void convert(ViewHolder holder, Course o, int position) {
                        holder.setText(R.id.tv_course_item_number, String.valueOf(position + 1));
                        holder.setText(R.id.tv_course_item_name, o.getCourseName());
                        holder.setOnClickListener(R.id.ll_course_item, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkCourseSignInfo = new Intent(getActivity(), StudentCheckCourseSignInfoActivity.class);
                                checkCourseSignInfo.putExtra("courseId", o.getId());
                                startActivity(checkCourseSignInfo);
                            }
                        });
                        holder.setOnLongClickListener(R.id.ll_course_item, new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                checkCourseInfo = new Intent(getActivity(), CheckCourseActivity.class);
                                checkCourseInfo.putExtra("courseId", o.getId());
                                startActivity(checkCourseInfo);
                                return true;
                            }
                        });
                    }
                });

            }

            @Override
            public void onCompleted() {

            }
        };
        courseApi.studentGetAllCourse(new ProgressSubscriber<List<Course>>(getCourseListener, getActivity()), AccountManager.getStudent().getClassId());
    }


}
