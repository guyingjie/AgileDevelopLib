package com.agiledeveloplib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.basictoolslib.utils.DialogUtil;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogUtil.INSTANCE.showDialog(this, "测试", "我来组成内容", view ->
                Toast.makeText(JavaActivity.this, "hehe", Toast.LENGTH_SHORT).show()
        );
    }
}