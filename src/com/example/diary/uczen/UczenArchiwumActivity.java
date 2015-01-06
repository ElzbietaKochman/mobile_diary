package com.example.diary.uczen;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class UczenArchiwumActivity extends Activity {
	static String rokW = "puste";
	String rok_szkolny;
	Intent i;
	List<Map<String, String>> result = null;
	Bundle b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_archiwum);
		result = Utilities.query(this, "getRokSz");
		ArrayList<String> lista = new ArrayList<String>();
		int b = result.size();

		for(int a=0;a<b;a++){
			rok_szkolny = result.get(a).get("rok_szkolny");
			lista.add(a, rok_szkolny );
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		Spinner spinner = (Spinner)findViewById(R.id.wybranyRok);
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
				
			}
		});
		
		
	}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.archiwum_student, menu);
			return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}
		public void onOceny(View v){
			i = new Intent(this,UczenArchiwumOcenyActivity.class);
			b = getIntent().getExtras();
			i.putExtra("rokW", rokW);
			i.putExtras(b);
			//i.putExtra("id_ucznia",getIntent().getExtras().getString("id"));
			//i.putExtra("rok_szkolny", rokW);
			startActivity(i);
		}

		public void onOgloszenia(View v){
			i = new Intent(this,UczenArchiwumOgloszeniaActivity.class);
			Bundle bundle = getIntent().getExtras();
			i.putExtra("rokW", rokW);
			i.putExtras(bundle);
			startActivity(i);
		}
		public void onUwagi(View v){
			i = new Intent(this,UczenArchiwumUwagiActivity.class);
			Bundle bundle = getIntent().getExtras();
			i.putExtras(bundle);
			i.putExtra("rokW", rokW);
			
			startActivity(i);
		}
		public void onObecnosci(View v){
			i = new Intent(this,UczenArchiwumObecnosciActivity.class);
			Bundle bundle = getIntent().getExtras();
			i.putExtra("rokW", rokW);
			i.putExtras(bundle);
			startActivity(i);
		}
	}
