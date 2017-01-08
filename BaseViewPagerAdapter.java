package com.sljr.sl.happybank.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liuyihui on 2015/5/26.
 * viewPager 轮播
 */
public abstract class BaseViewPagerAdapter extends PagerAdapter {


    private List mList;

    protected abstract List getAdapterList();

    protected abstract View getItemView(ViewGroup container, int position);

    public int getCount() {
        mList = getAdapterList();
        if (mList == null || mList.size() < 1) return 0;
        return mList.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        return getItemView(container, position);
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
