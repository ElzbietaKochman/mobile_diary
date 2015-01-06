package com.example.diary.nauczyciel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NauczycielOcenaActivity extends Activity {
	
	String id_przedmiotu="", id_grupy="", przedmiotW="", klasaW="";
	List<Map<String, String>> getKlasaOceny = null;
	ArrayList<String> listaUczniow;
	TextView pierwszy;
	
	Intent i;
	int iloscOcen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_ocena);
		
		id_przedmiotu = getIntent().getExtras().getString("id_przedmiotu");
		id_grupy = getIntent().getExtras().getString("id_grupy");
		przedmiotW = getIntent().getExtras().getString("przedmiotW");
		klasaW = getIntent().getExtras().getString("klasaW");
		
		TableLayout oceny = (TableLayout) findViewById(R.id.tabelka);
		oceny.setStretchAllColumns(true);
		oceny.setColumnShrinkable(0, true);
		oceny.setColumnShrinkable(1, true);
		oceny.bringToFront();
		
		pierwszy = (TextView)findViewById(R.id.info);
		pierwszy.setText("Wybierz ucznia, aby dodaæ ocenê.\nPrzedmiot: "+przedmiotW+".\nKlasa: "+klasaW+".");
		
		getKlasaOceny = Utilities.query(this, "getKlasaOceny", id_przedmiotu, id_grupy);
		
		listaUczniow = new ArrayList<String>();
		iloscOcen = getKlasaOceny.size();
		
		
		for(int a=0;a<iloscOcen;a++){
			// sprawdzenie czy dany uczen byl na liscie
			if(listaUczniow.contains(getKlasaOceny.get(a).get("nazwa"))){
				// jak by³ to idziemy dalej (nie wracamy do tych co byli, bo oni juz wszysko maj¹ wypisane
			}
			else{
				// jak nie by³o ucznia na liscie to dodajemy do listy
				listaUczniow.add(getKlasaOceny.get(a).get("nazwa"));
				String id_ucznia =  getKlasaOceny.get(a).get("id_ucznia");
				i = new Intent(NauczycielOcenaActivity.this, NauczycielDodajOcenaActivity.class);
				Bundle bundle = getIntent().getExtras();
				i.putExtra("id_ucznia", id_ucznia);
				i.putExtra("nazwaUcznia", getKlasaOceny.get(a).get("nazwa"));
				i.putExtras(bundle);
				// dodajemy wiersz z jego nazwa
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this);
				tv.setText(getKlasaOceny.get(a).get("nazwa"));
				tv.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						startActivity(i);
					}
				});
				tr.addView(tv);
				oceny.addView(tr);
				//i wiersz z ocenami
				TableRow tr2 = new TableRow(this);
				
				// przy czym przechodzimy po wszystkich ocenach raz jeszcze
				for(int b=0;b<iloscOcen;b++){
					if(getKlasaOceny.get(b).get("nazwa").equals(getKlasaOceny.get(a).get("nazwa"))){
						TextView tv2 = new TextView(this);
						tv2.setText(getKlasaOceny.get(b).get("wartosc"));
						String cos = getKlasaOceny.get(b).get("data") +", "+getKlasaOceny.get(b).get("uwaga_oceny").replace("+", " ");
						tv2.setOnClickListener(new Listener(cos, getApplicationContext()));
						tr2.addView(tv2);
						
					}
				}
				
				oceny.addView(tr2);				
			}
		
		}
		listaUczniow.clear();
	}
}
