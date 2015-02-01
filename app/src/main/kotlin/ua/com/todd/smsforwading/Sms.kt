package ua.com.todd.smsforwading

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

DatabaseTable(tableName = "messages")
public class Sms(val mes: String? = null) {

    class object {
        public val SMS_FIELD_MESSAGE: String = "message"
    }

    DatabaseField(generatedId = true)
    private val Id: Int = 0
    DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = SMS_FIELD_MESSAGE)
    private val message: String? = mes

    override fun toString(): String = message as String
}
