package com.example.diary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import com.example.diary.admin.*;
import com.example.diary.nauczyciel.*;
import com.example.diary.opiekun.*;
import com.example.diary.uczen.*;
import com.example.diary.wychowawca.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	private EditText usernameField,passwordField;
	String name = null, rok = null, semestr = null;
	String rola = null;
	String id = null, idN=null, id_wychowawcy="", user="";
	Intent i = null;

	List<Map<String, String>> result = null, result2=null, rokSzkolny = null, getSemestr = null;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		usernameField = (EditText)findViewById(R.id.editText1);
		passwordField = (EditText)findViewById(R.id.editText2);

	}


	public void loginPost(View view){
		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();

		if(username.equals("")||password.equals("")){
			Toast.makeText(getApplicationContext(), "Brak loginu lub has³a!", Toast.LENGTH_LONG).show();
			i = new Intent(this, MainActivity.class);
		}
		else {
			result = Utilities.query(this, "getUsername",username,password);
			if(result.size()==0){
				Toast.makeText(getApplicationContext(), "B³êdny login lub has³o!", Toast.LENGTH_LONG).show();
				i = new Intent(this, MainActivity.class);
			
			}
			else {
			Calendar kalendarz = Calendar.getInstance();
			SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			String dzis = d.format(kalendarz.getTime());
			Log.d("data", dzis);

			rokSzkolny = Utilities.query(this, "getRokSzkolny",dzis);
			getSemestr = Utilities.query(this, "getSemestr",dzis);

			try{
				rola = result.get(0).get("role");
				name = result.get(0).get("nazwa");
				rok = rokSzkolny.get(0).get("rok_szkolny");
				semestr = getSemestr.get(0).get("sem");

			}
			catch(IndexOutOfBoundsException e){
				rola = "wrong";
			}

		}
		}

		switch(rola){
		case "admin":
			i = new Intent(this, AdminActivity.class);
			id = result.get(0).get("id_admin");
			user = result.get(0).get("id_user");
			break;
		case "nauczyciel":
			i = new Intent(this, NauczycielActivity.class);
			id = result.get(0).get("id_nauczyciela");
			user = result.get(0).get("id_user");
			break;
		case "wychowawca":
			i = new Intent(this, WychowawcaActivity.class);
			id = result.get(0).get("id_nauczyciela");
			user = result.get(0).get("id_user");
			break;
		case "opiekun":
			i = new Intent(this, OpiekunActivity.class);
			id = result.get(0).get("id_opiekuna");
			user = result.get(0).get("id_user");
			break;
		case "uczen":
			i = new Intent(this, UczenActivity.class);
			id = result.get(0).get("id_ucznia");
			user = result.get(0).get("id_user");
			break;
			// w przypadku braku u¿ytkownika o podanych danych
		case "wrong":
			Toast.makeText(getApplicationContext(), "Login lub has³o jest b³êdne", 
					Toast.LENGTH_LONG).show();
			i = new Intent(this, MainActivity.class);
		default:

		}
		i.putExtra("nazwa", name);
		i.putExtra("id", id);
		i.putExtra("rok_szkolny", rok);
		i.putExtra("obecny_semestr", semestr);
		i.putExtra("idUser", user);
		startActivity(i);
		finish();
	}

}
