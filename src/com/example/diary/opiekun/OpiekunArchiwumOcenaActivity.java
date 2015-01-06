package com.example.diary.opiekun;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class OpiekunArchiwumOcenaActivity extends Activity {

	TextView tv1, tv2;
	String rok_szkolny=null, semestr=null, id=null, idP=null, imieW;
	Intent i;
	List<Map<String, String>> result = null, przedmioty = null, getUczenId = null;
	int iloscO, iloscP;
	static int pointer, pointer2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_archiwum_ocena);

		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		i = getIntent();
		rok_szkolny = getIntent().getExtras().getString("rokW");
		imieW = getIntent().getExtras().getString("imieW");
		TableLayout oceny = (TableLayout) findViewById(R.id.tabelka);
		oceny.setStretchAllColumns(true);
		oceny.setColumnShrinkable(1, true);
		oceny.bringToFront();


		getUczenId = Utilities.query(this, "getUczenId",imieW);
		id = getUczenId.get(0).get("id_ucznia");
		przedmioty = Utilities.query(this, "getUczenPrzedmioty",id);
		int iloscP = przedmioty.size();


		pointer = 1;
		pointer2 = 1;
		
		for(int a=0;a<iloscP;a++){
			idP = przedmioty.get(a).get("id_przedmiotu");
			semestr = "zimowy";

			result = Utilities.query(this, "getUczenOceny",id,idP,rok_szkolny,semestr);
			
			iloscO = result.size();
			if(iloscO==0){
				pointer++;
			}
			else {
				tv1.setText("Rok szkolny "+rok_szkolny+", semestr zimowy.");
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
			if(pointer==iloscP){
				TableRow pusta = new TableRow(this);
				TextView blank = new TextView(this);
				blank.setText("Rok szkolny "+rok_szkolny+", semestr zimowy.\n\nBrak ocen w tym semestrze.");
				pusta.addView(blank);
				oceny.addView(pusta);
			}
		}
		for(int a=0;a<iloscP;a++){
			idP = przedmioty.get(a).get("id_przedmiotu");
			semestr = "letni";

			result = Utilities.query(this, "getUczenOceny",id,idP,rok_szkolny,semestr);
			iloscO = result.size();
			if(iloscO==0){
				pointer2++;
			}
			else {
				TableRow pusta = new TableRow(this);
				TextView blank = new TextView(this);
				blank.setText("");
				pusta.addView(blank);
				oceny.addView(pusta);
				tv2 = new TextView(this);
				tv2.setText("Rok szkolny "+rok_szkolny+", semestr letni.");
				oceny.addView(tv2);
				TableRow pusta2 = new TableRow(this);
				TextView blank2 = new TextView(this);
				blank2.setText("");
				pusta2.addView(blank2);
				oceny.addView(pusta2);
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
		if(pointer==iloscP){
			if(pointer2==iloscP){
			TableRow pusta = new TableRow(this);
			TextView blank = new TextView(this);
			blank.setText("Rok szkolny "+rok_szkolny+",\nBrak ocen w danym roku szkolnym.");
			pusta.addView(blank);
			oceny.addView(pusta);
			}
		}
	}
}
