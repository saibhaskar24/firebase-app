package com.example.bhaskar.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText n;
    Button a;
    Spinner g;
    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        n = (EditText)findViewById(R.id.n);
        a = (Button)findViewById(R.id.a);
        g = (Spinner) findViewById(R.id.g);


    }


    public void ad(View view) {
        addArtist();
    }
    private  void  addArtist() {
        String name = n.getText().toString().trim();
        String genre = g.getSelectedItem().toString();
        if(!TextUtils.isEmpty(name)) {
            String id = databaseArtists.push().getKey();
             Artist artist = new Artist(id,name,genre);

             databaseArtists.child(id).setValue(artist);
             Toast.makeText(this, "Artist added",Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(this, "You should enter a name",Toast.LENGTH_LONG).show();
        }
    }
}