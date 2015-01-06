package com.example.diary.opiekun;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class OpiekunKomunikatorActivity extends Activity {
	
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opiekun_komunikator);
	}
	
	
	public void onNowe(View v){
		i = new Intent(this, OpiekunKomunikatorNoweActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	
	public void onMoje(View v){
		i = new Intent(this, OpiekunKomunikatorMojeActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	
	public void onRozmowa(View v){
		i = new Intent(this, OpiekunKomunikatorRozmowaWybierzActivity.class);
		Log.d("z komunikator, do rozmowa wybierz", "tak");
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	
	public void onZadzwon(View v){
		i = new Intent(this, OpiekunKomunikatorZadzwonActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	

}
