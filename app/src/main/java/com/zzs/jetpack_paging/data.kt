package com.zzs.jetpack_paging

/**
 * @author:      ZhangZhiSheng
 * @date:        2020/7/22 0022 下午 3:07
 * @description:
 */
data class User(
    val name: String,
    val age: Int,
    val index: Int = 0
) {
    override fun toString(): String {
        return "User(name='$name', age=$age, index=$index)"
    }
}