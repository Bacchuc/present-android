package com.larry.present.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.larry.present.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/*
*    
* 项目名称：present      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/4/13 14:52   
* 修改人：Larry-sea  
* 修改时间：2017/4/13 14:52   
* 修改备注：   
* @version    
*    
*/
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer[] array = new Integer[3];

        array[0] = 1;
        array[1] = 2;
        array[2] = 3;

        String[] stringList = {"a", "b", "c"};
        JSONObject jsonObject = new JSONObject();
        com.alibaba.fastjson.JSONObject fastJsonObject = new com.alibaba.fastjson.JSONObject();

        fastJsonObject.put("list", list);
        fastJsonObject.put("array", array);
        fastJsonObject.put("stringList", stringList);
        Log.e("TAG", jsonObject.toString());

        try {
            jsonObject.put("list", list);
            jsonObject.put("array", array);
            jsonObject.put("stringList", stringList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonObject.toString();
        Log.e("TAG", "fasdf" + jsonObject.toString());*/
        openReceiveMessage();

    }


    public void openReceiveMessage() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    // 提交用户信息（此方法可以不调用）
                    Toast.makeText(TestActivity.this, country + "   " + phone, Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerPage.show(TestActivity.this);
    }

}
