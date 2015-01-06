package com.example.diary.uczen;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UczenActivity extends Activity {
	TextView user;
	String username, rok_szkolny;
	Context context;
	Intent i;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen);
		user = (TextView)findViewById(R.id.textView1);
		username = getIntent().getExtras().getString("nazwa");
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		this.user.setText("Zalogowany u¿ytkownik:\n"+username);
	}

	public void onOceny(View v){
		i = new Intent(this,UczenOcenyActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}

	public void onOgloszenia(View v){
		i = new Intent(this,UczenOgloszeniaActivity.class);
		i.putExtra("id_ucznia",getIntent().getExtras().getString("id"));
		i.putExtra("rok_szkolny", rok_szkolny);
		startActivity(i);
	}
	public void onUwagi(View v){
		i = new Intent(this,UczenUwagiActivity.class);
		i.putExtra("id_ucznia",getIntent().getExtras().getString("id"));
		i.putExtra("rok_szkolny", rok_szkolny);
		startActivity(i);
	}
	public void onObecnosci(View v){
		i = new Intent(this,UczenObecnosciActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
	public void onArchiwum(View v){
		i = new Intent(this,UczenArchiwumActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
	public void onStatystyki(View v){
		i = new Intent(this,UczenStatystykiActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
}
