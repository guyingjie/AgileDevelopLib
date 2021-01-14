
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.View
import android.widget.Checkable
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by Gu
 * on 2020/11/26
 * 注意init是个函数形式的输入参数
 */

class ItemHolder<T>(private val context: Context, private val convertView: View, val init: (ItemHolder<T>, Int, T) -> Unit) : RecyclerView.ViewHolder(convertView) {

    private val mViews: SparseArray<View?> = SparseArray()


    fun bind(holder: ItemHolder<T>, position: Int, item: T) {
        init(holder, position, item)
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    fun <T : View> getView(viewId: Int): T {
        var view = mViews[viewId]
        if (view == null) {
            view = convertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }


    /****以下为辅助方法 */
    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    fun setText(viewId: Int, text: String?): ItemHolder<T> {
        text?.let {
            val tv = getView<TextView>(viewId)
            tv.text = it
        }

        return this
    }

    fun setImageResource(viewId: Int, resId: Int): ItemHolder<T> {
        val view = getView<ImageView>(viewId)
        view.setImageResource(resId)
        return this
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): ItemHolder<T> {
        bitmap?.let {
            val view = getView<ImageView>(viewId)
            view.setImageBitmap(it)
        }

        return this
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable?): ItemHolder<T> {
        drawable?.let {
            val view = getView<ImageView>(viewId)
            view.setImageDrawable(it)
        }

        return this
    }

    fun setBackgroundColor(viewId: Int, color: Int): ItemHolder<T> {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(viewId: Int, backgroundRes: Int): ItemHolder<T> {
        val view = getView<View>(viewId)
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextColor(viewId: Int, textColor: Int): ItemHolder<T> {
        val tv = getView<TextView>(viewId)
        tv.setTextColor(textColor)
        return this
    }

    fun setTextColorRes(viewId: Int, textColorRes: Int): ItemHolder<T> {
        val tv = getView<TextView>(viewId)
        tv.setTextColor(context.resources.getColor(textColorRes))
        return this
    }


    fun setVisible(viewId: Int, visible: Boolean): ItemHolder<T> {
        val view = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }


    fun setRating(viewId: Int, rating: Float): ItemHolder<T> {
        val rbView = getView<RatingBar>(viewId)
        rbView.rating = rating
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): ItemHolder<T> {
        val rbView = getView<RatingBar>(viewId)
        rbView.max = max
        rbView.rating = rating
        return this
    }

    fun setTag(viewId: Int, tag: Any?): ItemHolder<T> {
        tag?.let {
            val view = getView<View>(viewId)
            view.tag = it
        }

        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any?): ItemHolder<T> {
        tag?.let {
            val view = getView<View>(viewId)
            view.setTag(key, it)
        }

        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): ItemHolder<T> {
        val ckView = getView<View>(viewId) as Checkable
        ckView.isChecked = checked
        return this
    }


    /**
     * 关于事件的点击
     */

    fun setItemOnClick(listener: View.OnClickListener): ItemHolder<T> {
        convertView.setOnClickListener(listener)
        return this
    }

    fun setItemOnLongClick(listener: View.OnLongClickListener): ItemHolder<T> {
        convertView.setOnLongClickListener(listener)
        return this
    }

    fun setItemOnTouch(listener: View.OnTouchListener): ItemHolder<T> {
        convertView.setOnTouchListener(listener)
        return this
    }


    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): ItemHolder<T> {
        getView<View>(viewId).setOnClickListener(listener)
        return this
    }

    fun setOnLongClickListener(viewId: Int, listener: View.OnLongClickListener): ItemHolder<T> {
        getView<View>(viewId).setOnLongClickListener(listener)
        return this
    }

    fun setOnTouchListener(viewId: Int, listener: View.OnTouchListener): ItemHolder<T> {
        getView<View>(viewId).setOnTouchListener(listener)
        return this
    }

}
