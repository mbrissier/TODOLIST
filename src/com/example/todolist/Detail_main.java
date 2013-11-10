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
	public static final String RESULT_KEY_EDIT_TITEL = "edit_titel";
	public static final String RESULT_KEY_EDIT_BESCHREIBUNG = "edit_beschreibung";
	public static final String RESULT_KEY_DELETE = "delete";
	

	private Spinner prioritySpinner;
	private EditText titel;
	private EditText beschreibung;
	private Button deleteButton;
	private Button saveButton;

	private String[] priority;
	private static int priority_status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// Spinner
		prioritySpinner = (Spinner) findViewById(R.id.spinner_priority);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.array_priority,
				android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prioritySpinner.setAdapter(adapter);
		prioritySpinner.setSelection(priority_status);
		prioritySpinner.setOnItemSelectedListener(this);

		// EditText
		titel = (EditText) findViewById(R.id.editText_titel);
		

		deleteButton = (Button) findViewById(R.id.button_delete);
		deleteButton.setOnClickListener(deleteListener);

		saveButton = (Button) findViewById(R.id.button_save);
		saveButton.setOnClickListener(saveListener);

		beschreibung = (EditText) findViewById(R.id.editText_beschreibung);
		

		Intent intent = getIntent();
		Bundle b = intent.getExtras();

		if (b != null) {
			String c =(String)  b.get("beschreibung");
			String s = (String) b.get("titel");
			titel.setText(s);
			beschreibung.setText(c);
		}

	}

	OnClickListener deleteListener = new OnClickListener() {
		public void onClick(View v) {
			titel.setText("");
			beschreibung.setText("");
			Intent intent = new Intent(Detail_main.this, ToDoList_main.class);
			intent.putExtra(RESULT_KEY_DELETE , "delete");
			startActivity(intent);

		}
	};

	OnClickListener saveListener = new OnClickListener() {
		public void onClick(View v) {
			Intent data = new Intent();
			data.putExtra(RESULT_KEY_EDIT_TITEL, titel.getText().toString());
			data.putExtra(RESULT_KEY_EDIT_BESCHREIBUNG, beschreibung.getText().toString());
			priority_status= prioritySpinner.getSelectedItemPosition();
			String z = Integer.toString(priority_status);
//			priority_status= prioritySpinner.getSelectedItemPosition();
//			String pos = Integer.toString(priority_status);
//			data.putExtra("spinner", pos );
			setResult(RESULT_OK, data);
			
			finish();
		}
	};
	
	@Override
	public void finish() {
	  super.finish();
	} 


	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		
		// storing string resources into Array
		priority = getResources().getStringArray(R.array.array_priority);
		priority_status= prioritySpinner.getSelectedItemPosition();
		String z = Integer.toString(priority_status);
		Toast.makeText(Detail_main.this,z,Toast.LENGTH_LONG).show();
		

	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Do nothing
	}


}
