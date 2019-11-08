package com.example.testesockect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.MaskFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements Serializable {

    public TextInputEditText ip;
    public TextInputEditText porta;
    private TextInputEditText valor1;
    private TextInputEditText valor2;
    private TextView resultado;
    private Button conectar;
    private Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip  = findViewById(R.id.txtIp);
        resultado  = findViewById(R.id.txtResultado);
        porta  = findViewById(R.id.txtPorta);
        conectar = findViewById(R.id.btnConectar);
        valor1  = findViewById(R.id.txtValor1);
        valor2  = findViewById(R.id.txtValor2);
        calcular = findViewById(R.id.btnCalcular);
        resultado.setText(" Não Conectado");
        resultado.setTextColor(getColor(R.color.vermelho));
        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setVisibility(View.VISIBLE);

                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Socket soc = new Socket(ip.getText().toString(),Integer.parseInt(porta.getText().toString()));


                            if(soc.isConnected()){
                               resultado.setText("Conectado");
                               resultado.setTextColor(getColor(R.color.verde));
                               Intent intent = new Intent(getApplicationContext(),EscolhaEquacoesActivity.class);
                               intent.putExtra("ip",ip.getText().toString());
                               intent.putExtra("porta",porta.getText().toString());
                               startActivity(intent);
                            }
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        });

    }
}
