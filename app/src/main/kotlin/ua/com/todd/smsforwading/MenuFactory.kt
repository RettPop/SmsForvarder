package ua.com.todd.smsforwading

import ua.com.todd.baseapp.ui.menu.IMenuFactory
import android.content.Context
import android.widget.ListAdapter
import android.widget.BaseAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

public class MenuFactory : IMenuFactory {
    override fun getMenuAdapter(context: Context): ListAdapter? {
        val items = listOf(
                MenuItem(MenuItemType.PROFILE, "Profile"),
                MenuItem(MenuItemType.SETTINGS, "Settings"),
                MenuItem(MenuItemType.ABOUT, "About program"))
        return MenuAdapter(context, items)
    }
}

public enum class MenuItemType(val id: Long) {
    SETTINGS : MenuItemType(SETTINGS_ID)
    ABOUT : MenuItemType(ABOUT_ID)
    PROFILE : MenuItemType(PROFILE_ID)

    companion object {

        private val PROFILE_ID : Long = 1
        private val SETTINGS_ID : Long = 2
        private val ABOUT_ID : Long = 3

        public fun getType(id: Long): MenuItemType = when (id) {
            SETTINGS_ID -> MenuItemType.SETTINGS
            ABOUT_ID -> MenuItemType.ABOUT
            PROFILE_ID -> MenuItemType.PROFILE
            else -> throw RuntimeException("Invalid id value for MenuItemType")
        }
    }
}

public data class MenuItem(val type: MenuItemType, val text: String)

public class MenuAdapter(val context: Context, val items: List<MenuItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): MenuItem? {
        return items.elementAt(position)
    }

    override fun getItemId(position: Int): Long {
        return items.elementAt(position).type.id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var textView: TextView? = convertView as TextView?
        if (textView == null) {
            textView = View.inflate(context, R.layout.item_menu, null) as TextView
        }
        val item = getItem(position);
        textView!!.setText(item!!.text)
        return textView;
    }
}