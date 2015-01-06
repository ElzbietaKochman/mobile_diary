package com.example.diary.opiekun;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;
import com.example.diary.uczen.UczenStatystykiSrednieActivity;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class OpiekunStatystykiSrednieActivity extends Activity {

	List<Map<String, String>> result = null, result2= null, sredniaRoku=null;
	String id_ucznia, status, srednia, rok, statusW, rokW, cos;
	String rok_szkolny;
	Intent i;
	Bundle b;
	int wynik;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_statystyki_srednie);
		id_ucznia = getIntent().getExtras().getString("id");

		TableLayout statystyki = (TableLayout) findViewById(R.id.tabelka);
		statystyki.setStretchAllColumns(true);
		statystyki.setColumnShrinkable(0, true);
		statystyki.setColumnShrinkable(1, true);
		statystyki.bringToFront();

		// dla ogolnego toku nauki
		result = Utilities.query(this, "sredniaSzkolna",id_ucznia,"semestralna");
		TableRow tr = new TableRow(this);
		TextView tv1 = new TextView(this);
		try{
			srednia = result.get(0).get("srednia");
		}
		catch(IndexOutOfBoundsException e){
			srednia = "Brak œredniej.";
		}

		tv1.setText("Œrednia ocen semestralnych\nz ca³ego toku nauki:");
		TextView tv2 = new TextView(this);
		tv2.setText(srednia.substring(0, 4));
		tr.addView(tv1); 
		tr.addView(tv2);
		statystyki.addView(tr);

		result = Utilities.query(this, "sredniaSzkolna",id_ucznia,"koncowa");
		TableRow trk = new TableRow(this);
		TextView tv1k = new TextView(this);
		try{
			srednia = result.get(0).get("srednia");
		}
		catch(IndexOutOfBoundsException e){
			srednia = "Brak œredniej.";
		}

		tv1k.setText("Œrednia ocen koñcowych\nz ca³ego toku nauki:");
		TextView tvk = new TextView(this);
		tvk.setText(srednia.substring(0, 4));
		trk.addView(tv1k); 
		trk.addView(tvk);
		statystyki.addView(trk);

		// wybór i zapis do tabelki dynamicznie
		TableLayout statystykiD = (TableLayout) findViewById(R.id.tabelkaDynamiczna);
		statystykiD.setStretchAllColumns(true);
		statystykiD.setColumnShrinkable(0, true);
		statystykiD.setColumnShrinkable(1, true);
		statystykiD.bringToFront();

		// lista lat z bazy
		result2 = Utilities.query(this, "getRokSz");
		ArrayList<String> lista = new ArrayList<String>();
		int b = result2.size();

		for(int a=0;a<b;a++){
			rok_szkolny = result2.get(a).get("rok_szkolny");
			lista.add(a, rok_szkolny );
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		Spinner spinner = (Spinner)findViewById(R.id.wybierzRok);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				rok = parent.getItemAtPosition(position).toString();	
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), "Wybierz rok", Toast.LENGTH_SHORT).show();
			}
		});

		// lista lat z bazy
		ArrayList<String> statusLista = new ArrayList<String>();
		statusLista.add(0,"semestralna");
		statusLista.add(1,"koncowa");
		ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statusLista);
		Spinner spinnerStatus = (Spinner)findViewById(R.id.wybierzStatus);
		spinnerStatus.setAdapter(adapterStatus);
		spinnerStatus.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				status = parent.getItemAtPosition(position).toString();	
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		// pobieranie sredniej ocen semestralnej/koncowej z przedmiotów z danego roku		

		statusW = getIntent().getExtras().getString("status");
		if(statusW.equals("brak")){
		}
		else {
			rokW = getIntent().getExtras().getString("rokW");
			sredniaRoku = Utilities.query(this, "sredniaRoku", id_ucznia, rokW, statusW);
			wynik = sredniaRoku.size();
			if(wynik>0 ){

				TableRow td = new TableRow(this);
				TextView tvd = new TextView(this);
				tvd.setText("Rok szkolny: ");
				td.addView(tvd);

				TextView rokWy = new TextView(this);
				rokWy.setText(rokW);
				td.addView(rokWy);

				TableRow td2 = new TableRow(this);
				TextView tvd2 = new TextView(this);
				tvd2.setText("Œrednia ");
				td2.addView(tvd2);

				TextView statusD = new TextView(this);
				statusD.setText(statusW);
				td2.addView(statusD);

				TableRow td3 = new TableRow(this);
				TextView tvd3 = new TextView(this);
				tvd3.setText("Wynosi ");
				td3.addView(tvd3);
				
				if(sredniaRoku.get(0).get("srednia")==null){
					TextView srD = new TextView(this);
					srD.setText("Brak œredniej.");
					td3.addView(srD);

				}
				else {
					TextView srD = new TextView(this);
					srD.setText(sredniaRoku.get(0).get("srednia").substring(0, 4));
					td3.addView(srD);

				}
				statystykiD.addView(td);
				statystykiD.addView(td2);
				statystykiD.addView(td3);
			}
			else {
				TableRow td3 = new TableRow(this);
				TextView tvd3 = new TextView(this);
				tvd3.setText("Nie znaleziono szukanej œredniej.");
				td3.addView(tvd3);
				statystykiD.addView(td3);
			}
		}
	}

	public void onWyszukaj(View v){
		i = new Intent(this, OpiekunStatystykiSrednieActivity.class);
		//b = getIntent().getExtras();
		i.putExtra("id",id_ucznia);
		i.putExtra("rokW", rok);
		i.putExtra("status", status);
		startActivity(i);
	}
}

