package com.example.diary.uczen;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

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

public class UczenStatystykiKoncoweActivity extends Activity {

	List<Map<String, String>> ocenaRoku=null, result2=null, ocenaSem = null, getPrzedmiot=null, ocenaKoncowa= null;
	String rok_szkolny, rok, id_ucznia, rokW, id_przedmiotu, nazwaPrzedmiotu, oS, oK;
	int semRozmiar;
	Intent i;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_statystyki_koncowe);
		
		id_ucznia= getIntent().getExtras().getString("id");
		rokW = getIntent().getExtras().getString("rokW");
		
		//tworzenie listy lat, spinnera
		result2 = Utilities.query(this, "getRokSz");
		getPrzedmiot = Utilities.query(this, "getUczenPrzedmioty", id_ucznia);
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
		
		if(rokW.equals("brak")){
			
		}
		else {
			TableLayout koncowa = (TableLayout) findViewById(R.id.tabelka);
			koncowa.setStretchAllColumns(true);
			koncowa.setColumnShrinkable(0, true);
			koncowa.setColumnShrinkable(1, true);
			koncowa.bringToFront();
			
			ocenaKoncowa = Utilities.query(this, "ocenaKoncowa", id_ucznia, rokW);
			semRozmiar = ocenaKoncowa.size();
			if(semRozmiar>0){
				
				
				TableRow tr = new TableRow(this);
				TextView naz = new TextView(this);
				naz.setText("Przedmiot ");
				tr.addView(naz);
				TextView naz1 = new TextView(this);
				naz1.setText("Ocena\nsemestralna ");
				tr.addView(naz1);
				TextView naz2 = new TextView(this);
				naz2.setText("Ocena\nkoñcowa ");
				tr.addView(naz2);
				koncowa.addView(tr);
				
				for(int a = 0; a<semRozmiar;a++){
					id_przedmiotu = ocenaKoncowa.get(a).get("id_przedmiotu");
					
					for(Map<String,String> element : getPrzedmiot){
						if(element.get("id_przedmiotu").equals(id_przedmiotu)){
							nazwaPrzedmiotu = element.get("nazwa");
						}
					}
					
					oS = ocenaKoncowa.get(a).get("semestralna");
					oK = ocenaKoncowa.get(a).get("koncowa");
					
					TableRow tr1 = new TableRow(this);
					TextView nazwa = new TextView(this);
					nazwa.setText(nazwaPrzedmiotu);
					tr1.addView(nazwa);
					
					TextView ocenaSem1 = new TextView(this);
					ocenaSem1.setText(oS);
					tr1.addView(ocenaSem1);
					
					TextView ocenaKon1 = new TextView(this);
					ocenaKon1.setText(oK);
					tr1.addView(ocenaKon1);
					
					koncowa.addView(tr1);
				}
			}
			else{

				TableRow tr1 = new TableRow(this);
				TextView nazwa = new TextView(this);
				nazwa.setText("Brak ocen do wyœwietlenia.");
				tr1.addView(nazwa);
				koncowa.addView(tr1);
			}
			
		}
	}
	
	public void onKoncowe(View v){
		i = new Intent(this, UczenStatystykiKoncoweActivity.class);
		//b = getIntent().getExtras();
		i.putExtra("rokW", rok);
		i.putExtra("id", id_ucznia);
		startActivity(i);
	}
}
