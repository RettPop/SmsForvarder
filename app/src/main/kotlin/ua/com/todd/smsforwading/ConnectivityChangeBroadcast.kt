package ua.com.todd.smsforwading

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ua.com.todd.baseapp.utils.Log
import ua.com.todd.baseapp.utils.AndroidUtils
import ua.com.todd.baseapp.utils.NetworkUtils

public class ConnectivityChangeBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if(NetworkUtils.isOnline(context)){
            val i = Intent(context, javaClass<MailSenderService>())
            context.startService(i)
        }
    }
}