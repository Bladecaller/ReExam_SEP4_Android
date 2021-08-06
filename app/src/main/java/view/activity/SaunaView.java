package view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


import model.room.entity.Sauna.DataPoint;
import viewmodel.SaunaViewModel;


public class SaunaView extends AppCompatActivity {

    private TextView saunaName;
    private LineChart graphCO2,graphTemp, graphHum;
    private SaunaViewModel mSauna;
    private List<DataPoint> datapointList;
    private List<String> co2,humidity, temperature;
    private ArrayList<String> xAxis = new ArrayList<>();
    private ArrayList<String> yAxisHum = new ArrayList<>();
    private ArrayList<String> yAxisCO2 = new ArrayList<>();
    private ArrayList<String> yAxisTemp = new ArrayList<>();
    private ArrayList<Entry> yAxisHumEntry = new ArrayList<>();
    private ArrayList<Entry> yAxisCO2Entry = new ArrayList<>();
    private ArrayList<Entry> yAxisTempEntry = new ArrayList<>();

    private TextView txtCO2,txtHum,txtTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna);
        saunaName = findViewById(R.id.saunaName);
        graphCO2 = findViewById(R.id.lineChartCO2);
        graphTemp = findViewById(R.id.lineChartTemp);
        graphHum = findViewById(R.id.lineChartHum);
        txtCO2 = findViewById(R.id.cO2Val);
        txtHum = findViewById(R.id.humVal);
        txtTemp = findViewById(R.id.tempVal);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }

        mSauna = new ViewModelProvider(this).get(SaunaViewModel.class);


        Bundle extras = getIntent().getExtras();
        String name;
        name = String.valueOf(extras.getInt("Sauna"));
        saunaName.setText("Sauna " + name);
        datapointList = mSauna.getAllDatapointsForASauna(extras.getInt("Sauna")).getValue();
        mSauna.getAllDatapointsForASauna(extras.getInt("Sauna")).observe(this, new Observer<List<DataPoint>>() {
            @Override
            public void onChanged(List<DataPoint> dataPoints) {
                datapointList = dataPoints;

                float c;
                float h;
                float t;

                for (int i =0; i < datapointList.size(); i++){
                    xAxis.add(datapointList.get(i).getDateTime());
                    yAxisCO2.add(datapointList.get(i).getCo2());
                    yAxisTemp.add(datapointList.get(i).getTemperature());
                    yAxisHum.add(datapointList.get(i).getHumidity());

                    c = Float.parseFloat(datapointList.get(i).getCo2().trim().replace(",","."));
                    yAxisCO2Entry.add(i,new Entry(i,c));

                    h = Float.parseFloat(datapointList.get(i).getHumidity().trim().replace(",","."));
                    yAxisHumEntry.add(i,new Entry(i,h));

                    t = Float.parseFloat(datapointList.get(i).getTemperature().trim().replace(",","."));
                    yAxisTempEntry.add(i,new Entry(i,t));


                }
                txtCO2.setText(yAxisCO2.get(yAxisCO2.size()-1).trim().replace(",",".")+" ppm");
                txtHum.setText(yAxisHum.get(yAxisHum.size()-1).replace(",",".") + " %");
                txtTemp.setText(yAxisTemp.get(yAxisTemp.size()-1).replace(",",".") +"\u00B0");


                Description dCO2 = graphCO2.getDescription();
                Legend legendCO2 = graphCO2.getLegend();
                XAxis xCO2 = graphCO2.getXAxis();
                YAxis yCO2 = graphCO2.getAxisLeft();
                YAxis yRCO2 = graphCO2.getAxisRight();

                Description dHum = graphHum.getDescription();
                Legend legendHum = graphHum.getLegend();
                XAxis xHum = graphHum.getXAxis();
                YAxis yHum = graphHum.getAxisLeft();
                YAxis yRHum = graphHum.getAxisRight();

                Description dTemp = graphTemp.getDescription();
                Legend legendTemp = graphTemp.getLegend();
                XAxis xTemp = graphTemp.getXAxis();
                YAxis yTemp = graphTemp.getAxisLeft();
                YAxis yRTemp = graphTemp.getAxisRight();

                LineDataSet datasetCO2 = new LineDataSet(yAxisCO2Entry,"CO2");
                datasetCO2.setDrawCircles(false);
                datasetCO2.setColor(0xFF4F9FFF);
                datasetCO2.setValueTextColor(Color.WHITE);
                datasetCO2.setDrawHorizontalHighlightIndicator(false);
                datasetCO2.setDrawVerticalHighlightIndicator(false);
                dCO2.setText("");
                legendCO2.setTextColor(Color.WHITE);
                xCO2.setDrawAxisLine(false);
                xCO2.setDrawGridLines(false);
                xCO2.setDrawGridLinesBehindData(false);
                xCO2.setDrawLabels(false);
                yCO2.setDrawAxisLine(false);
                yCO2.setDrawGridLines(false);
                yCO2.setDrawLabels(false);
                yRCO2.setDrawAxisLine(false);
                yRCO2.setDrawGridLines(false);
                yRCO2.setDrawLabels(false);


                LineDataSet datasetHum = new LineDataSet(yAxisHumEntry,"Humidity");
                datasetHum.setDrawCircles(false);
                datasetHum.setColor(0xFF28D501);
                datasetHum.setValueTextColor(Color.WHITE);
                datasetHum.setDrawHorizontalHighlightIndicator(false);
                datasetHum.setDrawVerticalHighlightIndicator(false);
                dHum.setText("");
                legendHum.setTextColor(Color.WHITE);
                xHum.setDrawAxisLine(false);
                xHum.setDrawGridLines(false);
                xHum.setDrawGridLinesBehindData(false);
                xHum.setDrawLabels(false);
                yHum.setDrawAxisLine(false);
                yHum.setDrawGridLines(false);
                yHum.setDrawLabels(false);
                yRHum.setDrawAxisLine(false);
                yRHum.setDrawGridLines(false);
                yRHum.setDrawLabels(false);

                LineDataSet datasetTemp = new LineDataSet(yAxisTempEntry,"Temperature");
                datasetTemp.setDrawCircles(false);
                datasetTemp.setColor(0xFFFF505A);
                datasetTemp.setValueTextColor(Color.WHITE);
                datasetTemp.setDrawHorizontalHighlightIndicator(false);
                datasetTemp.setDrawVerticalHighlightIndicator(false);
                dTemp.setText("");
                legendTemp.setTextColor(Color.WHITE);
                xTemp.setDrawAxisLine(false);
                xTemp.setDrawGridLines(false);
                xTemp.setDrawGridLinesBehindData(false);
                xTemp.setDrawLabels(false);
                yTemp.setDrawAxisLine(false);
                yTemp.setDrawGridLines(false);
                yTemp.setDrawLabels(false);
                yRTemp.setDrawAxisLine(false);
                yRTemp.setDrawGridLines(false);
                yRTemp.setDrawLabels(false);



                graphCO2.setData(new LineData(datasetCO2));
                graphCO2.setDrawBorders(false);
                graphCO2.setDrawGridBackground(false);

                graphHum.setData(new LineData(datasetHum));
                graphHum.setDrawBorders(false);
                graphHum.setDrawGridBackground(false);

                graphTemp.setData(new LineData(datasetTemp));
                graphTemp.setDrawBorders(false);
                graphTemp.setDrawGridBackground(false);
            }
        });







    }
}