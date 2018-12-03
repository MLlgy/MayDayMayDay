package com.lgy.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends Activity implements View.OnClickListener {

    private Spinner mSpinner;
    private ImageView mExit;
    private TextView mSaveCompressInof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        initWindow();
        initView();
        initData();

    }

    private void initData() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        SpinnerAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, list);
        mSpinner.setAdapter(adapter);
        mSpinner.setBackgroundColor(0x0);
        ((ArrayAdapter) adapter).setDropDownViewResource(R.layout.spinner_item);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), list.get(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initView() {
        mSpinner = (Spinner) findViewById(R.id.sp_compress);
        mExit = findViewById(R.id.iv_express_exit);
        mSaveCompressInof = findViewById(R.id.tv_express_save);
        mExit.setOnClickListener(this);
        mSaveCompressInof.setOnClickListener(this);
    }

    private void initWindow() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.alpha = 1.0f;
        getWindow().setAttributes(params);
        getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_express_exit:

                finish();
                break;

            case R.id.tv_express_save:
                Toast.makeText(getApplicationContext(), "save", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
