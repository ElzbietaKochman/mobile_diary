package com.example.diary.opiekun;

import java.util.ArrayList;
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

public class OpiekunKomunikatorNoweActivity extends Activity {
	
	List<Map<String, String>> getNoweWiadomosci = null, getNauczyciel=null, getNauczycielPrzedmiot = null;
	String idOpiekuna, rokSzkolny, listaP, idUser="";
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_komunikator_nowe);
		TextView info = (TextView)findViewById(R.id.info);
		
		idOpiekuna = getIntent().getExtras().getString("id");
		idUser = getIntent().getExtras().getString("idUser");
		rokSzkolny = getIntent().getExtras().getString("rok_szkolny");
		
		TableLayout nowe = (TableLayout) findViewById(R.id.tabelka);
		nowe.setStretchAllColumns(true);
		nowe.setColumnShrinkable(0, true);
		nowe.setColumnShrinkable(1, true);
		nowe.setColumnShrinkable(2, true);
		nowe.bringToFront();
		
		// st¹d do OpiekunKomunikatorNoweRozmowaActivity
		Log.d("id_user", idUser);
		getNoweWiadomosci = Utilities.query(this, "getNoweWiadomosci", idUser);
		
		
		if(getNoweWiadomosci.isEmpty()){
			info.setText("Brak nowych wiadomoœci");
		}
		else {
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText("Data");
			TextView tv1 = new TextView(this);
			tv1.setText("Nadawca");
			tr.addView(tv);
			tr.addView(tv1);
			nowe.addView(tr);
			for(int a=0;a<getNoweWiadomosci.size();a++){
				final int b = a;
				TableRow tr2 = new TableRow(this);
				TextView tv2 = new TextView(this);
				tv2.setText(getNoweWiadomosci.get(a).get("data"));
				TextView tv3 = new TextView(this);
				String id_nadawcy = getNoweWiadomosci.get(a).get("id_nadawcy");
				getNauczyciel = Utilities.query(this,"getUserN",id_nadawcy);
				tv3.setText(getNauczyciel.get(0).get("nazwa"));
				
				tr2.addView(tv2);
				tr2.addView(tv3);
				tr2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						i = new Intent(OpiekunKomunikatorNoweActivity.this, OpiekunKomunikatorNoweRozmowaActivity.class);
						i.putExtra("data", getNoweWiadomosci.get(b).get("data"));
						Log.d("nadawca z nowe activity", getNoweWiadomosci.get(b).get("id_nadawcy"));
						i.putExtra("idNadawca", getNoweWiadomosci.get(b).get("id_nadawcy"));
						i.putExtra("idNadawcyNauczyciela", getNauczyciel.get(0).get("id_nauczyciela"));
						i.putExtra("nadawca", getNauczyciel.get(0).get("nazwa"));
						Bundle bundle = getIntent().getExtras();
						i.putExtras(bundle);
						startActivity(i);
						
					}
				});
				nowe.addView(tr2);
			}
		}
		
	}
	/*public void onClick(View v){
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}*/
}
