package com.luna.application.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.luna.application.R;

public class FragmentActivity extends AppCompatActivity implements FragmentThree.IOnMessageClick {

    private Fragment fragmentOne, fragmentTwo, fragmentThree;

    private Button   changeFragment;

    private int      count;

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        changeFragment = findViewById(R.id.fragment_btn);
        changeFragment.setOnClickListener(new Onclick());
        textView = findViewById(R.id.fragment_text);
        // 实例化fragment
        fragmentOne = FragmentOne.newInstance("我是fragmentOne");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmentOne, "one")
            .commitAllowingStateLoss();
    }

    @Override
    public void onclick(String string) {
        textView.setText(string);
    }

    class Onclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            count++;
            if (fragmentThree == null) {
                fragmentThree = FragmentThree.newInstance("我是fragmentThree");
            }
            if (fragmentTwo == null) {
                fragmentTwo = FragmentTwo.newInstance("我是fragmentTwo");
            }
            if (count % 2 == 1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentTwo)
                    .commitAllowingStateLoss();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentThree)
                    .commitAllowingStateLoss();
            }

        }
    }

}
