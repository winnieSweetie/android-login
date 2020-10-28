package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) //main function
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu); // link my self-defined front-end code to this back-end code

        Button printSrcs = findViewById(R.id.function1);
        printSrcs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this,PrintActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}