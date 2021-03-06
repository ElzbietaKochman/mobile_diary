package com.example.diary.opiekun;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class OpiekunNiebecnoscActivity extends Activity {

	TextView tv;
	List<Map<String, String>> result = null, result2 = null, getUczenId = null;
	String id_ucznia,cos, cosie, rok_szkolny,semestr,imieW;
	int iloscDni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_nieobecnosc);
		imieW = getIntent().getExtras().getString("imieW");
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		semestr = getIntent().getExtras().getString("obecny_semestr");
		getUczenId = Utilities.query(this, "getUczenId",imieW);
		id_ucznia = getUczenId.get(0).get("id_ucznia");

		tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Nieobecności w tym semestrze,\nUczeń: "+imieW+"\nStatus - usprawiedliwione.");

		result = Utilities.query(this, "getNieobecnosciData",id_ucznia,rok_szkolny,semestr);
		iloscDni = result.size();
		TableLayout nieobecnosci = (TableLayout) findViewById(R.id.tabelka);
		nieobecnosci.setStretchAllColumns(true);
		nieobecnosci.setColumnShrinkable(1, true);
		nieobecnosci.setColumnShrinkable(2, true);
		nieobecnosci.setColumnShrinkable(0, true);
		nieobecnosci.bringToFront();
		TableRow t0 = new TableRow(this);
		TextView idata = new TextView(this);
		idata.setText("Data");
		t0.addView(idata);
		TextView ilh = new TextView(this);
		ilh.setText("Liczba godzin:");
		t0.addView(ilh);
		TextView istatus = new TextView(this);
		istatus.setText("Status:");
		t0.addView(istatus);
		nieobecnosci.addView(t0);
		for(int a=0;a<iloscDni;a++){

			result2 = Utilities.query(this, "getNieobecnosci",id_ucznia,result.get(a).get("data"),"nie");
			int rozmiar = result2.size();
			TableRow tr = new TableRow(this);
			TextView data = new TextView(this);
			TextView l = new TextView(this);
			TextView status = new TextView(this);
			//String[] nie={};
			cos="Nieobecność o godzinie: \n";
			if(rozmiar>0){
				for(int c=0;c<rozmiar;c++){
					cos = cos + result2.get(c).get("godz_pocz").substring(0, 5)+"-"+result2.get(c).get("godz_kon").substring(0, 5)+"\n";
				}

				data.setText(result.get(a).get("data"));
				l.setText("   "+Integer.toString(result2.size()));
				status.setText("nie");

				//msg.add(pointer, cos);
				tr.addView(data);
				tr.addView(l);
				tr.addView(status);
				tr.setOnClickListener(new Listener(cos, getApplicationContext()));
				//pointer++;
				nieobecnosci.addView(tr);
			}
		}
		for(int a=0;a<iloscDni;a++){

			result2 = Utilities.query(this, "getNieobecnosci",id_ucznia,result.get(a).get("data"),"tak");
			int rozmiar = result2.size();
			if(rozmiar>0){
				TableRow tr = new TableRow(this);
				TextView data = new TextView(this);
				TextView l = new TextView(this);
				TextView status = new TextView(this);
				cosie="Nieobecność o godzinie: \n";
				for(int c=0;c<rozmiar;c++){
					cosie = cosie + result2.get(c).get("godz_pocz").substring(0, 5)+"-"+result2.get(c).get("godz_kon").substring(0, 5)+"\n";
				}

				data.setText(result.get(a).get("data"));
				l.setText("   "+Integer.toString(result2.size()));
				status.setText("tak");


				tr.addView(data);
				tr.addView(l);
				tr.addView(status);
				tr.setOnClickListener(new Listener(cosie, getApplicationContext()));
				nieobecnosci.addView(tr);
			}
		}
	}
}
