package com.example.diary.queries;

import java.io.Serializable;
import java.net.URLConnection;

import com.example.diary.Utilities;

import android.os.AsyncTask;
import android.util.Log;

public class makeQuery extends AsyncTask<String, Void, Serializable> implements
Constans {

	@Override
	protected Serializable doInBackground(String... arg0) {
		try{
			URLConnection conn = Utilities.makeOutput(arg0);
			Serializable params = (Serializable) Utilities.makeInput(conn);

			return params;

		}catch(Exception e){
			String info =  "Exception: " + e.getMessage();
			Log.d("info", info );
			return info;
		}
	}
}
