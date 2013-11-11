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

	// RESULT wird den Daten mitgegeben, damit ToDoList_main die Daten
	// identifizieren kann.
	public static final String RESULT_KEY = "adding";
	public static final String RESULT_KEY_EDIT_TITEL = "edit_titel";
	public static final String RESULT_KEY_EDIT_BESCHREIBUNG = "edit_beschreibung";
	
	

	private Spinner prioritySpinner;
	private EditText titel;
	private EditText beschreibung;
	private Button deleteButton;
	private Button saveButton;

	// Die Auswahlmoeglichkeiten, definiert in strings.xml, im Spinner werden in
	// priority gespeichert
	String[] priority;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		// Spinner
		prioritySpinner = (Spinner) findViewById(R.id.spinner_priority);
		// Spinner adapter
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.array_priority,
				android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		prioritySpinner.setAdapter(adapter);
		prioritySpinner.setOnItemSelectedListener(this);

		// EditText
		titel = (EditText) findViewById(R.id.editText_titel);
		beschreibung = (EditText) findViewById(R.id.editText_beschreibung);

		// Loesch-Button
		deleteButton = (Button) findViewById(R.id.button_delete);
		deleteButton.setOnClickListener(deleteListener);

		// Speicher_Button
		saveButton = (Button) findViewById(R.id.button_save);
		saveButton.setOnClickListener(saveListener);

		// Nimmt die Daten von ToDoList_main und uebergibt es den views von
		// Detail_main
		Intent intent = getIntent();
		Bundle b = intent.getExtras();

		if (b != null) {
			String c = (String) b.get("beschreibung");
			String s = (String) b.get("titel");
			int a = b.getInt("spinner");

			titel.setText(s);
			beschreibung.setText(c);
			prioritySpinner.setSelection(a);

		}

	}

	/**
	 * Delete Button Mehtode
	 */
	OnClickListener deleteListener = new OnClickListener() {
		public void onClick(View v) {
			
			
			titel.setText("");
			beschreibung.setText("");
			
		}
	};

	/**
	 * Save Button Methode Uebergibt die Daten einem Intent und startet
	 * ToDOList_main, dort wird dann die Methode onActivityResult gestartet!
	 */
	OnClickListener saveListener = new OnClickListener() {
		public void onClick(View v) {
			
			if (((titel.getText().toString().isEmpty()) && (beschreibung.getText().toString()).isEmpty()) ) { 
				Toast.makeText(Detail_main.this,"Nothing to save!",Toast.LENGTH_LONG).show();
			} else {
			Intent data = new Intent();
			data.putExtra(RESULT_KEY_EDIT_TITEL, titel.getText().toString());
			data.putExtra(RESULT_KEY_EDIT_BESCHREIBUNG, beschreibung.getText()
					.toString());
			data.putExtra("spinner", prioritySpinner.getSelectedItemPosition());
			setResult(RESULT_OK, data);
			finish();
			}
		}
	};

	@Override
	public void finish() {
		super.finish();
	}

	/**
	 * Hier werden die Spinner values in einem String array "priority"
	 * gespeichert
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		// storing string resources into Array
		priority = getResources().getStringArray(R.array.array_priority);

	}

	/**
	 * Muss auch implementiert werden wegen "implements OnItemSelectedListener"
	 */
	public void onNothingSelected(AdapterView<?> parent) {
		// Do nothing
	}

}
