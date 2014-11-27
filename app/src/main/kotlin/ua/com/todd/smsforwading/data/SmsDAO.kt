package ua.com.todd.smsforwading.data

import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource

import java.sql.SQLException

import ua.com.todd.smsforwading.Sms

public class SmsDAO [throws(javaClass<SQLException>())]
internal (connectionSource: ConnectionSource, dataClass: Class<Sms>) : BaseDaoImpl<Sms, Int>(connectionSource, dataClass) {

    throws(javaClass<SQLException>())
    public fun getAllSms(): List<Sms> {
        return this.queryForAll()
    }
}
