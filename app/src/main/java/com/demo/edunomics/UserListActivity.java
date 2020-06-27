package com.demo.edunomics;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<>();

    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> auto_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        setTitle("Edunomics");

        AutoCompleteTextView auto_editText = findViewById(R.id.auto_complete);
        ListView userListView = (ListView) findViewById(R.id.userListView);


        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);

                intent.putExtra("username", users.get(i));

                startActivity(intent);


            }
        });

        auto_editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);

                intent.putExtra("username", users.get(i));

                startActivity(intent);
            }
        });



        users.clear();

        auto_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, users);
        auto_editText.setAdapter(auto_adapter);


        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        userListView.setAdapter(arrayAdapter);

        ParseQuery<ParseUser> query = ParseUser.getQuery();

        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if (e == null) {

                    if (objects.size() > 0) {

                        for (ParseUser user : objects) {

                            users.add(user.getUsername());

                        }

                        auto_adapter.notifyDataSetChanged();
                        arrayAdapter.notifyDataSetChanged();

                    }

                }

            }
        });
    }
    private void Logout_AlertBox(){
        AlertDialog.Builder abuilder = new AlertDialog.Builder(UserListActivity.this)
                .setMessage("Do you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ParseUser.logOut();
                        startActivity(new Intent(UserListActivity.this, LoginActivity.class));
                        Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = abuilder.create();
        alert.setTitle("Alert!!!");
        alert.show();
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater optmenu =getMenuInflater();
        optmenu.inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Logout_AlertBox();
                break;
        }
        return true;
    }
}