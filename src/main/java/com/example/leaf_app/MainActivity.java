package com.example.leaf_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leaf_app.widget.ActionLeafView;

public class MainActivity extends AppCompatActivity {
    ActionLeafView rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView= new ActionLeafView(this);
        setContentView(rootView);
    }
}
