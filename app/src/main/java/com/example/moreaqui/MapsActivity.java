package com.example.moreaqui;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;



import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Location location;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private GoogleMap mMap;


    private Cursor wordsCursor;
    private ConfDatabase cursor;
    private List<LocationEstate> dataList;

    private BitmapDescriptor bitmapDescriptor;

    String type;
    String size;
    String status;
    String phone;
    double latitudeDB;
    double longitudeDB;
    double latitdeCam;
    double longitudeCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Puxa o mapa na api e notifica quando ele estiver pronto.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        cursor = new ConfDatabase(MapsActivity.this);
        wordsCursor = cursor.getReadableDatabase().rawQuery("SELECT * FROM "
                + cursor.TABLE_NAME, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //Colocando algumas configurações para o mapa da api
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);


        //Objeto que pega localização do usuário
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            //Acompanha a localização do gps
            @Override
            public void onLocationChanged(@NonNull Location location) {

                Log.d("Localização", "onLocationChanged: " + location.toString());

                //Setando a localização do usuario e ea colocando no mapa
                Double latitudeUser = location.getLatitude();
                Double longitudeUser = location.getLongitude();
                mMap.clear();
                LatLng localizacaoUsuario = new LatLng(latitudeUser , longitudeUser);
                mMap.addMarker(new MarkerOptions().position(localizacaoUsuario).title("Você está aqui!"));

                //abrindo a lista do banco de dados
                dataList = ControllDatabase.getListtData(MainActivity.getDB().Open());

                //verifica se existe alguma coisa na lista
                if(dataList != null) {
                    //Pegando os dados do banco para desenhar o ponteiro
                    while (wordsCursor.moveToNext()) {
                        latitudeDB = wordsCursor.getDouble(wordsCursor.getColumnIndex(cursor.LATITUDE));
                        longitudeDB = wordsCursor.getDouble(wordsCursor.getColumnIndex(cursor.LONGITUDE));
                        size = wordsCursor.getString(wordsCursor.getColumnIndex(cursor.SIZE));
                        type = wordsCursor.getString(wordsCursor.getColumnIndex(cursor.TYPE));
                        phone = wordsCursor.getString(wordsCursor.getColumnIndex(cursor.PHONE));
                        status = wordsCursor.getString(wordsCursor.getColumnIndex(cursor.STATUS));

                        LatLng localizacaoCriada = new LatLng(latitudeDB, longitudeDB);

                        //verifica qual o tipo de imovel para poder colocar o ponteiro no mapa dependendo do seu tip
                        if (type.equals("Casa")) {
                            mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.casa2))
                                    .anchor(0.0f, 1.0f)
                                    .position(localizacaoCriada)
                                    .title("Tamanho: " + size + " | Contato: " + phone + " | (" + status + ")"));
                        } else if (type.equals("Loja")) {
                            mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.loja))
                                    .anchor(0.0f, 1.0f)
                                    .position(localizacaoCriada)
                                    .title("Tamanho: " + size + " | Contato: " + phone + " | (" + status + ")"));
                        } else if (type.equals("Apartamento")) {
                            mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.apt))
                                    .anchor(0.0f, 1.0f)
                                    .position(localizacaoCriada)
                                    .title("Tamanho: " + size + " | Contato: " + phone + " | (" + status + ")"));
                        } else {
                            mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.nada))
                                    .anchor(0.0f, 1.0f)
                                    .position(localizacaoCriada)
                                    .title("Tamanho: " + size + " | Contato: " + phone + " | (" + status + ")"));
                        }

                    }

                    //fazendo a camera focar no primeiro  imovel criado
                    wordsCursor.moveToFirst();
                    double latC = wordsCursor.getDouble(wordsCursor.getColumnIndex(cursor.LATITUDE));
                    double longC = wordsCursor.getDouble(wordsCursor.getColumnIndex(cursor.LONGITUDE));
                    LatLng latitudeCamera = new LatLng(latC, longC);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeCamera, 17));


                    //caso não tenha nenhum ímovel ele irá forcar a localização do usúario
                }else{
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacaoUsuario, 17));
                }
            }
        };

        //acompanha as mudanças do mapa em tempo real
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);
        }

    }

}
