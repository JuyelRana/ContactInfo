package com.example.juyelrana.contactinfo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    private DbHelper dbHelper;

    private ListView listView;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DbHelper(this);

        listView = (ListView) findViewById(R.id.lvContact);

        contactList = new ArrayList<>();

        if (dbHelper.getContactList() != null) {
            contactList = dbHelper.getContactList();
            contactAdapter = new ContactAdapter(context, contactList);
            listView.setAdapter(contactAdapter);
        } else {
            Toast.makeText(this, "No Contact found!!", Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Add Student");

                final EditText etName = (EditText) dialog.findViewById(R.id.etName);
                final EditText etPhone = (EditText) dialog.findViewById(R.id.etPhone);
                final EditText etEmail = (EditText) dialog.findViewById(R.id.etEmail);

                Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Contact contact = new Contact(etName.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString());
                        if (dbHelper.addContact(contact) == -1) {
                            Toast.makeText(MainActivity.this, "Contact doesn't add!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Contact successfully added!!", Toast.LENGTH_SHORT).show();
                            //listView.invalidate();
                            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Clicked Cancel!!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        //String t = Config.SELECT_SQL;
                        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
