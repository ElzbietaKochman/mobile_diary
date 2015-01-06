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

public class OpiekunArchiwumNieobecnoscActivity extends Activity {

	TextView tv;
	List<Map<String, String>> result = null, result2 = null, getUczenId = null;
	String id_ucznia,cos, cosie, rok_szkolny,semestr,imieW;
	int iloscDni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_archiwum_nieobecnosc);

		TableLayout nieobecnosci = (TableLayout) findViewById(R.id.tabelka);
		nieobecnosci.setStretchAllColumns(true);
		nieobecnosci.setColumnShrinkable(1, true);
		nieobecnosci.setColumnShrinkable(2, true);
		nieobecnosci.setColumnShrinkable(0, true);
		nieobecnosci.bringToFront();
		
		imieW = getIntent().getExtras().getString("imieW");
		rok_szkolny = getIntent().getExtras().getString("rokW");
		getUczenId = Utilities.query(this, "getUczenId",imieW);
		id_ucznia = getUczenId.get(0).get("id_ucznia");

		semestr = "zimowy";
		tv = (TextView) findViewById(R.id.textView1);


		result = Utilities.query(this, "getNieobecnosciData",id_ucznia,rok_szkolny,semestr);
		iloscDni = result.size();
		if(iloscDni>0){
			tv.setText("Nieobecnosci, wybrany uczeñ: "+imieW+",\nrok szkolny "+rok_szkolny+", semestr "+semestr+".");
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
				cos="Nieobecnoœæ o godzinie: \n";
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
					cosie="Nieobecnoœæ o godzinie: \n";
					for(int c=0;c<rozmiar;c++){
						cosie = cosie + result2.get(c).get("godz_pocz").substring(0, 5)+"-"+result2.get(c).get("godz_kon").substring(0, 5)+"\n";
					}
					//msg.add(pointer, cosie);

					data.setText(result.get(a).get("data"));
					l.setText("   "+Integer.toString(result2.size()));
					status.setText("tak");


					tr.addView(data);
					tr.addView(l);
					tr.addView(status);
					tr.setOnClickListener(new Listener(cosie, getApplicationContext()));
					//pointer++;
					nieobecnosci.addView(tr);
				}
			}
		}
		else {
			tv.setText("Brak nieobecnosci w roku szkolnym "+rok_szkolny+", semestrze zimowym.");
		}

		semestr = "letni";

		result = Utilities.query(this, "getNieobecnosciData",id_ucznia,rok_szkolny,semestr);
		iloscDni = result.size();
		if(iloscDni>0){
			TableRow pusta = new TableRow(this);
			TextView blank = new TextView(this);
			blank.setText("");
			pusta.addView(blank);
			nieobecnosci.addView(pusta);
			TextView tv2 = new TextView(this);
			tv2.setText("Rok szkolny "+rok_szkolny+", semestr "+semestr+".");
			nieobecnosci.addView(tv2);
			TableRow pusta2 = new TableRow(this);
			TextView blank2 = new TextView(this);
			blank2.setText("");
			pusta2.addView(blank2);
			nieobecnosci.addView(pusta2);
			TableRow t02 = new TableRow(this);
			TextView idata2 = new TextView(this);
			idata2.setText("Data");
			t02.addView(idata2);
			TextView ilh2 = new TextView(this);
			ilh2.setText("Liczba godzin:");
			t02.addView(ilh2);
			TextView istatus2 = new TextView(this);
			istatus2.setText("Status:");
			t02.addView(istatus2);
			nieobecnosci.addView(t02);
			for(int a=0;a<iloscDni;a++){

				result2 = Utilities.query(this, "getNieobecnosci",id_ucznia,result.get(a).get("data"),"nie");
				int rozmiar = result2.size();
				TableRow tr = new TableRow(this);
				TextView data = new TextView(this);
				TextView l = new TextView(this);
				TextView status = new TextView(this);
				//String[] nie={};
				cos="Nieobecnoœæ o godzinie: \n";
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
					cosie="Nieobecnoœæ o godzinie: \n";
					for(int c=0;c<rozmiar;c++){
						cosie = cosie + result2.get(c).get("godz_pocz").substring(0, 5)+"-"+result2.get(c).get("godz_kon").substring(0, 5)+"\n";
					}
					//msg.add(pointer, cosie);

					data.setText(result.get(a).get("data"));
					l.setText("   "+Integer.toString(result2.size()));
					status.setText("tak");


					tr.addView(data);
					tr.addView(l);
					tr.addView(status);
					tr.setOnClickListener(new Listener(cosie, getApplicationContext()));
					//pointer++;
					nieobecnosci.addView(tr);
				}
			}
		}
		else {
			TableRow pusta = new TableRow(this);
			TextView blank = new TextView(this);
			blank.setText("");
			pusta.addView(blank);
			nieobecnosci.addView(pusta);
			TextView tv2 = new TextView(this);
			tv2.setText("Brak nieobecnosci w roku szkolnym "+rok_szkolny+", semestrze letnim.");
			nieobecnosci.addView(tv2);
		}
	}
}
