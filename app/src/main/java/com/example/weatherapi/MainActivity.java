package com.example.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressDialog progressDialog;
    EditText searchcity;
    ImageView search;
    TextView humidity,pressure,Wind,maxtemp,temp;
    Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        searchcity=findViewById(R.id.searchcity);
        search=findViewById(R.id.search);
        humidity=findViewById(R.id.humidity);
        pressure=findViewById(R.id.pressure);
        Wind=findViewById(R.id.wind);
        maxtemp=findViewById(R.id.maxtemp);
        temp=findViewById(R.id.temprature);
        progressDialog=new ProgressDialog(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String City=searchcity.getText().toString().trim();
                if (!City.isEmpty())
                {
                    closeKeyboard();
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();
                    WeatherInterface weatherInterface=WeatherInterface.retrofit.create(WeatherInterface.class);
                    Call<Main> call=weatherInterface.getreport(City,"9cf96029c663212051f9671ed98add7c","metric");
                    call.enqueue(new Callback<Main>() {
                        @Override
                        public void onResponse(Call<Main> call, Response<Main> response) {
                            if (response.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Main main=response.body();
                                Pojo pojo=main.getMain();
                                Wind wind=main.getWindObject();
                                float Temp=pojo.getTemp();
                                Integer Temprature=(int)(Temp);
                                temp.setText(""+Temprature+"°C");
                                humidity.setText(""+pojo.getHumidity());
                                pressure.setText(""+pojo.getPressure());
                                maxtemp.setText(""+pojo.getTemp_max());
                                Wind.setText(""+wind.getSpeed());
                            }
                        }

                        @Override
                        public void onFailure(Call<Main> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Please check your internet", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Please enter a city", Toast.LENGTH_SHORT).show();
                }

            }

        });

//        textView=findViewById(R.id.textView2);
//        button=findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WeatherInterface weatherInterface=WeatherInterface.retrofit.create(WeatherInterface.class);
//                Call<Main> call=weatherInterface.getreport("Gujarat","9cf96029c663212051f9671ed98add7c");
//                call.enqueue(new Callback<Main>() {
//                    @Override
//                    public void onResponse(Call<Main> call, Response<Main> response) {
//                        if (response.isSuccessful())
//                        {
//                            Toast.makeText(MainActivity.this, "Data get", Toast.LENGTH_SHORT).show();
//                        }
//                        Main main=response.body();
//                        Pojo pojo=main.getMain();
//                        Wind wind=main.getWindObject();
//                        float integer=wind.getSpeed();
//                        textView.setText(""+integer);
//                    }
//
//                    @Override
//                    public void onFailure(Call<Main> call, Throwable t) {
//
//                    }
//                });
//            }
//        });

    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}