/**
 * @version Beta
 * @author Raphael dos Santos Jesus
 */

package br.com.devwolf.clik;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnComecar = (Button)findViewById(R.id.btnComecar);
        btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jogo = new Intent(Menu.this, Jogo.class);
                startActivity(jogo);

                finish();
            }
        });
    }
}
