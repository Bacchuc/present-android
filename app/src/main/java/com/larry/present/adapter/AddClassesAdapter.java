package com.larry.present.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larry.present.R;
import com.larry.present.bean.classes.Classes;
import com.larry.present.listener.RecyclerviewClickInterface;
import com.larry.present.viewholder.AddClassesViewHolder;

import java.util.List;

/*
*    
* 项目名称：present-android      
* 类描述：  添加课程的适配器
* 创建人：Larry-sea   
* 创建时间：2017/5/12 20:29   
* 修改人：Larry-sea  
* 修改时间：2017/5/12 20:29   
* 修改备注：   
* @version    
*    
*/
public class AddClassesAdapter extends RecyclerView.Adapter<AddClassesViewHolder> {

    List<Classes> classesList;
    Context mcontext;
    RecyclerviewClickInterface mrecyclerClickInterface;


    public AddClassesAdapter(Context context, List<Classes> creditCards) {
        this.mcontext = context;
        this.classesList = creditCards;

    }

    @Override
    public AddClassesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddClassesViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.add_classes_item, parent, false), (Activity) mcontext);
    }

    @Override
    public void onBindViewHolder(AddClassesViewHolder holder, int position) {
        if (holder != null) {
            holder.className.setText(classesList.get(position).getClassName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mrecyclerClickInterface.onClick(holder.courseDelte, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return classesList != null ? classesList.size() : 0;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnClickListener(RecyclerviewClickInterface recyclerviewClickInterface) {
        this.mrecyclerClickInterface = recyclerviewClickInterface;
    }


    public void setData(List<Classes> studentCourseSignDtos) {
        this.classesList = studentCourseSignDtos;

    }


}
