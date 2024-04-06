package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calculator extends AppCompatActivity {

    static EditText edit_txt_number;
    static EditText edit_txt_number2;
    static TextView txt_view_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);

        edit_txt_number = findViewById(R.id.edit_txt_number);
        edit_txt_number2 = findViewById(R.id.edit_txt_number2);
        txt_view_resultado = findViewById(R.id.txt_view_resultado);
        Button btn_soma = findViewById(R.id.btn_soma),
                btn_subtrai = findViewById(R.id.btn_subtrai),
                btn_dividi = findViewById(R.id.btn_dividi),
                btn_multiplica = findViewById(R.id.btn_multiplica),
                btn_voltaMain = findViewById(R.id.btn_voltar_main_calculadora);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickVoltaMain(View v) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    public void onClickCalculator(View v) {
        double n1 = Double.parseDouble(edit_txt_number.getText().toString()), n2 = Double.parseDouble(edit_txt_number2.getText().toString());
        if (v.getId() == R.id.btn_soma) {txt_view_resultado.setText(String.valueOf(n1 + n2));}
        else if (v.getId() == R.id.btn_dividi) {txt_view_resultado.setText(String.valueOf(n1 / n2));}
        else if (v.getId() == R.id.btn_subtrai) {txt_view_resultado.setText(String.valueOf(n1 - n2));}
        else if (v.getId() == R.id.btn_multiplica) {txt_view_resultado.setText(String.valueOf(n1 * n2));}
        else{Toast.makeText(this, "Erro no botão!", Toast.LENGTH_SHORT).show();}
    }
}