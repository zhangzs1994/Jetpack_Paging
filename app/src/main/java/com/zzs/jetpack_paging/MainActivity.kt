package com.zzs.jetpack_paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzs.jetpack_paging.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化视图
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        //绑定监听
        binding.myHandles = MyHandles()

        //初始化viewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        //初始化列表
        binding.rvView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter()
        binding.rvView.adapter = adapter

        //通过viewModel拿到数据并提交数据
        viewModel.getUsers().observe(this, Observer {
            adapter!!.submitList(it)
        })

        //下拉刷新
        binding.srlLayout.setOnRefreshListener {
            srl_layout.isRefreshing = false
            viewModel.getUsers().value?.dataSource?.invalidate()
        }
    }

    inner class MyHandles{
        //主动点击刷新
        fun refresh(view: View) {
            Log.i("refresh","---------刷新")
            adapter!!.submitList(null)
            viewModel.getUsers().value?.dataSource?.invalidate()
        }
    }
}
