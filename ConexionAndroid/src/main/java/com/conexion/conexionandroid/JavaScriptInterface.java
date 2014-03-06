package com.conexion.conexionandroid;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jandresv on 06/03/14.
 */
public class JavaScriptInterface {

    Context mContext;

    public JavaScriptInterface(Context c) {
        mContext = c;
    }

    public void showToast(String toast){
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

}
