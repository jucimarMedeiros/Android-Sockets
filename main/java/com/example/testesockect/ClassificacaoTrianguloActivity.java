package com.example.testesockect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClassificacaoTrianguloActivity extends AppCompatActivity {

    private String ip,porta;
    private TextInputEditText lado1,lado2,lado3;
    private TextView resultado;
    private Button calcular;
    private double l1,l2,l3;
    private String res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificacao_triangulo);

        lado1 = findViewById(R.id.txtLado1);
        lado2 = findViewById(R.id.txtLado2);
        lado3 = findViewById(R.id.txtLado3);
        resultado = findViewById(R.id.txtTipoTrianguloResultado);
        calcular = findViewById(R.id.brnCalcularTriangulo);

        resultado.setVisibility(View.INVISIBLE);

        Bundle dados = getIntent().getExtras();
        ip =  dados.getString("ip");
        porta =  dados.getString("porta");

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRunnableTriangulo runnableTriangulo = new MyRunnableTriangulo();
                new Thread(runnableTriangulo).start();
            }
        });


    }

    class MyRunnableTriangulo implements Runnable {

        @Override
        public void run() {

            try {

                l1 = Double.parseDouble(lado1.getText().toString());
                l2 = Double.parseDouble(lado2.getText().toString());
                l3 = Double.parseDouble(lado3.getText().toString());

                Socket s = new Socket(ip, Integer.parseInt(porta));
                ObjectOutputStream enviar = new ObjectOutputStream(s.getOutputStream());
                final ObjectInputStream receber = new ObjectInputStream(s.getInputStream());

                enviar.writeInt(5564);
                enviar.writeDouble(l1);
                enviar.writeDouble(l2);
                enviar.writeDouble(l3);
                enviar.flush();

                res = receber.readUTF();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultado.setVisibility(View.VISIBLE);
                        resultado.setText(res);
                    }
                });



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
