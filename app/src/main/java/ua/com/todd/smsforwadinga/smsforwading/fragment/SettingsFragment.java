package ua.com.todd.smsforwadinga.smsforwading.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ua.com.todd.baseapp.fragment.BaseFragment;
import ua.com.todd.smsforwadinga.smsforwading.R;
import ua.com.todd.smsforwadinga.smsforwading.managers.PreferenceManager;

public class SettingsFragment extends BaseFragment {

    public static Fragment getInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final PreferenceManager pm = getPreferenceManager();
        final EditText etHost = getAQuery().id(R.id.etHost).text(pm.restoreHost()).getEditText();
        final EditText etPort = getAQuery().id(R.id.etPort).text(pm.restorePort().toString()).getEditText();
        final EditText etMail = getAQuery().id(R.id.etMail).text(pm.restoreEmail()).getEditText();
        final EditText etPass = getAQuery().id(R.id.etPassword).text(pm.restorePassword()).getEditText();
        getAQuery().id(R.id.buttonOk).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pm.storeEmail(etMail.getText().toString());
                pm.storeHost(etHost.getText().toString());
                pm.storePassword(etPass.getText().toString());
                pm.storePort(Integer.parseInt(etPort.getText().toString()));
            }
        });
    }
}
