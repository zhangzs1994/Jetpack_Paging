package com.zzs.jetpack_paging

import androidx.paging.ItemKeyedDataSource

/**
 * @author:      ZhangZhiSheng
 * @date:        2020/7/23 0023 上午 9:55
 * @description: 按条目加载，即请求数据需要传入其它item的信息，如加载第n+1项的数据需传入第n项的id
 */
class UserItemDataSource : ItemKeyedDataSource<Int, User>() {
    //初次加载
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<User>) {
        val result = setData(0)
        callback.onResult(result, 0, 20)
    }

    //滑到底部加载数据
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<User>) {
        val result = setData(params.key)
        callback.onResult(result)
    }

    //滑倒顶部加载数据
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<User>) {
        val result = setData(params.key)
        callback.onResult(result)
    }

    //获取key
    override fun getKey(item: User): Int {
        return item.index
    }

    //模拟数据
    private fun setData(key: Int): List<User> {
        val users = mutableListOf<User>()
        for (i in key + 1..(key + 1) + 10) {
            users.add(User("张三$i", 20, i))
        }
        return users
    }

}