package com.example.diary.nauczyciel;
import com.example.diary.*;
import com.example.diary.opiekun.OpiekunKomunikatorMojeActivity;
import com.example.diary.opiekun.OpiekunKomunikatorNoweActivity;
import com.example.diary.opiekun.OpiekunKomunikatorRozmowaWybierzActivity;
import com.example.diary.opiekun.OpiekunKomunikatorZadzwonActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class NauczycielKomunikatorActivity extends Activity {

	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_komunikator);
	}
	public void onNowe(View v){
		i = new Intent(this, NauczycielKomunikatorNoweActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	
	public void onMoje(View v){
		i = new Intent(this, NauczycielKomunikatorMojeActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	
	public void onRozmowa(View v){
		i = new Intent(this, NauczycielKomunikatorRozmowaWybierzActivity.class);
		Log.d("z komunikator, do rozmowa wybierz", "tak");
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	
	public void onZadzwon(View v){
		i = new Intent(this, NauczycielKomunikatorZadzwonActivity.class);
		Bundle bundle = getIntent().getExtras();
		i.putExtras(bundle);
		startActivity(i);
	}
	

}
