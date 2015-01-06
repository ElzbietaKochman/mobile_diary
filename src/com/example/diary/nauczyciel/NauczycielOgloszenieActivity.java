package com.example.diary.nauczyciel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class NauczycielOgloszenieActivity extends Activity {

	String opcja="", rok_szkolny="", wybranaKlasa="", wybranaGrupa="", getTresc="", getTemat="", id_nauczyciela;
	ArrayList<String> listaOdbiorcow;
	List<Map<String, String>> getKlasa=null, getGrupa=null, getTegoroczni=null;
	EditText temat, tresc;
	int dodano;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_ogloszenie);

		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		id_nauczyciela = getIntent().getExtras().getString("id");

		temat = (EditText)findViewById(R.id.temat);
		tresc = (EditText)findViewById(R.id.tresc);

		ArrayList<String> lista = new ArrayList<String>();

		lista.add(0,"klasa");
		lista.add(1,"wszyscy");

		ArrayAdapter<String> adapterKlasa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
		Spinner klasa = (Spinner)findViewById(R.id.odbiorca);
		klasa.setAdapter(adapterKlasa);
		klasa.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				opcja = parent.getSelectedItem().toString();
				// tworzymy spinnery z odbiorcami do wyboru
				if(opcja.equals("klasa")){
					getKlasa = Utilities.query(NauczycielOgloszenieActivity.this, "getKlasaPoziom", rok_szkolny);
					ArrayList<String> listaKlas = new ArrayList<String>();
					for(int a=0;a<getKlasa.size();a++){
						listaKlas.add(a,getKlasa.get(a).get("poziom"));
					}
					ArrayAdapter<String> klasa = new ArrayAdapter<String>(NauczycielOgloszenieActivity.this, android.R.layout.simple_spinner_item, listaKlas);
					Spinner wybor = (Spinner)findViewById(R.id.wybor);
					wybor.setAdapter(klasa);
					wybor.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							wybranaKlasa = parent.getSelectedItem().toString();
							for(int a=0;a<getKlasa.size();a++){
								if(getKlasa.get(a).get("poziom").equals(wybranaKlasa)){
									wybranaGrupa = getKlasa.get(a).get("id_grupy");
								}
							}
							getGrupa = Utilities.query(NauczycielOgloszenieActivity.this, "getGrupa",wybranaGrupa);
							listaOdbiorcow = new ArrayList<String>();
							for(int b=0;b<getGrupa.size();b++){
								listaOdbiorcow.add(b,getGrupa.get(b).get("id_ucznia"));
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub

						}
					});
				}
				else{
					getTegoroczni = Utilities.query(NauczycielOgloszenieActivity.this, "getTegoroczni",rok_szkolny);
					listaOdbiorcow = new ArrayList<String>();
					for(int b=0;b<getTegoroczni.size();b++){
						listaOdbiorcow.add(b,getTegoroczni.get(b).get("id_ucznia"));
					}
				}
				// lista odbiorców
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});	

	}

	public void onDodajOgloszenie(View v){

		getTresc = tresc.getText().toString();
		getTemat = temat.getText().toString();

		getTemat = getTemat.replace("+", " ");
		getTresc = getTresc.replace("+", " ");
		for(String list : listaOdbiorcow){
			dodano = Utilities.udi(NauczycielOgloszenieActivity.this, "dodajOgloszenie", list, id_nauczyciela, getTemat, getTresc);
			if(dodano<1){
				Toast.makeText(this, "Nie mo¿na by³o wys³aæ og³oszenia", Toast.LENGTH_SHORT).show();
			}
		}
		if(dodano>0){
			Toast.makeText(this, "Pomyœlnie wys³ano og³oszenie", Toast.LENGTH_SHORT).show();
			tresc.setText("");
			temat.setText("");
		}

	}
}
