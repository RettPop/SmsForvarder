package ua.com.todd.smsforwading

import android.content.Context
import android.widget.BaseAdapter
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList
import android.widget.TextView
import ua.com.todd.smsforwading.model.MenuEvent

public class ProfileAdapter(private val context: Context) : BaseAdapter() {

    private val items = ArrayList<Profile>()

    override fun getCount(): Int = items.size()

    override fun getItem(position: Int) = items.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val text = TextView(context)
        text.setText(getItem(position).mail)
        return text
    }

    public fun setData(data: List<Profile>?) {
        data ?: return
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}