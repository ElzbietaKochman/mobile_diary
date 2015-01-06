package com.example.diary.opiekun;
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

public class OpiekunUwagaActivity extends Activity {

	TextView tv;
	List<Map<String, String>> result = null, result2=null,getUczenId=null;
	int ilosc;
	String id, idN, rok, imieW;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_uwaga);
		
		tv = (TextView)findViewById(R.id.textView1);
		rok = getIntent().getExtras().getString("rok_szkolny");
		imieW = getIntent().getExtras().getString("imieW");
		tv.setText("Uwagi od nauczycieli,\n"+imieW+", rok szkolny "+rok);
		TableLayout uwagi = (TableLayout)findViewById(R.id.tabelka);
		 uwagi.setStretchAllColumns(true);
		 uwagi.setColumnShrinkable(1, true);
		 uwagi.bringToFront();
		 getUczenId = Utilities.query(this, "getUczenId",imieW);
		 id = getUczenId.get(0).get("id_ucznia");
		 rok = getIntent().getExtras().getString("rok_szkolny");
		 Log.d("rok", rok);
		 result = Utilities.query(this, "getUczenUwagi",id,rok);
		 ilosc = result.size();
		 
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
		 }

	}

	
}
