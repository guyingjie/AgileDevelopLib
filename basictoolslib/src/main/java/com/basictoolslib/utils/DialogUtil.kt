package com.basictoolslib.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.basictoolslib.R


object DialogUtil {

    /**
     * 默认两个按钮，默认左边取消右边确认
     * @isOnlyBtn 设置是否自有一个底部按钮
     * @iconRes 标题左边是否带小图标
     * @isCanceledOnTouchOutside 触摸弹框外区域能否取消
     * @isCancelable 返回键能否取消
     *
     * @JvmOverloads 注解实现Java以方法重载的方式兼容默认参数，注意Java中不支持命名参数的赋值方式
    */
    @JvmOverloads
    fun showDialog(
        context: Context?,
        title: String,
        content: String,
        listener: View.OnClickListener,
        yesText: String = "确认",
        noText: String = "取消",
        iconRes: Int = 0,
        isOnlyBtn: Boolean = false,
        isCanceledOnTouchOutside: Boolean = false,
        isCancelable: Boolean = true
    ): Dialog? {
        context?.let {
            if (it is Activity && it.isFinishing) {
                return null
            } else {
                val dialog = AlertDialog.Builder(it).create()
                dialog.window?.setGravity(Gravity.CENTER_VERTICAL)
                dialog.show()
                //必须先show才能使用setContentView关联布局；否则setView方法加载可在show之前使用
                dialog.setContentView(R.layout.dlg_layout)

                val tvTitle = dialog.findViewById<TextView>(R.id.tv_title)
                val tvContent = dialog.findViewById<TextView>(R.id.tv_content)
                val tvNo = dialog.findViewById<TextView>(R.id.tv_no)
                val tvYes = dialog.findViewById<TextView>(R.id.tv_yes)
                val iconTip = dialog.findViewById<ImageView>(R.id.iv_tip)

                if (iconRes != 0) {
                    iconTip?.visibility = View.VISIBLE
                    iconTip?.setImageResource(iconRes)
                } else {
                    iconTip?.visibility = View.GONE
                }

                tvTitle?.text = title
                tvNo?.text = noText
                tvYes?.text = yesText
                // 设置gravity  一行就center  否则center|left
                tvContent?.text = content
                tvContent?.post {
                    if (tvContent.lineCount <= 1) {
                        tvContent.gravity = Gravity.CENTER
                    } else {
                        tvContent.gravity = Gravity.CENTER or Gravity.START
                    }
                }

                if (isOnlyBtn) {
                    tvNo?.visibility = View.GONE
                    dialog.findViewById<View>(R.id.line2)?.visibility = View.GONE
                } else {
                    tvNo?.visibility = View.VISIBLE
                    dialog.findViewById<View>(R.id.line2)?.visibility = View.VISIBLE
                    tvNo?.setOnClickListener { dialog.cancel() }
                }

                tvYes?.setOnClickListener(listener)

                dialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside)
                dialog.setCancelable(isCancelable)

                return dialog
            }
        }
        return null
    }


}