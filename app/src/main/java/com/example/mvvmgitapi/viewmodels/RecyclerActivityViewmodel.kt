package com.example.mvvmgitapi.viewmodels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmgitapi.RecyclerList
import com.example.mvvmgitapi.network.RetroInstance
import com.example.mvvmgitapi.network.RetroService
import retrofit2.Call
import retrofit2.Response

class RecyclerActivityViewmodel:ViewModel() {
    lateinit var recyclerListData : MutableLiveData<RecyclerList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerDataObserver(): MutableLiveData<RecyclerList>{
        return recyclerListData
    }

    fun makeApiCall(input:String){
        val retrofitInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retrofitInstance.getDataFromAPI(input)

        call.enqueue(object :retrofit2.Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful)
                {
//                    recyclerViewAdapter.setListData(response.body()?.items!!)
//                    recyclerViewAdapter.notifyDataSetChanged()
                    recyclerListData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                //Toast.makeText(this@RecyclerViewActivity , "Failed to retrieve ", Toast.LENGTH_SHORT).show()
                recyclerListData.postValue(null)
            }

        })

    }
}