package com.example.loginreg;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Bundle;


public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextName;
    EditText mTextId;
    EditText mTextDepartment;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    RadioGroup mFacStu;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db= new DatabaseHelper(this);
        mTextName = (EditText)findViewById(R.id.edittext_name);
        mTextId = (EditText)findViewById(R.id.edittext_id);
        mTextDepartment = (EditText)findViewById(R.id.edittext_department);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mFacStu = (RadioGroup)findViewById(R.id.fac_stu);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = mTextName.getText().toString();
                String id = mTextId.getText().toString();
                String dept = mTextDepartment.getText().toString();
                String username = mTextUsername.getText().toString();
                String pwd = mTextPassword.getText().toString();
                String cnf_pwd = mTextCnfPassword.getText().toString();
                RadioButton checkedBtn = findViewById(mFacStu.getCheckedRadioButtonId());
                String type = checkedBtn.getText().toString();


                if(name.length()>0 && id.length()>0 && dept.length()>0 && username.length()>0 && pwd.length()>0) {

                    if (pwd.equals(cnf_pwd)) {

                        long val = db.addUser(name,id,dept,username, pwd,type);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "Registeration Successful!", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registeration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords doesn't match!", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
