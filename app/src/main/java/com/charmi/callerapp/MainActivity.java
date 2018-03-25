package com.charmi.callerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String num="";
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bstr,bhsh,bcall,bsave,blog,bclr;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b5 = (Button) findViewById(R.id.btn5);
        b6 = (Button) findViewById(R.id.btn6);
        b7 = (Button) findViewById(R.id.btn7);
        b8 = (Button) findViewById(R.id.btn8);
        b9 = (Button) findViewById(R.id.btn9);
        b0 = (Button) findViewById(R.id.btn0);
        bstr = (Button) findViewById(R.id.btnstr);
        bhsh = (Button) findViewById(R.id.btnhsh);
        bcall = (Button) findViewById(R.id.btncall);
        bsave = (Button) findViewById(R.id.btnsave);
        blog = (Button) findViewById(R.id.btnlog);
        bclr = (Button) findViewById(R.id.btnclr);
        txt = (TextView) findViewById(R.id.numtxt);

    }

    public void zero(View view) {
        num=num+"0";
        txt.setText(num);
    }

    public void clr(View view) {

        if (num.length()>0) {

            num= num.substring(0, num.length()-1);
            txt.setText(num);

        }else{
            txt.setText(null);
        }

    }

    public void one(View view) {
        num=num+"1";
        txt.setText(num);
    }

    public void two(View view) {
        num=num+"2";
        txt.setText(num);
    }
    public void three(View view) {
        num=num+"3";
        txt.setText(num);
    }

    public void four(View view) {
        num=num+"4";
        txt.setText(num);
    }

    public void five(View view) {
        num=num+"5";
        txt.setText(num);
    }

    public void six(View view) {
        num=num+"6";
        txt.setText(num);
    }

    public void seven(View view) {
        num=num+"7";
        txt.setText(num);
    }

    public void eight(View view) {
        num=num+"8";
        txt.setText(num);
    }

    public void nine(View view) {
        num=num+"9";
        txt.setText(num);
    }

    public void str(View view) {
        num=num+"*";
        txt.setText(num);
    }


    public void hsh(View view) {
        num=num+"#";
        txt.setText(num);
    }

    public void save(View view) {
        if (num.length()>0) {
            // Creates a new Intent to insert a contact
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            // Sets the MIME type to match the Contacts Provider
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);



            // Inserts a phone number
                   intent.putExtra(ContactsContract.Intents.Insert.PHONE, num);


            startActivity(intent);


        }
        else{
            Toast.makeText(this, "Please provide a number", Toast.LENGTH_SHORT).show();
        }

    }

    public void call(View view) {
        if (num.length()>0) {
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:" + num));
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Please provide a number", Toast.LENGTH_SHORT).show();
        }
    }

    public void call_log(View view) {
        Intent i = new Intent(this, CallLogActivity.class);
        startActivity(i);
    }
}
