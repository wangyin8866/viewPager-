package com.sljr.sl.happybank.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by xuxinkai on 2015/12/28.
 */
public class RotateAdsAdapter extends BaseViewPagerAdapter {
    public static final String INVITE = "invite";
    public static final String REDBAG = "redbag";
    public static final String URL = "url";
    private Context mContext;
    private List mList;

    public RotateAdsAdapter(Context context, List<Integer> list) {
        this.mContext = context;
        mList = list;
    }

    protected List getAdapterList() {
        return mList;
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    protected View getItemView(ViewGroup container, int position) {
        position = position % mList.size();
        final ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (position == 0) {
            Glide.with(mContext).load("http://ww4.sinaimg.cn/large/610dc034gw1fac4t2zhwsj20sg0izahf.jpg").into(mImageView);
            final int finalPosition = position;
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position:" + finalPosition, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (position == 1) {
            Glide.with(mContext).load("http://upload.qlwb.com.cn/2017/0107/1483744737653.jpg").into(mImageView);
            final int finalPosition = position;
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position:" + finalPosition, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (position == 2) {
            Glide.with(mContext).load("http://upload.qlwb.com.cn/2017/0107/1483744737650.jpg").into(mImageView);
            final int finalPosition = position;
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "position:" + finalPosition, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (position == 3) {
            Glide.with(mContext).load("http://upload.qlwb.com.cn/2017/0107/1483744737786.jpg").into(mImageView);
        } else {
            Glide.with(mContext).load("http://ww4.sinaimg.cn/large/610dc034gw1fac4t2zhwsj20sg0izahf.jpg").into(mImageView);
        }
        container.addView(mImageView);
        return mImageView;
    }

}

