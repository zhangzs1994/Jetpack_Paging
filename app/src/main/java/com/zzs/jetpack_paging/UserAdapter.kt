package com.zzs.jetpack_paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @author:      ZhangZhiSheng
 * @date:        2020/7/22 0022 下午 3:46
 * @description: 适配器
 */
class UserAdapter() : PagedListAdapter<User, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        //对数据源返回的数据进行了比较处理, 它的意义是——我需要知道怎么样的比较，
        //才意味着数据源的变化，并根据变化再进行的UI刷新操作
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //绑定数据
        holder.dataBinding.setVariable(BR.user, getItem(position))
    }


    inner class ViewHolder(var dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root)
}