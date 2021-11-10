package com.example.broadcastdemo;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import static android.content.ContentValues.TAG;

public class NumberReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
//        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
//            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
//            DbHelper dbHelper= new DbHelper(context);
//            SQLiteDatabase database = dbHelper.getWritableDatabase();
//            dbHelper.saveNumber(number,database);
//            dbHelper.close();
//        }
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                Log.e(TAG, "onCallStateChanged"+state);
                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    Log.e(TAG, "onReceive"+incomingNumber);
                    DbHelper dbHelper= new DbHelper(context);
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dbHelper.saveNumber(incomingNumber,database);
                    dbHelper.close();
                }

            }
        },PhoneStateListener.LISTEN_CALL_STATE);

        Intent intent1 = new Intent(DbContact.UPDATE_UI_FILTER);
        context.sendBroadcast(intent1);
    }
}
