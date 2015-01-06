package com.example.diary.nauczyciel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.example.diary.*;
import com.example.diary.wychowawca.WychowawcaNieobecnoscActivity;
import com.example.diary.wychowawca.WychowawcaUsprawiedliwActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NauczycielDownloadActivity extends Activity {

	List<Map<String, String>> getGrupa = null, getPoziom=null, zlicz=null;
	String id_grupy="", id_nauczyciela="", id_ucznia="", nazwaUcznia="", rokSzkolny="", wybranaKlasa="";
	Intent i;
	Bundle bundle;
	int iloscKlas;
	List<String> klasy;
	static int flaga;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_download);
		flaga = -1;
		id_nauczyciela = getIntent().getExtras().getString("id");
		rokSzkolny = getIntent().getExtras().getString("rok_szkolny");
		Log.d("id", getIntent().getExtras().getString("id"));
		//getWycho = Utilities.query(this, "getWycho", id_nauczyciela);
		//id_grupy = getWycho.get(0).get("id_grupy");
		//Log.d("id_grupy", id_grupy);
		//getGrupa = Utilities.query(this, "getGrupa", id_grupy);
		getPoziom = Utilities.query(this, "getPoziom",rokSzkolny);
		iloscKlas = getPoziom.size();
		bundle = getIntent().getExtras();
		klasy = new ArrayList<String>();
		for(int m=0;m<iloscKlas;m++){
			klasy.add(getPoziom.get(m).get("poziom"));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, klasy);
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				wybranaKlasa = parent.getItemAtPosition(position).toString();
				flaga = position;
				createKlasa(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), "Wybierz klasê", Toast.LENGTH_SHORT).show();				
			}
		});

		
	}
	
	public void createKlasa(int position){
		
		Log.d("getPoziom",getPoziom.get(position).get("id_grupy"));
			getGrupa = Utilities.query(this, "getGrupa",getPoziom.get(position).get("id_grupy"));
			
			TableLayout nieobecnosc = (TableLayout) findViewById(R.id.tabelka);
			nieobecnosc.setStretchAllColumns(true);
			nieobecnosc.setColumnShrinkable(0, true);
			nieobecnosc.setColumnShrinkable(1, true);
			nieobecnosc.bringToFront();

			TableRow tr0 = new TableRow(this);
			TextView tv0 = new TextView(this);
			tv0.setText("Uczeñ");
			tr0.addView(tv0);
			TextView tv0a = new TextView(this);
			tv0a.setText("Przes³anych plików ");
			tr0.addView(tv0a);
			nieobecnosc.addView(tr0);


			for(int a=0; a<getGrupa.size();a++){
				final int b = a;
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this);
				tv.setText(getGrupa.get(a).get("nazwa"));
				Log.d("nazwa", getGrupa.get(a).get("nazwa"));
				id_ucznia = getGrupa.get(a).get("id_ucznia");

				nazwaUcznia = getGrupa.get(a).get("nazwa");
				tr.addView(tv);

				zlicz = Utilities.query(this, "zliczPliki", id_ucznia, rokSzkolny);
				TextView tv2 = new TextView(this);
				tv2.setText(zlicz.get(0).get("liczba"));
				tr.addView(tv2);
				tr.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						i = new Intent(NauczycielDownloadActivity.this, NauczycielWybierzPlikActivity.class);
						id_ucznia = getGrupa.get(b).get("id_ucznia");
						nazwaUcznia = getGrupa.get(b).get("nazwa");
						i.putExtra("id_ucznia", id_ucznia);
						i.putExtra("nazwaUcznia",nazwaUcznia);
						i.putExtras(bundle);
						Log.d("Uczen: ",nazwaUcznia);
						startActivity(i);
					}
				});


				nieobecnosc.addView(tr);
			}

		}
	
}

