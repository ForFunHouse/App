package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class fibonacci extends AppCompatActivity {

    EditText vezes=findViewById(R.id.editText_vezes),
             result=findViewById(R.id.editText_resultado);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fibonacci);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public Boolean button_calcula_fibonacci(View v){
        try {
            int a=1,b=0,aux,limite=Integer.parseInt(vezes.getText().toString());
            String fibonacci="";
            for(int i=0;i<limite;i++){
                fibonacci+=String.valueOf(a)+"\n";
                aux=a;
                a=a+b;
                b=aux;
            }
            result.setText(fibonacci);
            return true;
        }catch (Exception e){
            Toast.makeText(this, "Erro ao calcular! Algo deu errado!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}