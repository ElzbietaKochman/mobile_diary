package com.example.diary.opiekun;


import com.example.diary.*;
import com.example.diary.uczen.UczenStatystykiCzastkoweActivity;
import com.example.diary.uczen.UczenStatystykiKoncoweActivity;
import com.example.diary.uczen.UczenStatystykiPrzedmiotActivity;
import com.example.diary.uczen.UczenStatystykiSrednieActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class OpiekunStatystykiActivity extends Activity {

	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_statystyki);
	}
	public void onSrednie(View v){
		i = new Intent(this,OpiekunStatystykiSrednieActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("rokW", "brak");
		i.putExtra("status", "brak");
		startActivity(i);
	}
	
	public void onKoncowe(View v){
		i = new Intent(this,OpiekunStatystykiKoncoweActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("rokW", "brak");
		startActivity(i);
	}
	
	public void onPrzedmiot(View v){
		i = new Intent(this,OpiekunStatystykiPrzedmiotActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("przedmiotW", "brak");
		startActivity(i);
	}
	
	public void onCzastkowe(View v){
		i = new Intent(this,OpiekunStatystykiCzastkoweActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("rokW", "brak");
		i.putExtra("przedmiotW", "brak");
		startActivity(i);
	}
	
}
