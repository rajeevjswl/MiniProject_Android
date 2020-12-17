package com.example.calulator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
public class MyService extends Service {
     int number1,number2;
     String operator;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        number1=Integer.parseInt(intent.getStringExtra("number1"));
        number2=Integer.parseInt(intent.getStringExtra("number2"));
        operator=intent.getStringExtra("operator");
        Log.d("receiver", "Got message: " + number1);
        Log.d("receiver", "Got message: " + number2);
        Log.d("receiver", "Got message: " + operator);
        getResult(number1,number2,operator);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    private void getResult(int number1,int number2,String operator) {
        int result=0;
        if(operator.equals("+"))
            result=add(number1,number2);
        else if(operator.equals("-"))
            result=subtract(number1,number2);
        else if(operator.equals("*"))
            result=multiply(number1,number2);
        else if(operator.equals("/"))
            result=divide(number1,number2);
        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", String.valueOf(result));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
    private int add(int number1,int number2)
    {
        return number1+number2;
    }
    private int subtract(int number1,int number2)
    {
        return number1-number2;
    }
    private int multiply(int number1,int number2)
    {
        return number1*number2;
    }
    private int divide(int number1,int number2)
    {
        return number1/number2;
    }

}
