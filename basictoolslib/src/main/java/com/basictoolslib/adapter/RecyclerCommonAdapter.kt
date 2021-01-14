
import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by Gu on 2018/10/22
 *
 * 列表视图通用适配器
 * 将具体业务中会变化的三类要素抽取出来，作为外部传进来的变量。
 * 这三类要素包括：布局文件对应的资源编号、列表项的数据结构、各个控件对象的初始化操作(高阶函数负责展示，低阶函数负责具体实现)
 */


class RecyclerCommonAdapter<T>(
        private val mContext: Context,
        private val layoutId: Int,
        private val items: List<T>,
        private val init: (ItemHolder<T>, Int, T) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(layoutId, parent, false)
        return ItemHolder<T>(mContext, view, init)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: ItemHolder<T> = holder as ItemHolder<T>
        vh.bind(holder, position, items[position])
    }


}