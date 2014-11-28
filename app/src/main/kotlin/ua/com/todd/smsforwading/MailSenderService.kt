package ua.com.todd.smsforwading

import android.app.IntentService
import android.content.Intent

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.Email
import org.apache.commons.mail.SimpleEmail

import ua.com.todd.baseapp.utils.Utils
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.managers.PreferenceManager

public class MailSenderService : IntentService(javaClass<MailSenderService>().getName()) {

    private var login: String? = null
    private var pass: String? = null
    private var host: String? = null
    private var port: Int = 0

    override fun onCreate() {
        super.onCreate()
        val app = getApplication() as MyApplication
        val preferenceManager = app.getPreferenceManager<PreferenceManager>()
        login = preferenceManager.restoreEmail()
        pass = preferenceManager.restorePassword()
        host = preferenceManager.restoreHost()
        port = preferenceManager.restorePort()!!
    }

    override fun onHandleIntent(intent: Intent) {
        if (Utils.isNotNull(login, pass, host, port))
            try {
                val smsList = HelperFactory.getHelper().getSmsDAO()?.getAllSms()
                if(smsList!!.empty)
                    return;
                val email = SimpleEmail()
                email.setHostName(host)
                email.setSmtpPort(port)
                email.setStartTLSEnabled(true)
                email.setAuthenticator(DefaultAuthenticator(login, pass))
                email.setFrom(login)
                email.setSubject("TestMail")
                email.setMsg(smsList.toString())
                email.addTo("timandriyaschenko@gmail.com")
                email.send()
                HelperFactory.getHelper().getSmsDAO()?.delete(smsList)
            } catch (e: Exception) {
                e.printStackTrace()
            }

    }
}
