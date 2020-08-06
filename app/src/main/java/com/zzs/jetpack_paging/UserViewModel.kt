package com.zzs.jetpack_paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * @author: ZhangZhiSheng
 * @date: 2020/7/22 0022 下午 3:37
 * @description:
 */
class UserViewModel : ViewModel() {
    private var users: LiveData<PagedList<User>>

    init {
        //初始化数据工厂
        val factory = UserDataSourceFactory()
        //初始化分页配置
        val config = PagedList.Config.Builder().apply {
            setPageSize(20)             //每页显示条目数量
            setInitialLoadSizeHint(20)  //首次加载条目数量 默认为 pageSize * 3
            setEnablePlaceholders(false)//当item为null是否使用PlaceHolder展示
            setPrefetchDistance(1)      //距离底部多少条数据开始预加载，设置0则表示滑到底部才加载
        }.build()
        users = LivePagedListBuilder<Int, User>(factory, config).build()
    }

    //获取数据
    fun getUsers(): LiveData<PagedList<User>> = users

}
