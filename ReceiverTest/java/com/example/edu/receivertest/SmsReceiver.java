package com.example.edu.receivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by edu on 2016-03-21.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object messages[] = (Object[])bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];

        for(int i = 0; i < messages.length; i++) {
            // PDU 포맷으로 되어 있는 메시지를 복원합니다.
            smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);
        }

// SMS 수신 시간 확인
        Date curDate = new Date(smsMessage[0].getTimestampMillis());
        Log.d("문자 수신 시간", curDate.toString());

// SMS 발신 번호 확인
        String origNumber = smsMessage[0].getOriginatingAddress();

// SMS 메시지 확인
        String message = smsMessage[0].getMessageBody().toString();
        Log.d("문자 내용", "발신자 : "+origNumber+", 내용 : " + message);
        Toast.makeText(context, message +":"+ curDate.toString(), Toast.LENGTH_LONG).show();
    }
}
