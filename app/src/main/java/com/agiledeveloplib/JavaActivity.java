package com.agiledeveloplib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.basictoolslib.utils.DialogUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class JavaActivity extends AppCompatActivity {

    private int r, a, b = 2;
    private Integer m, n = 2;
    private String who, name = "顾英杰";
    private String[] anis, animals = {"鸟", "鱼", "虾"};
    //引用数据类型如String、Integer、ArrayList、[]数组等只声明不初始化，系统会默认赋值为null，直接调用对象属性方法会抛出空指针异常
    private ArrayList<String> peoples, persons = new ArrayList<>(), others = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = b;
        b = 3;
        Log.e("TAG", "onCreate基本数据类型: a=" + a + ",,,b=" + b + ",,,,r的默认值=" + r);


        m = n;
        n = 3;
        Log.e("TAG", "onCreate基本数据类型包装而来的引用数据类型: m=" + m + ",,,n=" + n);

        who = name;
        name = "黄秀锋";
        Log.e("TAG", "onCreate基本数据类型包装而来的引用数据类型: who=" + who + ",,,name=" + name);

        anis = animals;
        animals[2] = "罗氏虾";
        Log.e("TAG", "onCreate数组引用数据类型: anis=" + Arrays.toString(anis) + ",,,,animals=" + Arrays.toString(animals));

        persons.add("张三");
        persons.add("李四");
        persons.add("王五");
        peoples = persons;
        //others已经初始化
        others.addAll(peoples);
        persons.set(1, "我不当李四");
        Log.e("TAG", "onCreate集合引用数据类型: peoples=" + peoples.toString() + ",,,,others=" + others.toString() + ",,,,persons=" + persons.toString());

//        DialogUtil.INSTANCE.showDialog(this, "测试", "我来组成内容", view ->
//                Toast.makeText(JavaActivity.this, "hehe", Toast.LENGTH_SHORT).show()
//        );
    }
}