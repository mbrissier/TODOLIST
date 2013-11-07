package com.example.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.content.*;

public class Detail_main extends Activity {

	public static final String RESULT_KEY = "adding";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	public void addfinish() {
		
		Intent intent = new Intent();
		Intent resultData = null;
		resultData.putExtra(RESULT_KEY, "Eingabe User");
		setResult(RESULT_OK, resultData);
	}
	
}
