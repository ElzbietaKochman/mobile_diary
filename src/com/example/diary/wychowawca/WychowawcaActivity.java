package com.example.diary.wychowawca;

import com.example.diary.*;
import com.example.diary.nauczyciel.NauczycielDownloadActivity;
import com.example.diary.nauczyciel.NauczycielKomunikatorActivity;
import com.example.diary.nauczyciel.NauczycielObecnoscActivity;
import com.example.diary.nauczyciel.NauczycielOcenaActivity;
import com.example.diary.nauczyciel.NauczycielOcenaWybierzActivity;
import com.example.diary.nauczyciel.NauczycielOgloszenieActivity;
import com.example.diary.nauczyciel.NauczycielUwagaActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class WychowawcaActivity extends Activity {
	TextView user;
	String username; 
	Context context;
	Intent i;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wychowawca);
		user = (TextView)findViewById(R.id.textView1);
		username = getIntent().getExtras().getString("nazwa");
		this.user.setText("Zalogowany u¿ytkownik:\n"+username);
		b = getIntent().getExtras();
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		
		return true;
	};
	
	public void onOcena(View view){
		i = new Intent(this, NauczycielOcenaWybierzActivity.class);
		i.putExtras(b);
		startActivity(i);
	}
	public void onObecnosc(View view){
		i = new Intent(this, NauczycielObecnoscActivity.class);
		i.putExtras(b);
		startActivity(i);
	}
	public void onNieobecnosc(View view){
		i = new Intent(this, WychowawcaNieobecnoscActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}

	public void onOgloszenie(View view){
		i = new Intent(this, NauczycielOgloszenieActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
	public void onKomunikator(View view){
		i = new Intent(this, NauczycielKomunikatorActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
	
	public void onUwaga(View view){
		i = new Intent(this, NauczycielUwagaActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
	
	public void onPobierz(View view){
		i = new Intent(this, NauczycielDownloadActivity.class);
		Bundle b = getIntent().getExtras();
		i.putExtras(b);
		startActivity(i);
	}
	
	
}
