package ua.com.todd.smsforwading.fragment

import ua.com.todd.baseapp.ui.fragment.BaseFragment
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.smsforwading.R
import android.app.Fragment
import android.view.View
import android.os.Bundle
import ua.com.todd.smsforwading.Profile
import ua.com.todd.smsforwading.data.HelperFactory
import de.greenrobot.event.EventBus
import ua.com.todd.smsforwading.model.AddProfileEvent

[LayoutId(R.layout.fragment_item)]
public class AddItemFragment : BaseFragment() {
    companion object {
        public fun getInstance(): Fragment = AddItemFragment()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edit = getAQuery()
                .id(R.id.text_mail)
                .getEditText()
        getAQuery()
                .id(R.id.button_add)
                .clicked {
                    val profile = Profile(edit.getText().toString())
                    HelperFactory.getHelper().getProfileDAO()!!.create(profile)
                    EventBus.getDefault().post(AddProfileEvent())
                }
    }
}
