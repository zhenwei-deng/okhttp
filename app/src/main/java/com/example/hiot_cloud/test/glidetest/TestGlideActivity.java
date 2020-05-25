package com.example.hiot_cloud.test.glidetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.hiot_cloud.R;
import com.example.hiot_cloud.utils.ImageUtils;

public class TestGlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_glide);
        final String url = "http://p1.pstatp.com/large/166200019850062839d3";

        final ImageView iv = findViewById(R.id.iv_glide_test);

        //图片加载按钮
        Button btnGlideLoad = findViewById(R.id.btn_glide_load_test);
        btnGlideLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(TestGlideActivity.this)
                        .asGif()
                        .load(url)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
//                        .transition(new DrawableTransitionOptions().crossFade())
//                        .centerCrop()
                        .into(iv);
            }
        });

        //使用工具类图片显示
        Button btnGlideUtil = findViewById(R.id.btn_glide_util_test);
        btnGlideUtil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUtils.show(TestGlideActivity.this,iv,url);
            }
        });
    }
}
