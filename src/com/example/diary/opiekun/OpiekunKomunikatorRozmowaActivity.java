package com.example.diary.opiekun;

import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class OpiekunKomunikatorRozmowaActivity extends Activity {
	
	//bedzie mozna do nauczyciela,wychowawcy

	List<Map<String, String>> getWiadomosci = null;
	int ustawOdebrane, wyslij;
	String data="", idOdbiorcy="", idUser="", odbiorca="", nowa="";
	EditText odpowiedz;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_komunikator_rozmowa);
		TextView info = (TextView)findViewById(R.id.info);
		odpowiedz = (EditText)findViewById(R.id.odpowiedz);
		
		idUser = getIntent().getExtras().getString("idUser");
		idOdbiorcy = getIntent().getExtras().getString("idOdbiorcy");
		odbiorca = getIntent().getExtras().getString("odbiorca");
		
		// pobieramy ostatnie 10 wiadomosci
		Log.d("w rozmowa ****", "tak");
		Log.d("idUsera z rozmowa", idUser);
		Log.d("idOdbiorcy z rozmowa", idOdbiorcy);
		getWiadomosci = Utilities.query(this, "getWiadomosci", idUser, idOdbiorcy);
		
		TableLayout nowe = (TableLayout) findViewById(R.id.tabelka);
		nowe.setStretchAllColumns(true);
		nowe.setColumnShrinkable(1, true);
		nowe.setColumnShrinkable(0, true);
		nowe.bringToFront();
		
		//wyswietlamy wszystkie nowe wiadomosci z danego dnia, sortowane od najstarszej godziny
		info.setText("Rozmowa z: "+odbiorca);
		
		
		if(getWiadomosci.size()<20){
		for(int a=(getWiadomosci.size()-1);a>0;a--){
			//Log.d("id konwersiarza", getWiadomosci.get(a).get("id_nadawcy"));
		//	Log.d("idOdbiorcy z rozmowa", idOdbiorcy);
			if(getWiadomosci.get(a).get("id_nadawcy").equals(idOdbiorcy)){
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this);
				tv.setText(getWiadomosci.get(a).get("tresc").replace("+", " "));
				tv.setTextColor(Color.GREEN);
				tv.setBackgroundColor(Color.GRAY);
				TextView puste = new TextView(this);
				puste.setText(" ");
				puste.setWidth(10);
				tr.addView(tv);
				tr.addView(puste);
				nowe.addView(tr);
				//Log.d("gdy rozmowca jest nadawca", "AAAAAAAAAA");
			}
			else{
				//Log.d("z nadawca", "AAAAAAAAAA");
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this);
				tv.setText(getWiadomosci.get(a).get("tresc").replace("+", " "));
				tv.setTextColor(Color.BLUE);
				tv.setBackgroundColor(Color.LTGRAY);
				TextView puste = new TextView(this);
				puste.setText(" ");
				puste.setWidth(10);
				tr.addView(puste);
				tr.addView(tv);
				nowe.addView(tr);
			}
			
		}
		}
		else {
			for(int a=20;a>0;a--){
				if(getWiadomosci.get(a).get("id_nadawcy").equals(idOdbiorcy)){
					//Log.d("z nadawca", "AAAAAAAAAA");
					TableRow tr = new TableRow(this);
					TextView tv = new TextView(this);
					tv.setText(getWiadomosci.get(a).get("tresc").replace("+", " "));
					//tv.setTextColor(Color.parseColor("#0FAC34"));
					tv.setTextColor(Color.GREEN);
					tv.setBackgroundColor(Color.GRAY);
					TextView puste = new TextView(this);
					puste.setText(" ");
					puste.setWidth(10);
					tr.addView(tv);
					tr.addView(puste);
					nowe.addView(tr);
				}
				else{
				//	Log.d("z odbiorca", "AAAAAAAAAA");
					TableRow tr = new TableRow(this);
					TextView tv = new TextView(this);
					tv.setText(getWiadomosci.get(a).get("tresc").replace("+", " "));
					//tv.setTextColor(Color.parseColor("#0F24AC"));
					tv.setTextColor(Color.BLUE);
					tv.setBackgroundColor(Color.LTGRAY);
					TextView puste = new TextView(this);
					puste.setText(" ");
					puste.setWidth(10);
					tr.addView(puste);
					tr.addView(tv);
					nowe.addView(tr);
				}
			}
		}
	}
	
	public void onWyslij(View v){
		nowa = odpowiedz.getText().toString();
		wyslij = Utilities.udi(this, "wyslij", idUser, idOdbiorcy, nowa);
		if(wyslij==0){
			Toast.makeText(this, "Nie uda³o siê wys³aæ wiadomoœci.\nSpróbuj ponownie póŸniej.", Toast.LENGTH_LONG).show();
			finish();
		}
		else {
			Toast.makeText(this, "Pomyœlnie wyslano wiadomoœæ.", Toast.LENGTH_SHORT).show();
			i = new Intent(this, OpiekunKomunikatorRozmowaActivity.class);
			Bundle bundle = getIntent().getExtras();
			i.putExtras(bundle);
			startActivity(i);
			finish();
		}
	}
}

