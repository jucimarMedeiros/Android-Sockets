package com.example.testesockect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.brouding.simpledialog.SimpleDialog;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EscolhaEquacoesActivity extends AppCompatActivity {

    private Button equacao,tipoTriangulo,CPF,sobre;
    private String ip,porta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_equacoes);

        equacao = findViewById(R.id.btnSegundoGrau);
        tipoTriangulo = findViewById(R.id.btnTipoTriangulo);
        CPF = findViewById(R.id.btnCPF);
        sobre = findViewById(R.id.btnSobre);

        Bundle dados = getIntent().getExtras();
        ip =  dados.getString("ip");
        porta =  dados.getString("porta");

        equacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SegundoGrauEquacaoActivity.class);
                intent.putExtra("ip",ip);
                intent.putExtra("porta",porta);
                startActivity(intent);
            }
        });
        tipoTriangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ClassificacaoTrianguloActivity.class);
                intent.putExtra("ip",ip);
                intent.putExtra("porta",porta);
                startActivity(intent);
            }
        });
        CPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConsultaCPFActivity.class);
                intent.putExtra("ip",ip);
                intent.putExtra("porta",porta);
                startActivity(intent);
            }
        });
        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo();
            }
        });
    }

    void dialogo(){
        new SimpleDialog.Builder(this)
                .setTitle("TRABALHO DE SISTEMA DISTRIBUÍDO")
                .setBtnConfirmText("OK")
                .setContent("Alunos: Mateus e Jucimar", 4)
                .onConfirm(new SimpleDialog.BtnCallback() {
                    @Override
                    public void onClick(@NonNull SimpleDialog dialog, @NonNull SimpleDialog.BtnAction which) {

                    }
                }).show();
    }
}
