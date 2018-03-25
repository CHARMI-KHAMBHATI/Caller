package com.charmi.callerapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CallLogActivity extends Activity {

    private ArrayList<String> conNames;
    private ArrayList<String> conNumbers;
    private ArrayList<String> conTime;
    private ArrayList<String> conDate;
    private ArrayList<String> conType;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("before","create");
       setContentView(R.layout.activity_call_log);
        Log.i("after","create");

        conNames = new ArrayList<String>();
        conNumbers = new ArrayList<String>();
        conTime = new ArrayList<String>();
        conDate = new ArrayList<String>();
        conType = new ArrayList<String>();

        Log.i("before","lv");
        lv=(ListView) findViewById(android.R.id.list);
        Log.i("after","lv");

        Cursor curLog = CallLogHelper.getAllCallLogs(getContentResolver());
        Log.i("after","cur");
        Log.i("cursor", String.valueOf(curLog.getCount()));
        MyAdapter adapter = new MyAdapter(this, curLog);

        Log.i("after","myadap");
        lv.setAdapter(adapter);
        Log.i("after","set");
        adapter.changeCursor(curLog);
    }

    private class MyAdapter extends CursorAdapter {

        public MyAdapter(Context context, Cursor cursor) {
            super(context, cursor,0);




        }
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.liststyle, parent, false);
        }



        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            String s,nm,type;

            TextView tvName = (TextView) view.findViewById(R.id.tvNameMain);
            TextView tvNumber = (TextView) view.findViewById(R.id.tvNumberMain);
           TextView tvduration = (TextView) view.findViewById(R.id.duration);
            TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
            TextView tvType = (TextView) view.findViewById(R.id.tvType);

            Log.i("cursor", String.valueOf(cursor.getCount()));


            s=String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER)));
            nm=String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(android.provider.CallLog.Calls.CACHED_NAME)));

            if(nm.compareTo("null")==0){
                nm="Unknown";

            }
            tvName.setText(nm);

            tvNumber.setText(s);
            String callDate = cursor.getString(cursor
                    .getColumnIndex(android.provider.CallLog.Calls.DATE));
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm");
            String dateString = formatter.format(new Date(Long
                    .parseLong(callDate)));

           tvDate.setText(dateString);
           type=String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE)));
           switch (type){
               case "1" : type="Incoming Call"; break;
               case "2" : type="Outgoing Call"; break;
               case "3" : type="Missed Call"; break;
           }


           tvType.setText(type);
           /*
            String dur = cursor.getString(cursor
                    .getColumnIndex(CallLog.Calls.DURATION));
            formatter = new SimpleDateFormat(
                    "HH:mm:ss");
           dur= formatter.format(new Date(Long
                   .parseLong(dur)));

            tvduration.setText(dur);*/


        }
    }
/*
    private void setCallLogs(Cursor curLog) {
        while (curLog.moveToNext()) {
            String callNumber = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.NUMBER));
            conNumbers.add(callNumber);

            String callName = curLog
                    .getString(curLog
                            .getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME));
            if (callName == null) {
                conNames.add("Unknown");
            } else
                conNames.add(callName);

            String callDate = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.DATE));
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd-MMM-yyyy HH:mm");
            String dateString = formatter.format(new Date(Long
                    .parseLong(callDate)));
            conDate.add(dateString);

            String callType = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.TYPE));
            if (callType.equals("1")) {
                conType.add("Incoming");
            } else
                conType.add("Outgoing");

            String duration = curLog.getString(curLog
                    .getColumnIndex(android.provider.CallLog.Calls.DURATION));
            conTime.add(duration);

        }
    }*/


}
