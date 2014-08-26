package com.alloyteam.android.uidemo;

import android.app.Activity;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class ButtonActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
	}

}
