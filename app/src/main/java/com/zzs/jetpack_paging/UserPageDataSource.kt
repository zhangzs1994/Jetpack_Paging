package com.zzs.jetpack_paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

/**
 * @author:      ZhangZhiSheng
 * @date:        2020/7/23 0023 上午 9:03
 * @description: 按页加载，如请求数据时传入page页码
 */
class UserPageDataSource : PageKeyedDataSource<Int, User>() {

    //初次加载
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        val result = setData(0)
        callback.onResult(result, 0, 1)
    }

    //加载下一页
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val result = setData(params.key)
        callback.onResult(result, params.key + 1)
    }

    //加载上一页
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val result = setData(params.key)
        callback.onResult(result, params.key - 1)
    }

    //模拟数据
    private fun setData(page: Int): List<User> {
        val users = mutableListOf<User>()
        for (i in page * 10 + 1..(page + 1) * 10) {
            users.add(User("张三$i", 20))
        }
        return users
    }
}