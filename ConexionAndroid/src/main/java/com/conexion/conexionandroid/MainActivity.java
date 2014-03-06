package com.conexion.conexionandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }



    }

    @Override
    protected void onResume(){
        super.onResume();
        try{
            WebView webView = (WebView)findViewById(R.id.webView);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);

            webView.setWebChromeClient(new WebChromeClient());

            final JavaScriptInterface javaScriptInterface = new JavaScriptInterface(this);


            webView.addJavascriptInterface(javaScriptInterface,"AndroidFunction");

            webView.loadUrl("file:///android_asset/www/index.html");

        }
        catch (NullPointerException e){
            Log.d("",e.getMessage());
        }

    }

    public void onClick(View v) {
        WebView webView = (WebView)findViewById(R.id.webView);
        TextView textView = (TextView)findViewById(R.id.textView);

        webView.loadUrl("javascript:callFromActivity(\""+textView.getText()+"\")");
    }

   @Override
  public boolean onCreateOptionsMenu(Menu menu) {

      // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
       return true;
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


          /*  Resources res = getResources(); //if you are in an activity
            AssetManager am = res.getAssets();

            String folder = "www";

            String fileList[] = new String[0];
            try {
                fileList = am.list(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (fileList != null)
            {
                for ( int i = 0;i<fileList.length;i++)
                {
                    Log.d("", fileList[i]);
                }
            }*/






            return rootView;
        }
    }

}
