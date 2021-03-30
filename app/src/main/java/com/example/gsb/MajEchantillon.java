package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MajEchantillon extends AppCompatActivity {

    private EditText editTexCode;
    private EditText editTextQTE;
    private Button ajoutButtonAjouter;
    private Button ajoutButtonSupprimer;
    private Button ajoutButtonRetour;
    private String code;
    private String QTE;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj_echantillon);
        initialisations();
    }

    public void initialisations(){
        BdAdapter echantBdd = new BdAdapter(this);
        editTexCode = (EditText) findViewById(R.id.majEditTextCode);
        editTextQTE = (EditText) findViewById(R.id.majEditTextQte);
        ajoutButtonAjouter = (Button) findViewById(R.id.majButtonAjouter);
        ajoutButtonSupprimer = (Button) findViewById(R.id.majButtonSupprimer);
        ajoutButtonRetour = (Button) findViewById(R.id.majButtonQuitterListe);


        ajoutButtonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTexCode.getText().toString();
                QTE = editTextQTE.getText().toString();
                echantBdd.open();
                    if (!code.equals("")) {
                        if (isNumeric(QTE)) {
                            if(echantBdd.IsEchantillionExist(code)){

                            Echantillon unEnchantion = echantBdd.getEchantillonWithCode(code);
                            float qteSaisie = Float.parseFloat(QTE);
                            float qteBDD = Float.parseFloat(unEnchantion.getQuantiteStock());
                            float qteTotal = qteBDD + qteSaisie;
                            String qteTotalBDD = Float.toString(qteTotal);
                            unEnchantion.setQuantiteStock(qteTotalBDD);
                            echantBdd.updateEchantillon(code, unEnchantion);
                            echantBdd.close();
                            editTexCode.setText("");
                            editTextQTE.setText("");
                        }else {Toast.makeText(getApplicationContext(), "echantillion non trouvé", Toast.LENGTH_LONG).show();}
                    }
                }
            }
        });
        ajoutButtonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTexCode.getText().toString();
                QTE = editTextQTE.getText().toString();
                echantBdd.open();
                if (!code.equals("")) {
                    if (isNumeric(QTE)) {
                        if(echantBdd.IsEchantillionExist(code)){

                            Echantillon unEnchantion = echantBdd.getEchantillonWithCode(code);
                            float qteSaisie = Float.parseFloat(QTE);
                            float qteBDD = Float.parseFloat(unEnchantion.getQuantiteStock());
                            float qteTotal = qteBDD - qteSaisie;
                            String qteTotalBDD = Float.toString(qteTotal);
                            unEnchantion.setQuantiteStock(qteTotalBDD);
                            echantBdd.updateEchantillon(code, unEnchantion);
                            echantBdd.close();
                            editTexCode.setText("");
                            editTextQTE.setText("");
                        }else {Toast.makeText(getApplicationContext(), "echantillion non trouvé", Toast.LENGTH_LONG).show();}
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