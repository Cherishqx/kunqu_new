package com.example.myapplication2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class NowPastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_now_past);

        // 检查是否已经存在Fragment实例，避免旋转屏幕时重复添加
        if (savedInstanceState == null) {
            // 创建Fragment的实例
            FirstFragment_now_past fragment = new FirstFragment_now_past();
            // 开始Fragment事务
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 添加Fragment到容器中
            transaction.add(R.id.fragment_container, fragment);
            // 提交事务
            transaction.commit();
        }
    }
}
