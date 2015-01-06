package com.example.diary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;

public class Graph {

	ArrayList<?> x,y;
	public String napis;
	
	public String setTitle(String nowy){
		this.napis = nowy;
		return napis;
	}
	
	
	public Intent intentHistogram(Context context, ArrayList<String> x, ArrayList<Integer> y){
		this.x = x;
		this.y = y;
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float)2);
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setXLabels(0);
		mRenderer.setYLabelsAlign(Align.RIGHT);
		mRenderer.setXTitle("Wartoœæ ocen");
		mRenderer.setYTitle("Iloœæ ocen");
		
		CategorySeries series = new CategorySeries(napis);
		for(int i=0;i<x.size();i++){
			System.out.println("trrrrrrrrrrrr"+x.get(i));
			Log.d("wartooooooooooooosc", x.get(i));
			series.add(x.get(i).toString(), y.get(i));
			mRenderer.addXTextLabel(i+1.0, x.get(i));
		}
		dataset.addSeries(series.toXYSeries());
		Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
		return intent;
	}
	
	public Intent getIntent(Context context, ArrayList<Date> x, ArrayList<Double> y){
	
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setXLabels(0);
		mRenderer.setBackgroundColor(Color.GREEN);
		mRenderer.setYLabelsColor(0, Color.RED);
		mRenderer.setYLabelsAlign(Align.RIGHT);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d;
		CategorySeries ts = new CategorySeries(napis);
		this.x = x;
		this.y = y;
		for(int i=0;i<x.size();i++){
			d = format.format(x.get(i));
			ts.add(d,y.get(i));
			mRenderer.addXTextLabel(i+1, d);
			System.out.println(x.get(i).toString());
		}		
		dataset.addSeries(ts.toXYSeries());
		XYSeriesRenderer renderer = new XYSeriesRenderer();		
		mRenderer.addSeriesRenderer(renderer);		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer,"tytul");
		
		return intent;
	}
}
