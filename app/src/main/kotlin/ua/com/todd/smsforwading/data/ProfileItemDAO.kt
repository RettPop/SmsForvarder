package ua.com.todd.smsforwading.data

import com.j256.ormlite.support.ConnectionSource
import ua.com.todd.smsforwading.Sms
import com.j256.ormlite.dao.BaseDaoImpl
import java.sql.SQLException
import ua.com.todd.smsforwading.Profile

public class ProfileItemDAO internal (connectionSource: ConnectionSource, dataClass: Class<Profile>) : BaseDaoImpl<Profile, Int>(connectionSource, dataClass) {

    throws(javaClass<SQLException>())
    public fun getAllItems(): List<Profile> = queryForAll()
}