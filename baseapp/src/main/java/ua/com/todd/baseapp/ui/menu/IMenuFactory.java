package ua.com.todd.baseapp.ui.menu;

import android.content.Context;
import android.widget.ListAdapter;

public interface IMenuFactory {

    public ListAdapter getMenuAdapter(Context context);
}
