package ua.com.todd.smsforwadinga.smsforwading;

import android.app.IntentService;
import android.content.Intent;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.util.List;

import ua.com.todd.baseapp.utils.Utils;
import ua.com.todd.smsforwadinga.smsforwading.data.HelperFactory;
import ua.com.todd.smsforwadinga.smsforwading.managers.PreferenceManager;

public class MailSenderService extends IntentService {

    public MailSenderService() {
        super(MailSenderService.class.getName());
    }

    private String login;
    private String pass;
    private String host;
    private int port;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication app = (MyApplication) getApplication();
        PreferenceManager preferenceManager = app.getPreferenceManager();
        login = preferenceManager.restorePassword();
        pass = preferenceManager.restorePassword();
        host = preferenceManager.restoreHost();
        port = preferenceManager.restorePort();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (Utils.isNotNull(login, pass, host, port))
            try {
                List<Sms> smsList = HelperFactory.getHelper().getSmsDAO().getAllSms();
                Email email = new SimpleEmail();
                email.setHostName(host);
                email.setSmtpPort(port);
                email.setStartTLSEnabled(true);
                email.setAuthenticator(new DefaultAuthenticator(login, pass));
                email.setFrom(login);
                email.setSubject("TestMail");
                email.setMsg(smsList.toString());
                email.addTo("timandriyaschenko@gmail.com");
                email.send();
                HelperFactory.getHelper().getSmsDAO().delete(smsList);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
