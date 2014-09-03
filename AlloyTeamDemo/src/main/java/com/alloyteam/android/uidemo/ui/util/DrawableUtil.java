package com.alloyteam.android.uidemo.ui.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * drawable工具类
 * Created by freedeng on 2014/9/2.
 */
public class DrawableUtil {

    /**
     * 根据不同状态drawable资源文件获取StateListDrawable
     * @param context 上下文
     * @param normalDrawableId normal drawable，不需要设置传入0
     * @param pressedDrawableId pressed drawable，不需要设置传入0
     * @param focusedDrawableId focused drawable，不需要设置传入0
     * @param unableDrawableId unable drawable，不需要设置传入0
     * @return StateListDrawable
     */
    public static StateListDrawable getStateListDrawable(Context context, int normalDrawableId, int pressedDrawableId, int focusedDrawableId, int unableDrawableId) {
        StateListDrawable drawable = new StateListDrawable();
        Drawable normal = normalDrawableId == 0 ? null : context.getResources().getDrawable(normalDrawableId);
        Drawable pressed = pressedDrawableId == 0 ? null : context.getResources().getDrawable(pressedDrawableId);
        Drawable focused = focusedDrawableId == 0 ? null : context.getResources().getDrawable(focusedDrawableId);
        Drawable unable = unableDrawableId == 0 ? null : context.getResources().getDrawable(unableDrawableId);
        // View.PRESSED_ENABLED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
        // View.ENABLED_FOCUSED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
        // View.ENABLED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_enabled }, normal);
        // View.FOCUSED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_focused }, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_window_focused }, unable);
        // View.EMPTY_STATE_SET
        drawable.addState(new int[] {}, normal);
        return drawable;
    }

    /**
     * 根据不同状态drawable获取StateListDrawable
     * @param context 上下文
     * @param normalDrawable normal drawable，不需要设置传入null
     * @param pressedDrawable pressed drawable，不需要设置传入null
     * @param focusedDrawable focused drawable，不需要设置传入null
     * @param unableDrawable unable drawable，不需要设置传入null
     * @return StateListDrawable
     */
    public static StateListDrawable getStateListDrawable(Context context, Drawable normalDrawable, Drawable pressedDrawable, Drawable focusedDrawable, Drawable unableDrawable) {
        StateListDrawable drawable = new StateListDrawable();
        // View.PRESSED_ENABLED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressedDrawable);
        // View.ENABLED_FOCUSED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focusedDrawable);
        // View.ENABLED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_enabled }, normalDrawable);
        // View.FOCUSED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_focused }, focusedDrawable);
        // View.WINDOW_FOCUSED_STATE_SET
        drawable.addState(new int[] { android.R.attr.state_window_focused }, unableDrawable);
        // View.EMPTY_STATE_SET
        drawable.addState(new int[] {}, normalDrawable);
        return drawable;
    }

    /**
     * 根据Color id获取ColorStateList
     * @param normalColorId normal color id
     * @param pressedColorId pressed color id
     * @param focusedColorId focused color id
     * @param unableColorId unable color id
     * @return ColorStateList
     */
    public static ColorStateList getColorStateList(int normalColorId, int pressedColorId, int focusedColorId, int unableColorId) {
        int[] colors = new int[] { pressedColorId, focusedColorId, normalColorId, focusedColorId, unableColorId, normalColorId};
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
