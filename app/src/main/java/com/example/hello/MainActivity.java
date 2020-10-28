package com.example.hello;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private MySqlite mSQlite;
    private EditText username;
    private EditText userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // execute my self defined front-end code

        Button login = findViewById(R.id.denglu);
        Button register = findViewById(R.id.zhuce);
        Button visitor = findViewById(R.id.visitor);
        username = findViewById(R.id.text_userid);
        userpassword = findViewById(R.id.text_userpwd);

        //https://blog.csdn.net/Xu_sa_sa/article/details/88911435


        //给 register 这个 button 控件设置的点击事件
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //为了跳转到注册界面RegisterActivity，并实现注册功能
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //给游客入口设置点击事件
        visitor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //给 login 这个button 控件设置的点击事件
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password))
                {
                    HashMap<String,String> data = mSQlite.getAllData();
                    String temPwd = data.get(name);
                    if ((data.containsKey(name)) && (password.equals(temPwd)))
                    {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        //intent.putExtra("username",name);
                        //intent.putExtra("password",password);  //展示账号密码功能
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mSQlite = new MySqlite(MainActivity.this);
    }
}
