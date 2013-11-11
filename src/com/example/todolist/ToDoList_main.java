package com.example.todolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.*;

public class ToDoList_main extends Activity {
	
	public static final String DATA_TITEL = "titel";
	public static final String DATA_COMMENT = "beschreibung";
	public static final String DATA_SPINNER = "spinner";
	
	//Wird beim start einer Subactivity mitgegeben, um Antworten interpretieren zu koennen
	public static final int REQUEST_CODE_DETAIL_MAIN = 30;
	public static final int ADD_REQUEST_CODE = 10;
	public static final int SETTINGS_REQUEST_CODE = 20;
	
	//Hier werden die titel und beschreibungen gespeichert
	static ArrayList<String> values = new ArrayList<String>();
	static ArrayList<String> comment = new ArrayList<String>();
	static ArrayList<Integer> spinnerposition = new ArrayList<Integer>();
	
	//Um beim start der activity dem benutzer ein beispiel zu zeigen
	String beispiel_titel = "Neuer Eintrag";
	String beispiel_beschreibung ="";
	int beispiel_spinner = 0;
	
	// Speichern die positionen der titel-, beschreibungs- und spinnerwerte
	int spinnerPosition;
	int positionString;
	int commentPostionString;
	
	//ListView und Adapter
	private ListView todoListView;
	private ArrayAdapter<String> arrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list_main);
		
		
		// List View und Adapter Initialisierung
		todoListView = (ListView) findViewById(R.id.listView_todoList);
		arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, values);
		todoListView.setAdapter(arrayAdapter);
		todoListView.setOnItemClickListener(todoListViewListener);
		
		// die Beispiele werden hinzugefuegt
		values.add(beispiel_titel);
		comment.add(beispiel_beschreibung);
		spinnerposition.add(beispiel_spinner);
		
				
	}
		
		/**
		 * Wenn ein Item in der ListView ausgewaehlt wird
		 */
		OnItemClickListener todoListViewListener = new OnItemClickListener() {
		  @Override
		  public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		    
			// Positionen von titel und beschreibung 
			String s =values.get(position);
			positionString = position;
			
			String c = comment.get(position);
			commentPostionString = position;
			
			int a = spinnerposition.get(position);
			spinnerPosition = position;
			
			
			// Detail_main wird mit den Daten des Items aufgerufen			
		    Intent intent = new Intent (ToDoList_main.this, Detail_main.class);
		    intent.putExtra(DATA_TITEL , s);
		    intent.putExtra(DATA_COMMENT , c);
		    intent.putExtra(DATA_SPINNER, a );
		    startActivityForResult(intent, REQUEST_CODE_DETAIL_MAIN);
		   }
		};
		

	
		
	
	/**
	 * Menu wird aufgebaut
	 */
	@Override	
	public boolean onCreateOptionsMenu(Menu menu) {
		
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.to_do_list_main, menu);
	    return true;
		}
	
	
	/**
	 * Wenn ein Menu-Item ausgewaehlt wird
	 */
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
	    
	    case R.id.delete_last:
	    	if (!values.isEmpty()) {
	    		values.remove(positionString);
	    		comment.remove(commentPostionString);
	    		spinnerposition.remove(spinnerPosition);
	    		
	    		arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, values);
				todoListView.setAdapter(arrayAdapter);
	    	}
	    		
	    	
	    	
	    return true;
	        
	    default:
	    	
	    return super.onOptionsItemSelected(item);
			}
		}
	
	private void startDetailActivity() {

        Intent intent = new Intent(this, Detail_main.class);
        //Start der Detail_main
        startActivityForResult(intent, ADD_REQUEST_CODE);
        
        }
	
	private void startSettingsActivity() {
		
		Intent intent = new Intent(this, Setting_main.class);
		//Start der Setting_main
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
					values.add(data.getExtras().getString("edit_titel"));
					comment.add(data.getExtras().getString("edit_beschreibung"));
					spinnerposition.add(data.getExtras().getInt("spinner"));
					
					arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, values);
					todoListView.setAdapter(arrayAdapter);
				
				} else {
					Toast.makeText(ToDoList_main.this,"Could not add item!",Toast.LENGTH_LONG).show();
				}
				
			break;
				
			//Wenn die SettingsActivity_main ihre veraenderte Schriftgroesse zurueck gibt
			case SETTINGS_REQUEST_CODE:
				
				// RESULT_OK konnte von SettingsActivity erfolgreich zurueckgegeben werden oder nicht
				
				if (resultCode == RESULT_OK) {
					String resultsetting = data.getStringExtra(Setting_main.RESULT_KEY);
				} else {
					Toast.makeText(ToDoList_main.this,"Could not change settings!",Toast.LENGTH_LONG).show();
				}
				
			 break;
			
			//Wenn in Detail_main etwas veraendert wurde, ein Item in ListView wurde ausgewaehlt und hier wird 
			 //die Antwort von Detail_main verarbeitet.
			case REQUEST_CODE_DETAIL_MAIN:
				
				if(resultCode == RESULT_OK) {
								
				
					values.set(positionString, (data.getExtras().getString("edit_titel")));
					comment.set(commentPostionString, (data.getExtras().getString("edit_beschreibung")));
					spinnerposition.set(spinnerPosition, (data.getExtras().getInt("spinner")));
					
					arrayAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, values);
					todoListView.setAdapter(arrayAdapter);
				
			} else {
					Toast.makeText(ToDoList_main.this,"Could not edit task!",Toast.LENGTH_LONG).show();
				}
				
			break;
			
			default: 
					
					Toast.makeText(ToDoList_main.this,"Could not transfer data",Toast.LENGTH_LONG).show();
			
			
			break;
			}
		}

	}
	
	
	
	
	
	
	
	
		
