package com.example.frontendpage.networkutils;

import android.util.Log;

public class NetworkCheckout {
    public static void getMacAddr() {
        String macAddrwlan0 = Utils.getMACAddress("wlan0");
        String macAddreth0 = Utils.getMACAddress("eth0");
        Log.d("MACADDR", "wlan0: " + macAddrwlan0);
        Log.d("MACADDR", "eth0: " + macAddreth0);

        String ipv4 = Utils.getIPAddress(true);
        String ipv6 = Utils.getIPAddress(false);
        Log.d("IPADDR", "V4: " + ipv4);
        Log.d("IPADDR", "V6: " + ipv6);
    }
}
