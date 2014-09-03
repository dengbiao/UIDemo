package com.alloyteam.android.uidemo.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.alloyteam.android.uidemo.R;
import com.alloyteam.android.uidemo.ui.util.DisplayUtil;
import com.alloyteam.android.uidemo.ui.util.DrawableUtil;

/**
 * 带圆角 填充色 边框的TextView
 * 适用于做按钮，头衔小图片
 *
 * @author freedeng
 */
public class ATMTextView extends TextView {

    /**
     * 填充色
     */
    private int mFillColor;

    /**
     * 边框颜色
     */
    private int mStokeColor;

    /**
     * 边框线宽度
     */
    private float mStokeWidth;

    /**
     * 圆角
     */
    private float mRoundRadius;

    /**
     * Pressed状态下文本颜色
     */
    private int mPressedColor;

    /**
     * Pressed状态填充色
     */
    private int mPressedFillColor;

    /**
     * Pressed状态边框颜色
     */
    private int mPressedStokeColor;

    /**
     * Pressed状态边框线宽度
     */
    private float mPressedStokeWidth;

    /**
     * Pressed状态圆角
     */
    private float mPressedRoundRadius;

    /**
     * 默认样式
     */
    private int mType;

    /**
     * 常量 默认按钮样式
     */
    public static final int TYPE_BUTTON = 0x1;

    /**
     * 常量 默认tag样式
     */
    public static final int TYPE_TAG = 0x2;

    /**
     * 生成的normal状态背景图
     */
    private GradientDrawable mBackgroundDrawable;

    /**
     * 生成的pressed状态背景图
     */
    private GradientDrawable mPressedBackgroundDrawable;

    public ATMTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ATMTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ATMTextView);

        mType = typedArray.getInt(R.styleable.ATMTextView_type, 0);
        if(mType == 0) {
            initData(typedArray, android.R.color.transparent, android.R.color.transparent, 0, 0, 0, 0, 0, 0, 0);
        }else if(mType == TYPE_BUTTON) {
            this.setClickable(true);
            this.setPadding(DisplayUtil.dp2pxOffset(10.0f), DisplayUtil.dp2pxOffset(5.0f), DisplayUtil.dp2pxOffset(10.0f), DisplayUtil.dp2pxOffset(5.0f));
            //按钮样式
            initData(typedArray, Color.WHITE, Color.parseColor("#d4d6d7"), 2, DisplayUtil.dp2pxOffset(2.0f), 0, Color.parseColor("#f0f0f0"), 0, 0, 0);
        }
    }

    /**
     * 初始化数据
     * @param typedArray
     * @param fillColor 默认填充色
     * @param stokeColor 默认边框颜色
     * @param stokeWidth 默认边框宽度
     * @param roundRadius 默认边框圆角
     * @param pressedFillColor 默认pressed状态填充色
     * @param pressedStokeColor 默认pressed状态边框颜色
     * @param pressedStokeWidth 默认pressed状态边框宽度
     * @param pressedRoundRadius 默认pressed状态边框圆角
     */
    public void initData( TypedArray typedArray, int fillColor, int stokeColor, int stokeWidth, int roundRadius, int pressedTextColor, int pressedFillColor, int pressedStokeColor, int pressedStokeWidth, int pressedRoundRadius) {

        //默认属性
        mFillColor = typedArray.getColor(R.styleable.ATMTextView_fillColor,
                fillColor);
        mStokeColor = typedArray.getColor(R.styleable.ATMTextView_stokeColor,
                stokeColor);
        mStokeWidth = typedArray.getDimension(R.styleable.ATMTextView_stokeWidth,
                stokeWidth);
        mRoundRadius = typedArray.getDimension(
                R.styleable.ATMTextView_roundRadius, roundRadius);

        //pressed状态属性
        mPressedColor = typedArray.getColor(R.styleable.ATMTextView_pressedTextColor,
                pressedTextColor);
        if(mPressedColor != 0) {
            //配置了pressed状态颜色
            setTextColor(DrawableUtil.getColorStateList(getCurrentTextColor(), mPressedColor, 0, 0));
        }
        mPressedFillColor = typedArray.getColor(R.styleable.ATMTextView_pressedFillColor,
                pressedFillColor);
        mPressedStokeColor = typedArray.getColor(R.styleable.ATMTextView_pressedStokeColor, pressedStokeColor);
        mPressedStokeWidth = typedArray.getDimension(R.styleable.ATMTextView_pressedStokeWidth,
                pressedStokeWidth);
        mPressedRoundRadius = typedArray.getDimension(
                R.styleable.ATMTextView_pressedRoundRadius, pressedRoundRadius);

        mBackgroundDrawable = getBackgroundDrawable(mFillColor,
                mRoundRadius, (int) mStokeWidth, mStokeColor);
        mPressedBackgroundDrawable = getBackgroundDrawable(mPressedFillColor == 0 ? mFillColor : mPressedFillColor,
                mPressedRoundRadius == 0 ? mRoundRadius : mPressedRoundRadius, mPressedStokeWidth == 0 ? (int) mStokeWidth : (int) mPressedStokeWidth, mPressedStokeColor == 0 ? mStokeColor : mPressedStokeColor);
        this.setBackgroundDrawable(DrawableUtil.getStateListDrawable(getContext(), mBackgroundDrawable, mPressedBackgroundDrawable, null, null));
    }

    /**
     * 根据参数获取可用于绘制的背景图
     *
     * @param fillColor   填充色
     * @param roundRadius 边框圆角
     * @param stokeWidth  边框宽度
     * @param stokeColor  边框颜色
     * @return 可用于绘制的背景图
     */
    public GradientDrawable getBackgroundDrawable(int fillColor,
                                                  float roundRadius, int stokeWidth, int stokeColor) {
        GradientDrawable gd = new GradientDrawable();
        if (fillColor != 0) {
            gd.setColor(fillColor);
        }
        if (roundRadius != 0) {
            gd.setCornerRadius(roundRadius);
        }
        if (stokeWidth != 0 && stokeColor != 0) {
            gd.setStroke((int) stokeWidth, stokeColor);
        }
        return gd;
    }

    public ATMTextView(Context context) {
        super(context);
    }

    public int getFillColor() {
        return mFillColor;
    }

    public void setFillColor(int fillColor) {
        this.mFillColor = fillColor;
        mBackgroundDrawable.setColor(mFillColor);
        this.invalidate();
    }

    public int getStokeColor() {
        return mStokeColor;
    }

    public void setStokeColor(int stokeColor) {
        this.mStokeColor = stokeColor;
        mBackgroundDrawable.setStroke((int) mStokeWidth, mStokeColor);
        this.invalidate();
    }

    public float getStokeWidth() {
        return mStokeWidth;
    }

    public void setStokeWidth(float stokeWidth) {
        this.mStokeWidth = stokeWidth;
        mBackgroundDrawable.setStroke((int) mStokeWidth, mStokeColor);
        this.invalidate();
    }

    public float getRoundRadius() {
        return mRoundRadius;
    }

    public void setmRoundRadius(float roundRadius) {
        this.mRoundRadius = roundRadius;
        mBackgroundDrawable.setCornerRadius(roundRadius);
        this.invalidate();
    }

}
