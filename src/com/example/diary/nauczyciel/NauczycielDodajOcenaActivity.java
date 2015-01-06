package com.example.diary.nauczyciel;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;

import com.example.diary.*;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NauczycielDodajOcenaActivity extends Activity {

	EditText wartosc, komentarz; 
	String id_ucznia, id_przedmiotu, wartoscOceny, komentarzOceny, nazwaUcznia, przedmiot, rok_szkolny, id_rok, id_ocenyK;
	int dodano;
	Intent i;
	TextView tv;
	List<Map<String, String>> ocenaKoncowa =null, getRokId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_dodaj_ocena);

		wartosc = (EditText)findViewById(R.id.wartosc);
		komentarz = (EditText)findViewById(R.id.komentarz);
		id_ucznia = getIntent().getExtras().getString("id_ucznia");
		id_przedmiotu = getIntent().getExtras().getString("id_przedmiotu");
		nazwaUcznia = getIntent().getExtras().getString("nazwaUcznia");
		przedmiot = getIntent().getExtras().getString("przedmiotW");
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");

		tv = (TextView)findViewById(R.id.info);
		tv.setText("Wybrany uczeñ: "+nazwaUcznia+".\nPrzedmiot: "+przedmiot+".");

	}

	public void onDodajOcena(View v){

		wartoscOceny = wartosc.getText().toString();
		komentarzOceny = komentarz.getText().toString();
		dodano = Utilities.udi(this, "dodajOcena", id_przedmiotu,id_ucznia, wartoscOceny, komentarzOceny);
		if(dodano>0){
			Toast.makeText(this, "Dodano ocenê", Toast.LENGTH_SHORT).show();
			wartosc.setText("");
			komentarz.setText("");
		}
		else{
			Toast.makeText(this, "Nie uda³o siê dodaæ oceny.", Toast.LENGTH_SHORT).show();
		}
	}

	public void onDodajOcenaSemestralna(View v){
		// jak i w koncowej
		// zapytka czy jest juz ocena 
		//jest info, nie ma to dodaj
		ocenaKoncowa = Utilities.query(this, "ocenaKoncowaZ",id_ucznia, rok_szkolny, id_przedmiotu);
		Log.d("id_ucznia", id_ucznia);
		Log.d("rok_szkolny", rok_szkolny);
		Log.d("id_przedmiotu", id_przedmiotu);
		if(ocenaKoncowa.size()>0){
			Toast.makeText(this, "Nie mo¿na dodaæ oceny.\nJu¿ wystawiono.", Toast.LENGTH_SHORT).show();
		}
		else{
			wartoscOceny = wartosc.getText().toString();
			getRokId = Utilities.query(this, "getRokId", rok_szkolny);
			id_rok = getRokId.get(0).get("id_rok");
			dodano = Utilities.udi(this, "dodajOcenaSemestralna", id_przedmiotu,id_ucznia, id_rok, wartoscOceny);
			if(dodano>0){
				Toast.makeText(this, "Dodano ocenê semestraln¹.", Toast.LENGTH_SHORT).show();
				wartosc.setText("");
				komentarz.setText("");
			}
			else{
				Toast.makeText(this, "Nie uda³o siê dodaæ oceny semestralnej.\nSprawdŸ, czy ju¿ nie wystawiono.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	public void onDodajOcenaKoncowa(View v){

		ocenaKoncowa = Utilities.query(this, "ocenaKoncowa",id_ucznia, rok_szkolny);
		if(ocenaKoncowa.get(0).get("koncowa")==null){
			if(ocenaKoncowa.get(0).get("semestralna")==null){
				Toast.makeText(this, "Nie mo¿na dodaæ oceny koñcowej, gdy nie ma oceny semestralnej.", Toast.LENGTH_SHORT).show();
			}
			else{
				id_ocenyK = ocenaKoncowa.get(0).get("id_ocenyK");
				wartoscOceny = wartosc.getText().toString();
				dodano = Utilities.udi(this, "dodajOcenaKoncowa", wartoscOceny, id_ocenyK);

				if(dodano>0){
					Toast.makeText(this, "Dodano ocenê koñcow¹.", Toast.LENGTH_SHORT).show();
					wartosc.setText("");
					komentarz.setText("");
				}
				else{
					Toast.makeText(this, "Nie uda³o siê dodaæ oceny.\nSprawdŸ, czy ju¿ nie wystawiono.", Toast.LENGTH_SHORT).show();
				}
			}
		}
		else Toast.makeText(this, "Nie mo¿na dodaæ oceny.\nSprawdŸ, czy ju¿ nie wystawiono.", Toast.LENGTH_SHORT).show();

	}

}
