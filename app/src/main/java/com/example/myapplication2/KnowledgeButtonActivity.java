package com.example.myapplication2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class KnowledgeButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_knowledge_button);

        // 检查是否已经存在Fragment实例，避免旋转屏幕时重复添加
        if (savedInstanceState == null) {
            // 创建Fragment的实例
            SecondFragment fragment = new SecondFragment();
            // 开始Fragment事务
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 添加Fragment到容器中
            transaction.add(R.id.fragment_container, fragment);
            // 提交事务
            transaction.commit();
        }
    }
}
