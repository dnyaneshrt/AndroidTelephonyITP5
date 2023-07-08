package com.itp.mytelephonyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_call,btn_sms;
    EditText et_number,et_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_call=findViewById(R.id.btn_call);
        btn_sms=findViewById(R.id.btn_sms);
        et_number=findViewById(R.id.et_mobile);
        et_message=findViewById(R.id.et_message);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+et_number.getText().toString()));
                startActivity(intent);

            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String mobile_number= et_number.getText().toString();
               String[] numbers= mobile_number.split(",");
                for(String number:numbers)
                {
                    SmsManager smsManager=SmsManager.getDefault();
                    //Pending Intent.
                    Intent sent_intent=new Intent(MainActivity.this, SentActivity.class);
                    PendingIntent si=PendingIntent.getActivity(MainActivity.this, PendingIntent.FLAG_IMMUTABLE, sent_intent, PendingIntent.FLAG_IMMUTABLE);


                    Intent delivered_intent=new Intent(MainActivity.this, DeliveredActivity.class);
                    PendingIntent di=PendingIntent.getActivity(MainActivity.this, PendingIntent.FLAG_IMMUTABLE, delivered_intent, PendingIntent.FLAG_IMMUTABLE);

                    smsManager.sendTextMessage(number,null,et_message.getText().toString(),si,di);

                }

               }
        });
    }
}