package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button

import ua.com.todd.baseapp.ui.fragment.BaseFragment
import ua.com.todd.smsforwading.R
import ua.com.todd.smsforwading.managers.PreferenceManager
import kotlin.properties.Delegates
import java.util.ArrayList
import ua.com.todd.baseapp.ui.activity.LayoutId

[LayoutId(R.layout.fragment_settings)]
public class SettingsFragment : BaseFragment() {

    private var host: String by Delegates.notNull()
    private var port: String by Delegates.notNull()
    private var email: String by Delegates.notNull()
    private var pass: String by Delegates.notNull()

    private var buttonCancel: Button by Delegates.notNull()
    private var buttonOk: Button by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(getPreferenceManager<PreferenceManager>()) {
            host = restoreHost()
            port = restorePort().toString()
            email = restoreEmail()
            pass = restorePassword()
        }
        val etHost = getAQuery().id(R.id.etHost).text(host).getEditText()
        val etPort = getAQuery().id(R.id.etPort).text(port).getEditText()
        val etMail = getAQuery().id(R.id.etMail).text(email).getEditText()
        val etPass = getAQuery().id(R.id.etPassword).text(pass).getEditText()

        buttonOk = getAQuery().id(R.id.buttonOk).clicked(object : View.OnClickListener {
            override fun onClick(v: View) {
                email = etMail.getText().toString()
                host = etHost.getText().toString()
                pass = etPass.getText().toString()
                port = etPort.getText().toString()
                val port = Integer.parseInt(port)
                with(getPreferenceManager<PreferenceManager>()) {
                    storeEmail(email as String)
                    storeHost(host as String)
                    storePassword(pass as String)
                    storePort(port)
                }
                v.setVisibility(View.GONE)
                buttonCancel!!.setVisibility(View.GONE)
            }
        }).getButton()

        buttonCancel = getAQuery().id(R.id.buttonCancel).clicked(object : View.OnClickListener {
            override fun onClick(v: View) {
                etMail.setText(email)
                etHost.setText(host)
                etPass.setText(pass)
                etPort.setText(port)
                v.setVisibility(View.GONE)
                buttonOk!!.setVisibility(View.GONE)
            }
        }).getButton()

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                val currMail = etMail.getText().toString()
                val currHost = etHost.getText().toString()
                val currPass = etPass.getText().toString()
                val currPort = etPort.getText().toString()
                if (currHost != host || currMail != email || currPass != pass || currPort != port) {
                    buttonOk!!.setVisibility(View.VISIBLE)
                    buttonCancel!!.setVisibility(View.VISIBLE)
                } else {
                    buttonOk!!.setVisibility(View.GONE)
                    buttonCancel!!.setVisibility(View.GONE)
                }
            }
        }
        w(etHost, etPort, etMail, etPass) {addTextChangedListener(textWatcher)}
    }

    class object {
        public fun getInstance(): Fragment = SettingsFragment()
    }
}

public inline fun <T, R> w(vararg receivers: T, f: T.() -> R): Unit {
    for (r in receivers)
        r.f()
}
