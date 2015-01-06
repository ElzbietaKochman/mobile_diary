package com.example.diary.uczen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class UczenStatystykiPrzedmiotActivity extends Activity {

	Intent i;
	List<Map<String, String>> przedmioty= null, rokSz=null, ocenySem = null, ocenyKon = null, oceny=null;
	String id, wybranyPrzedmiot, przedmiotW, przedmiotRok,  przedmiotId, ocenaSem, ocenaKon;
	int sumaSem, sumaKon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_statystyki_przedmiot);

		id = getIntent().getExtras().getString("id");
		przedmiotW = getIntent().getExtras().getString("przedmiotW");
		
		TableLayout statystykiP = (TableLayout) findViewById(R.id.tabelka);
		statystykiP.setStretchAllColumns(true);
		statystykiP.setColumnShrinkable(0, true);
		statystykiP.setColumnShrinkable(1, true);
		statystykiP.bringToFront();

		przedmioty = Utilities.query(this, "getUczenPrzedmioty",id);
		rokSz = Utilities.query(this, "getRokSz");
		int iloscP = przedmioty.size();
		if(iloscP>0){
			ArrayList<String> lista = new ArrayList<String>();
			for(int a=0;a<iloscP;a++){
				lista.add(a, przedmioty.get(a).get("nazwa"));
			}

			// spiner
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista);
			Spinner spinner = (Spinner)findViewById(R.id.wybierzPrzedmiot);
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					wybranyPrzedmiot = parent.getItemAtPosition(position).toString();	
				}
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Toast.makeText(getApplicationContext(), "Wybierz przedmiot", Toast.LENGTH_SHORT).show();
				}
			});

			
			
			if(przedmiotW.equals("brak")){
					// jak nie wybrano
				TableRow tr0 = new TableRow(this);
				TextView tv1 = new TextView(this);
				tv1.setText("Nie wybrano przedmiotu.");
				tr0.addView(tv1);
				statystykiP.addView(tr0);
			}
			else{
				TableRow tr1 = new TableRow(this);
				TextView tv1 = new TextView(this);
				tv1.setText("Wybrany przedmiot: "+przedmiotW);
				tr1.addView(tv1);
				statystykiP.addView(tr1);
				
				// wypis w tabelce ocen koncowych z tego rpzedmiotu wszystkich lat
				// rokSzkolny | suma ocen | ocena sem | ocena kon
				for(Map<String,String> el : przedmioty){
					if(el.get("nazwa").equals(przedmiotW)){
						przedmiotId = el.get("id_przedmiotu");
						przedmiotRok = el.get("rok_szkolny");
						ocenyKon = Utilities.query(this, "getUczenOceny",id,przedmiotId,przedmiotRok,"letni");
						ocenySem = Utilities.query(this, "getUczenOceny",id,przedmiotId,przedmiotRok,"zimowy");
						oceny = Utilities.query(this, "ocenaKoncowa",id,przedmiotRok);
						sumaSem = ocenySem.size();
						sumaKon = ocenyKon.size();
						
						TableRow tr2 = new TableRow(this);
						TextView tv2 = new TextView(this);
						tv2.setText("Rok szkolny: ");
						tr2.addView(tv2);
						
						TextView tv3 = new TextView(this);
						tv3.setText(przedmiotRok);
						tr2.addView(tv3);
						
						TableRow tr3 = new TableRow(this);
						TextView tv4 = new TextView(this);
						tv4.setText("Semestr");
						tr3.addView(tv4);
						
						TextView tv5 = new TextView(this);
						tv5.setText("zimowy");
						tr3.addView(tv5);
						
						TextView tv6 = new TextView(this);
						tv6.setText("letni");
						tr3.addView(tv6);
						// suma
						TableRow tr4 = new TableRow(this);
						TextView tv7 = new TextView(this);
						tv7.setText("Suma ocen:");
						tr4.addView(tv7);
						
						TextView tv8 = new TextView(this);
						tv8.setText(Integer.toString(sumaSem));
						tr4.addView(tv8);
						
						TextView tv9 = new TextView(this);
						tv9.setText(Integer.toString(sumaKon));
						tr4.addView(tv9);
						
						// koncowe
						TableRow tr5 = new TableRow(this);
						TextView tv10 = new TextView(this);
						tv10.setText("Ocena koñcowa: ");
						tr5.addView(tv10);
						
						for(Map<String,String> koncowe : oceny){
							if(koncowe.get("id_przedmiotu").equals(przedmiotId)){
								ocenaSem = koncowe.get("semestralna");
								ocenaKon = koncowe.get("koncowa");
							}
						}
						if(ocenaSem==null){
							ocenaSem="brak";
						}
						if(ocenaKon==null){
							ocenaKon="brak";
						}
						
						TextView tv11 = new TextView(this);
						tv11.setText(ocenaSem);
						tr5.addView(tv11);
						
						TextView tv12 = new TextView(this);
						tv12.setText(ocenaKon);
						tr5.addView(tv12);
						
						
						statystykiP.addView(tr2);
						statystykiP.addView(tr3);
						statystykiP.addView(tr4);
						statystykiP.addView(tr5);
					}
				}
				
				
			}
		}
		else{
			// jak nie ma oceny z zadnego przedmiotu
			TableRow tr = new TableRow(this);
			TextView tv1 = new TextView(this);
			tv1.setText("Brak informacji.");
			tr.addView(tv1);
			statystykiP.addView(tr);
		}
	}

	public void onWybierz(View v){
		i = new Intent(this, UczenStatystykiPrzedmiotActivity.class);
		i.putExtra("id", id);
		i.putExtra("przedmiotW", wybranyPrzedmiot);
		startActivity(i);
	}
}
