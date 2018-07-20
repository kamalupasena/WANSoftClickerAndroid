package mrt.lk.softclicker.config;

import android.app.Application;

/**
 * Created by Kamal on 7/20/18.
 */

public class MainApplication extends Application {

    private String ServerIPAddress;

    public String getServerIPAddress() {
        return ServerIPAddress;
    }

    public void setServerIPAddress(String serverIPAddress) {
        ServerIPAddress = serverIPAddress;
    }
}
