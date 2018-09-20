package kulikova.weather.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kulikova.weather.R;

public class ListFragment extends Fragment {
    private static final String ARG_CAPTION = "param1";

    private String caption="def";
    private TextView captionView;

    public ListFragment() {}

    public static ListFragment newInstance(String param1) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CAPTION, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            caption = getArguments().getString(ARG_CAPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        captionView = view.findViewById(R.id.caption);
        captionView.setText(caption);
        return view;
    }
}
