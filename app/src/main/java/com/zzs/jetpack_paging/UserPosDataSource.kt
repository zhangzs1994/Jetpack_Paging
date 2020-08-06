package com.zzs.jetpack_paging

import android.util.Log
import androidx.paging.PositionalDataSource

/**
 * @author:      ZhangZhiSheng
 * @date:        2020/7/22 0022 下午 3:20
 * @description: 按位置加载，如加载指定从第n条到n+20条
 */
class UserPosDataSource : PositionalDataSource<User>() {

    //初次加载
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<User>) {
        Log.i("UserPosDataSource", "pageSize====${params.pageSize}")
        val result = setData(0, params.pageSize)
        Log.i("UserPosDataSource", "result====${result}")
        callback.onResult(result, 0)
    }

    //分页加载
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<User>) {
        Log.i("UserPosDataSource", "startPosition====${params.startPosition}")
        Log.i("UserPosDataSource", "loadSize====${params.loadSize}")
        val result = setData(params.startPosition, params.loadSize)
        callback.onResult(result)
    }

    //模拟数据
    private fun setData(start: Int, size: Int): List<User> {
        val users = mutableListOf<User>()
        for (i in start until start + size) {
            users.add(User("张三$i", 20))
        }
        return users
    }

}