package com.example.hello;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class PrintActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) //main function
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        TextView pr = findViewById(R.id.printAll);
        try {
            PaChong pc = new PaChong("https://blog.csdn.net/u010814849/article/details/52526582?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase");
            pr.setText("setting text is successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
