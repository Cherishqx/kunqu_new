package com.example.myapplication2;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class TwoLevelTransformer implements ViewPager2.PageTransformer {
    private ViewPager2 mViewPager;
    private boolean fromFloorPage = true;
    /** 以下为可调参数，请根据实际情况进行调整 **/
    //floorPageVisibleOffset：顶页可见部分百分比（其余是底页可见部分）
    private static final float floorPageVisibleOffset = 0.91f;

    TwoLevelTransformer(ViewPager2 viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void transformPage(@NonNull View page, float position) {
        switch (page.getId()) {
            case 2:  doInFloorPage(page, position); break;
            case 3:  doInCeilPage(page, position);  break;
        }
        ViewCompat.setElevation(page, mViewPager.getOffscreenPageLimit() - position);
    }

    /**
     * 实现对底部页面的操作
     * @param page 页面对象
     * @param position 位移量
     */
    private void doInFloorPage(@NonNull View page, float position) {

        float offset;  //设置底部偏移（在大于某值时不移动，使底页部分露出）
        if (position > -floorPageVisibleOffset) {
            offset = 0;
        } else {
            offset = page.getHeight() * (Math.abs(position) - floorPageVisibleOffset);
        }
        page.setTranslationY(offset);

        float alpha;  //设置底页的透明度（三个点完全展开后，开始渐变）
        if (Math.abs(position) < 0.21f) {
            alpha = 1f;
        }

    }

    /**
     * 实现对顶部页面的操作
     * @param page 页面对象
     * @param position 位移量
     */
    private void doInCeilPage(@NonNull View page, float position) {
        if (getFromFloorPage()) {
            //设置顶页缩放动画，以及透明度渐变
            float translationY = (page.getHeight() - 300) * position;
            page.setTranslationY(-translationY);
            float scaleFactor = Math.min(1f - position * 0.6f, 1f);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
        page.setAlpha(1f-Math.abs(position));
    }

    /**
     * 设置起始滑动页面的标志
     * @param fromFloorPage 起始页面标志
     *        true：从底页开始滑动
     *        false：从顶页开始滑动
     */
    public void setFromFloorPage(boolean fromFloorPage) {
        this.fromFloorPage = fromFloorPage;
    }

    /**
     * 设置起始滑动页面的标志
     * @return 起始页面标志
     */
    public boolean getFromFloorPage() {
        return this.fromFloorPage;
    }

}
