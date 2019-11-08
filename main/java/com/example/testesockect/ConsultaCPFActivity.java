package com.example.testesockect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConsultaCPFActivity extends AppCompatActivity {


    private String ip,porta,cpf,res;
    private TextInputEditText textCpf;
    private TextView resposta;
    private Button btnCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cpf);

        textCpf = findViewById(R.id.txtCPF);
        resposta = findViewById(R.id.txtRespostaCpf);
        btnCPF = findViewById(R.id.btnCPF);

        resposta.setVisibility(View.INVISIBLE);
        Bundle dados = getIntent().getExtras();
        ip =  dados.getString("ip");
        porta =  dados.getString("porta");

        btnCPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunnableCPF runnableCPF = new RunnableCPF();
                new Thread(runnableCPF).start();
            }
        });

    }

    class RunnableCPF implements Runnable{

        @Override
        public void run() {

            Socket s = null;
            try {

                cpf = textCpf.getText().toString();

                s = new Socket(ip, Integer.parseInt(porta));
                ObjectOutputStream enviar = new ObjectOutputStream(s.getOutputStream());
                final ObjectInputStream receber = new ObjectInputStream(s.getInputStream());

                enviar.writeInt(5566);
                enviar.writeUTF(cpf);
                enviar.flush();

                res = receber.readUTF();

            } catch (IOException e) {
                e.printStackTrace();
            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resposta.setVisibility(View.VISIBLE);
                    resposta.setText(res);
                }
            });

        }
    }
}
