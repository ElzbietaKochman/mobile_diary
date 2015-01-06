package com.example.diary.nauczyciel;

import com.example.diary.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class NauczycielActivity extends Activity {
	TextView user;
	String username;
	Context context;
	Intent i;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel);
		user = (TextView)findViewById(R.id.textView2);
		username = getIntent().getExtras().getString("nazwa");
		this.user.setText("Zalogowany u¿ytkownik:\n"+username);
	}

	public void onOcena(View view){
		i = new Intent(this, NauczycielOcenaWybierzActivity.class);
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
	
	public void onObecnosc(View view){
		i = new Intent(this, NauczycielObecnoscActivity.class);
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

}
