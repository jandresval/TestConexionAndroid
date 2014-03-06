package com.conexion.conexionandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by jandresv on 04/03/14.
 */
public class RequesTask extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... uri){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri[0]);
        HttpResponse response;
        String responseString = null;
        try {
            response = httpClient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                return responseString;
            } else {
                response.getEntity().getContent().close();
                return Integer.toString(statusLine.getStatusCode());

            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

}
