package ua.com.todd.smsforwading

import android.app.IntentService
import android.content.Intent

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

import ua.com.todd.baseapp.utils.Utils
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.managers.PreferenceManager
import kotlin.properties.Delegates

public class MailSenderService : IntentService(javaClass<MailSenderService>().getName()) {

    private var login: String by Delegates.notNull()
    private var pass: String by Delegates.notNull()
    private var host: String by Delegates.notNull()
    private var port: Int by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        val app = getApplication() as MyApplication
        with(app.getPreferenceManager<PreferenceManager>()) {
            login = restoreEmail()
            pass = restorePassword()
            host = restoreHost()
            port = restorePort()
        }
    }

    override fun onHandleIntent(intent: Intent) {
        if (Utils.isNotNull(login, pass, host, port))
            try {
                val smsList = HelperFactory.getHelper().getSmsDAO()?.getAllSms()
                if (!smsList!!.empty) with(SimpleEmail()) {
                    setHostName(host)
                    setSmtpPort(port)
                    setStartTLSEnabled(true)
                    setAuthenticator(DefaultAuthenticator(login, pass))
                    setFrom(login)
                    setSubject("TestMail")
                    setMsg(smsList.toString())
                    addTo("timandriyaschenko@gmail.com")
                    send()
                    HelperFactory.getHelper().getSmsDAO()?.delete(smsList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

    }
}
