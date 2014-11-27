package ua.com.todd.smsforwadinga.smsforwading.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ua.com.todd.baseapp.fragment.BaseFragment;
import ua.com.todd.smsforwadinga.smsforwading.R;
import ua.com.todd.smsforwadinga.smsforwading.managers.PreferenceManager;

public class SettingsFragment extends BaseFragment {

    public static Fragment getInstance() {
        return new SettingsFragment();
    }

    private String host;
    private String port;
    private String email;
    private String pass;

    private Button buttonCancel;
    private Button buttonOk;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final PreferenceManager pm = getPreferenceManager();
        host = pm.restoreHost();
        port = pm.restorePort().toString();
        email = pm.restoreEmail();
        pass = pm.restorePassword();
        final EditText etHost = getAQuery().id(R.id.etHost).text(host).getEditText();
        final EditText etPort = getAQuery().id(R.id.etPort).text(port).getEditText();
        final EditText etMail = getAQuery().id(R.id.etMail).text(email).getEditText();
        final EditText etPass = getAQuery().id(R.id.etPassword).text(pass).getEditText();

        buttonOk = getAQuery().id(R.id.buttonOk).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pm.storeEmail(email = etMail.getText().toString());
                pm.storeHost(host = etHost.getText().toString());
                pm.storePassword(pass = etPass.getText().toString());
                pm.storePort(Integer.parseInt(port = etPort.getText().toString()));
                v.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
            }
        }).getButton();

        buttonCancel = getAQuery().id(R.id.buttonCancel).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMail.setText(email);
                etHost.setText(host);
                etPass.setText(pass);
                etPort.setText(port);
                v.setVisibility(View.GONE);
                buttonOk.setVisibility(View.GONE);
            }
        }).getButton();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String currMail = etMail.getText().toString();
                String currHost = etHost.getText().toString();
                String currPass = etPass.getText().toString();
                String currPort = etPort.getText().toString();
                if (!currHost.equals(host) || !currMail.equals(email) || !currPass.equals(pass) || !currPort.equals(port)) {
                    buttonOk.setVisibility(View.VISIBLE);
                    buttonCancel.setVisibility(View.VISIBLE);
                } else {
                    buttonOk.setVisibility(View.GONE);
                    buttonCancel.setVisibility(View.GONE);
                }
            }
        };

        etHost.addTextChangedListener(textWatcher);
        etPort.addTextChangedListener(textWatcher);
        etMail.addTextChangedListener(textWatcher);
        etPass.addTextChangedListener(textWatcher);
    }
}
