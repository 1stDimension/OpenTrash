package com.example.frontendpage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.frontendpage.networkutils.NetworkCheckout;
import com.example.frontendpage.networkutils.NetworkUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
