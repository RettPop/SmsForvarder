package ua.com.todd.smsforwading.data;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import ua.com.todd.smsforwading.Sms;

public class SmsDAO extends BaseDaoImpl<Sms, Integer> {

    protected SmsDAO(ConnectionSource connectionSource,
                     Class<Sms> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Sms> getAllSms() throws SQLException {
        return this.queryForAll();
    }
}
