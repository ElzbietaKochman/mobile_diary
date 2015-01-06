package com.example.diary.nauczyciel;
import java.util.List;
import java.util.Map;


import com.example.diary.*;
import com.example.diary.opiekun.OpiekunKomunikatorRozmowaActivity;
import com.example.diary.opiekun.OpiekunKomunikatorRozmowaWybierzActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NauczycielKomunikatorRozmowaWybierzActivity extends Activity {

	List<Map<String, String>> getWszyscyOpiekun= null, getRelacja=null, getUczen=null;
	String rokSzkolny="", nazwa="",odbiorca="";
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_komunikator_rozmowa_wybierz);
		rokSzkolny = getIntent().getExtras().getString("rok_szkolny");
		getWszyscyOpiekun = Utilities.query(this, "getWszyscyOpiekun");

		TableLayout nowe = (TableLayout) findViewById(R.id.tabelka);
		nowe.setStretchAllColumns(true);
		nowe.setColumnShrinkable(1, true);
		nowe.bringToFront();

		TableRow tr0 = new TableRow(this);
		TextView tv0 = new TextView(this);
		tv0.setText("Opiekun");
		tr0.addView(tv0);
		TextView tv00 = new TextView(this);
		tv00.setText("Ucznia");
		tr0.addView(tv00);
		nowe.addView(tr0);

		for(int a=0;a<getWszyscyOpiekun.size();a++){
			Log.d("w rozmowa wybierz", "for "+a);
			final int b = a;
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText(getWszyscyOpiekun.get(a).get("nazwa"));
			
			TextView tv2 = new TextView(this);
			getRelacja = Utilities.query(this, "getRelacja", getWszyscyOpiekun.get(a).get("id_opiekuna"));
			String nazwaUcznia="";
			getUczen = Utilities.query(this, "getUczen",getRelacja.get(0).get("id_ucznia"));
			nazwaUcznia = getUczen.get(0).get("nazwa");
			if(getRelacja.size()>1){
			for(int n=1;n<getRelacja.size();n++){
				getUczen = Utilities.query(this, "getUczen",getRelacja.get(n).get("id_ucznia"));
				nazwaUcznia = nazwaUcznia +", "+ getUczen.get(0).get("nazwa");
			}
			}
			tv2.setText(nazwaUcznia);
			tr.addView(tv);
			tr.addView(tv2);
			tr.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					i = new Intent(NauczycielKomunikatorRozmowaWybierzActivity.this,NauczycielKomunikatorRozmowaActivity.class);
					i.putExtra("odbiorca",  getWszyscyOpiekun.get(b).get("nazwa"));
					i.putExtra("idOdbiorcy", getWszyscyOpiekun.get(b).get("id_user"));
					Bundle bundle = getIntent().getExtras();
					i.putExtras(bundle);
					Log.d("w rozmowa wybierz", "on click");
					Log.d("odbiorca", odbiorca);
					startActivity(i);
				}
			});
			nowe.addView(tr);
		}
	} 
}

