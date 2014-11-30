package ua.com.todd.smsforwading.activity

import android.view.Menu
import android.view.MenuItem

import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.BaseActivity
import ua.com.todd.smsforwading.fragment.FragmentFactory.FragmentType
import ua.com.todd.baseapp.ui.activity.SlideMenuBaseActivity


public class MainActivity : SlideMenuBaseActivity() {

    override fun getActivityLayoutId() = R.layout.activity_main

}
