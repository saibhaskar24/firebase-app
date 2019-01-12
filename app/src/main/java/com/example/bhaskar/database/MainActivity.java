package com.example.bhaskar.database;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText n;
    Button a;
    Spinner g;
    DatabaseReference databaseArtists;

    ListView listView ;
    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        n = (EditText)findViewById(R.id.n);
        a = (Button)findViewById(R.id.a);
        g = (Spinner) findViewById(R.id.g);
        listView = (ListView) findViewById(R.id.listview);
        artistList = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }

                ArtistList adapter = new  ArtistList(MainActivity.this, artistList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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