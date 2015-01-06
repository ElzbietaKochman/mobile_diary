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

public class OpiekunArchiwumOgloszenieActivity extends Activity {

	List<Map<String, String>> result = null, result2 = null, getUczenId = null;
	TextView tv1;
	String id, rokW,imieW;
	Intent i;
	String idN = null;
	int ilosc;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_archiwum_ogloszenie);

		i = getIntent();
		rokW = i.getExtras().getString("rokW");
		imieW = i.getExtras().getString("imieW");
		getUczenId = Utilities.query(this, "getUczenId",imieW);
		id = getUczenId.get(0).get("id_ucznia");
		tv1 = (TextView) findViewById(R.id.textView1);
		tv1.setText("Tablica og³oszeñ,\nuczeñ: "+imieW+", rok szkolny "+rokW);
		result = Utilities.query(this, "getUczenArchiwumOgloszenia",id,rokW);

		TableLayout ogloszenia = (TableLayout)findViewById(R.id.tabelka);
		ogloszenia.setStretchAllColumns(true);
		ogloszenia.setColumnShrinkable(1, true);
		ogloszenia.bringToFront();

		ilosc = result.size();
		if(ilosc > 0){
			for(int a=0;a<ilosc;a++){
				idN = result.get(a).get("id_nauczyciela");
				result2 = Utilities.query(this, "getNauczyciel",idN);


				TableRow tr = new TableRow(this);
				TextView data = new TextView(this);
				data.setText(result.get(a).get("data"));
				TextView godz = new TextView(this);
				String godzina = result.get(a).get("godz_wyslania");
				godzina = godzina.substring(0, 5);
				godz.setText("     o "+godzina);
				tr.addView(data);
				tr.addView(godz);
				ogloszenia.addView(tr);

				TableRow tr2 = new TableRow(this);
				TextView naucz = new TextView(this);
				naucz.setText("Od: ");
				TextView nauczyciel = new TextView(this);
				nauczyciel.setText(result2.get(0).get("nazwa"));
				tr2.addView(naucz);
				tr2.addView(nauczyciel);

				TableRow tr3 = new TableRow(this);
				TextView temat = new TextView(this);
				temat.setText(result.get(a).get("temat").replace("+", " "));
				TextView tm = new TextView(this);
				tm.setText("Temat: ");
				tr3.addView(tm);
				tr3.addView(temat);

				TableRow tr4 = new TableRow(this);

				TextView t = new TextView(this);
				t.setText("");
				TextView tresc = new TextView(this);
				tresc.setText(result.get(a).get("tresc").replace("+", " "));
				tr4.addView(t);
				tr4.addView(tresc);


				TableRow tr5 = new TableRow(this);
				TextView blank = new TextView(this);
				blank.setText("");
				tr5.addView(blank);

				ogloszenia.addView(tr2);
				ogloszenia.addView(tr3);
				ogloszenia.addView(tr4);
				ogloszenia.addView(tr5);
			}
		}
		else {
			TableRow tr = new TableRow(this);
			TextView data = new TextView(this);
			data.setText("Brak og³oszeñ w tym roku szkolnym.");
			tr.addView(data);
			ogloszenia.addView(tr);
		}
	}
}
