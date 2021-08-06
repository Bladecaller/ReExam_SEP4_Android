package view.activity.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


import model.room.entity.Sauna.DataPoint;
import viewmodel.SaunaViewModel;


public class SaunaActivity extends AppCompatActivity {

    private TextView saunaName;
    private LineChart graph;
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
        graph = findViewById(R.id.lineChart);
        txtCO2 = findViewById(R.id.cO2Val);
        txtHum = findViewById(R.id.humVal);
        txtTemp = findViewById(R.id.tempVal);

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
                ArrayList<Entry> datavals = new ArrayList<>();
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

                    /*yAxisCO2Entry.add(new Entry(Float.parseFloat(xAxis.get(i)),Float.parseFloat(datapointList.get(i).getCo2())));
                    yAxisHumEntry.add(new Entry(Float.parseFloat(xAxis.get(i)),Float.parseFloat(datapointList.get(i).getHumidity())));
                    yAxisTempEntry.add(new Entry(Float.parseFloat(xAxis.get(i)),Float.parseFloat(datapointList.get(i).getTemperature())));*/
                }
                txtCO2.setText(yAxisCO2.get(0).trim().replace(",",".")+" ppm");
                txtHum.setText(yAxisHum.get(0).replace(",",".") + " %");
                txtTemp.setText(yAxisTemp.get(0).replace(",",".") +"\u00B0");

                ArrayList<ILineDataSet> dataSets = new ArrayList<>();

                LineDataSet datasetCO2 = new LineDataSet(yAxisCO2Entry,"CO2");
                datasetCO2.setDrawCircles(false);
                datasetCO2.setColor(Color.BLUE);

                LineDataSet datasetHum = new LineDataSet(yAxisHumEntry,"Humidity");
                datasetHum.setDrawCircles(false);
                datasetHum.setColor(Color.YELLOW);

                LineDataSet datasetTemp = new LineDataSet(yAxisTempEntry,"Temperature");
                datasetTemp.setDrawCircles(false);
                datasetTemp.setColor(Color.RED);

                dataSets.add(datasetCO2);
                dataSets.add(datasetHum);
                dataSets.add(datasetTemp);

                graph.setData(new LineData(dataSets));
            }
        });







    }
}