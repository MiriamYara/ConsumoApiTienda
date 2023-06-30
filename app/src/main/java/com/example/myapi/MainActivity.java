package com.example.myapi;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapi.databinding.ActivityMainBinding;

import java.util.ArrayList;

import api.TiendapiService;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TiendaRespuesta tiendaRespuesta;
    private ListaTienda listaTienda;
    private static final String TAG = "TIENDA";



    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new  Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addCallAdapterFactory(GsonConverterFactory.create())
                .build();

        obtenerDatos();
    }
    private void obtenerDatos(){
        TiendapiService service = retrofit.create(TiendapiService.class);
        Call<TiendaRespuesta> tiendaRespuestaCall =  service.obtenerListaTienda();

        tiendaRespuestaCall.enqueue(new Callback<TiendaRespuesta>() {
            @Override
            public void onResponse(Call<TiendaRespuesta> call, Response<TiendaRespuesta> response) {
                if(response.isSuccessful()){

                 TiendaRespuesta tiendaRespuesta=response.body();
                 ArrayList<Tienda> listaTienda = tiendaRespuesta.getResults();

                 for (int i = 0; i < listaTienda.size(); i++){
                     Tienda p =  listaTienda.get(i);
                     Log.i(TAG,"Tienda:" + p.getTitulo());
                 }

                }else {
                  Log.e(TAG,"onResponse:" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<TiendaRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());

            }
        });

    }

}