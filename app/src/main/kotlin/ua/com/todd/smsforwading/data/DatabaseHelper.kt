package ua.com.todd.smsforwading.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

import java.sql.SQLException

import ua.com.todd.smsforwading.Sms
import kotlin.properties.Delegates
import ua.com.todd.smsforwading.Profile

public class DatabaseHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DatabaseHelper.DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION) {

    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private var smsDAO: SmsDAO? = null
    private var profileDAO: ProfileItemDAO? = null;

    //Выполняется, когда файл с БД не найден на устройстве
    override fun onCreate(db: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            TableUtils.createTable<Sms>(connectionSource, javaClass<Sms>())
            TableUtils.createTable<Profile>(connectionSource, javaClass<Profile>())
        } catch (e: SQLException) {
            Log.e(TAG, "error creating DB " + DATABASE_NAME)
            throw RuntimeException(e)
        }
    }

    //Выполняется, когда БД имеет версию отличную от текущей
    override fun onUpgrade(db: SQLiteDatabase, connectionSource: ConnectionSource, oldVer: Int, newVer: Int) {
        try {
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable<Sms, Any>(connectionSource, javaClass<Sms>(), true)
            onCreate(db, connectionSource)
        } catch (e: SQLException) {
            Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver " + oldVer)
            throw RuntimeException(e)
        }
    }

    throws(javaClass<SQLException>())
    public fun getSmsDAO(): SmsDAO? = smsDAO ?: SmsDAO(getConnectionSource(), javaClass<Sms>())

    throws(javaClass<SQLException>())
    public fun getProfileDAO(): ProfileItemDAO? = profileDAO ?: ProfileItemDAO(getConnectionSource(), javaClass<Profile>())

    //выполняется при закрытии приложения
    override fun close() {
        super.close()
        smsDAO = null
        profileDAO = null
    }

    companion object {

        private val TAG = javaClass<DatabaseHelper>().getSimpleName()

        //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
        private val DATABASE_NAME = "myappname.db"

        //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
        private val DATABASE_VERSION = 1
    }
}
