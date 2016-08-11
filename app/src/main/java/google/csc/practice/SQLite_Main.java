package google.csc.practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class SQLite_Main extends AppCompatActivity {
    EditText name, email, phone, pass, email2, pass2;
    Button register, login;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        dbHelper = new DatabaseHelper(this); // for creating the db
        Toast.makeText(SQLite_Main.this, "Created", Toast.LENGTH_SHORT).show();
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
                //Creating table for storing values

                boolean flag= onValidate();
                // SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(flag == true) {
                    boolean isSaved = dbHelper.saveData(name.getText().toString(), email.getText().toString(), phone.getText().toString(), pass.getText().toString());
                    if (isSaved == true) {
                        Toast.makeText(SQLite_Main.this, "SAVED", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SQLite_Main.this, " NOT SAVED", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SQLite_Main.this, " VALIDATION ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SQLite_Main.this, " checking login", Toast.LENGTH_SHORT).show();
                String check = dbHelper.checkLogin(email2.getText().toString(), pass2.getText().toString());
                if (check.equals("no data")) {
                    Toast.makeText(SQLite_Main.this, " Incorrect Email/Password", Toast.LENGTH_SHORT).show();
                } else if (check.equals("data")) {
                    Toast.makeText(SQLite_Main.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public boolean onValidate() {
        if (name.getText().toString().isEmpty()) {
            name.setError("Enter name");
            return false;
        }
        if (email.getText().toString().isEmpty()) {
            email.setError("Enter email");
            return false;
        }
        if (phone.getText().toString().isEmpty())
        {
            phone.setError("Enter phone");
            return  false;
        }
        if (pass.getText().toString().isEmpty()){
            pass.setError("Enter password");
            return false;
        }
        return true;
    }


}
