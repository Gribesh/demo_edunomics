package com.demo.edunomics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText dob;
    public void redirectLogin(View view) {
        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void redirectIfLoggedIn() {
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean validatePassword(EditText password) {
         final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        "(?=.*[a-z])" +         //at least 1 lower case letter
                        "(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 4 characters
                        "$");
        String passwordInput = password.getText().toString().trim();
        if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak Requirement :\nMinimum 8 letters\nSpecial Character \nOne Uppercase & LowerCase \nNumber");
            return false;
        }else {
            password.setError(null);
            return true;
        }
    }

    public void signup(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText cpassword = findViewById(R.id.cpassword);


        if (TextUtils.isEmpty(username.getText())){
            username.setError("Username Required");
        }else if (!(password.length() >0)){
            Toast.makeText(SignUpActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(cpassword.getText())){
            cpassword.setError("Required");
        }else if(!(password.getText().toString().equals(cpassword.getText().toString()))){
            Toast.makeText(SignUpActivity.this, "Passwords donot matches", Toast.LENGTH_SHORT).show();
        }else{
            if(validatePassword(password)){
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Info", "User Signed Up Successfully");
                            redirectIfLoggedIn();
                        } else {
                            String message = e.getMessage();
                            if (message.toLowerCase().contains("java")) {
                                message = e.getMessage().substring(e.getMessage().indexOf(" "));
                            }
                            ParseUser.logOut();
                            Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(" ");
        setContentView(R.layout.activity_sign_up);
        redirectIfLoggedIn();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        dob=findViewById(R.id.dob);
        dob.setInputType(InputType.TYPE_NULL);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(SignUpActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }
}
