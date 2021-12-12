package com.example.mvvmgitapi

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setListData(data: ArrayList<RecyclerData>)
    {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int {
        return items.size
    }

     class MyViewHolder(view : View): RecyclerView.ViewHolder(view){

         val tvTitle = view.tvTitle
         val tvDescription = view.tvDescription
         val imgview: ImageView? = view.imageThumb
         fun bind(data: RecyclerData)
         {
             tvTitle.text = data.name
             if(!TextUtils.isEmpty(data.description))
             tvDescription.text = data.description
             else
                 tvDescription.text = "No Description found"
             val url = data.owner.avatar_url
             Glide.with(imgview!!)
                 .load(url)
                 .circleCrop()
                 .placeholder(R.drawable.default_thumb)
                 .error(R.drawable.default_thumb)
                 .fallback(R.drawable.default_thumb)
                 .into(imgview)
         }
     }
}