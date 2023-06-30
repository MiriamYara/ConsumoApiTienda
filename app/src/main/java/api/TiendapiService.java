package api;

import android.telecom.Call;

import com.example.myapi.TiendaRespuesta;

import retrofit2.http.GET;

public interface TiendapiService {

    @GET("tienda")
    Call<TiendaRespuesta> obtenerListaTienda();
}
