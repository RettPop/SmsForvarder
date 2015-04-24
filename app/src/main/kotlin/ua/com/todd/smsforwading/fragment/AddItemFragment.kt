package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.View
import de.greenrobot.event.EventBus
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.baseapp.ui.fragment.BaseFragment
import ua.com.todd.smsforwading.Profile
import ua.com.todd.smsforwading.R
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.model.AddProfileEvent
import kotlinx.android.synthetic.fragment_item.*

[LayoutId(R.layout.fragment_item)]
public class AddItemFragment : BaseFragment() {
    companion object {
        public fun getInstance(): Fragment = AddItemFragment()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        buttonAdd.setOnClickListener {
//            val profile = Profile(textMail.getText().toString())
//            HelperFactory.getHelper().getProfileDAO()!!.create(profile)
//            EventBus.getDefault().post(AddProfileEvent())
//        }
    }
}
