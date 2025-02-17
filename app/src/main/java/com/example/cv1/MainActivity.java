package com.example.cv1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView maintextView;
    private String number1 = "";
    private double setNumber = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        maintextView = findViewById(R.id.textView2);

    }

    public void clear(View view) {
        String resetNumber = "0";
        maintextView.setText(resetNumber);
        number1 = "";

    }

    public void delete(View view){
        if (!number1.isEmpty()){
            number1 = number1.substring(0,number1.length()-1);
            maintextView.setText(number1);
        }

    }


    public void digitButton(View view){
        Button button = (Button) view;
        number1 += button.getText().toString();
        maintextView.setText(number1);
    }

    public void operatorButton(View view){
        if (!number1.isEmpty()){
            setNumber = Double.parseDouble(number1);
            Button button = (Button) view;
            operator = button.getText().toString();
            number1 = "";
        }
    }

    public void plusminusButton(View view){
        if (number1.startsWith("-")){
            number1 = number1.substring(1);
        }
        else {
            number1 = "-"+number1;
        }
        maintextView.setText(number1);
    }

    public void decimalButton(View view){
        if (number1.isEmpty() || number1.matches("-")){
            number1 = number1 + "0";
        }
        number1 = number1 + ".";
        maintextView.setText(number1);

    }

    public void equalButton(View view){
        if (!number1.isEmpty() && !operator.isEmpty()){
            double setNumber2 = Double.parseDouble(number1);
            double result = 0;
            String error = "E";

            switch(operator) {
                case "+":
                    result = setNumber + setNumber2;
                    maintextView.setText(String.valueOf(result));
                    number1 = String.valueOf(result);
                    break;
                case "-":
                    result = setNumber - setNumber2;
                    maintextView.setText(String.valueOf(result));
                    number1 = String.valueOf(result);
                    break;
                case "*":
                    result = setNumber * setNumber2;
                    maintextView.setText(String.valueOf(result));
                    number1 = String.valueOf(result);
                    break;
                case "/":
                    if (setNumber2==0){
                        maintextView.setText(error);
                        number1 = "";
                    } else {
                        result = setNumber/setNumber2;
                        maintextView.setText(String.valueOf(result));
                        number1 = String.valueOf(result);
                    }
                    break;
            }
            operator = "";
        }

    }
}