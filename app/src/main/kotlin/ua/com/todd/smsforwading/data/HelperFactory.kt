package ua.com.todd.smsforwading.data

import android.content.Context

import com.j256.ormlite.android.apptools.OpenHelperManager

public class HelperFactory {
    companion object {

        private var databaseHelper: DatabaseHelper? = null

        public fun getHelper(): DatabaseHelper = databaseHelper as DatabaseHelper

        public fun setHelper(context: Context) {
            databaseHelper = OpenHelperManager.getHelper<DatabaseHelper>(context, javaClass<DatabaseHelper>())
        }

        public fun releaseHelper() {
            OpenHelperManager.releaseHelper()
            databaseHelper = null
        }
    }
}
