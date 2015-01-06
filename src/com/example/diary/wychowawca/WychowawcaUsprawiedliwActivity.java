package com.example.diary.wychowawca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;
import com.example.diary.*;
import com.example.diary.nauczyciel.NauczycielObecnoscActivity;

public class WychowawcaUsprawiedliwActivity extends Activity {

	String id_ucznia="", semestr="", rok_szkolny="", wybranaData="", wybranaGodzina="", dataW="", godzinaW="", id_nieobecnosci, idW="", nazwaUcznia="";
	List<Map<String, String>> getData=null, getGodziny=null;
	ArrayList<String> listaDni, listaGodzin;
	int usprawiedliwiono;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wychowawca_usprawiedliw);

		nazwaUcznia = getIntent().getExtras().getString("nazwaUcznia");
		id_ucznia = getIntent().getExtras().getString("id_ucznia");
		semestr = getIntent().getExtras().getString("obecny_semestr");
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		TextView info = (TextView)findViewById(R.id.info);
		info.setText("Uczeñ: "+nazwaUcznia);
		Log.d("id_ucznia", id_ucznia);
		Log.d("semestr", semestr);
		Log.d("rok_szkolny", rok_szkolny);

		getData = Utilities.query(this, "getNieobecnosciNieusprawiedliwione", id_ucznia, rok_szkolny, semestr);
		listaDni = new ArrayList<String>();

		for(int a=0;a<getData.size();a++){
				listaDni.add(getData.get(a).get("data"));
		}
		ArrayAdapter<String> adapterData = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaDni);
		Spinner data = (Spinner)findViewById(R.id.data);
		data.setAdapter(adapterData);
		data.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				wybranaData =parent.getItemAtPosition(position).toString();


				getGodziny = Utilities.query(WychowawcaUsprawiedliwActivity.this, "getNieobecnosci", id_ucznia, wybranaData,"nie");

				Log.d("a", Integer.toString(getGodziny.size()));
				listaGodzin = new ArrayList<String>();
				for(int b=0;b<getGodziny.size();b++){
					listaGodzin.add(b, getGodziny.get(b).get("godz_pocz"));
					Log.d(Integer.toString(b), getGodziny.get(b).get("godz_pocz"));
				}
				ArrayAdapter<String> adapterGodzina = new ArrayAdapter<String>(WychowawcaUsprawiedliwActivity.this, android.R.layout.simple_spinner_item, listaGodzin);
				Spinner godzina = (Spinner)findViewById(R.id.godzina);
				godzina.setAdapter(adapterGodzina);
				godzina.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						wybranaGodzina = parent.getItemAtPosition(position).toString();
						for(int c=0;c<getGodziny.size();c++){
							if(getGodziny.get(c).get("godz_pocz").equals(wybranaGodzina)){
								id_nieobecnosci = getGodziny.get(c).get("id_nieobecnosci");
								Log.d("id_nieobecnosci", id_nieobecnosci);
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


	public void onUsprawiedliw(View v){
		dataW = wybranaData;
		godzinaW = wybranaGodzina;
		idW = id_nieobecnosci;
		Log.d("idW", idW);

		usprawiedliwiono = Utilities.udi(this, "usprawiedliw", idW);
		if(usprawiedliwiono>0){
			Toast.makeText(this, "Pomyœlnie usprawiedliwiono nieobecnoœæ.", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Nie uda³o siê usprawiedliwiæ nieobecnoœci.", Toast.LENGTH_SHORT).show();
		}
	}
	
}
