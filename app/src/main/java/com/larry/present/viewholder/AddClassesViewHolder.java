package com.larry.present.viewholder;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.larry.present.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/5/12 20:36   
* 修改人：Larry-sea  
* 修改时间：2017/5/12 20:36   
* 修改备注：   
* @version    
*    
*/
public class AddClassesViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.tv_classes_item_name)
    public TextView className;
    @BindView(R.id.tv_add_course_delete)
    public TextView courseDelte;

    public AddClassesViewHolder(View itemView, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
