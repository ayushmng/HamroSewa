package com.example.ayush.myapplication.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ayush.myapplication.Fragmentation.FragmentActivity;
import com.example.ayush.myapplication.R;

public class MainActivity extends AppCompatActivity {

    //    private int CALL_PERMISSION_CODE = 1;
    //    PermissionManager permissionManager;

    private static int SPLASH_TIME_OUT = 2300;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        progressbar = findViewById(R.id.progressBar); // These 2 lines code is for ProgressBar.
        progressbar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

//        if (!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
//        else {
//            setContentView(R.layout.activity_main);
//            progressbar = findViewById(R.id.progressBar); // These 2 lines code is for ProgressBar.
//            progressbar.setVisibility(View.VISIBLE);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }, SPLASH_TIME_OUT);
//        }
//    }
//
//    public boolean isConnected(Context context) {
//
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netinfo = cm.getActiveNetworkInfo();
//
//        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
//            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
//                return true;
//            else return true;
//        } else
//            return false;
//    }
//
//    public AlertDialog.Builder buildDialog(Context c){
//
////---- final AlertDialog.Builder builder = new AlertDialog.Builder(c); // Yo tala ko code ho i.e. taking default app Theme.----//
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(c, R.style.AlertDialogTheme); //Yo theme style ma set gareko ho.
//        builder.setCancelable(false); // Yesle background ma n back btn press huna rokx.
//        builder.setTitle("No Internet Connection");
//        builder.setMessage("Please check your Internet Connection and Try Again.");
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                finish();
//            }
//        });
//
//        return builder;
    }
}


