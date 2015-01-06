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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class OpiekunOcenaActivity extends Activity {
	TextView tv1;
	String rok_szkolny=null, semestr=null, id=null, idP=null, imieW;
	Intent i;
	List<Map<String, String>> result = null, przedmioty = null, getUczenId = null;
	int iloscO, iloscP;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_ocena);
		tv1 = (TextView) findViewById(R.id.textView1);
		i = getIntent();
		imieW = getIntent().getExtras().getString("imieW");
		Log.d("imieW", imieW);
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		semestr = getIntent().getExtras().getString("obecny_semestr");
		tv1.setText("Oceny ucznia: "+imieW+"\nRok szkolny "+rok_szkolny+", semestr "+semestr+".");

		TableLayout oceny = (TableLayout) findViewById(R.id.tabelka);
		oceny.setStretchAllColumns(true);
		oceny.setColumnShrinkable(1, true);
		oceny.bringToFront();
		// tu zapytanie do bazy zwracaj¹ce najlepiej wszystkie dane ocen, i wpisywanie ich do odpowiednich 
		// kratek

		getUczenId = Utilities.query(this, "getUczenId",imieW);
		 id = getUczenId.get(0).get("id_ucznia");
		przedmioty = Utilities.query(this, "getUczenPrzedmioty",id);
		int iloscP = przedmioty.size();
		for(int a=0;a<iloscP;a++){
			idP = przedmioty.get(a).get("id_przedmiotu");
			result = Utilities.query(this, "getUczenOceny",id,idP,rok_szkolny,semestr);
			iloscO = result.size();
			if(iloscO==0){

			}
			else {
				TableRow p1 = new TableRow(this);
				TextView p = new TextView(this);
				p.setText(przedmioty.get(a).get("nazwa"));
				p1.addView(p);
				oceny.addView(p1);
				TableRow p2 = new TableRow(this);

				for(int b=0;b<iloscO;b++){
					TextView o = new TextView(this);
					o.setId(b);
					o.setText(result.get(b).get("wartosc"));
					String cos = result.get(b).get("data") +", "+result.get(b).get("uwaga_oceny");
					o.setOnClickListener(new Listener(cos, getApplicationContext()));
					p2.addView(o);
				}
				oceny.addView(p2);
			}
		}
	}
}
