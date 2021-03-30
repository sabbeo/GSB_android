package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button AjoutButton;
    private Button ListButton;
    private Button MAJButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialisations();
        testBd();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ajout:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre Ajout !", Toast.LENGTH_LONG).show();
                return true;
            case R.id.liste:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();
                return true;
            case R.id.maj:
                Toast.makeText(getApplicationContext(), "ouverture fenêtre Maj !", Toast.LENGTH_LONG).show();
                return true;
            case R.id.quitter:
                finish();
        }
        return false;
    }

    public void initialisations() {

        AjoutButton = (Button) findViewById(R.id.menuButtonAjoutEchantillon);
        AjoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AjoutEchantillon.class);
                startActivity(intent);

            }
        });

        ListButton = (Button) findViewById(R.id.menuButtonListeEchantillons);
        ListButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListeEchantillons.class);
                startActivity(intent);

            }
        });

        MAJButton = (Button) findViewById(R.id.menuButtonMajEchantillons);
        MAJButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MajEchantillon.class);
                startActivity(intent);

            }
        });

    }

    public void testBd() {

//Création d'une instance de la classe echantBDD

        BdAdapter echantBdd = new BdAdapter(this);

//On ouvre la base de données pour écrire dedans

        echantBdd.open();

//On insère DES ECHANTILLONS DANS LA BD

        echantBdd.insererEchantillon(new Echantillon("code1", "lib1", "3"));

        echantBdd.insererEchantillon(new Echantillon("code2", "lib2", "5"));

        echantBdd.insererEchantillon(new Echantillon("code3", "lib3", "7"));

        echantBdd.insererEchantillon(new Echantillon("code4", "lib4", "6"));

        Cursor unCurseur = echantBdd.getData();


        echantBdd.close();
    }
}