package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class encrypt_decrypt extends AppCompatActivity {
    public encrypt_decrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {}

    EditText edit_txt_texto, edit_txt_encrypt_decrypt_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encrypt_decrypt);

        edit_txt_texto=findViewById(R.id.edit_txt_texto);
        edit_txt_encrypt_decrypt_resultado=findViewById(R.id.edit_txt_encrypt_decrypt_resultado);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    String secretKey = "AArr@2024",guarda_encrypt="";
    byte[] keyData = secretKey.getBytes();
    SecretKeySpec secretKeySpec = new SecretKeySpec(keyData, "Blowfish");
    Cipher cipher = Cipher.getInstance("Blowfish");
    //Botão Voltar para Main (Tela principal do APP)
    public void onClickVoltarMain(View v){
        Intent main=new Intent(this, MainActivity.class);
        startActivity(main);
    }

    //Botão Criptografar (Lança exceções para construtor)
    public void onClickEncrypt(View v) throws Exception {
        if (edit_txt_texto.getText().toString()==""||edit_txt_texto.getText().toString()==null){
            Toast.makeText(this, "Campo vazio!", Toast.LENGTH_SHORT).show();
        }else{
            edit_txt_encrypt_decrypt_resultado.setText(encrypt(edit_txt_texto.getText().toString()));
        }
    }

    //Botão Descriptografar (Lança exceções para construtor)
    public void onClickDecrypt(View v) throws Exception {
        if (guarda_encrypt==""||guarda_encrypt==null){
            Toast.makeText(this, "Campo vazio!", Toast.LENGTH_SHORT).show();
        }else{
            edit_txt_encrypt_decrypt_resultado.setText(decrypt(guarda_encrypt));
        }

    }

    //Classe Encrypt, transforma um texto em dados encriptados Base 64 (Lança exceções para construtor)
    public String encrypt(String texto) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(texto.getBytes(StandardCharsets.UTF_8));
        return guarda_encrypt=Base64.getEncoder().encodeToString(encryptedBytes);
    }

    //Classe Decrypt, transforma dados encriptados Base 64 em texto (Lança exceções para construtor)
    public String decrypt(String secretMessage) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(secretMessage));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}