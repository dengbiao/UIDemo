package com.alloyteam.android.uidemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.alloyteam.android.uidemo.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_button)
public class ButtonActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
	}

}
