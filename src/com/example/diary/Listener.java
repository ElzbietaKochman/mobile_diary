package com.example.diary;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Listener implements OnClickListener {

	String message;
	Context context;
	
	public Listener(String message, Context context){
		this.message = message;
		this.context = context;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(context, message.replace("+", " "), Toast.LENGTH_SHORT).show();
	}

}
