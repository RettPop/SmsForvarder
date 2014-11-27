package ua.com.todd.smsforwading.activity

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import ua.com.todd.smsforwading.R
import ua.com.todd.smsforwading.fragment.SettingsFragment


public class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragment: Fragment? = getFragmentManager().findFragmentByTag(SettingsFragment.javaClass.getSimpleName())
        if (fragment == null) {
            fragment = SettingsFragment.getInstance()
        }
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_settings, fragment, SettingsFragment.javaClass.getName())
                .commit()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
