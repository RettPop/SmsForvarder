package ua.com.todd.smsforwading.activity

import android.view.Menu
import android.view.MenuItem

import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.activity.BaseActivity
import ua.com.todd.smsforwading.fragment.FragmentFactory.FragmentType


public class MainActivity : BaseActivity() {

    override fun getActivityLayoutId() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.getItemId()) {
            R.id.action_settings -> {
                getFragmentLauncher().addFragmentWithStack(FragmentType.SETTINGS)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
