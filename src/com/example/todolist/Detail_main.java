package com.example.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Detail_main extends Activity implements OnItemSelectedListener {

	public static final String RESULT_KEY = "adding";
	
	private Spinner prioritySpinner;
	private EditText titel;
	private EditText beschreibung;
	private Button 	deleteButton;
	private Button	saveButton;
	
	
	private String[] priority;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
	
	//Spinner	
	prioritySpinner = (Spinner) findViewById(R.id.spinner_priority);
	
	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	        R.array.array_priority, android.R.layout.simple_spinner_item);
	
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	prioritySpinner.setAdapter(adapter);
	prioritySpinner.setOnItemSelectedListener(this);
	
	//EditText
	titel = (EditText) findViewById(R.id.editText_titel);
	titel.setOnClickListener(titelListener);
	
	deleteButton = (Button) findViewById(R.id.button_delete);
	deleteButton.setOnClickListener(deleteListener);
	
	saveButton = (Button) findViewById(R.id.button_save);
	saveButton.setOnClickListener(saveListener);
	
	beschreibung = (EditText) findViewById(R.id.editText_beschreibung);
	beschreibung.setOnClickListener(beschreibungListener);
	
	
	} 
	
	OnClickListener deleteListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			titel.setText("");
			beschreibung.setText("");
			
		}
	};
	
	OnClickListener saveListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			//TODO
		}
	};
	
	OnClickListener titelListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			//TODO
		}
	};
	
	OnClickListener beschreibungListener = new OnClickListener()
	{
		public void onClick(View v)
		{
			//TODO
		}
	};
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		
		int index = parent.getSelectedItemPosition();
		// storing string resources into Array 
		priority = getResources().getStringArray(R.array.array_priority);
		Toast.makeText(getBaseContext(), "You have selected : " +priority[index], Toast.LENGTH_SHORT).show();
		
        }
	
	 public void onNothingSelected(AdapterView<?> parent) {
	        // Do nothing
	    }
	 
	
	
	public void addfinish() {
		
		Intent resultData = new Intent();
		resultData.putExtra(RESULT_KEY, "Eingabe User");
		setResult(RESULT_OK, resultData);
		}
	
}
