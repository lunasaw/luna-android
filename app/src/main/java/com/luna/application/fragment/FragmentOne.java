package com.luna.application.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.luna.application.R;

public class FragmentOne extends Fragment {

    private TextView           textView;

    private Activity           activity;

    public static final String TITLE = "title";

    private Button             change, reset;

    private Fragment           fragmentTwo;

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

    public FragmentOne() {}

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
        return inflater.inflate(R.layout.fragment_one, container, false);
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
        Log.i("TAG", "onViewCreated: ");
        textView = view.findViewById(R.id.fragment_one);
        if (activity != null) {
            // TODO
        }
        // 此为获取Fragment所在的Activity 可能为空
        FragmentActivity activity = getActivity();
        if (getArguments() != null) {
            textView.setText(getArguments().getString(TITLE));
        }

        change = view.findViewById(R.id.fragment_one_btn_change);
        reset = view.findViewById(R.id.fragment_one_btn_reset);
        change.setOnClickListener(new Onclick());
        reset.setOnClickListener(new Onclick());
    }

    class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.fragment_one_btn_reset:
                    textView.setText("我是新文字");
                    break;
                case R.id.fragment_one_btn_change:
                    if (fragmentTwo == null) {
                        fragmentTwo = FragmentTwo.newInstance("我是新来的B");
                    }
                    Fragment fragmentByTag = getFragmentManager().findFragmentByTag("one");
                    if (fragmentByTag != null) {
                        getFragmentManager().beginTransaction().hide(fragmentByTag)
                            .add(R.id.fragment_container, fragmentTwo).addToBackStack(null).commitAllowingStateLoss();
                    } else {
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTwo)
                            .addToBackStack(null).commitAllowingStateLoss();
                    }
                    getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragmentTwo)
                        .addToBackStack(null).commitAllowingStateLoss();
                    break;
            }
        }
    }

    /**
     * 与Activity产生联系的时候调用
     * 
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity)context;
    }

    /**
     * 与Activity结束联系时调用
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * 销毁方法 可在此取消异步任务
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}