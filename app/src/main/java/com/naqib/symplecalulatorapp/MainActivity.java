package com.naqib.symplecalulatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView output;
    Button ac, pn, per, div, mul, min, add, eq, one, two, three, four, five, six, seven, eight, nine, zero, dot;
    String num1 = "0";
    String num2 = "0";
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.tvDisplay);
        ac = findViewById(R.id.btnAC);
        pn = findViewById(R.id.btnPosNeg);
        div = findViewById(R.id.btnDivide);
        mul = findViewById(R.id.btnMultiply);
        min = findViewById(R.id.btnMinus);
        per = findViewById(R.id.btnpercent);
        add = findViewById(R.id.btnPlus);
        eq = findViewById(R.id.btnEquals);
        one = findViewById(R.id.btnOne);
        two = findViewById(R.id.btnTwo);
        three = findViewById(R.id.btnThree);
        four = findViewById(R.id.btnFour);
        five = findViewById(R.id.btnFive);
        six = findViewById(R.id.btnSix);
        seven = findViewById(R.id.btnSeven);
        eight = findViewById(R.id.btnEight);
        nine = findViewById(R.id.btnNine);
        zero = findViewById(R.id.btnZero);
        dot = findViewById(R.id.btnDot);

        zero.setOnClickListener(view -> appendToNum2(zero.getText().toString()));

        one.setOnClickListener(view -> appendToNum2(one.getText().toString()));

        two.setOnClickListener(view -> appendToNum2(two.getText().toString()));

        three.setOnClickListener(view -> appendToNum2(three.getText().toString()));

        four.setOnClickListener(view -> appendToNum2(four.getText().toString()));

        five.setOnClickListener(view -> appendToNum2(five.getText().toString()));

        six.setOnClickListener(view -> appendToNum2(six.getText().toString()));

        seven.setOnClickListener(view -> appendToNum2(seven.getText().toString()));

        eight.setOnClickListener(view -> appendToNum2(eight.getText().toString()));

        nine.setOnClickListener(view -> appendToNum2(nine.getText().toString()));

        ac.setOnClickListener(view -> clear());

        pn.setOnClickListener(view -> togglePositiveNegative());

        per.setOnClickListener(view -> calculatePercentage());

        div.setOnClickListener(view -> setOperator(div.getText().toString()));

        mul.setOnClickListener(view -> setOperator("*"));

        min.setOnClickListener(view -> setOperator(min.getText().toString()));

        add.setOnClickListener(view -> setOperator(add.getText().toString()));

        eq.setOnClickListener(view -> calculateResult());

        dot.setOnClickListener(view -> appendDecimalPoint());
    }

    private void appendToNum2(String value) {
        if (num2.equals("0")) {
            num2 = value;
        } else {
            num2 += value;
        }

        output.setText(formatOutput(num2));
    }

    private void clear() {
        num1 = "0";
        num2 = "0";
        operator = "";
        output.setText("");
    }

    private void togglePositiveNegative() {
        if (!num2.isEmpty()) {
            double number = Double.parseDouble(num2);
            number = -number;
            num2 = String.valueOf(number);
            output.setText(num2);
        }
    }

    private void calculatePercentage() {
        if (!num2.isEmpty()) {
            double number = Double.parseDouble(num2);
            number /= 100.0;
            num2 = String.valueOf(number);
            output.setText(num2);
        }
    }

    private void setOperator(String op) {
        if (!num2.isEmpty()) {
            if (!num1.isEmpty() && !operator.isEmpty()) {
                calculateResult();
            }

            operator = op;
            num1 = num2;
            num2 = "";
            calculateResult();
        }
    }

    private void appendDecimalPoint() {
        if (!num2.contains(".")) {
            if (num2.isEmpty()) {
                num2 = "0.";
            } else {
                num2 += ".";
            }
            output.setText(num2);
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateResult() {
        if (!num1.isEmpty() && !num2.isEmpty() && !operator.isEmpty()) {
            double operand1 = Double.parseDouble(num1);
            double operand2 = Double.parseDouble(num2);
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        output.setText("Error");
                        return;
                    }
                    break;
            }

            num1 = "";
            num2 = String.valueOf(result);
            output.setText(formatOutput(num2));
        }
    }

    @SuppressLint("DefaultLocale")
    private String formatOutput(String value) {
        double number = Double.parseDouble(value);

        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            int decimalPlaces = getDecimalPlaces(number);
            if (decimalPlaces > 9) {
                return String.format("%.9f", number);
            } else if (decimalPlaces > 8) {
                return String.format("%.8f", number);
            } else if (decimalPlaces > 7) {
                return String.format("%.7f", number);
            } else if (decimalPlaces > 6) {
                return String.format("%.6f", number);
            } else if (decimalPlaces > 5) {
                return String.format("%.5f", number);
            } else if (decimalPlaces > 4) {
                return String.format("%.4f", number);
            } else if (decimalPlaces > 3) {
                return String.format("%.3f", number);
            } else if (decimalPlaces > 2) {
                return String.format("%.2f", number);
            } else if (decimalPlaces == 0) {
                return String.format("%.0f", number);
            } else {
                return String.format("%.1f", number);
            }
        }
    }

    private int getDecimalPlaces(double number) {
        String numberString = String.valueOf(number);
        int decimalIndex = numberString.indexOf('.');
        if (decimalIndex >= 0) {
            return numberString.length() - decimalIndex-1;
        }
        return 0;
    }
}