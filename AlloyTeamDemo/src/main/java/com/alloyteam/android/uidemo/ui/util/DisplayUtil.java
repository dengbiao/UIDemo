package com.alloyteam.android.uidemo.ui.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 显示相关单位转换工具类
 * Created by freedeng on 2014/9/2.
 */
public class DisplayUtil {

    public static final DisplayMetrics mMetrics = Resources.getSystem().getDisplayMetrics();

    /**
     * dp转px
     * @param value dp值
     * @return px值
     */
    public static float dp2px(float value) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value, mMetrics);
    }

    /**
     * dp转px 向上取整
     * @param value dp值
     * @return px向上取整值
     */
    public static int dp2pxOffset(float value) {
        return  (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value, mMetrics) + 0.5f);
    }

    /**
     * sp 转 px
     * @param value sp值
     * @return px值
     */
    public static float sp2px(float value) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, mMetrics);
    }

    /**
     * sp 转 px 向上取整
     * @param value sp值
     * @return px向上取整值
     */
    public static float sp2pxOffset(float value) {
        return  (int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, mMetrics) + 0.5f);
    }



}
