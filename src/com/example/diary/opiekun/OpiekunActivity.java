package com.example.diary.opiekun;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OpiekunActivity extends Activity {
	TextView user;
	List<Map<String, String>> result = null, getUczen=null, getUczenId=null;
	String username, wybranyUczen, id_opiekuna,id_ucznia, imie, imieW;
	Context context;
	Intent i;
	static int pozycja;
	int relacja;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun);
		
		user = (TextView) findViewById(R.id.textView1);
		username = getIntent().getExtras().getString("nazwa");
		this.user.setText("Zalogowany u¿ytkownik:\n"+username);
		id_opiekuna = getIntent().getExtras().getString("id");
		result = Utilities.query(this, "getRelacja",id_opiekuna);
		relacja = result.size();
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> listaID = new ArrayList<String>();
		for(int x = 0;x<relacja;x++){
			getUczen = Utilities.query(this, "getUczen",result.get(x).get("id_ucznia"));
			imie = getUczen.get(0).get("nazwa");
			lista.add(x,imie);
		}
			
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imieW = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), "Wybierz ucznia", Toast.LENGTH_SHORT).show();				
			}
		});
		
	}

	
	public void onOcena(View view){
		i = new Intent(this, OpiekunOcenaActivity.class);
		getUczenId = Utilities.query(this, "getUczenId", imieW);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("imieW", imieW);
		i.putExtra("id", getUczenId.get(0).get("id_ucznia"));
		startActivity(i);
	}
	public void onUwaga(View view){
		i = new Intent(this, OpiekunUwagaActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("imieW", imieW);
		startActivity(i);
	}
	public void onNieobecnosc(View view){
		i = new Intent(this, OpiekunNiebecnoscActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("imieW", imieW);
		startActivity(i);
	}
	public void onOgloszenie(View view){
		i = new Intent(this, OpiekunOgloszenieActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("imieW", imieW);
		startActivity(i);
	}
	public void onArchiwum(View view){
		i = new Intent(this, OpiekunArchiwumActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("imieW", imieW);
		startActivity(i);
	}
	public void onStatystyki(View view){
		i = new Intent(this, OpiekunStatystykiActivity.class);
		getUczenId = Utilities.query(this, "getUczenId", imieW);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("id", getUczenId.get(0).get("id_ucznia"));
		Log.d("id z on statystyki", getUczenId.get(0).get("id_ucznia"));
		startActivity(i);
	}
	public void onKomunikator(View view){
		i = new Intent(this, OpiekunKomunikatorActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("imieW", imieW);
		startActivity(i);
	}
	
	public void onZwolnienie(View view){
		i = new Intent(this, OpiekunZwolnienieActivity.class);
		getUczenId = Utilities.query(this, "getUczenId", imieW);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("id_ucznia", getUczenId.get(0).get("id_ucznia"));
		startActivity(i);
	}
}
