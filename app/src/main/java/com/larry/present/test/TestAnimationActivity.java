package com.larry.present.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.larry.present.R;

/*
*    
* 项目名称：present-android      
* 类描述：   
* 创建人：Larry-sea   
* 创建时间：2017/5/19 15:54   
* 修改人：Larry-sea  
* 修改时间：2017/5/19 15:54   
* 修改备注：   
* @version    
*    
*/
public class TestAnimationActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        image = (ImageView) findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = new MyAnimation(image, 150);
                anim.setDuration(3000);
                image.startAnimation(anim);
                image.startAnimation(anim);
            }
        });
    }

}
