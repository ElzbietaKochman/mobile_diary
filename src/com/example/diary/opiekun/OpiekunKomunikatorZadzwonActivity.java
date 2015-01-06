package com.example.diary.opiekun;

import java.util.List;
import java.util.Map;

import com.example.diary.*;

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

public class OpiekunKomunikatorZadzwonActivity extends Activity {

	List<Map<String, String>> getWszyscyNauczyciel= null, getNauczycielPrzedmiot=null;
	String rokSzkolny="", nazwa="",odbiorca="";
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_komunikator_zadzwon);

		rokSzkolny = getIntent().getExtras().getString("rok_szkolny");
		getWszyscyNauczyciel = Utilities.query(this, "getWszyscyNauczyciel");

		TableLayout nowe = (TableLayout) findViewById(R.id.tabelka);
		nowe.setStretchAllColumns(true);
		nowe.setColumnShrinkable(1, true);
		nowe.bringToFront();

		TableRow tr0 = new TableRow(this);
		TextView tv0 = new TextView(this);
		tv0.setText("Nauczyciel");
		tr0.addView(tv0);
		TextView tv00 = new TextView(this);
		tv00.setText("Prowadzony przedmiot");
		tr0.addView(tv00);
		nowe.addView(tr0);

		for(int a=0;a<getWszyscyNauczyciel.size();a++){
			Log.d("w rozmowa wybierz", "for "+a);
			final int b = a;
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText(getWszyscyNauczyciel.get(a).get("nazwa"));
			
			TextView tv2 = new TextView(this);
			getNauczycielPrzedmiot = Utilities.query(this, "getNauczycielPrzedmiot", getWszyscyNauczyciel.get(a).get("id_nauczyciela"), rokSzkolny);
			
			String nazwaP = getNauczycielPrzedmiot.get(0).get("nazwa");
			if(getNauczycielPrzedmiot.size()>1){
				for(int x=1;x<getNauczycielPrzedmiot.size();x++){
					nazwaP = nazwaP+","+getNauczycielPrzedmiot.get(x).get("nazwa");
				}
			}
			tv2.setText(nazwaP);
			tr.addView(tv);
			tr.addView(tv2);
			tr.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					i = new Intent(OpiekunKomunikatorZadzwonActivity.this, CallActivity.class);
					Bundle bundle = getIntent().getExtras();
					i.putExtras(bundle);
					i.putExtra("telefon", getWszyscyNauczyciel.get(b).get("telefon"));
					i.putExtra("nazwa_opiekun", getWszyscyNauczyciel.get(b).get("nazwa"));
					startActivity(i);
					/*  getWszyscyNauczyciel.get(b).get("telefon") - numer tel
					i = new Intent(OpiekunKomunikatorRozmowaWybierzActivity.this,OpiekunKomunikatorRozmowaActivity.class);
					i.putExtra("odbiorca",  getWszyscyNauczyciel.get(b).get("nazwa"));
					i.putExtra("idOdbiorcy", getWszyscyNauczyciel.get(b).get("id_user"));
					Bundle bundle = getIntent().getExtras();
					i.putExtras(bundle);
					Log.d("w rozmowa wybierz", "on click");
					Log.d("odbiorca", odbiorca);
					startActivity(i);*/
				}
			});
			nowe.addView(tr);
		}

	} // st¹d do OpiekunKomunikatorRozmowaActivity
	/*public void onClick(View v){
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		Log.d("w rozmowa wybierz", "on click");
		Log.d("odbiorca", odbiorca);
		startActivity(i);
	} */
}
