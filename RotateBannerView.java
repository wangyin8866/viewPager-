package com.sljr.sl.happybank.view.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sljr.sl.happybank.R;
import com.sljr.sl.happybank.adapter.BaseViewPagerAdapter;
import com.sljr.sl.happybank.adapter.RotateAdsAdapter;
import com.sljr.sl.happybank.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyihui on 2015/5/26.
 */
public class RotateBannerView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;

    private LinearLayout mDotContainerLayout;
    private ViewPager mViewPager;
    private BaseViewPagerAdapter adapter;

    private final int MARGIN_BOTTOM = 16;

    private final int MARGIN_LEFT_RIGHT = 3;

    private final int DELAY_TIME = 2000;

    private final int DOT_WIDTH_HEIGHT = 8;

    private final int WHAT_AUTO_SCROLL = 1;

    private int lastPosition = -1;

    private int currPosition = 0;

    private List<Integer> adsList;

    private List<View> dotList = new ArrayList<>();

    private boolean isPause = true;

    public RotateBannerView(Context context) {
        this(context, null);
    }

    public RotateBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        mViewPager = new ViewPager(mContext);
        mViewPager.addOnPageChangeListener(this);
        LayoutParams viewpagerLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mViewPager, viewpagerLayoutParams);

        //指示器
//        mDotContainerLayout = new LinearLayout(mContext);
//        mDotContainerLayout.setOrientation(LinearLayout.HORIZONTAL);
//        mDotContainerLayout.setOrientation(LinearLayout.HORIZONTAL);
//        LayoutParams dotRelativeLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dotRelativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
//        dotRelativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//        dotRelativeLayoutParams.setMargins(0, 0, 0, UIUtils.dip2px(MARGIN_BOTTOM));
//        this.addView(mDotContainerLayout, dotRelativeLayoutParams);
    }

    private void initAdsView() {
        if (null == mViewPager) {
            return;
        }
        adapter = new RotateAdsAdapter(mContext, getAdsList());
        mViewPager.setAdapter(adapter);

//        initDotView();
        currPosition = 0;
        lastPosition = -1;
        //设定选中项
        mViewPager.setCurrentItem(currPosition);
        onPageSelected(currPosition);
    }

    private void initDotView() {
        if (null == mDotContainerLayout) {
            return;
        }
        mDotContainerLayout.removeAllViews();
        dotList.clear();
        View view;
        for (int i = 0; i < getAdsList().size(); i++) {
            view = new View(mContext);
            view.setBackgroundResource(R.drawable.selector_ads_dot);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(DOT_WIDTH_HEIGHT), UIUtils.dip2px(DOT_WIDTH_HEIGHT));
            layoutParams.setMargins(UIUtils.dip2px(MARGIN_LEFT_RIGHT), 0, UIUtils.dip2px(MARGIN_LEFT_RIGHT), 0);
            mDotContainerLayout.addView(view, layoutParams);
            dotList.add(view);
        }
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int position) {
        currPosition = position % getAdsList().size();
//        dotList.get(currPosition).setEnabled(true);
//        if (lastPosition != -1) dotList.get(lastPosition).setEnabled(false);
        lastPosition = currPosition;
        sendMessage();
    }

    public void onPageScrollStateChanged(int state) {

    }

    private List<Integer> getAdsList() {
        if (adsList == null) adsList = new ArrayList<>();
        return adsList;
    }

    public void setData(List<Integer> list) {
        this.adsList = list;
        initAdsView();
        start();
    }

    private void sendMessage() {
        if (null != handler) {
            handler.removeMessages(WHAT_AUTO_SCROLL);
            handler.sendEmptyMessageDelayed(WHAT_AUTO_SCROLL, DELAY_TIME);
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isPause) mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    };

    public void start() {
        isPause = false;
        if (null != handler) {
            handler.removeCallbacksAndMessages("");
        }
        sendMessage();
    }

    public void stop() {
        isPause = true;
    }

    public void destroy() {
        if (null != dotList) {
            for (View view : dotList) {
                if (view != null) view = null;
            }
        }
        adsList = null;
        dotList = null;
        mViewPager = null;
        if (null != handler) {
            handler.removeMessages(WHAT_AUTO_SCROLL);
        }
        handler = null;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.getVisibility() == View.VISIBLE) {
            if (hasFocus) {
                start();
            } else {
                stop();
            }
        }
    }
}
