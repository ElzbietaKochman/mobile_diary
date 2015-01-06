package com.example.diary.wychowawca;
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

public class WychowawcaNieobecnoscActivity extends Activity {

	List<Map<String, String>> getWycho=null, getGrupa=null, zlicz=null;
	String id_grupy="", id_nauczyciela="", id_ucznia="", nazwaUcznia="";
	Intent i;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wychowawca_nieobecnosc);

		id_nauczyciela = getIntent().getExtras().getString("id");
		Log.d("id", getIntent().getExtras().getString("id"));
		getWycho = Utilities.query(this, "getWycho", id_nauczyciela);
		id_grupy = getWycho.get(0).get("id_grupy");
		Log.d("id_grupy", id_grupy);
		getGrupa = Utilities.query(this, "getGrupa", id_grupy);
		
		bundle = getIntent().getExtras();
//		i = new Intent(WychowawcaNieobecnoscActivity.this, WychowawcaUsprawiedliwActivity.class);
		TableLayout nieobecnosc = (TableLayout) findViewById(R.id.tabelka);
		nieobecnosc.setStretchAllColumns(true);
		nieobecnosc.setColumnShrinkable(0, true);
		nieobecnosc.setColumnShrinkable(1, true);
		nieobecnosc.bringToFront();

		TableRow tr0 = new TableRow(this);
		TextView tv0 = new TextView(this);
		tv0.setText("Uczeñ");
		tr0.addView(tv0);
		TextView tv0a = new TextView(this);
		tv0a.setText("Iloœæ godzin");
		tr0.addView(tv0a);
		nieobecnosc.addView(tr0);
		

		for(int a=0; a<getGrupa.size();a++){
			final int b = a;
			TableRow tr = new TableRow(this);
			TextView tv = new TextView(this);
			tv.setText(getGrupa.get(a).get("nazwa"));
			Log.d("nazwa", getGrupa.get(a).get("nazwa"));
			id_ucznia = getGrupa.get(a).get("id_ucznia");
			
			nazwaUcznia = getGrupa.get(a).get("nazwa");
			tr.addView(tv);
			
			zlicz = Utilities.query(WychowawcaNieobecnoscActivity.this, "zlicz", id_ucznia);
			TextView tv2 = new TextView(this);
			tv2.setText(zlicz.get(0).get("liczba"));
			tr.addView(tv2);
			tr.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					i = new Intent(WychowawcaNieobecnoscActivity.this, WychowawcaUsprawiedliwActivity.class);
					id_ucznia = getGrupa.get(b).get("id_ucznia");
					nazwaUcznia = getGrupa.get(b).get("nazwa");
					i.putExtra("id_ucznia", id_ucznia);
					i.putExtra("nazwaUcznia",nazwaUcznia);
					i.putExtras(bundle);
					Log.d("Uczen: ",nazwaUcznia);
					startActivity(i);
				}
			});
			
		
			nieobecnosc.addView(tr);
		}
		
	}
}
