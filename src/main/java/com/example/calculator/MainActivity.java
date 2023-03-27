package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean firstPass;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b10;
    Button b11;
    Button b12;
    Button b13;
    Button b14;
    Button b15;
    Button b16;
    TextView display;
    String statement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        b13 = findViewById(R.id.button13);
        b14 = findViewById(R.id.button14);
        b15 = findViewById(R.id.button15);
        b16 = findViewById(R.id.button16);
        display = findViewById(R.id.textView1);
        statement = "";

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
    }

    public void onClick(View view){
        if(display.getText().equals("Error")){
            display.setText("");
            statement = "";
        }
        if(view.equals(b1)){
            display.setText(display.getText()+"1");
            statement += "1";
        }
        if(view.equals(b2)){
            display.setText(display.getText()+"2");
            statement += "2";
        }
        if(view.equals(b3)){
            display.setText(display.getText()+"3");
            statement += "3";
        }
        if(view.equals(b4)){
            display.setText(display.getText()+"+");
            statement += "+";
        }
        if(view.equals(b5)){
            display.setText(display.getText()+"4");
            statement += "4";
        }
        if(view.equals(b6)){
            display.setText(display.getText()+"5");
            statement += "5";
        }
        if(view.equals(b7)){
            display.setText(display.getText()+"6");
            statement += "6";
        }
        if(view.equals(b8)){
            display.setText(display.getText()+"-");
            statement += "-";
        }
        if(view.equals(b9)){
            display.setText(display.getText()+"7");
            statement += "7";
        }
        if(view.equals(b10)){
            display.setText(display.getText()+"8");
            statement += "8";
        }
        if(view.equals(b11)){
            display.setText(display.getText()+"9");
            statement += "9";
        }
        if(view.equals(b12)){
            display.setText(display.getText()+"*");
            statement += "*";
        }
        if(view.equals(b13)){
            display.setText("");
            statement = "";
        }
        if(view.equals(b14)){
            display.setText(display.getText()+"0");
            statement += "0";
        }
        if(view.equals(b15)){
            firstPass = true;
            Simplify("" + statement);
            statement = display.getText() + "";
        }
        if(view.equals(b16)){
            display.setText(display.getText()+"/");
            statement += "/";
        }

        if(display.getText().length()>11){
            String temp = "";
            for(int i=statement.length()-11; i<statement.length(); i++) {
                display.setText(temp + statement.charAt(i));
                temp = "" + display.getText();
            }
        }
    }

    public String Simplify(String s){
        Pair <String, String> MultiplyDivide = new Pair<>("*", "/");
        Pair <String, String> AddSubtract = new Pair<>("+", "-");
        ArrayList<Pair<String, String>> OOO = new ArrayList<>();
        OOO.add(MultiplyDivide);
        OOO.add(AddSubtract);
        StringTokenizer separator = new StringTokenizer(s, "*/+-");
        ArrayList<String> nums = new ArrayList<>();
        while (separator.hasMoreTokens()) {
            nums.add(separator.nextToken());
        }
        if(firstPass) {
            firstPass = false;
            int numsLength = 0;
            for(int l=0; l<nums.size(); l++){
                numsLength += nums.get(l).length();
            }
            if((numsLength+ nums.size()-1)!=s.length()){
                errorHandler();
                return "";
            }
        }
        if(nums.size()==1){
            display.setText(s);
        }
        else {
            for(int i=0; i<OOO.size(); i++){
                int count = 0;
                int operatorIndex = 0;
                String currentOperator = "";
                if(s.contains(OOO.get(i).first)||s.contains(OOO.get(i).second)){
                    if(s.indexOf(OOO.get(i).first)==-1){
                        operatorIndex = s.indexOf(OOO.get(i).second);
                        currentOperator = OOO.get(i).second;
                    }
                    else if(s.indexOf(OOO.get(i).second)==-1){
                        operatorIndex = s.indexOf(OOO.get(i).first);
                        currentOperator = OOO.get(i).first;
                    }
                    else if(s.indexOf(OOO.get(i).first)<s.indexOf(OOO.get(i).second)){
                        operatorIndex = s.indexOf(OOO.get(i).first);
                        currentOperator = OOO.get(i).first;
                    }
                    else if(s.indexOf(OOO.get(i).second)<s.indexOf(OOO.get(i).first)){
                        operatorIndex = s.indexOf(OOO.get(i).second);
                        currentOperator = OOO.get(i).second;
                    }
                    for(int j=0; j<operatorIndex; j++){
                        for(int k=0; k<OOO.size(); k++){
                            if((s.charAt(j)+"").equals(OOO.get(k).first)||(s.charAt(j)+"").equals(OOO.get(k).second))
                                count++;
                        }
                    }
                    if(evaluate(nums.get(count),nums.get(count+1),currentOperator).equals("")){
                        errorHandler();
                    }
                    return Simplify(s.substring(0,operatorIndex-nums.get(count).length()) + evaluate(nums.get(count),nums.get(count+1),currentOperator) + s.substring(operatorIndex+nums.get(count+1).length()+1));
                }
            }
        }
        return "";
    }

    public void errorHandler(){
        display.setText("Error");
    }

    public String evaluate(String num1, String num2, String Operation){
        double eval = 0.0;
        if(Double.parseDouble(num2)==0.0)
            return "";
        if(Operation.equals("*")){
            eval = Double.parseDouble(num1)*Double.parseDouble(num2);
        }
        else if(Operation.equals("/")){
            eval = Double.parseDouble(num1)/Double.parseDouble(num2);
        }
        else if(Operation.equals("+")){
            eval = Double.parseDouble(num1)+Double.parseDouble(num2);
        }
        else if(Operation.equals("-")){
            eval = Double.parseDouble(num1)-Double.parseDouble(num2);
        }
        return String.format("%f", eval);
    }
}