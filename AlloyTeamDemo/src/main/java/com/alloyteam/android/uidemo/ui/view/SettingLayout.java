package com.alloyteam.android.uidemo.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alloyteam.android.uidemo.ui.util.DrawableUtil;

import java.util.List;

/**
 * Created by freedeng on 2014/9/2.
 */
public class SettingLayout extends LinearLayout{

    /**
     * 是否显示item的indicator图标
     */
    private boolean mShowIndicator = true;

    /**
     * 是否显示logo
     */
    private boolean mShowLogo = true;

    /**
     * item数组
     */
    private List<SettingItem> mSettingItems;

    /**
     * 分割线颜色
     */
    private int mDividerColor = Color.parseColor("#cccccc");



    private OnSettingItemClickListener mListener;

    public OnSettingItemClickListener getOnSettingItemClickListener() {
        return mListener;
    }

    public void setOnSettingItemClickListener(OnSettingItemClickListener listener) {
        this.mListener = listener;
    }

    public SettingLayout(Context context) {
        super(context);
    }

    public SettingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SettingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public List<SettingItem> getSettingItems() {
        return mSettingItems;
    }

    public void setSettingItems(List<SettingItem> settingItems) {
        this.mSettingItems = settingItems;
        this.removeAllViews();
        if(mSettingItems.size() > 0) {
            //添加分割线
            this.addDividerView();
        }
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        for(SettingItem item : mSettingItems) {
            item.setPadding(dp2pxOffset(20.0f), dp2pxOffset(10.0f), dp2pxOffset(15.0f), dp2pxOffset(10.0f));
            this.addView(item, params);
            this.addDividerView();
        }
        invalidate();
    }

    /**
     * 添加分割线
     * @return 分割线
     */
    public View addDividerView() {
        LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
        View v = new View(getContext());
        v.setBackgroundColor(mDividerColor);
        this.addView(v,params);
        return v;
    }

    public Drawable getBackgroundDrawable() {
        return DrawableUtil.getStateListDrawable(getContext(), new ColorDrawable(Color.WHITE), new ColorDrawable(Color.parseColor("#f0f0f0")),null,null);
    }

    /**
     * Item类 包含图标 文本 分组等属性
     */
    public class SettingItem extends LinearLayout{
        /**
         * 文本左边显示用的icon
         */
        private Drawable mItemLogoDrawable;

        /**
         * item左边logo ImageView
         */
        private ImageView mItemLogoImageView;

        /**
         * item 文本TextView
         */
        private TextView mItemTextView;

        /**
         * item 文本
         */
        private String mItemText;

        /**
         * item 居右的indicator图标
         */
        private Drawable mIndicatorDrawable;

        /**
         * item居右的indicator imageview
         */
        private ImageView mIndicatorImageView;

        /**
         * item 高度  默认高度40dp
         */
        private int mLogoWidth = SettingLayout.dp2pxOffset(35.0f);

        /**
         * 分组号
         */
        private int mGroup;

        public SettingItem(Context context, Drawable logoDrawable, String text, Drawable indicatorDrawable, int group) {
            super(context);
            this.mItemLogoDrawable = logoDrawable;
            this.mItemText = text;
            this.mGroup = group;
            this.mIndicatorDrawable = indicatorDrawable;

            //设置垂直居中 和 水平布局
            this.setGravity(Gravity.CENTER_VERTICAL);
            this.setOrientation(LinearLayout.HORIZONTAL);
            this.setClickable(true);
            this.setBackground(getBackgroundDrawable());

            //初始化左边logo
            mItemLogoImageView = new ImageView(context);
            mItemLogoImageView.setImageDrawable(mItemLogoDrawable);
            mItemLogoImageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //设置logo布局
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mLogoWidth, mLogoWidth);
            params.rightMargin = SettingLayout.dp2pxOffset(15.0f);
            if(!mShowLogo) {
                mItemLogoImageView.setVisibility(View.GONE);
            }
            this.addView(mItemLogoImageView, params);

            //初始化textview
            mItemTextView = new TextView(context);
            mItemTextView.setText(mItemText);
            mItemTextView.setTextSize(18.0f);
            //mItemTextView.setTypeface(Typeface.DEFAULT_BOLD);
            mItemTextView.setGravity(Gravity.CENTER_VERTICAL);

            //textview布局
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            this.addView(mItemTextView, params);

            //配置indicator
            mIndicatorImageView = new ImageView(context);
            mIndicatorImageView.setImageDrawable(mIndicatorDrawable);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = SettingLayout.dp2pxOffset(5.0f);
            //不显示indicator
            if(!mShowIndicator) {
                mIndicatorImageView.setVisibility(View.GONE);
            }
            this.addView(mIndicatorImageView, params);

            //初始化点击事件
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        for(int i=0;i<mSettingItems.size();i++) {
                            if(SettingItem.this == mSettingItems.get(i)) {
                                mListener.onItemClick(i, SettingItem.this, mGroup);
                            }
                        }
                    }
                }
            });
        }

        public ImageView getLogo() {
            return mItemLogoImageView;
        }
    }

    public void setShowLogo(boolean showLogo) {
        this.mShowLogo = showLogo;
        if(!mShowLogo) {
            for(int i=0;i<mSettingItems.size();i++) {
                mSettingItems.get(i).getLogo().setVisibility(View.GONE);
            }
        }else {
            for(int i=0;i<mSettingItems.size();i++) {
                mSettingItems.get(i).getLogo().setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * dp转px
     * @param value dp值
     * @return px值
     */
    public static float dp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().getDisplayMetrics());
    }

    public static int dp2pxOffset(float value) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, Resources.getSystem().getDisplayMetrics()) + 0.5f);
    }


    public static float sp2px(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, Resources.getSystem().getDisplayMetrics());
    }

    public interface OnSettingItemClickListener {
        public void onItemClick(int position, View v, int group);
    }
}
