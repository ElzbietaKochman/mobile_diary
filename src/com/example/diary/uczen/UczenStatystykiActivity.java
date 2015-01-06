package com.example.diary.uczen;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class UczenStatystykiActivity extends Activity {

	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_statystyki);
		
		
	}
	
	public void onSrednie(View v){
		i = new Intent(this,UczenStatystykiSrednieActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("rokW", "brak");
		i.putExtra("status", "brak");
		startActivity(i);
	}
	
	public void onKoncowe(View v){
		i = new Intent(this,UczenStatystykiKoncoweActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("rokW", "brak");
		startActivity(i);
	}
	
	public void onPrzedmiot(View v){
		i = new Intent(this,UczenStatystykiPrzedmiotActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("przedmiotW", "brak");
		startActivity(i);
	}
	
	public void onCzastkowe(View v){
		i = new Intent(this,UczenStatystykiCzastkoweActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		i.putExtra("rokW", "brak");
		i.putExtra("przedmiotW", "brak");
		startActivity(i);
	}
}
