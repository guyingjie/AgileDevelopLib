package com.basictoolslib.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import com.basictoolslib.R

/**
 * 根据状态显示加载中，加载错误，空数据界面
 */
class MultiStateLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    private var mContentView: View? = null  //内容视图
    private var mLoadingView: View? = null  //加载状态视图
    private var mErrorView: View? = null    //加载错误视图
    private var mEmptyView: View? = null    //空数据视图

    private var mViewState = VIEW_STATE_LOADING //默认状态，加载中

    private val mInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(value = [VIEW_STATE_CONTENT, VIEW_STATE_LOADING, VIEW_STATE_ERROR, VIEW_STATE_EMPTY])
    annotation class ViewState

    companion object {
        const val VIEW_STATE_CONTENT = 0
        const val VIEW_STATE_LOADING = 1
        const val VIEW_STATE_ERROR = 2
        const val VIEW_STATE_EMPTY = 3
    }


    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiStateLayout, 0, 0)
        //获取加载视图引用
        val loadingResId = typedArray.getResourceId(R.styleable.MultiStateLayout_loading_view, -1)
        if (loadingResId > -1) {
            mLoadingView = mInflater.inflate(loadingResId, this, false)
            addView(mLoadingView!!, mLoadingView!!.layoutParams)
        }
        //获取错误视图引用
        val errorResId = typedArray.getResourceId(R.styleable.MultiStateLayout_error_view, -1)
        if (errorResId > -1) {
            mErrorView = mInflater.inflate(errorResId, this, false)
            addView(mErrorView!!, mErrorView!!.layoutParams)
        }
        //获取空视图引用
        val emptyResId = typedArray.getResourceId(R.styleable.MultiStateLayout_empty_view, -1)
        if (emptyResId > -1) {
            mEmptyView = mInflater.inflate(emptyResId, this, false)
            addView(mEmptyView!!, mEmptyView!!.layoutParams)
        }
        //获取默认状态
        mViewState = typedArray.getInt(R.styleable.MultiStateLayout_view_state, VIEW_STATE_LOADING)
        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        requireNotNull(mContentView) { "Content view is not defined" }
        setView()
    }

    override fun addView(child: View) {
        if (isValidContentView(child)) {
            mContentView = child
        }
        super.addView(child)
    }

    override fun addView(child: View, index: Int) {
        if (isValidContentView(child)) {
            mContentView = child
        }
        super.addView(child, index)
    }

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams) {
        if (isValidContentView(child)) {
            mContentView = child
        }
        super.addView(child, index, params)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        if (isValidContentView(child)) {
            mContentView = child
        }
        super.addView(child, params)
    }

    override fun addView(child: View, width: Int, height: Int) {
        if (isValidContentView(child)) {
            mContentView = child
        }
        super.addView(child, width, height)
    }

    override fun addViewInLayout(child: View, index: Int, params: ViewGroup.LayoutParams): Boolean {
        if (isValidContentView(child)) {
            mContentView = child
        }
        return super.addViewInLayout(child, index, params)
    }

    override fun addViewInLayout(
        child: View,
        index: Int,
        params: ViewGroup.LayoutParams,
        preventRequestLayout: Boolean
    ): Boolean {
        if (isValidContentView(child)) {
            mContentView = child
        }
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }

    @Nullable
    fun getView(@ViewState viewState: Int): View? {
        return when (viewState) {
            VIEW_STATE_LOADING -> mLoadingView
            VIEW_STATE_ERROR -> mErrorView
            VIEW_STATE_EMPTY -> mEmptyView
            VIEW_STATE_CONTENT -> mContentView
            else -> null
        }
    }

    /**
     * 设置VIEW显示状态,刷新界面
     *
     * @param state state
     */
    @get:ViewState
    var viewState: Int
        get() = mViewState
        set(state) {
            if (mViewState != state) {
                mViewState = state
                setView()
            }
        }

    /**
     * 判断是否是 mContentView
     *
     * @param view v
     * @return boolean
     */
    private fun isValidContentView(view: View): Boolean {
        return if (mContentView != null && mContentView !== view) {
            false
        } else view !== mLoadingView && view !== mErrorView && view !== mEmptyView
    }

    /**
     * 根据不同状态显示不同界面
     */
    private fun setView() {
        when (mViewState) {
            VIEW_STATE_LOADING -> {
                if (mLoadingView == null) {
                    throw NullPointerException("Loading view is a nullPoint")
                }
                mLoadingView!!.visibility = VISIBLE
                if (mErrorView != null) mErrorView!!.visibility = GONE
                if (mEmptyView != null) mEmptyView!!.visibility = GONE
                if (mContentView != null) mContentView!!.visibility = GONE
            }
            VIEW_STATE_ERROR -> {
                if (mErrorView == null) {
                    throw NullPointerException("Error view is a NullPoint")
                }
                mErrorView!!.visibility = VISIBLE
                if (mLoadingView != null) mLoadingView!!.visibility = GONE
                if (mEmptyView != null) mEmptyView!!.visibility = GONE
                if (mContentView != null) mContentView!!.visibility = GONE
            }
            VIEW_STATE_EMPTY -> {
                if (mEmptyView == null) {
                    throw NullPointerException("Empty view is a NullPoint")
                }
                mEmptyView!!.visibility = VISIBLE
                if (mLoadingView != null) mLoadingView!!.visibility = GONE
                if (mErrorView != null) mErrorView!!.visibility = GONE
                if (mContentView != null) mContentView!!.visibility = GONE
            }
            VIEW_STATE_CONTENT -> {
                if (mContentView == null) {
                    throw NullPointerException("Content view is a NullPoint")
                }
                mContentView!!.visibility = VISIBLE
                if (mLoadingView != null) mLoadingView!!.visibility = GONE
                if (mErrorView != null) mErrorView!!.visibility = GONE
                if (mEmptyView != null) mEmptyView!!.visibility = GONE
            }
            else -> {
                if (mContentView == null) {
                    throw NullPointerException("Content view is a NullPoint")
                }
                mContentView!!.visibility = VISIBLE
                if (mLoadingView != null) mLoadingView!!.visibility = GONE
                if (mErrorView != null) mErrorView!!.visibility = GONE
                if (mEmptyView != null) mEmptyView!!.visibility = GONE
            }
        }
    }

    /**
     * 根据状态,在代码中动态替换 mLoadingView,mErrorView,mEmptyView,
     *
     * @param view          view
     * @param state         state
     * @param switchToState 是否切换状态
     */
    private fun setViewForState(view: View?, @ViewState state: Int, switchToState: Boolean) {
        when (state) {
            VIEW_STATE_LOADING -> {
                if (mLoadingView != null) {
                    removeView(mLoadingView)
                }
                mLoadingView = view
                addView(mLoadingView!!)
            }
            VIEW_STATE_ERROR -> {
                if (mErrorView != null) {
                    removeView(mErrorView)
                }
                mErrorView = view
                addView(mErrorView!!)
            }
            VIEW_STATE_EMPTY -> {
                if (mEmptyView != null) {
                    removeView(mEmptyView)
                }
                mEmptyView = view
                addView(mEmptyView!!)
            }
            VIEW_STATE_CONTENT -> {
                if (mContentView != null) {
                    removeView(mContentView)
                }
                mContentView = view
                addView(mContentView!!)
            }
        }
        if (switchToState) {
            viewState = state
        }
    }

    fun setViewForState(view: View?, @ViewState state: Int) {
        setViewForState(view, state, false)
    }

    private fun setViewForState(
        @LayoutRes layoutRes: Int,
        @ViewState state: Int,
        switchToState: Boolean
    ) {
        val view = mInflater.inflate(layoutRes, this, false)
        setViewForState(view, state, switchToState)
    }

    fun setViewForState(@LayoutRes layoutRes: Int, @ViewState state: Int) {
        setViewForState(layoutRes, state, false)
    }


}