package com.example.diary.uczen;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UczenArchiwumUwagiActivity extends Activity {
	TextView tv;
	List<Map<String, String>> result = null, result2=null;
	int ilosc;
	String id, idN, rok;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_archiwum_uwagi);
		tv = (TextView)findViewById(R.id.textView1);
		rok = getIntent().getExtras().getString("rokW");
		tv.setText("Uwagi od nauczycieli, rok szkolny "+rok);
		TableLayout uwagi = (TableLayout)findViewById(R.id.tabelka);
		 uwagi.setStretchAllColumns(true);
		 uwagi.setColumnShrinkable(1, true);
		 uwagi.bringToFront();
		 id = getIntent().getExtras().getString("id");
		 Log.d("rok", rok);
		 result = Utilities.query(this, "getUczenUwagi",id,rok);
		 ilosc = result.size();
		 if(ilosc>0){
		 for(int a=0;a<ilosc;a++){
			 idN = result.get(a).get("id_nauczyciela");
				result2 = Utilities.query(this, "getNauczyciel",idN);
			 TableRow tr1 = new TableRow(this);
			 TextView data = new TextView(this);
			 data.setText(result.get(a).get("data"));
			 TextView nadawca = new TextView(this);
			 nadawca.setText(result2.get(0).get("nazwa"));
			 tr1.addView(data);
			 tr1.addView(nadawca);
			 
			 TableRow tr2 = new TableRow(this);
			 TextView t = new TextView(this);
			 t.setText("Treœæ uwagi: ");
			 TextView tresc = new TextView(this);
			 tresc.setText(result.get(a).get("tresc"));
			 tr2.addView(t);
			 tr2.addView(tresc);
			 uwagi.addView(tr1);
			 uwagi.addView(tr2);
		 }}
		 else {
			 TableRow tr1 = new TableRow(this);
			 TextView data = new TextView(this);
			 data.setText("Brak uwag w wybranym roku szkolnym.");
			 tr1.addView(data);
			 uwagi.addView(tr1);
		 }
	}
}
