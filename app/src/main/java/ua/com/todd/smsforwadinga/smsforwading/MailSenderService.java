package ua.com.todd.smsforwadinga.smsforwading;

import android.app.IntentService;
import android.content.Intent;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.sql.SQLException;
import java.util.List;

import ua.com.todd.smsforwadinga.smsforwading.model.Sms;
import ua.com.todd.smsforwadinga.smsforwading.data.HelperFactory;

public class MailSenderService extends IntentService {

    public MailSenderService() {
        super(MailSenderService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            List<Sms> smsList = HelperFactory.getHelper().getSmsDAO().getAllSms();
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setStartTLSEnabled(true);
            email.setAuthenticator(new DefaultAuthenticator("mr.kakashka@gmail.com", ""));
            email.setFrom("mr.kakashka@gmail.com");
            email.setSubject("TestMail");
            email.setMsg(smsList.toString());
            email.addTo("anndomashenko@hotmail.com");
            email.send();
            HelperFactory.getHelper().getSmsDAO().delete(smsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
