package com.example.testesockect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class SegundoGrauEquacaoActivity extends AppCompatActivity implements Serializable {

    private TextInputEditText valorA;
    private TextInputEditText valorB;
    private TextInputEditText valorC;
    private TextView resultado,x1,x2;
    private Button calcularEquacao;
    private String ip,porta;
    private int v1,v2,v3;
    private  double res1,res2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo_grau_equacao);

        valorA = findViewById(R.id.txtValorA);
        valorB = findViewById(R.id.txtValorB);
        valorC = findViewById(R.id.txtValorC);
        calcularEquacao = findViewById(R.id.btnCalcularEquacao);
        x1 = findViewById(R.id.txtX1);
        x2 = findViewById(R.id.txtX2);

        x1.setVisibility(View.INVISIBLE);
        x2.setVisibility(View.INVISIBLE);



        Bundle dados = getIntent().getExtras();
        ip =  dados.getString("ip");
        porta =  dados.getString("porta");

        calcularEquacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyRunnable runnable = new MyRunnable();
                new Thread(runnable).start();

            }

        });

    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            try {
                v1 = Integer.parseInt(valorA.getText().toString());
                v2 = Integer.parseInt(valorB.getText().toString());
                v3 = Integer.parseInt(valorC.getText().toString());

                Socket s = new Socket(ip, Integer.parseInt(porta));

                ObjectOutputStream enviar = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream receber = new ObjectInputStream(s.getInputStream());

                enviar.writeInt(5560);
                enviar.writeInt(v1);
                enviar.writeInt(v2);
                enviar.writeInt(v3);
                enviar.flush();


                res1 = receber.readDouble();
                res2 = receber.readDouble();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        x1.setVisibility(View.VISIBLE);
                        x2.setVisibility(View.VISIBLE);
                        x1.setText("x'  = "+String.valueOf(res1));
                        x2.setText("x'' = "+String.valueOf(res2));
                    }
                });

                s.close();

            }
            catch (Exception err) {
                System.err.println(err);
            }
        }
    }

}
