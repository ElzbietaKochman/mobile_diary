package com.example.diary.nauczyciel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NauczycielUwagaActivity extends Activity {
	
	List<Map<String, String>> getTegoroczni=null;
	ArrayList<String> listaUczniow;
	String rok_szkolny="", wybranyUczen="", id_ucznia="", trescUwagi="", id_nauczyciela="";
	EditText tresc;
	int dodano;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_uwaga);
		
		rok_szkolny = getIntent().getExtras().getString("rok_szkolny");
		id_nauczyciela = getIntent().getExtras().getString("id");
		Log.d("nauczyciel", id_nauczyciela);
		tresc = (EditText)findViewById(R.id.trescUwagi);
		
		getTegoroczni = Utilities.query(this, "getTegoroczni", rok_szkolny);
		listaUczniow = new ArrayList<String>();
		for(int a=0;a<getTegoroczni.size();a++){
			listaUczniow.add(a,getTegoroczni.get(a).get("nazwa"));
		}
		ArrayAdapter<String> uczniowie = new ArrayAdapter<String>(NauczycielUwagaActivity.this, android.R.layout.simple_spinner_item, listaUczniow);
		Spinner wybor = (Spinner)findViewById(R.id.wybor);
		wybor.setAdapter(uczniowie);
		wybor.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				wybranyUczen = parent.getSelectedItem().toString();
				Log.d("wybrany", wybranyUczen);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
	}
	
	public void onDodajUwaga(View v){
		for(int a=0;a<getTegoroczni.size();a++){
			Log.d(Integer.toString(a), getTegoroczni.get(a).get("nazwa"));
			if(getTegoroczni.get(a).get("nazwa").equals(wybranyUczen)){
				id_ucznia = getTegoroczni.get(a).get("id_ucznia");
				
			}
		}
		Log.d("uczen", id_ucznia);
		trescUwagi = tresc.getText().toString();
		Log.d("tresc", trescUwagi);
		dodano = Utilities.udi(NauczycielUwagaActivity.this, "dodajUwaga", id_ucznia, id_nauczyciela, trescUwagi);
		if(dodano>0){
			Toast.makeText(this, "Pomyœlnie wystawiono uwagê uczniowi.", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Nie uda³o siê dodaæ uwagi.", Toast.LENGTH_SHORT).show();
		}
	}

}
