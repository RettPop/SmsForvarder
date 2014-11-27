package ua.com.todd.smsforwading;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.sql.SQLException;

import ua.com.todd.smsforwading.data.HelperFactory;

public class IncomingSmsBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            final SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            if (messages.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (SmsMessage s : messages) {
                    sb.append(s.getMessageBody());
                    sb.append("\n\n");
                }
                Sms sms = new Sms(sb.toString());
                try {
                    HelperFactory.getHelper().getSmsDAO().create(sms);
                    Intent i = new Intent(context, MailSenderService.class);
                    context.startService(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
