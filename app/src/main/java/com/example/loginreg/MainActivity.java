package com.example.loginreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    RadioGroup mFacStu;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button)findViewById(R.id.button_login);
        mTextViewRegister = (TextView)findViewById(R.id.textview_register);

        mFacStu = (RadioGroup)findViewById(R.id.fac_stu);

        mTextViewRegister.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
               startActivity(registerIntent);


           }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res =db.checkUser(user,pwd);
                RadioButton checkedBtn = findViewById(mFacStu.getCheckedRadioButtonId());
                String type = checkedBtn.getText().toString();

                if(res){

                    if(type.equalsIgnoreCase("Student")){
                        Intent StudentPage = new Intent(MainActivity.this, StudentActivity.class);
                        Toast.makeText(MainActivity.this, "Successfully Logged In as Student!", Toast.LENGTH_SHORT).show();
                        startActivity(StudentPage);
                    }
                    else if(type.equalsIgnoreCase("Faculty")){
                        Intent HomePage = new Intent(MainActivity.this, HomeActivity.class);
                        Toast.makeText(MainActivity.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                        startActivity(HomePage);
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Username or Password is incorrect!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
