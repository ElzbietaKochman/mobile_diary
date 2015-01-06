package com.example.diary;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import android.content.Context;

import com.example.diary.queries.Constans;
import com.example.diary.queries.makeQuery;

public class Utilities implements Constans{
	
	public static URLConnection makeOutput(String... arg0) throws Exception{
		String action = arg0[0];
        String link= URL;
        Map<String,String> params = new HashMap<String, String>();
        params.put("action", action);
        for(int i = 1 ; i < arg0.length ; i++){
        	params.put("Arg"+i, URLEncoder.encode(arg0[i],"UTF-8"));
        }
        
        URL url = new URL(link);
        URLConnection conn = url.openConnection(); 
        conn.setDoOutput(true); 
        
        ObjectOutputStream wr = new ObjectOutputStream(conn.getOutputStream());
        wr.writeObject(params);
        wr.close(); 
        return conn;
	}
	
	public static Serializable makeInput(URLConnection conn) throws Exception{
		ObjectInputStream in = new ObjectInputStream(conn.getInputStream());
        Serializable sb = (Serializable) in.readObject();
        return sb;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> query(Context context, String... params){
		List<Map<String, String>> result = null;
		try {
			result = (List<Map<String, String>>) new makeQuery().execute(params).get();
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Error:" + e.getMessage());
		}
		
		return result;
	}
	
	public static int udi(Context context, String... params){
		int result = 0;
		try {
			result = (int) new makeQuery().execute(params).get();
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Error:" + e.getMessage());
		}
		
		return result;
	}
}
