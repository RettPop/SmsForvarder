package ua.com.todd.smsforwading

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.DataType
import com.j256.ormlite.table.DatabaseTable

DatabaseTable(tableName = "profiles")
public class Profile(private val m: String? = null) {

    companion object {
        public val PROFILE_FIELD: String = "mail"
    }

    DatabaseField(generatedId = true)
    private val Id: Int = 0
    DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = PROFILE_FIELD)
    public var mail: String? = m

    override fun toString(): String = mail as String
}