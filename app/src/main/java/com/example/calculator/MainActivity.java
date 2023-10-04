package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button nine, eight, seven, six, five, four, three, two, one, zero, dot,
            plus, minus, div, mul, eq, clear;
    private EditText et;
    private String currentInput = "0";
    private int result = 0;
    private char lastOperator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and EditText
        nine = findViewById(R.id.b9);
        eight = findViewById(R.id.b8);
        seven = findViewById(R.id.b7);
        six = findViewById(R.id.b6);
        five = findViewById(R.id.b5);
        four = findViewById(R.id.b4);
        three = findViewById(R.id.b3);
        two = findViewById(R.id.b2);
        one = findViewById(R.id.b1);
        zero = findViewById(R.id.b0);
        dot = findViewById(R.id.bd);
        plus = findViewById(R.id.bpl);
        minus = findViewById(R.id.bmin);
        div = findViewById(R.id.bdiv);
        mul = findViewById(R.id.bmul);
        eq = findViewById(R.id.beq);
        clear = findViewById(R.id.bcl);
        et = findViewById(R.id.tv);

        // Set onClickListener for buttons
        zero.setOnClickListener(this);
        nine.setOnClickListener(this);
        eight.setOnClickListener(this);
        seven.setOnClickListener(this);
        six.setOnClickListener(this);
        five.setOnClickListener(this);
        four.setOnClickListener(this);
        three.setOnClickListener(this);
        two.setOnClickListener(this);
        one.setOnClickListener(this);
        dot.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        eq.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b0:
            case R.id.b1:
            case R.id.b2:
            case R.id.b3:
            case R.id.b4:
            case R.id.b5:
            case R.id.b6:
            case R.id.b7:
            case R.id.b8:
            case R.id.b9:
                String inputDigit = ((Button) v).getText().toString();
                if (currentInput.equals("0")) {
                    currentInput = inputDigit;
                } else {
                    currentInput += inputDigit;
                }
                et.setText(currentInput);
                if (lastOperator == '=') {
                    result = 0;
                    lastOperator = ' ';
                }
                break;
            case R.id.bd:
                // Handle decimal point
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                }
                et.setText(currentInput);
                break;
            case R.id.bpl:
            case R.id.bmin:
            case R.id.bdiv:
            case R.id.bmul:
                compute();
                lastOperator = ((Button) v).getText().toString().charAt(0);
                break;
            case R.id.beq:
                compute();
                lastOperator = '=';
                break;
            case R.id.bcl:
                result = 0;
                currentInput = "0";
                lastOperator = ' ';
                et.setText("0");
                break;
        }
    }

    private void compute() {
        double inputNumber = Double.parseDouble(currentInput);
        currentInput = "0";

        switch (lastOperator) {
            case ' ':
                result = (int) inputNumber;
                break;
            case '+':
                result += inputNumber;
                break;
            case '-':
                result -= inputNumber;
                break;
            case '*':
                result *= inputNumber;
                break;
            case '/':
                if (inputNumber != 0) {
                    result /= inputNumber;
                } else {
                    et.setText("Error");
                    return;
                }
                break;
        }

        et.setText(String.valueOf(result));
    }
}
