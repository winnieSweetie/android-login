package com.example.hello;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private MySqlite mSQlite;
    private EditText username;
    private EditText userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) //main function
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // execute my self defined front-end code
        Button confirm = findViewById(R.id.confirm);
        Button back = findViewById(R.id.back);
        username = findViewById(R.id.text_userid2);
        userpassword =findViewById( R.id.text_userpwd2);

        // 给按钮控件back设置功能，返回main页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //给按钮控件reday设置功能
        confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                HashMap<String,String> data = mSQlite.getAllData();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password))
                {
                    if(data.containsKey(name))
                    {
                        Toast.makeText(RegisterActivity.this,"该账号已经注册", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        mSQlite.insert(name,password);
                        Intent intent1 = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent1);
                        finish();
                        Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSQlite = new MySqlite(RegisterActivity.this);
    }
}
