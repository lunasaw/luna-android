package com.luna.application.fragment;

import com.luna.application.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentThree extends Fragment {

    public static final String TITLE = "title";
    private TextView           textView;
    private IOnMessageClick    iOnMessageClick;
    private Button             change, reset;

    public interface IOnMessageClick {
        void onclick(String string);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            iOnMessageClick = (IOnMessageClick)context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity 必须实现 IOnMessageClick 接口");
        }
    }

    /**
     * Fragment参数传递
     *
     * @param string
     * @return
     */
    public static FragmentThree newInstance(String string) {
        FragmentThree fragmentThree = new FragmentThree();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, string);
        fragmentThree.setArguments(bundle);
        return fragmentThree;
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
        return inflater.inflate(R.layout.fragment_three, container, false);
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
        textView = view.findViewById(R.id.fragment_three);
        if (getArguments() != null) {
            textView.setText(getArguments().getString(TITLE));
        }
        change = view.findViewById(R.id.fragment_three_btn_change);
        reset = view.findViewById(R.id.fragment_three_btn_reset);
        change.setOnClickListener(new Onclick());
        reset.setOnClickListener(new Onclick());
    }

    class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.fragment_three_btn_reset:
                    iOnMessageClick.onclick("这是iOnMessageClick过来的");
                    break;
            }
        }
    }
}