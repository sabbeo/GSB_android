package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListeEchantillons extends Activity {

    private ListView listViewEchant;

    private BdAdapter echantBdd;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_echantillons);

        listViewEchant = (ListView) findViewById(R.id.listeEchantillons);

        echantBdd = new BdAdapter(this);

//On ouvre la base de données pour écrire dedans

        echantBdd.open();

        Cursor leCurseur = echantBdd.getData();

        Toast.makeText(getApplicationContext(), "il y a " + String.valueOf(leCurseur.getCount()) + " echantillons dans la BD", Toast.LENGTH_LONG).show();

// colonnes à afficher

        String[] colNoms = new String[]{BdAdapter.COL_CODE, BdAdapter.COL_LIB, BdAdapter.COL_STOCK};

// champs dans lesquelles afficher les colonnes

        int[] colNumeros = new int[]{R.id.listeTextViewCode, R.id.listeTextViewLib, R.id.listeTextViewStock};

        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.list_entree, leCurseur, colNoms, colNumeros);

// Assign adapter to ListView

        listViewEchant.setAdapter(dataAdapter);

        echantBdd.close();

        Button buttonQuitter = (Button) findViewById(R.id.listeButtonQuitter);

        buttonQuitter.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

// TODO Auto-generated method stub

                finish(); //fermeture de la fenêtre

            }

        });

    }
}
