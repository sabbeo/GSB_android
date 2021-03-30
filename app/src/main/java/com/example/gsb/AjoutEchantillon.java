package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AjoutEchantillon extends Activity {
    private BdAdapter echantBdd;
    private EditText editTexCode;
    private EditText editTextLibelle;
    private EditText editTextStock;
    private Button ajoutButtonAjouter;
    private Button ajoutButtonRetour;
    private String code;
    private String libelle;
    private String stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);
        //Création d'une instance de la classe echantBDD
        echantBdd = new BdAdapter(this);
        initialisations();
    }
    public void initialisations(){
        editTexCode = (EditText) findViewById(R.id.ajoutEditTextCode);
        editTextLibelle = (EditText) findViewById(R.id.ajoutEditTextLib);
        editTextStock = (EditText) findViewById(R.id.ajoutEditTextStock);
        ajoutButtonAjouter = (Button) findViewById(R.id.ajoutButtonAjouter);
        ajoutButtonRetour = (Button) findViewById(R.id.ajoutButtonQuitter);

        ajoutButtonAjouter.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            code = editTexCode.getText().toString();
            libelle = editTextLibelle.getText().toString();
            stock = editTextStock.getText().toString();
            //On ouvre la base de données pour écrire dedans
            echantBdd.open();

            if(!code.equals("")){
                if(!libelle.equals("")){
                    if(isNumeric(stock)){
                        if(echantBdd.IsEchantillionNotExist(code)){
                            //On insère DES ECHANTILLONS DANS LA BD
                            echantBdd.insererEchantillon(new Echantillon(code, libelle, stock));
                            Cursor unCurseur = echantBdd.getData();
                            echantBdd.close();

                            editTexCode.setText("");
                            editTextLibelle.setText("");
                            editTextStock.setText("");
                        }
                    }
                }
            }


        }
    });

        ajoutButtonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}