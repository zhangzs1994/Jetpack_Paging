package com.zzs.jetpack_paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


/**
 * @author:      ZhangZhiSheng
 * @date:        2020/7/22 0022 下午 3:33
 * @description:
 */
class UserDataSourceFactory : DataSource.Factory<Int, User>() {

    override fun create(): DataSource<Int, User> {
        return UserPosDataSource()
        //return UserPageDataSource()
        //return UserItemDataSource()
    }

}