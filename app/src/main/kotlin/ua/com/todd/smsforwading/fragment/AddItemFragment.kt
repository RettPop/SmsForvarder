package ua.com.todd.smsforwading.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import de.greenrobot.event.EventBus
import kotlinx.android.synthetic.fragment_item.buttonAdd
import kotlinx.android.synthetic.fragment_item.list
import ua.com.todd.baseapp.ui.activity.LayoutId
import ua.com.todd.baseapp.ui.fragment.BaseFragment
import ua.com.todd.smsforwading.R
import ua.com.todd.smsforwading.model.AddProfileEvent

[LayoutId(R.layout.fragment_item)]
public class AddItemFragment : BaseFragment() {
    companion object {
        public fun getInstance(): Fragment = AddItemFragment()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.setAdapter(CreateProfileAdapter(getActivity()))
        Log.i("AddItemFragment", "onViewCreated")
        buttonAdd.setOnClickListener {
            //            val profile = Profile(textMail.getText().toString())
            //            HelperFactory.getHelper().getProfileDAO()!!.create(profile)
            EventBus.getDefault().post(AddProfileEvent())
        }
    }


}

public class CreateProfileAdapter(private val context: Context) : BaseAdapter() {
    private val items = arrayListOf(SpinnerData(SpinnerType.TO, ""), SpinnerData(SpinnerType.NONE, ""))

    override fun getCount(): Int {
        return items.size()
    }

    override fun getItem(position: Int): SpinnerData {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val item = getItem(position)
        val (spinnerData, str) = item
        var view = convertView
        if (view == null) {
            view = View.inflate(context, R.layout.layout_item_profile, null)
            view!!.setTag(
                    ViewHolder(view!!.findViewById(R.id.spinnerPref) as Spinner,
                            view!!.findViewById(R.id.editData) as EditText))
        }
        val (spinner, edit) = view!!.getTag() as ViewHolder
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, SpinnerType.values().map { it.str })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
        spinner.setSelection(spinnerData.ordinal())
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val str = parent!!.getAdapter().getItem(position) as String
                Log.i("CreateProfileAdapter", " " + item + " " + str +  " " + items.indexOf(item))
                notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        })
        val tag = Tag(this, item)
        spinner.setTag(tag)
        edit.setText(str)
        return view
    }

    data class ViewHolder(val spin: Spinner, val edit: EditText)

    data class Tag(val adapter: CreateProfileAdapter, val spinnerData: SpinnerData)

    data class SpinnerData(val spinnerType: SpinnerType = SpinnerType.NONE, val str: String = "")

    enum class SpinnerType(val str: String) {
        NONE : SpinnerType("---")
        TO : SpinnerType("to:")
        FROM : SpinnerType("from:")
        AND : SpinnerType("and:")
        OR : SpinnerType("or:")
        DATA : SpinnerType("contain:")
    }

}
