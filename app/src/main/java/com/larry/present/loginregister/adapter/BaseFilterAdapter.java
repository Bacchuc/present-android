package com.larry.present.loginregister.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/*
*    
* 项目名称：Here      
* 类描述： 基础的中文适配器配合 Chinese fiter 使用匹配中文
* 创建人：Larry-sea   
* 创建时间：2017/1/7 13:28   
* 修改人：Larry-sea  
* 修改时间：2017/1/7 13:28   
* 修改备注：   
* @version    
*    
*/
public class BaseFilterAdapter extends BaseAdapter implements Filterable {


    //没有过滤的数据集合
    private ArrayList<String> mUnfilteredData;


    //过滤之后的数据集合
    private ArrayList<String> mList;
    //过滤器
    private ChineseFilter mFilter;
    private Context mContext;


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = View.inflate(mContext, android.R.layout.simple_expandable_list_item_1, null);
            viewHolder = new ViewHolder();
            viewHolder.NameTextView = (TextView) view.findViewById(android.R.id.text1);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.NameTextView.setText(mList.get(position));
        return view;
    }


    /*
    * 假定现在的atuoComplete 中的item只是含有一个textView的这种类型
    *
    * */
    private class ViewHolder {
        public TextView NameTextView;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ChineseFilter(this);
        }
        return mFilter;
    }

    public BaseFilterAdapter(Context context, ArrayList<String> mUnfilteredData) {
        this.mUnfilteredData = mUnfilteredData;
        this.mContext = context;
    }


    public ArrayList<String> getmUnfilteredData() {
        return mUnfilteredData;
    }

    public void setmUnfilteredData(ArrayList<String> mUnfilteredData) {
        this.mUnfilteredData = mUnfilteredData;
    }


    public ArrayList<String> getmList() {
        return mList;
    }

    public void setmList(ArrayList<String> mList) {
        this.mList = mList;
    }

}
