package com.example.n2;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Http {
    private static final String LINK_URL = "http://127.0.0.1:4444",
                                METHOD = "POST";

    private static URL url;
    private static HttpURLConnection connection;

    public static boolean OpenConnection(){
        try{
            url = new URL(LINK_URL);
            connection = (HttpURLConnection) url.openConnection();

            return true;
        }catch (Exception e){
            Log.e("Erro ao conectar: ", e.toString());

            return false;
        }
    }

    public static void Disconnect(){
        connection.disconnect();
    }

    public static void sendHttp(String mensagem){
        connection.setReadTimeout(15000);
        connection.setConnectTimeout(15000);
        try{
            connection.setRequestMethod(METHOD);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
        }catch(Exception e){
           Log.e("Não possível enviar mensagem: ", e.toString());
        }
    }
}
