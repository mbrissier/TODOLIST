package com.example.todolist;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ToDoList_main extends Activity {
	
	public static final int ADD_REQUEST_CODE = 10;
	public static final int SETTINGS_REQUEST_CODE = 20;
	
	//Warum wird adapter nicht benutzt, ich mach das doch da unten in der onCreate?
	private ArrayAdapter <String> adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list_main);
		
		adapter= new ArrayAdapter <String> (this, android.R.layout.activity_list_item);
	}
				
	
	@Override
    protected void onStart() {
     super.onStart();
     // TODO Auto-generated method stub
    	}
	
	@Override
    protected void onResume() {
     super.onResume();
     // TODO Auto-generated method stub
    	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.to_do_list_main, menu);
	    return true;
		}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		switch (item.getItemId()) {
		
	    case R.id.add:
	    	
	    	// Start der Detail_main mit Rueckgabewert: Strings fuer task item
	    	startDetailActivity();
	    		    	    	    	
	    return true;
	    
	    case R.id.setting:
	    	// Start der SettingsActivity mit Rueckgabewert: dimens fuer Schriftgroesse
	    	startSettingsActivity();
	    	
	    return true;
	    
	        
	    default:
	    return super.onOptionsItemSelected(item);
			}
		}
	
	private void startDetailActivity() {

        Intent intent = new Intent(this, Detail_main.class);
        startActivityForResult(intent, ADD_REQUEST_CODE);
        
        }
	
	private void startSettingsActivity() {
		
		Intent intent = new Intent(this, SettingsActivity_main.class);
		startActivityForResult(intent, SETTINGS_REQUEST_CODE);
		}
	
	@Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode) {
		
			//Wenn die Detail_main ihre Eingabe fuer das Erstellen einer Task zurueck gibt
			case ADD_REQUEST_CODE: 
				
				// RESULT_OK konnte von Detail_main erfolgreich zurueckgegeben werden oder nicht
				
				if (resultCode == RESULT_OK) {
					String resultadd = data.getStringExtra(Detail_main.RESULT_KEY);
				
				} else {
					Toast.makeText(ToDoList_main.this,"Could not add item!",Toast.LENGTH_LONG).show();
				}
				
			//Wenn die SettingsActivity_main ihre veraenderte Schriftgroesse zurueck gibt
			case SETTINGS_REQUEST_CODE:
				
				// RESULT_OK konnte von SettingsActivity erfolgreich zurueckgegeben werden oder nicht
				
				if (resultCode == RESULT_OK) {
					String resultsetting = data.getStringExtra(SettingsActivity_main.RESULT_KEY);
				} else {
					Toast.makeText(ToDoList_main.this,"Could not change settings!",Toast.LENGTH_LONG).show();
				}
				
			default: 
					Toast.makeText(ToDoList_main.this,"Data transfer not possible!",Toast.LENGTH_LONG).show();
				
			}
		}
	
	
	
	
	
	
	
	
	
	}
