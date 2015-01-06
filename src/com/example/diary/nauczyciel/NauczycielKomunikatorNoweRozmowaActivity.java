package com.example.diary.nauczyciel;
import java.util.List;
import java.util.Map;


import com.example.diary.*;
import com.example.diary.opiekun.OpiekunKomunikatorRozmowaActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class NauczycielKomunikatorNoweRozmowaActivity extends Activity {


	List<Map<String, String>> getNowe = null;
	int ustawOdebrane, wyslij;
	String data="", idNadawcy="", idUser="", nadawca="", nowa="";
	EditText odpowiedz;
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_komunikator_nowe_rozmowa);

		TextView info = (TextView)findViewById(R.id.info);
		odpowiedz = (EditText)findViewById(R.id.odpowiedz);
		
		idUser = getIntent().getExtras().getString("idUser");
		data = getIntent().getExtras().getString("data");
		idNadawcy = getIntent().getExtras().getString("idNadawca");
		nadawca = getIntent().getExtras().getString("nadawca");
		
		Log.d("idUser", idUser);
		Log.d("data", data);
		Log.d("idNadawcy", idNadawcy);
		// na poczatek ustawiamy wiadomosc jako przeczytana
		getNowe = Utilities.query(this, "getNowe",idUser, idNadawcy,data );
		ustawOdebrane = Utilities.udi(this, "ustawOdebrane",data, idNadawcy);
		if(ustawOdebrane>0){
			Log.d("odebrane", "ustawiono");
		}
		TableLayout nowe = (TableLayout) findViewById(R.id.tabelka);
		nowe.setStretchAllColumns(true);
		nowe.setColumnShrinkable(1, true);
		nowe.setColumnShrinkable(0, true);
		nowe.bringToFront();
		
		//wyswietlamy wszystkie nowe wiadomosci z danego dnia, sortowane od najstarszej godziny
		info.setText("Od: "+nadawca);
		info.setTypeface(Typeface.DEFAULT_BOLD);
		
		for(int a=0;a<getNowe.size();a++){
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText(getNowe.get(a).get("tresc").replace("+", " "));
			tv.setTextColor(Color.GREEN);
			tv.setBackgroundColor(Color.GRAY);
			tv.setTextSize(15F);
			tv.setInputType(EditorInfo.TYPE_TEXT_FLAG_IME_MULTI_LINE);
			TextView puste = new TextView(this);
			puste.setText(" ");
			puste.setWidth(10);
			tr.addView(tv);
			tr.addView(puste);
			nowe.addView(tr);
		}
	}
	
	public void onWyslij(View v) throws InterruptedException{
		nowa = odpowiedz.getText().toString();
		Log.d("OST: idUser",idUser );
		Log.d("idNadawcy", idNadawcy);
		Log.d("nowa", nowa );
		wyslij = Utilities.udi(this, "wyslij", idUser, idNadawcy, nowa);
		if(wyslij==0){
			Toast.makeText(this, "Nie uda³o siê wys³aæ wiadomoœci.\nSpróbuj ponownie póŸniej.", Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(this, "Pomyœlnie wyslano wiadomoœæ.", Toast.LENGTH_SHORT).show();
			i = new Intent(this, NauczycielKomunikatorRozmowaActivity.class);
			Bundle bundle = getIntent().getExtras();
			i.putExtras(bundle);
			i.putExtra("idOdbiorcy", idNadawcy);
			i.putExtra("odbiorca", nadawca);
			// fajnie wrzucic czekanie chwile
			startActivity(i);
			finish();
		}
	}
}
