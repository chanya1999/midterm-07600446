package com.midexam.speedcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.format;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText distanceEditText = findViewById(R.id.distance_edit_text);
        final EditText timeEditText = findViewById(R.id.time_edit_text);
        Button clearButton = findViewById(R.id.clear_button);
        Button calculateButton = findViewById(R.id.calculate_button);
        final TextView resultTextView = findViewById(R.id.result_text_view);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(distanceEditText.getText().toString().length()==0 && timeEditText.getText().toString().length()==0){
                    Toast t = Toast.makeText(MainActivity.this, R.string.toast_d_t,Toast.LENGTH_LONG);
                    t.show();
                } else if(timeEditText.getText().toString().length()==0 || timeEditText.getText().toString().equals("0")){
                    Toast t = Toast.makeText(MainActivity.this, R.string.toast_t,Toast.LENGTH_LONG);
                    t.show();
                } else {
                    double calSpeed = Calculate(Double.parseDouble(distanceEditText.getText().toString()),Double.parseDouble(timeEditText.getText().toString()));
                    resultTextView.setText(String.format("%.2f",calSpeed));
                    if(calSpeed>80.0){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("SPEED CALCULATER");
                        dialog.setMessage(R.string.speed_over_msg);
                        dialog.setPositiveButton("OK",null);
                        dialog.show();

                    }
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distanceEditText.setText("");
                timeEditText.setText("");
                resultTextView.setText("");
            }
        });


    }

    double Calculate (double distance, double time){
        return (distance/time)*(3.6);
                //String.format("%.2f",(distance/time)*(3.6));
    }
}