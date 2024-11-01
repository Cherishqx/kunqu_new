package com.example.myapplication2;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //默认首页选中
        selectedFragment(0);

        //bottomNavigationView点击切换事件
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_first){
                    selectedFragment(0);
                }else if(item.getItemId() == R.id.navigation_second){
                    selectedFragment(1);
                } else if (item.getItemId() == R.id.navigation_third) {
                    selectedFragment(2);
                }else {
                    selectedFragment(3);
                }
                return true;
            }
        });

    }



    private void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if(position==0){
            if(firstFragment == null){
                firstFragment = new FirstFragment();
                fragmentTransaction.add(R.id.container, firstFragment);
            }else {
                fragmentTransaction.show(firstFragment);
            }
        } else if (position == 1) {
            if(secondFragment == null){
                secondFragment = new SecondFragment();
                fragmentTransaction.add(R.id.container, secondFragment);
            }else {
                fragmentTransaction.show(secondFragment);
                //mBookFragment.refreshData();
            }
        }else if(position == 2){
            if(thirdFragment == null){
                thirdFragment = new ThirdFragment();
                fragmentTransaction.add(R.id.container, thirdFragment);
            }else {
                fragmentTransaction.show(thirdFragment);
                //mHearFragment.refreshData();
            }
        }else{
            if(fourthFragment == null){
                fourthFragment = new FourthFragment();
                fragmentTransaction.add(R.id.container, fourthFragment);
            }else {
                fragmentTransaction.show(fourthFragment);
                //mMeFragment.refreshData();
            }
        }
        //提交
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(firstFragment != null){
            fragmentTransaction.hide(firstFragment);
        }

        if(secondFragment !=null){
            fragmentTransaction.hide(secondFragment);
        }

        if(thirdFragment !=null){
            fragmentTransaction.hide(thirdFragment);
        }

        if(fourthFragment !=null){
            fragmentTransaction.hide(fourthFragment);
        }

    }


}
