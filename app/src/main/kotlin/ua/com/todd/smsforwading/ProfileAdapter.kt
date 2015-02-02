package ua.com.todd.smsforwading

import android.content.Context
import android.widget.BaseAdapter
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList
import android.widget.TextView

public class ProfileAdapter(private val context: Context, private val onClick : (View) -> Unit) : BaseAdapter() {

    private val items = ArrayList<Profile>()

    override fun getCount(): Int = items.size()

    override fun getItem(position: Int) = items.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val item = getItem(position)
        val view = View.inflate(context, R.layout.item_profile, null)
        val buttonRemove = view.findViewById(R.id.button_remove)
        val textProfile = view.findViewById(R.id.text_profile) as TextView
        textProfile.setText(item.mail)
        buttonRemove.setOnClickListener(onClick)
        buttonRemove.setTag(item)
        return view
    }

    public fun setData(data: List<Profile>?) {
        data ?: return
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    fun remove(item : Profile) {
        items.remove(items.indexOf(item))
        notifyDataSetChanged()
    }
}