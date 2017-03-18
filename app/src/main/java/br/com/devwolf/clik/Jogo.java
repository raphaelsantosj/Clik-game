/**
 * @version Beta
 * @author Raphael dos Santos Jesus
 */

package br.com.devwolf.clik;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Random;

public class Jogo extends AppCompatActivity {

    //Declaração de variaveis
    Button btnCompartilhar, btnSair, btnSurpresa1, btnSurpresa2, btnClik;
    TextView txtCliks, txtCronometro;
    int cont_clik = 0;
    long tempo;
    String []surpresa = {"-","+"};
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        //Esconde ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Instancia de objetos
        txtCronometro = (TextView) findViewById(R.id.txtCronometro);
        btnClik = (Button) findViewById(R.id.btnClik);
        btnSurpresa1 = (Button) findViewById(R.id.btnSurpresa1);
        txtCliks = (TextView) findViewById(R.id.txtCliks);
        btnSair = (Button) findViewById(R.id.btnSair);
        btnCompartilhar = (Button) findViewById(R.id.btnCompartilhar);

        //Chama metodo Cronometro
        Cronometro();
        //Chama metodo Clik
        Clik();
        //Chama metodo Supresa1
        Surpresa1();
        //Chama metodo Sair
        Sair();
        //Chama metodo Compartilhar
        Compartilhar();
    }

    //Metodo Cronometro
    public void Cronometro(){
        new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                tempo = millisUntilFinished / 1000;
                txtCronometro.setText(String.valueOf(tempo));
            }

            public void onFinish() {
                txtCronometro.setText("Tempo esgotado!");
                txtCliks.setText("hue!");
            }
        }.start();
    }

    /*=================================================================*/

    //Metodo Clik (Conta o número de cliques no botão)
    public void Clik(){
        btnClik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont_clik++;
                if(cont_clik >= 0){
                    txtCliks.setText(String.valueOf(cont_clik));
                }
            }
        });
    }

    /*=================================================================*/

    //Metodo Surpresa1 (Sorteia um bônus de tempo ou uma desvantagem)
    public void Surpresa1(){
        //btnSurpresa1.setVisibility(View.INVISIBLE);
        btnSurpresa1.setText(surpresa[random.nextInt(surpresa.length)]);
        if(btnSurpresa1.getText().equals("-")){
            btnSurpresa1.setBackgroundResource(R.drawable.btn_surpresa);
        }
        else {
            btnSurpresa1.setBackgroundResource(R.drawable.btn_surpresa2);
        }
    }

    /*=================================================================*/

    //Metodo Ssair (Volta pra tela de menu)
    public void Sair(){
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sair = new Intent(Jogo.this, Menu.class);
                startActivity(sair);

                finish();
            }
        });
    }

    /*=================================================================*/

    //Metodo Compartilhar (Possibilita compartilhar a pontuação)
    public void Compartilhar(){
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
    }
}
