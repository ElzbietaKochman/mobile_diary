package com.example.diary.nauczyciel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.example.diary.*;
import com.example.diary.opiekun.OpiekunKomunikatorMojeActivity;
import com.example.diary.opiekun.OpiekunKomunikatorRozmowaActivity;

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

public class NauczycielKomunikatorMojeActivity extends Activity {

	List<Map<String, String>> getRozmowy=null, getUserN=null;
	String idUser="", rozmowca="", nazwaOdbiorcy="";
	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_komunikator_moje);
		TextView info = (TextView) findViewById(R.id.info);

		idUser = getIntent().getExtras().getString("idUser");

		// stad do okna rozmowy OpiekunKomunikatorMojeRozmowaActivity
		getRozmowy = Utilities.query(this, "getRozmowy", idUser);

		if(getRozmowy.isEmpty()){
			info.setText("Nie prowadzi�e� jeszcze\n�adnych rozm�w");
		}
		else {
			TableLayout nowe = (TableLayout) findViewById(R.id.tabelka);
			nowe.setStretchAllColumns(true);
			nowe.setColumnShrinkable(1, true);
			nowe.setColumnShrinkable(0, true);
			nowe.bringToFront();

			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText("Data");
			tr.addView(tv);
			TextView tv1 = new TextView(this);
			tv1.setText("Rozm�wca");
			tr.addView(tv1);
			nowe.addView(tr);

			ArrayList<String> listaOdbiorcow = new ArrayList<String>();
			for(int a=0;a<getRozmowy.size();a++){
				if(getRozmowy.get(a).get("id_odbiorcy").equals(idUser)){
					rozmowca = getRozmowy.get(a).get("id_nadawcy");
				}
				else rozmowca =  getRozmowy.get(a).get("id_odbiorcy");
				if(listaOdbiorcow.contains(rozmowca)){

				}

				else{
					listaOdbiorcow.add(rozmowca);
					TableRow tr2 = new TableRow(this);
					TextView tv3 = new TextView(this);
					tv3.setText(getRozmowy.get(a).get("data"));
					tr2.addView(tv3);
					TextView tv4 = new TextView(this);
					getUserN = Utilities.query(this, "getUserO",rozmowca);
					nazwaOdbiorcy = getUserN.get(0).get("nazwa");
					tv4.setText(nazwaOdbiorcy);
					tr2.addView(tv4);
					tr2.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							i = new Intent (NauczycielKomunikatorMojeActivity.this, NauczycielKomunikatorRozmowaActivity.class);
							i.putExtra("idOdbiorcy", rozmowca);
							i.putExtra("odbiorca", nazwaOdbiorcy);
							Bundle b = getIntent().getExtras();
							i.putExtras(b);
							startActivity(i);
							finish();
						}
					});
					nowe.addView(tr2);
				}
			}
		}
	}
}
