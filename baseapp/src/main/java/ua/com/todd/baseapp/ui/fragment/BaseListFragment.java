package ua.com.todd.baseapp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import ua.com.todd.baseapp.utils.Utils;

public class BaseListFragment extends BaseFragment {
    private ListAdapter adapter = null;
    private ListView listView = null;

    public ListAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ListAdapter adapter) {
        this.adapter = adapter;
        if (Utils.isNotNull(adapter, listView))
            listView.setAdapter(adapter);
    }

    public ListView getListView() {
        return listView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseListFragment.this.onItemClick((ListAdapter) parent.getAdapter(), view, position, id);
            }
        });
        setAdapter(adapter);
    }

    protected void onItemClick(ListAdapter adapter, View view, int position, long id) {

    }
}
