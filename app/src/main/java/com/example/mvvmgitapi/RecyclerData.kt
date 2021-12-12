package com.example.mvvmgitapi


data class RecyclerData(val name: String , val description:String, val owner: Owner)
data class RecyclerList(val items: ArrayList<RecyclerData>)
data class Owner(val avatar_url: String)