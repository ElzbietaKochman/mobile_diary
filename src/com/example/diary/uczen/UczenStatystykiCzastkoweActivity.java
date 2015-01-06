package com.example.diary.uczen;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.achartengine.ChartFactory;

import com.example.diary.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class UczenStatystykiCzastkoweActivity extends Activity {

	Intent i;
	String rokW, przedmiotW, id, wybranyPrzedmiot="", wybranyRok ="", pId,pNazwa, zmienna, przedmiotId;
	ArrayList<String> listaPrzedmiot, listaRok;
	Map<String, List<Map<String,String>>> tablica;
	List<Map<String, String>> przedmioty= null, getUczenOceny = null;
	Map<String,String> mapa = new HashMap<String, String>();
	List<Map<String,String>> lista = new ArrayList<Map<String,String>>() ;
	TextView t;
	ArrayList<Date> data = new ArrayList<Date>();
	ArrayList<Double> oceny = new ArrayList<Double>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uczen_statystyki_czastkowe);
		t = (TextView)findViewById(R.id.przedmiotek);
		przedmiotW = getIntent().getExtras().getString("przedmiotW");
		rokW = getIntent().getExtras().getString("rokW");
		id = getIntent().getExtras().getString("id");

		przedmioty = Utilities.query(this, "getUczenPrzedmioty",id);

		listaPrzedmiot = new ArrayList<String>();
		int iloscP = przedmioty.size();
		tablica = new HashMap<String,List<Map<String,String>>>();

		if(iloscP>0){

			for(int a=0;a<iloscP;a++){
				
				if(tablica.containsKey(przedmioty.get(a).get("rok_szkolny"))){
					for(Entry<String, List<Map<String,String>>> entry: tablica.entrySet()){
						if(entry.getKey().equals(przedmioty.get(a).get("rok_szkolny"))){
							mapa = new HashMap<String, String>();
							mapa.put("id_przedmiotu",przedmioty.get(a).get("id_przedmiotu"));
							mapa.put("nazwa",przedmioty.get(a).get("nazwa"));
							tablica.get(przedmioty.get(a).get("rok_szkolny")).add(mapa);
						}
					}
				}
				else{
					lista = new ArrayList<Map<String,String>>();
					mapa = new HashMap<String, String>();
					mapa.put("id_przedmiotu",przedmioty.get(a).get("id_przedmiotu"));
					mapa.put("nazwa",przedmioty.get(a).get("nazwa"));
					lista.add(mapa);
					tablica.put(przedmioty.get(a).get("rok_szkolny"), lista);
				}
			}
		}
		else {
			listaPrzedmiot.add(0, "brak przedmiotów");
		}



		listaRok = new ArrayList<String>();
		for(Entry<String, List<Map<String,String>>> entry: tablica.entrySet()) {
			listaRok.add(entry.getKey());
		}
		if(tablica.isEmpty()){
			listaRok.add(0, "brak");
		}
		// spiner rok szkolny
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaRok);
		Spinner spinner2 = (Spinner)findViewById(R.id.wybierzRok);

		spinner2.setAdapter(adapter2);
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				listaPrzedmiot.clear();
				wybranyRok = parent.getItemAtPosition(position).toString();	
				for(Map<String,String> map: tablica.get(wybranyRok)){
					listaPrzedmiot.add(map.get("nazwa"));
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(UczenStatystykiCzastkoweActivity.this, android.R.layout.simple_spinner_item, listaPrzedmiot);
				Spinner spinner = (Spinner)findViewById(R.id.wybierzPrzedmiot);
				spinner.setAdapter(adapter);
				spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						wybranyPrzedmiot = parent.getItemAtPosition(position).toString();
						Log.d("asdf", wybranyPrzedmiot);
					}
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						Toast.makeText(getApplicationContext(), "Wybierz rok", Toast.LENGTH_SHORT).show();
					}
				});


			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), "Wybierz przedmiot", Toast.LENGTH_SHORT).show();
			}
		});


		listaPrzedmiot.add(wybranyPrzedmiot);

		if(rokW.equals("brak") || przedmiotW.equals("brak")){
			Toast.makeText(getApplicationContext(), "Wybierz rok i przedmiot", Toast.LENGTH_LONG).show();
		}
		else {}
	}

	public void onCzastkowe(View v){

		// logika pobierania danych 
		//przedmiotId = z tabeli pobieramy id przedmiotu, ktorego nazwe i  rok wybralismy
		for(Map<String,String> map: tablica.get(wybranyRok)){
			if(map.get("nazwa").equals(wybranyPrzedmiot)){
				przedmiotId = map.get("id_przedmiotu");
			}
		}
		
		rokW = wybranyRok;
		Log.d("rokW", rokW);
		Log.d("id", id);
		Log.d("przedmiot", przedmiotId);
		
		
		getUczenOceny = Utilities.query(this, "getUczenOceny",id,przedmiotId,rokW,"cos");
		Log.d("DATA", getUczenOceny.get(0).get("data"));
		Log.d("OCENA", getUczenOceny.get(0).get("wartosc"));
		// tutaj zapis do czegoœ, z czego potem robimy wykresy, ale nie wiem jak robiæ wykresy, wiec ide dalej
		
		for(int c=0;c<getUczenOceny.size();c++){
			oceny.add(c,Double.parseDouble(getUczenOceny.get(c).get("wartosc")));
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d;
			try {
				d = format.parse(getUczenOceny.get(c).get("data"));
				//Log.d("dataD", d.toString());
				if(data.contains(d)){
					
				}
				else {
					
					data.add(d);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
		Graph g = new Graph();
		g.setTitle("Wykres ocen w zale¿noœci od czasu.");
		Intent i = g.getIntent(this, data, oceny);
		startActivity(i);
	}
	
	public void onHistogram(View v){
		for(Map<String,String> map: tablica.get(wybranyRok)){
			if(map.get("nazwa").equals(wybranyPrzedmiot)){
				przedmiotId = map.get("id_przedmiotu");
			}
		}
		
		rokW = wybranyRok;
		Log.d("rokW", rokW);
		Log.d("id", id);
		Log.d("przedmiot", przedmiotId);
		
		
		getUczenOceny = Utilities.query(this, "getUczenOceny",id,przedmiotId,rokW,"caly");
		//lista wartosci ocen
		ArrayList<String> listaWartosci = new ArrayList<String>();
		ArrayList<Integer> listaZliczonych = new ArrayList<Integer>();
		int pointer=1;
		for(int b=0;b<getUczenOceny.size();b++){
			String ocena = getUczenOceny.get(b).get("wartosc");
			if(listaWartosci.contains(ocena)){
				int pozycja = listaWartosci.indexOf(ocena);
				int ilosc = listaZliczonych.get(pozycja)+1;
				listaZliczonych.set(pozycja, ilosc);
				Log.d("pozycja", Integer.toString(pozycja));
				Log.d("wartosc", ocena);
				Log.d("ilosc oceny", Integer.toString(listaZliczonych.get(pozycja)));
			}
			else {
				listaWartosci.add(ocena);
				int tmp = listaWartosci.indexOf(ocena);
				listaZliczonych.add(tmp,pointer);
				Log.d("wartosc oceny z else" , ocena);
				Log.d("pozycja z else", Integer.toString(listaWartosci.indexOf(ocena)));
				Log.d("pozycja pointera w zliczonych", Integer.toString(listaZliczonych.lastIndexOf(pointer)));
			}
		}	
		Graph graph = new Graph();
		graph.setTitle("Histogram ocen z wybranego przedmiotu\nw wybranym roku szkolnym.");
		Intent i = graph.intentHistogram(this, listaWartosci, listaZliczonych);
		startActivity(i);
	}
	
	public void onCalyRok(View v){
		ArrayList<String> listaWartosci = new ArrayList<String>();
		ArrayList<Integer> listaZliczonych = new ArrayList<Integer>();
		rokW = wybranyRok;
		getUczenOceny = Utilities.query(this, "ocenyCalyRok", id, rokW);
		int pointer=1;
		for(int b=0;b<getUczenOceny.size();b++){
			String ocena = getUczenOceny.get(b).get("wartosc");
			if(listaWartosci.contains(ocena)){
				int pozycja = listaWartosci.indexOf(ocena);
				int ilosc = listaZliczonych.get(pozycja)+1;
				listaZliczonych.set(pozycja, ilosc);
				Log.d("pozycja", Integer.toString(pozycja));
				Log.d("wartosc", ocena);
				Log.d("ilosc oceny", Integer.toString(listaZliczonych.get(pozycja)));
			}
			else {
				listaWartosci.add(ocena);
				int tmp = listaWartosci.indexOf(ocena);
				listaZliczonych.add(tmp,pointer);
				Log.d("wartosc oceny z else" , ocena);
				Log.d("pozycja z else", Integer.toString(listaWartosci.indexOf(ocena)));
				Log.d("pozycja pointera w zliczonych", Integer.toString(listaZliczonych.lastIndexOf(pointer)));
			}
		}	
		
		Graph graph = new Graph();
		graph.setTitle("Histogram ocen z ca³ego roku.");
		Intent i = graph.intentHistogram(this, listaWartosci, listaZliczonych);
		startActivity(i);
	}
}
