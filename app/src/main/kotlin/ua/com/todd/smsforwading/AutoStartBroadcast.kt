package ua.com.todd.smsforwading

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

public class AutoStartBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, javaClass<MailSenderService>())
        context.startService(i)
    }
}