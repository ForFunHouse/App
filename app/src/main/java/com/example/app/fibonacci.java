package com.example.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class fibonacci extends AppCompatActivity {

    String fibonacci="";
    EditText edit_txt_vezes, edit_txt_resultado;
    ScrollView scroll_view_resultado;
    ClipboardManager copytextforclipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fibonacci);

        //Viariavel do tipo EditText guardam informações das View's (Button's, EditText, ViewText etc..)
        Button btn_voltar_main_fibonacci=findViewById(R.id.btn_voltar_main_fibonacci),
                copy_clipboard=findViewById(R.id.btn_copia_txt_fibonacci);
        edit_txt_vezes=findViewById(R.id.edit_txt_vezes);
        edit_txt_resultado=findViewById(R.id.edit_txt_resultado);
        //Variavel do tipo ClipBoardManager com get Services
        copytextforclipboard=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onClickcalcula(View v){
        try {
            int a=1,b=0,aux,limite=Integer.parseInt(edit_txt_vezes.getText().toString());
            for (int i=0;i<limite;i++){
                fibonacci+=a+" ";
                aux=a;
                a=a+b;
                b=aux;
            }
            edit_txt_resultado.setText(fibonacci);
        }catch (Exception e){
            System.out.println("Problema na inicialiazação do calculo!");
            Toast.makeText(this, "Problema na inicialiazação do calculo!", Toast.LENGTH_SHORT).show();
        }
    }

    //Botão volta para main
    public void onClickVoltarMain(View v){
        Intent main=new Intent(this, MainActivity.class);
        startActivity(main);
    }

    //Botão copia para ClipBoard
    public void onClickcopyclipboard(View v){
        ClipData clipData=ClipData.newPlainText("label", fibonacci);
        copytextforclipboard.setPrimaryClip(clipData);
        Toast.makeText(this, "Copiado!", Toast.LENGTH_SHORT).show();
    }
}