package com.example.diary.nauczyciel;
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class NauczycielObecnoscActivity extends Activity {

	TextView przedmiot,klasa;
	Intent i;
	String id_nauczyciela="", klasaW="", rok_szkolny="", przedmiotW="", id_grupy="", nazwaGrupy="", id_przedmiotu="";
	ArrayList<String> listaPrzedmiotow= new ArrayList<String>();
	ArrayList<String> listaKlas= new ArrayList<String>();
	ArrayList<String> listaGrup= new ArrayList<String>();
	int rozmiarPrzedmiot;
	Bundle bundle ;
	List<Map<String, String>> getNauczycielPrzedmiot=null, getKlasaPoziom=null, getGrupa=null;
	TextView info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_obecnosc);
		info = (TextView)findViewById(R.id.info);
		i = new Intent(NauczycielObecnoscActivity.this,NauczycielNieobecnoscActivity.class);
		bundle = getIntent().getExtras();
		
		
		id_nauczyciela = getIntent().getExtras().getString("id");
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");

		getNauczycielPrzedmiot = Utilities.query(this, "getNauczycielPrzedmiot",id_nauczyciela, rok_szkolny);

		rozmiarPrzedmiot = getNauczycielPrzedmiot.size();
		for(int a=0;a<rozmiarPrzedmiot;a++){
			listaPrzedmiotow.add(a,getNauczycielPrzedmiot.get(a).get("nazwa"));
		}

		ArrayAdapter<String> adapterPrzedmiot = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPrzedmiotow);
		Spinner przedmiot = (Spinner)findViewById(R.id.przedmioty);
		przedmiot.setAdapter(adapterPrzedmiot);
		przedmiot.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				przedmiotW =parent.getItemAtPosition(position).toString();

				for(int a=0;a<rozmiarPrzedmiot;a++){
					if(getNauczycielPrzedmiot.get(a).get("nazwa").equals(przedmiotW)){
						nazwaGrupy = getNauczycielPrzedmiot.get(a).get("poziom");
						id_przedmiotu = getNauczycielPrzedmiot.get(a).get("id_przedmiotu");

						listaGrup.add(nazwaGrupy);
					}
				}


				ArrayAdapter<String> adapterKlasa = new ArrayAdapter<String>(NauczycielObecnoscActivity.this, android.R.layout.simple_spinner_item, listaGrup);
				Spinner klasa = (Spinner)findViewById(R.id.klasy);
				klasa.setAdapter(adapterKlasa);
				klasa.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						klasaW =parent.getItemAtPosition(position).toString();
											}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
					}
				});	
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void onWybierz(View v){
		

		TableLayout oceny = (TableLayout) findViewById(R.id.tabelka);
		oceny.setStretchAllColumns(true);
		oceny.setColumnShrinkable(0, true);
		oceny.setColumnShrinkable(1, true);
		oceny.bringToFront();
		info.setText("Wybierz ucznia, któremu chcesz dodaæ\nnieobecnosœæ na zajêciach");
		for(int a=0;a<rozmiarPrzedmiot;a++){
			if(getNauczycielPrzedmiot.get(a).get("poziom").equals(klasaW)){
				id_grupy = getNauczycielPrzedmiot.get(a).get("id_grupy");
			}
		}
		
		getGrupa = Utilities.query(NauczycielObecnoscActivity.this, "getGrupa",id_grupy);
		
		for(int b=0;b<getGrupa.size();b++){
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText(getGrupa.get(b).get("nazwa"));
			String id_ucznia = getGrupa.get(b).get("id_ucznia");
			i.putExtra("id_ucznia", id_ucznia);
			i.putExtra("id_przedmiotu", id_przedmiotu);
			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					i.putExtras(bundle);
					startActivity(i);
				}
			});
			tr.addView(tv);
			oceny.addView(tr);
		}
		
		
		
		
	}
}
