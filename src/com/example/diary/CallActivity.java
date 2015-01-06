package com.example.diary;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CallActivity extends Activity {

	String phone;
	TextView nazwa, telefon, informacja;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		b = getIntent().getExtras();
		
		nazwa = (TextView)findViewById(R.id.nazwa);
		telefon = (TextView)findViewById(R.id.telefon);
		informacja = (TextView)findViewById(R.id.info);
		nazwa.setText("Wykonujesz po³¹czenie telefoniczne do:\n"+b.getString("nazwa_opiekun"));
		telefon.setText(b.getString("telefon"));
		informacja.setText("Op³aty zgodnie z taryf¹ u operatora");
		phone = b.getString("telefon");
	}
	
	public void call(View view) {   
        Intent callIntent = new Intent(Intent.ACTION_CALL);          
        callIntent.setData(Uri.parse("tel:"+phone));          
        startActivity(callIntent);  
	}
}
