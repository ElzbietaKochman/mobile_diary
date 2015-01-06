package com.example.diary.opiekun;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;
import com.example.diary.uczen.UczenArchiwumObecnosciActivity;
import com.example.diary.uczen.UczenArchiwumOcenyActivity;
import com.example.diary.uczen.UczenArchiwumOgloszeniaActivity;
import com.example.diary.uczen.UczenArchiwumUwagiActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class OpiekunArchiwumActivity extends Activity {

	TextView tv;
	static String rokW = "puste";
	String rok_szkolny,imieW;
	Intent i;
	List<Map<String, String>> result = null, getUczenId = null;
	Bundle b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_archiwum);

		tv = (TextView)findViewById(R.id.textView1);
		imieW = getIntent().getExtras().getString("imieW");
		tv.setText("Wybrany uczen: "+imieW+"\nWybierz rok szkolny: ");
		result = Utilities.query(this, "getRokSz");
		ArrayList<String> lista = new ArrayList<String>();
		int b = result.size();

		for(int a=0;a<b;a++){
			rok_szkolny = result.get(a).get("rok_szkolny");
			lista.add(a, rok_szkolny );
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		Spinner spinner = (Spinner)findViewById(R.id.spinner);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				rokW = parent.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), "Wybierz rok", Toast.LENGTH_SHORT).show();
				rokW="";
			}
		});


	}

	public void onOceny(View v){
		i = new Intent(this,OpiekunArchiwumOcenaActivity.class);
		b = getIntent().getExtras();
		i.putExtra("rokW", rokW);
		i.putExtras(b);
		startActivity(i);
	}

	public void onOgloszenia(View v){
		i = new Intent(this,OpiekunArchiwumOgloszenieActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtra("rokW", rokW);
		i.putExtras(bundle);
		startActivity(i);
	}
	public void onUwagi(View v){
		i = new Intent(this,OpiekunArchiwumUwagaActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		i.putExtra("rokW", rokW);

		startActivity(i);
	}
	public void onObecnosci(View v){
		i = new Intent(this,OpiekunArchiwumNieobecnoscActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtra("rokW", rokW);
		i.putExtras(bundle);
		startActivity(i);
	}
}
