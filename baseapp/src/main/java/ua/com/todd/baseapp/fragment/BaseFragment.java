package ua.com.todd.baseapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

public class BaseFragment extends Fragment {
    private AQuery aQuery;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aQuery = new AQuery(getActivity(), view);
    }

    public AQuery getAQuery() {
        return aQuery;
    }
}
