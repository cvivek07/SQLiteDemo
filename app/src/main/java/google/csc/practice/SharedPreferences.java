package google.csc.practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferences extends AppCompatActivity {
EditText name, email, phone, pass, email2, pass2;
    Button register, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        email2 = (EditText) findViewById(R.id.email2);
        pass2 = (EditText) findViewById(R.id.password2);
        login = (Button) findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String in_name = name.getText().toString();
                String in_email = email.getText().toString();
                String in_phone = phone.getText().toString();
                String in_pass = pass.getText().toString();
                android.content.SharedPreferences sharedPref = getSharedPreferences("USER_INFO", MODE_PRIVATE);
                android.content.SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name", in_name);
                editor.putString("email", in_email);
                editor.putString("phone", in_phone);
                editor.putString("pass", in_pass);
                editor.apply();
                Toast.makeText(SharedPreferences.this, "Successfully saved. Please login", Toast.LENGTH_SHORT).show();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.content.SharedPreferences sharedPref = getSharedPreferences("USER_INFO", MODE_PRIVATE);
                String email_in = sharedPref.getString("email", "");
                String pass_in = sharedPref.getString("pass", "");
                Toast.makeText(SharedPreferences.this, email_in + pass_in, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
