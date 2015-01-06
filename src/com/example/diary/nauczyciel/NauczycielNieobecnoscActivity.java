package com.example.diary.nauczyciel;

import java.util.ArrayList;


import com.example.diary.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NauczycielNieobecnoscActivity extends Activity {

	EditText godzPocz, godzKon;
	String id_ucznia, id_przedmiotu,status, poczatek, koniec, usprawiedliwione;
	ArrayList<String> statusLista;
	int dodano;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_nieobecnosc);
		
		godzPocz = (EditText)findViewById(R.id.godzPocz);
		godzKon = (EditText)findViewById(R.id.godzKon);
		
		id_ucznia= getIntent().getExtras().getString("id_ucznia");
		id_przedmiotu = getIntent().getExtras().getString("id_przedmiotu");
		
		statusLista = new ArrayList<String>();
		statusLista.add(0,"tak");
		statusLista.add(1,"nie");
		
		ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(NauczycielNieobecnoscActivity.this, android.R.layout.simple_spinner_item, statusLista);
		Spinner status = (Spinner)findViewById(R.id.status);
		status.setAdapter(adapterStatus);
		status.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
usprawiedliwione = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});	
		
	}
	
	public void onDodajNieobecnosc(View v){
		poczatek = godzPocz.getText().toString();
		koniec = godzKon.getText().toString();
		Log.d("pocz", poczatek);
		Log.d("kon", koniec);
		Log.d("uczen", id_ucznia);
		Log.d("przedmiot", id_przedmiotu);
		Log.d("usprawiedliwione", usprawiedliwione);
		dodano = Utilities.udi(this, "dodajNieobecnosc", id_ucznia, id_przedmiotu, poczatek, koniec, usprawiedliwione);
		if(dodano>0){
			Toast.makeText(this, "Dodano nieobecnoœæ ucznia do bazy.", Toast.LENGTH_SHORT).show();
		
		}
		else {
			Toast.makeText(this, "Dodano nieobecnoœæ ucznia do bazy.", Toast.LENGTH_SHORT).show();
		}
		godzPocz.setText("");
		godzKon.setText("");
	}
}
