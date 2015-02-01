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
        val pref = MyApplication.app().getPreferenceManager<PreferenceManager>()
        val items = HelperFactory.getHelper().getProfileDAO()?.getAllItems()
        if (items!!.isNotEmpty() && pref.isEnabled() && Utils.isNotNull(login, pass, host, port))
            try {
                val smsList : List<Sms> = HelperFactory.getHelper().getSmsDAO()!!.getAllSms()
                for (s in smsList)
                    with(SimpleEmail()) {
                        setHostName(host)
                        setSmtpPort(port)
                        setStartTLSEnabled(true)
                        setAuthenticator(DefaultAuthenticator(login, pass))
                        setFrom(login)
                        setSubject("TestMail")
                        setMsg(s.toString())
                        items.map { addTo(it.mail) }
                        send()
                        HelperFactory.getHelper().getSmsDAO()?.delete(s)
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            }

    }

    fun n(vararg n: String) {
        n.clone()
    }
}
