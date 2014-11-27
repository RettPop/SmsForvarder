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
            if (messages.size > 0) {
                val sb = StringBuilder()
                for (s in messages) {
                    sb.append(s?.getMessageBody())
                    sb.append("\n\n")
                }
                val sms = Sms(sb.toString())
                try {
                    HelperFactory.getHelper().getSmsDAO()?.create(sms)
                    val i = Intent(context, javaClass<MailSenderService>())
                    context.startService(i)
                } catch (e: SQLException) {
                    e.printStackTrace()
                }

            }
        }
    }
}
