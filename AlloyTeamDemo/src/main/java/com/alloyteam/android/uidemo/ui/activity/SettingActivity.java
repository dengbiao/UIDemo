package com.alloyteam.android.uidemo.ui.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alloyteam.android.uidemo.R;
import com.alloyteam.android.uidemo.ui.view.SettingLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_setting)
public class SettingActivity extends Activity{

    @ViewById(R.id.settinglayout)
    SettingLayout mSettingLayout;

    int[] icons = new int[] {R.drawable.icon0, R.drawable.icon1, R.drawable.atm_checkbox_circle_checked_blue, R.drawable.icon3};
    String[] itemTexts = new String[] {"吃喝玩乐", "附近的群", "应用宝", "腾讯新闻"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
	}

    @AfterViews
    void init() {
        ArrayList<SettingLayout.SettingItem> items = new ArrayList<SettingLayout.SettingItem>();
        Drawable indicator = getResources().getDrawable(R.drawable.atm_icon_indicator);
        for(int i=0;i<icons.length;i++) {
            Drawable drawable = getResources().getDrawable(icons[i]);
            SettingLayout.SettingItem item = mSettingLayout.new SettingItem(this, drawable, itemTexts[i], indicator, i);
            items.add(item);
        }
        mSettingLayout.setSettingItems(items);
        mSettingLayout.setOnSettingItemClickListener(new SettingLayout.OnSettingItemClickListener() {
            @Override
            public void onItemClick(int position, View v, int group) {
                Toast.makeText(SettingActivity.this, "clicked psoition:" + position + " group:" + group, Toast.LENGTH_SHORT).show();
            }
        });
//        mSettingLayout.setShowLogo(false);
    }

}
