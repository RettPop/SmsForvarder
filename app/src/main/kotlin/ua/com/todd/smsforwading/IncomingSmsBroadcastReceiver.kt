package ua.com.todd.smsforwading

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage

import java.sql.SQLException

import ua.com.todd.smsforwading.data.HelperFactory

public class IncomingSmsBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.getExtras()
        if (bundle != null) {
            val pdus = bundle.get("pdus") as Array<Any>
            val messages = arrayOfNulls<SmsMessage>(pdus.size)
            for (i in pdus.indices) {
                messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
            }
            if (messages.size() > 0) {
                for (s in messages) {
                    val sms = Sms(s?.getMessageBody())
                    try {
                        HelperFactory.getHelper().getSmsDAO()?.create(sms)
                        context.startService(Intent(context, javaClass<MailSenderService>()))
                    } catch (e: SQLException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}
