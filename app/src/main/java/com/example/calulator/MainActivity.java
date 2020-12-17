package com.example.calulator;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    Button result;
    EditText operand1;
    EditText operand2;
    EditText operator;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(Button)findViewById(R.id.result_button);
        display=findViewById(R.id.result);

        Intent serviceIntent = new Intent(this,MyService.class);
        result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                operand1   = (EditText)findViewById(R.id.operand1);
                String number1=operand1.getText().toString();
                operator   = (EditText)findViewById(R.id.operator);
                String oper=operator.getText().toString();
                operand2  = (EditText)findViewById(R.id.operand2);
                String number2=operand2.getText().toString();
                serviceIntent.putExtra("number1",number1);
                serviceIntent.putExtra("operator",oper);
                serviceIntent.putExtra("number2",number2);
                startService(serviceIntent);
            }
        });


    }
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mMessageReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-event-name"));
        super.onResume();
    }
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            display.setText("Result= "+message);
        }
    };
}