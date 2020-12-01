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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



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
    private ConfDatabase helper;


    String type;
    String size;
    String status;
    String phone;
    double latitudeDB;
    double longitudeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        helper = new ConfDatabase(MapsActivity.this);
        wordsCursor = helper.getReadableDatabase().rawQuery("SELECT * FROM "
                + helper.TABLE_NAME, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//       getImoveis();

        //Objeto que pega localização do usuário
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Localização", "onLocationChanged: " + location.toString());
                Double latitudeUser = location.getLatitude();
                Double longitudeUser = location.getLongitude();
                mMap.clear();
                LatLng localizacaoUsuario = new LatLng(latitudeUser , longitudeUser);
                mMap.addMarker(new MarkerOptions().position(localizacaoUsuario).title("Você está aqui!"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacaoUsuario, 17));
                while (wordsCursor.moveToNext()) {
                    latitudeDB = wordsCursor.getDouble(wordsCursor.getColumnIndex(helper.LATITUDE));
                    longitudeDB = wordsCursor.getDouble(wordsCursor.getColumnIndex(helper.LONGITUDE));
                    size = wordsCursor.getString(wordsCursor.getColumnIndex(helper.SIZE));
                    type = wordsCursor.getString(wordsCursor.getColumnIndex(helper.TYPE));
                    phone = wordsCursor.getString(wordsCursor.getColumnIndex(helper.PHONE));
                    status = wordsCursor.getString(wordsCursor.getColumnIndex(helper.STATUS));
                    LatLng localizacaoCriada = new LatLng(latitudeDB, longitudeDB);
                    mMap.addMarker(new MarkerOptions().position(localizacaoCriada).title("Tipo: "+type+" | Tamanho: "+size+" | Contato: "+phone+" | ("+status+")"));
                    Log.e("Teste", latitudeDB+""+longitudeDB);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);
        }


    }
/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultado : grantResults) {
            //Permissão Concedida
            if (resultado == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);
                }
            }
            else
            {
                AlertaPermissao();
            }
        }
    }

    private void AlertaPermissao()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissão Negada");
        builder.setMessage("Namoral, libera ai se não, não vai funcionar bença");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void getImoveis() {
        // aramazena os dados da busca na lista
        while (wordsCursor.moveToNext()) {
            int id = wordsCursor.getInt(wordsCursor.getColumnIndex(helper._ID));
           size = wordsCursor.getString(wordsCursor.getColumnIndex(helper.SIZE));
           type = wordsCursor.getString(wordsCursor.getColumnIndex(helper.TYPE));
           phone = wordsCursor.getString(wordsCursor.getColumnIndex(helper.PHONE));
            status = wordsCursor.getString(wordsCursor.getColumnIndex(helper.STATUS));

            listaDeImoveis.add("Tamanho: "+ size + ", Tipo: " + type + ", Contato: " + phone + ", Em construção : " + status + latitudeDB + longitudeDB );
        }
    }
*/
}
