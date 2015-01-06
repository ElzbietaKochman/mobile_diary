package com.example.diary.uczen;
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
import android.widget.Toast;

public class UczenOcenyActivity extends Activity {
	TextView tv1;
	String rok_szkolny=null, semestr=null, id=null, idP=null;
	Intent i;
	List<Map<String, String>> result = null, przedmioty = null;
	int iloscO, iloscP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_oceny);
		tv1 = (TextView) findViewById(R.id.textView1);
		i = getIntent();
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		semestr = getIntent().getExtras().getString("obecny_semestr");
		tv1.setText("Rok szkolny "+rok_szkolny+", semestr "+semestr+".");

		TableLayout oceny = (TableLayout) findViewById(R.id.tabelka);
		oceny.setStretchAllColumns(true);
		oceny.setColumnShrinkable(1, true);
		oceny.bringToFront();
		
		id = getIntent().getExtras().getString("id");
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
