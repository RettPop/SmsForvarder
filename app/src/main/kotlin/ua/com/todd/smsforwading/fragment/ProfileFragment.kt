package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.baseapp.ui.fragment.BaseListFragment
import ua.com.todd.baseapp.utils.AndroidUtils
import ua.com.todd.smsforwading.Profile
import ua.com.todd.smsforwading.ProfileAdapter
import ua.com.todd.smsforwading.R
import ua.com.todd.smsforwading.animation.HAnimation
import ua.com.todd.smsforwading.data.HelperFactory
import ua.com.todd.smsforwading.fragment.FragmentFactory.FragmentType
import ua.com.todd.smsforwading.model.AddProfileEvent
import kotlin.properties.Delegates

[LayoutId(R.layout.fragment_profile)]
public class ProfileFragment : BaseListFragment() {
    companion object {
        public fun getInstance(): Fragment = ProfileFragment()
    }

    private var buttonAdd: View by Delegates.notNull()
    private var buttonContainer: ViewGroup by Delegates.notNull()
    private var adapter: ProfileAdapter by Delegates.notNull()
    private var animHeight: Int by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProfileAdapter(getActivity()) {
            val item = it.getTag() as Profile
            HelperFactory.getHelper().getProfileDAO()?.delete(item)
            adapter.remove(item)
        }
        setAdapter(adapter)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animHeight = (AndroidUtils.getScreenHeight(getActivity()).toFloat() * 0.5).toInt();
        buttonContainer = getAQuery().id(R.id.add_button_container).getView() as ViewGroup
        buttonAdd = getAQuery().id(R.id.add_button).clicked {
            buttonAdd.setVisibility(View.GONE)
            getFragmentManager().beginTransaction()
                    .replace(R.id.add_button_container, getFragment(FragmentType.ITEM), FragmentType.ITEM.toString())
                    .commit()
            val a = HAnimation(animHeight, 300, buttonContainer)
            buttonContainer.startAnimation(a)
        }.getView()
        setProfiles()
    }

    override fun onBackPressed() {
        if (buttonAdd.getVisibility() == View.GONE) {
            val a = HAnimation(-animHeight, 300, buttonContainer)
            a.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    buttonAdd.setVisibility(View.VISIBLE)
                    val fragment = getFragmentManager().findFragmentByTag(FragmentType.ITEM.toString());
                    getFragmentManager().beginTransaction()
                            .remove(fragment)
                            .commit()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
            buttonContainer.startAnimation(a)
        } else {
            onBack()
        }
    }

    public fun onEvent(event: AddProfileEvent): Unit {
        onBackPressed()
        setProfiles()
    }

    private fun setProfiles() {
        val items = HelperFactory.getHelper().getProfileDAO()?.getAllItems()
        adapter.setData(items)
    }
}
