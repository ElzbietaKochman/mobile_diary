package com.example.diary.nauczyciel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

import com.example.diary.R;
import com.example.diary.queries.Constans;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class NauczycielPokazZdjecieActivity extends Activity {

	Bundle b;
	Bitmap bit;
	ImageView iw;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nauczyciel_pokaz_zdjecie);
		b = getIntent().getExtras();
		iw = (ImageView)findViewById(R.id.imageView1);
		Toast.makeText(this, "Zdjêcie w trakcie ³adowania...", Toast.LENGTH_SHORT).show();
		BitmapWorker task = new BitmapWorker(iw);
		String url = Constans.uploadedPhotos+b.getString("fileName");
	    task.execute(url);
	}
	
	private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		    bmOptions.inJustDecodeBounds = false;
		    bmOptions.inSampleSize = 2;
            bm = BitmapFactory.decodeStream(bis,null, bmOptions);
            bis.close();
            is.close();
       } catch (IOException e) {
           Log.e("error", "Error getting bitmap", e);
       }
       return bm;
    } 
	class BitmapWorker extends AsyncTask<String, Void, Bitmap>{

		private final WeakReference<ImageView> imageViewReference;

		public BitmapWorker(ImageView imageView) {
	        // Use a WeakReference to ensure the ImageView can be garbage collected
	        imageViewReference = new WeakReference<ImageView>(imageView);
	    }

		
		@Override
		protected Bitmap doInBackground(String... params) {
			
			return getImageBitmap(params[0]);
		}
		@Override
	    protected void onPostExecute(Bitmap bitmap) {
	        if (imageViewReference != null && bitmap != null) {
	            final ImageView imageView = imageViewReference.get();
	            if (imageView != null) {
	                imageView.setImageBitmap(bitmap);
	            }
	        }
	    }

	}
	
}
