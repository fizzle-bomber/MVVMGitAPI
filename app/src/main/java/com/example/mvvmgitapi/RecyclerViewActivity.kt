package com.example.mvvmgitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.mvvmgitapi.network.RetroInstance
import com.example.mvvmgitapi.network.RetroService
import com.example.mvvmgitapi.viewmodels.RecyclerActivityViewmodel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.activity_recycler_view.view.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter:RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initRecylcerView()
        createData()
    }

    private fun initRecylcerView(){
        recyclerView.apply {
           layoutManager= LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun createData(){
//        val item = ArrayList<RecyclerData>()
//
//        item.add(RecyclerData("Java", "Java Description"))
//        item.add(RecyclerData("C++", "Java Description"))
//        item.add(RecyclerData("kotlin", "Java Description"))
//        item.add(RecyclerData("Python", "Java Description"))
//        item.add(RecyclerData("PHP", "Java Description"))
//        item.add(RecyclerData("HTML", "Java Description"))
//        item.add(RecyclerData("GoLang", "Java Description"))
//
//        recyclerViewAdapter.setListData(item)
//        recyclerViewAdapter.notifyDataSetChanged()

        val viewmodel = ViewModelProvider(this).get(RecyclerActivityViewmodel::class.java)
        viewmodel.getRecyclerDataObserver().observe(this , Observer {
            if(it != null) {
                recyclerViewAdapter.setListData(it.items!!)
                recyclerViewAdapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(this , "No Data Found", Toast.LENGTH_SHORT).show()
            }
        })
        searchButton.setOnClickListener {
            //val input = it.searchBox.text.toString()
            searchBox.onEditorAction(EditorInfo.IME_ACTION_DONE)
            viewmodel.makeApiCall(searchBox.text.toString())
        }
    }
}