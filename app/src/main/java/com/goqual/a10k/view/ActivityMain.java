package com.goqual.a10k.view;

import android.os.Bundle;

import com.goqual.a10k.R;
import com.goqual.a10k.view.common.BaseActivity;

public class ActivityMain extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectSocketServer();
    }

    private void init() {

    }
}
