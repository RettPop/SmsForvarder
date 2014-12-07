package ua.com.todd.baseapp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import ua.com.todd.baseapp.ui.menu.IMenuFactory;

public abstract class BaseMenuFragment extends BaseFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IMenuFactory menuFragment = getApp().getMenuFactory();
        getAQuery()
                .id(android.R.id.list)
                .adapter(menuFragment.getMenuAdapter(getActivity()))
                .itemClicked(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onMenuItemClick(id);
        }
    };

    protected void onMenuItemClick(long id) {
    }
}
