package com.luna.application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.luna.application.R;

public class FragmentTwo extends Fragment {

    public static final String TITLE = "title";
    private TextView           textView;

    /**
     * Fragment参数传递
     *
     * @param string
     * @return
     */
    public static FragmentOne newInstance(String string) {
        FragmentOne fragmentOne = new FragmentOne();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, string);
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }

    /**
     * 传入布局文件
     * 
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    /**
     * view 创建完成后调用
     * 
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.fragment_two);
        if (getArguments() != null) {
            textView.setText(getArguments().getString(TITLE));
        }
    }
}