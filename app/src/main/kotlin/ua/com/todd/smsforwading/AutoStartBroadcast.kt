package ua.com.todd.smsforwading

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

public class AutoStartBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.startService(Intent(context, javaClass<MailSenderService>()))
    }
}