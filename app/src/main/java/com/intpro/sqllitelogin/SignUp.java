package com.intpro.sqllitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText id, name, email, phone, pass, repass;
    Button sign;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DBHelper(this);

        id = findViewById(R.id.index);
        name = findViewById(R.id.usename);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.password);
        repass = findViewById(R.id.repassword);
        sign  = findViewById(R.id.signUp);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String index = id.getText().toString().trim();
                String nm = name.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String phn = phone.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String repassword = repass.getText().toString().trim();


                if(password.equals(repassword))
                {
                    Long val = dbHelper.addUser(index, nm, mail, phn, password);

                    if(val > 0)
                    {
                        Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, SignIn.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SignUp.this, "Registration error", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(SignUp.this, "Password is not matching", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
