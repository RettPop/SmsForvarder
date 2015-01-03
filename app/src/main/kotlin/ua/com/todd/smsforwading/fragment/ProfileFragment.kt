package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import ua.com.todd.smsforwading.R
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.baseapp.ui.fragment.BaseListFragment
import android.view.View
import android.os.Bundle
import kotlin.properties.Delegates
import android.view.ViewGroup
import ua.com.todd.smsforwading.animation.HAnimation
import ua.com.todd.smsforwading.fragment.FragmentFactory.FragmentType
import android.view.animation.Animation

[LayoutId(R.layout.fragment_profile)]
public class ProfileFragment : BaseListFragment() {
    class object {
        public fun getInstance(): Fragment = ProfileFragment()
    }

    private var buttonAdd: View by Delegates.notNull()
    private var buttonContainer: ViewGroup by Delegates.notNull()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonContainer = getAQuery().id(R.id.add_button_container).getView() as ViewGroup
        buttonAdd = getAQuery().id(R.id.add_button).clicked {
            buttonAdd.setVisibility(View.GONE)
            getBaseActivity().getFragmentLauncher()
                    .replaceFragmentWithStack(R.id.add_button_container, FragmentType.ITEM)
            val a = HAnimation(400 as Integer, 500, buttonContainer)
            buttonContainer.startAnimation(a)
        }.getView()
    }

    override fun onBackPressed() {
        if (buttonAdd.getVisibility() == View.GONE) {
            val a = HAnimation(-400 as Integer, 500, buttonContainer)
            a.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    buttonAdd.setVisibility(View.VISIBLE)
                    onBackPressed()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
            buttonContainer.startAnimation(a)
        } else
            onBack()
    }

    override fun onDetach() {
        super.onDetach()
        getBaseActivity().getFragmentLauncher().clearStack()
    }
}
