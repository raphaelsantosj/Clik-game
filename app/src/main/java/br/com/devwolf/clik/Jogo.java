/**
 * @version Beta
 * @author Raphael dos Santos Jesus
 */

package br.com.devwolf.clik;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Jogo extends AppCompatActivity {

    //Declaração de variaveis
    Button btnCompartilhar, btnSair, btnSurpresa1, btnSurpresa2, btnClik;
    TextView txtCliks, txtCronometro;
    int cont_clik = 0, cont_surp = 16;
    long tempo, tempo_surpresa;
    String []surpresa = {"-","+"};
    Random random = new Random();
    Random surp = new Random();

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
        btnSurpresa2 = (Button) findViewById(R.id.btnSurpresa2);
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
        new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                tempo_surpresa = millisUntilFinished / 1000;
                int num_sort = surp.nextInt(20);
                int divi = 0;
                btnCompartilhar.setText("" + num_sort);

                for(int i = 1; i <= num_sort; i++){
                    if(num_sort % i == 0){
                        divi++;
                    }
                }
                if(divi == 2){
                    btnSurpresa2.setVisibility(View.VISIBLE);
                    btnSurpresa2.setText(surpresa[random.nextInt(surpresa.length)]);
                    if(btnSurpresa2.getText().equals("+")){
                        btnSurpresa2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btnClik.setText("");
                            }
                        });
                    }
                }else{
                    btnSurpresa2.setVisibility(View.INVISIBLE);
                }
//                if((r%1 == 0) && (r%r == 0)){
//                    btnSurpresa2.setText("Primo");
//                }else{
//                    btnSurpresa2.setText("Cu");
//                }


            }

            public void onFinish() {
                btnSurpresa1.setVisibility(View.INVISIBLE);
            }
        }.start();
//        btnSurpresa1.setVisibility(View.VISIBLE);
//        btnSurpresa1.setText(surpresa[random.nextInt(surpresa.length)]);
//        if(btnSurpresa1.getText().equals("-")){
//            btnSurpresa1.setBackgroundResource(R.drawable.btn_surpresa);
//        }
//        else {
//            btnSurpresa1.setBackgroundResource(R.drawable.btn_surpresa2);
//        }
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
