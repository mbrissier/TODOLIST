package com.example.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

public class Setting_main extends PreferenceActivity {
	
	public static final String RESULT_KEY = "setting";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);        
        addPreferencesFromResource(R.xml.preferences); 
        
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        StringBuilder builder = new StringBuilder();

        builder.append("\n" + sharedPrefs.getString("text_size", "NULL"));
        
        //TODO Textsize String in dimens xml schreiben
        
    }
    
    public void settingfinish() {
		
		Intent resultData = new Intent();
		resultData.putExtra(RESULT_KEY, "Eingabe User");
		setResult(RESULT_OK, resultData);
		}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "Show current settings");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(this, Setting_main.class));
                return true;
        }
        return false;
    }
    
}