package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import ua.com.todd.baseapp.fragment.BaseFragment
import ua.com.todd.smsforwading.R
import ua.com.todd.smsforwading.managers.PreferenceManager

public class SettingsFragment : BaseFragment() {

    private var host: String? = null
    private var port: String? = null
    private var email: String? = null
    private var pass: String? = null

    private var buttonCancel: Button? = null
    private var buttonOk: Button? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pm :PreferenceManager = getPreferenceManager()
        host = pm.restoreHost()
        port = pm.restorePort().toString()
        email = pm.restoreEmail()
        pass = pm.restorePassword()
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
                pm.storeEmail(email as String)
                pm.storeHost(host as String)
                pm.storePassword(pass as String)
                pm.storePort(port)
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

        etHost.addTextChangedListener(textWatcher)
        etPort.addTextChangedListener(textWatcher)
        etMail.addTextChangedListener(textWatcher)
        etPass.addTextChangedListener(textWatcher)
    }

    class object {

        public fun getInstance(): Fragment {
            return SettingsFragment()
        }
    }
}
