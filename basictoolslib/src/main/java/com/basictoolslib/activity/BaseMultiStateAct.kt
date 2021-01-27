package com.basictoolslib.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.basictoolslib.R
import com.basictoolslib.view.MultiStateLayout
import kotlinx.android.synthetic.main.abc_multi_state_layout.*

/**
 * 显示多种状态的Activity(加载中，加载错误，空数据)
 */
@SuppressLint("Registered")
abstract class BaseMultiStateAct : BaseUIActivity() {

    /**
     * 返回内容布局
     * @return LayoutId
     */
    protected abstract fun onCreateContentView(): Int

    /**
     * 内容视图创建完成
     * @contentView 内容视图
     */
    protected abstract fun onContentViewCreated(contentView: View)

    /**
     * 加载出错，点击刷新回调
     */
    protected abstract fun onReload()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.abc_multi_state_layout)

        //加载布局
        val contentView = layoutInflater.inflate(onCreateContentView(), null)
        multiStateLayout.setViewForState(contentView, MultiStateLayout.VIEW_STATE_CONTENT)

        //布局初始化完成
        onContentViewCreated(contentView)

        multiStateLayout.getView(MultiStateLayout.VIEW_STATE_ERROR)?.findViewById<View>(R.id.reload)
            ?.setOnClickListener {
                //切换到加载中，重新加载
                switchLoadingState()
                onReload()
            }

    }

    /**
     * 切换到加载中视图
     */
    protected fun switchLoadingState() {
        if (multiStateLayout!!.viewState != MultiStateLayout.VIEW_STATE_LOADING) {
            multiStateLayout!!.viewState = MultiStateLayout.VIEW_STATE_LOADING
        }
    }

    /**
     * 切换到加载错误视图
     */
    protected fun switchErrorState() {
        if (multiStateLayout!!.viewState != MultiStateLayout.VIEW_STATE_ERROR) {
            multiStateLayout!!.viewState = MultiStateLayout.VIEW_STATE_ERROR
        }
    }

    /**
     * 切换到内容视图
     */
    protected fun switchContentState() {
        if (multiStateLayout!!.viewState != MultiStateLayout.VIEW_STATE_CONTENT) {
            multiStateLayout!!.viewState = MultiStateLayout.VIEW_STATE_CONTENT
        }
    }

    /**
     * 切换到空数据视图
     */
    protected fun switchEmptyState() {
        if (multiStateLayout!!.viewState != MultiStateLayout.VIEW_STATE_EMPTY) {
            multiStateLayout!!.viewState = MultiStateLayout.VIEW_STATE_EMPTY
        }
    }


}