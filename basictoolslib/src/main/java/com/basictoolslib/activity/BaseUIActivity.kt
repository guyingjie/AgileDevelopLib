package com.basictoolslib.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.basictoolslib.R
import kotlinx.android.synthetic.main.activity_base_ui.*

/**
 * AppCompatActivity基类，与业务逻辑无关
 */
abstract class BaseUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //必须调用父类的
        super.setContentView(R.layout.activity_base_ui)

        //在子类的setContentView之前调用,沉浸式状态栏
        steepBeforeContentView()

        ivBack.setOnClickListener { onBackPressed() }
    }

    override fun setContentView(view: View) {
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        flContent.addView(view, params)
    }

    override fun setContentView(layoutResID: Int) {
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        //此种inflate方式防止加载的view布局错位
        val v = layoutInflater.inflate(layoutResID, flContent, false)
        flContent.addView(v, params)
    }


    fun setTitle(content: String?) {
        tvTitle.text = content ?: ""
    }

    fun setMenuTxt(content: String?) {
        tvMenu.text = content ?: ""
    }

    fun setLeftListener(leftListener: View.OnClickListener?) {
        ivBack.setOnClickListener(leftListener)
    }

    fun setRightListener(rightListener: View.OnClickListener?) {
        tvMenu.setOnClickListener(rightListener)
    }


    fun showMenu(isVisible: Boolean) {
        tvMenu.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun steepBeforeContentView() {
        //Android5.0-Android6.0
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //注意要清除 FLAG_TRANSLUCENT_STATUS flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }

        //Android6.0及以上，新增亮色背景重绘状态栏图标字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}