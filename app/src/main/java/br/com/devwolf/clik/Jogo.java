/**
 * @version Beta
 * @author Raphael dos Santos Jesus
 */

package br.com.devwolf.clik;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Jogo extends AppCompatActivity {

    Button btnCompartilhar;
    Button btnSair;
    Button btnSurpresa1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Metodo Compartilhar
        btnCompartilhar = (Button) findViewById(R.id.btnCompartilhar);
        btnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,  "COMPARTILHAR"));
            }
        });
        //Fim Metodo Compartilhar

        //Metodo Voltar Menu
        btnSair = (Button) findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sair = new Intent(Jogo.this, Menu.class);
                startActivity(sair);

                finish();
            }
        });
        //Fim metodo Voltar Menu

        //Metodo btnSurpresa1
        String []surpresa = {"-","+"};
        Random random = new Random();
        btnSurpresa1 = (Button) findViewById(R.id.btnSurpresa1);

        //btnSurpresa1.setVisibility(View.INVISIBLE);
        btnSurpresa1.setText(surpresa[random.nextInt(surpresa.length)]);
        if(btnSurpresa1.getText().equals("-")){
            btnSurpresa1.setBackgroundResource(R.drawable.btn_surpresa);
        }
        else{
            btnSurpresa1.setBackgroundResource(R.drawable.btn_surpresa2);
        }



    }
}
