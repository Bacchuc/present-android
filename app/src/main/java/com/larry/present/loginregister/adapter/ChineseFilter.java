package com.larry.present.loginregister.adapter;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Larry-sea on 2016/11/23.
 * <p>
 * 中文匹配过滤器
 */
class ChineseFilter<T extends BaseFilterAdapter> extends Filter {

    //继承了BaseFilterAdapter的适配器
    private T mAdapter;

    public ChineseFilter(T adapter) {
        if (adapter != null) {
            this.mAdapter = adapter;
        }

    }


    /**
     * @param prefix
     * @return
     */
    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        FilterResults results = new FilterResults();

        if (mAdapter.getmUnfilteredData() == null) {
            mAdapter.setmUnfilteredData(mAdapter.getmList());
        }

        if (prefix == null || prefix.length() == 0) {
            ArrayList<String> list = mAdapter.getmUnfilteredData();
            results.values = list;
            results.count = list.size();
        } else {
            String prefixString = prefix.toString().toLowerCase();

            ArrayList<String> unfilteredValues = mAdapter.getmUnfilteredData();
            int count = unfilteredValues.size();

            ArrayList<String> newValues = new ArrayList<String>(count);
            for (int i = 0; i < count; i++) {
                String chinese = unfilteredValues.get(i);
                if (chinese != null && chinese.substring(0, prefixString.length()).equals(prefixString)) {
                    newValues.add(chinese);
                }
            }

            results.values = newValues;
            results.count = newValues.size();
        }

        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint,
                                  FilterResults results) {
        if (mAdapter != null && results.values instanceof ArrayList) {
            mAdapter.setmList((ArrayList<String>) results.values);
            if (results.count > 0) {
                mAdapter.notifyDataSetChanged();
            } else {
                mAdapter.notifyDataSetInvalidated();
            }
        }

    }

}
