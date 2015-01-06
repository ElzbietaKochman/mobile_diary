package com.example.diary.nauczyciel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NauczycielOcenaWybierzActivity extends Activity {

	TextView przedmiot,klasa;
	Intent i;
	String id_nauczyciela="", klasaW="", rok_szkolny="", przedmiotW="", id_grupy="", nazwaGrupy="", id_przedmiotu;
	ArrayList<String> listaPrzedmiotow= new ArrayList<String>();
	ArrayList<String> listaKlas= new ArrayList<String>();
	ArrayList<String> listaGrup= new ArrayList<String>();
	int rozmiarPrzedmiot;

	List<Map<String, String>> getNauczycielPrzedmiot=null, getKlasaPoziom=null, getKlasaOceny=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_ocena_wybierz);

		id_nauczyciela = getIntent().getExtras().getString("id");
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");

		getNauczycielPrzedmiot = Utilities.query(this, "getNauczycielPrzedmiot",id_nauczyciela, rok_szkolny);

		rozmiarPrzedmiot = getNauczycielPrzedmiot.size();
		for(int a=0;a<rozmiarPrzedmiot;a++){
			listaPrzedmiotow.add(a,getNauczycielPrzedmiot.get(a).get("nazwa"));
		}

		ArrayAdapter<String> adapterPrzedmiot = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPrzedmiotow);
		Spinner przedmiot = (Spinner)findViewById(R.id.przedmiot);
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


				ArrayAdapter<String> adapterKlasa = new ArrayAdapter<String>(NauczycielOcenaWybierzActivity.this, android.R.layout.simple_spinner_item, listaGrup);
				Spinner klasa = (Spinner)findViewById(R.id.klasa);
				klasa.setAdapter(adapterKlasa);
				klasa.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						klasaW =parent.getItemAtPosition(position).toString();
						for(int a=0;a<rozmiarPrzedmiot;a++){
							if(getNauczycielPrzedmiot.get(a).get("poziom").equals(klasaW)){
								id_grupy = getNauczycielPrzedmiot.get(a).get("id_grupy");
							}
						}

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
	public void onDalej(View v){
		i = new Intent(this,NauczycielOcenaActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("id_przedmiotu", id_przedmiotu);
		i.putExtra("id_grupy", id_grupy);
		i.putExtra("przedmiotW", przedmiotW);
		i.putExtra("klasaW", klasaW);
		startActivity(i);
	}
}
