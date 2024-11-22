package com.example.myapplication2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.myapplication2.Adapter.TwoLevelAdapter;


public class ViewPage2Fragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_page2, container, false);
        final ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        final TwoLevelTransformer transformer = new TwoLevelTransformer(viewPager2);

        TwoLevelAdapter adapter = new TwoLevelAdapter(requireActivity().getSupportFragmentManager(), getLifecycle());
        adapter.addFragment(new FirstFragment_next_history());
        adapter.addFragment(new FirstFragment_next());
        viewPager2.setAdapter(adapter);

        //viewPager2.setRotation(180);
        viewPager2.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setPageTransformer(transformer);
        viewPager2.setCurrentItem(1, false); // 第二个参数为是否使用动画过渡
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                if (position == 1) {
                    transformer.setFromFloorPage(false);
                }
            }
        });


        return view;


    }


}