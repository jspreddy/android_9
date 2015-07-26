package com.example.inclass4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	ImageView ivMain;
	ProgressDialog pdMain;
	String xmlapi="https://itunes.apple.com/us/rss/toppaidapplications/limit=50/xml";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivMain = (ImageView) findViewById(R.id.ivMain);
		ivMain.setScaleType(ScaleType.FIT_CENTER);
		
		new DownloadFileTask().execute();
		
		
	}

	private class DownloadFileTask extends AsyncTask<Void, Integer, ArrayList<XmlEntry>> {

		protected ArrayList<XmlEntry> doInBackground(Void... params) {
			try {
				 URL url = new URL(xmlapi);
				 HttpURLConnection con = (HttpURLConnection) url.openConnection();
				 con.setRequestMethod("GET");
				 con.connect();	 	
				 int statusCode = con.getResponseCode();
				 if (statusCode == HttpURLConnection.HTTP_OK) {
					 Log.d("DEBUG","file get done");
					 InputStream in = con.getInputStream();
					 ArrayList<XmlEntry> list=EntryUtils.SAXParser.parseItems(in); 
					 con.disconnect();
					 return list;
				 }
			 } catch (MalformedURLException e) {
				 e.printStackTrace();
			 } catch (IOException e) {
				 e.printStackTrace();
			 } catch (SAXException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			
		}

		protected void onPostExecute(Void result) {
			
		}
		
		protected void onPreExecute() {
			pdMain = new ProgressDialog(MainActivity.this);
			pdMain.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pdMain.setCancelable(false);
			pdMain.setMessage("Retrieving App Details");
			pdMain.show();
		}
	}

}
