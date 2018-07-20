package mrt.lk.softclicker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import mrt.lk.softclicker.config.MainApplication;

public class SplashActivity extends AppCompatActivity {

    private TextView txtStatus;
    private ProgressBar progressBar;
    private BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtStatus = findViewById(R.id.txtLoadingStatus);
        progressBar = findViewById(R.id.progressBar);

        WifiManager wifi;
        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiManager.MulticastLock mLock = null;
        if (wifi != null) {
            mLock = wifi.createMulticastLock("lock");
        }
        if (mLock != null) {
            mLock.acquire();
        }


        txtStatus.setText("Seeking for server");

        Intent i = new Intent(this,UDPListenerService.class);

        startService(i);

        IntentFilter filter = new IntentFilter(UDPListenerService.UDP_BROADCAST);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                unregisterReceiver(receiver);
                receiver = null;


                final String ip =  intent.getExtras().getString("sender");
                String message = intent.getExtras().getString("message");

                txtStatus.setText(String.format("Server Found :%s ", ip));


                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainApplication)getApplication()).setServerIPAddress(ip);
                        Intent intent1 = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent1);
                        finish();

                    }
                },3000);






            }
        };

        registerReceiver(receiver, filter);

    }





    @Override
    protected void onPause() {
        super.onPause();
        if(receiver != null){
            unregisterReceiver(receiver);
        }

    }



}
