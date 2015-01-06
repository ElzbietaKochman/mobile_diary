package com.example.diary.nauczyciel;

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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NauczycielWybierzPlikActivity extends Activity {
	
	Bundle b;
	String nazwaUcznia="", klasa="",data="",etykieta="",idUcznia="",rokSzkolny="";
	List<Map<String, String>> getPlik=null;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_wybierz_plik);
		
		b = getIntent().getExtras();
		nazwaUcznia = b.getString("nazwaUcznia");
		idUcznia = b.getString("id_ucznia");
		klasa = b.getString("poziom");
		rokSzkolny = b.getString("rok_szkolny");
		
		getPlik = Utilities.query(this, "getPlik",idUcznia, rokSzkolny);
		
		TableLayout nieobecnosc = (TableLayout) findViewById(R.id.tabelka);
		nieobecnosc.setStretchAllColumns(true);
		nieobecnosc.setColumnShrinkable(0, true);
		nieobecnosc.setColumnShrinkable(1, true);
		nieobecnosc.bringToFront();
		
		if(getPlik.size()==0){
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText("Brak plików.");
			tr.addView(tv);
			nieobecnosc.addView(tr);
		}
		else {
			for(int a=0;a<getPlik.size();a++){
				final int ba = a;
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this);
				tv.setText(getPlik.get(a).get("data"));
				tr.addView(tv);
				nieobecnosc.addView(tr);
				TableRow tr1 = new TableRow(this);
				TextView tv1 = new TextView(this);
				tv1.setText(getPlik.get(a).get("etykieta"));
				tr1.addView(tv1);
				tr1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						i = new Intent(NauczycielWybierzPlikActivity.this,NauczycielPokazZdjecieActivity.class);
						i.putExtras(b);
						i.putExtra("fileName", getPlik.get(ba).get("nazwa"));
						startActivity(i);
					}
				});
				nieobecnosc.addView(tr1);
			}
		}
		
	}
}
