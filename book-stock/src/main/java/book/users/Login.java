package book.users;

/**
 * Created by sam on 12/20/17.
 */


import android.app.Activity;
import android.os.StrictMode;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import book.views.MainActivity;
import book.bookMethods.Books;
import books.R;


public class Login extends Activity {

    EditText userName,password;
    Button login,forgot_pass;
    final Context context = this;

    @Override
    protected  void  onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        userName =findViewById(R.id.uname);
        password =findViewById(R.id.upass);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Books books = new Books(userName.getText().toString(),
                        password.getText().toString());


                boolean status = books.login();

                if (status){

                    Toast.makeText(getApplicationContext(),"Login success",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        forgot_pass =(Button) findViewById(R.id.forgot_password);

        forgot_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
    }



}
