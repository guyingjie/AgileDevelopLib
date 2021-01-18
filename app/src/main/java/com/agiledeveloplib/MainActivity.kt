package com.agiledeveloplib

import RecyclerCommonAdapter
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.basictoolslib.utils.DialogUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val datas: MutableList<String> by lazy { mutableListOf() }
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        datas.add("顾1")
        datas.add("顾2")
        datas.add("顾3")
        datas.add("顾4")
        datas.add("顾英杰")

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter =
            RecyclerCommonAdapter(this, R.layout.item_my_comments, datas) { holder, _, item ->
                holder.setText(R.id.tv_name, item)
                holder.setItemOnClick {
//                    dialog = DialogUtil.showDialog(this, "测试", "我来组成内容", {
//                        dialog?.cancel()
//                        Toast.makeText(this, "顾英杰", Toast.LENGTH_SHORT).show()
//                    })
                    dialog = DialogUtil.showDialog(this, "测试", "我来组成内容", {
                        dialog?.cancel()
                    }, iconRes = R.mipmap.ic_tip, isOnlyBtn = true)

                }
            }

    }


}