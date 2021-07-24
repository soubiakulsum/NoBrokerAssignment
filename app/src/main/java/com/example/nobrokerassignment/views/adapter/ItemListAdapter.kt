package com.example.nobrokerassignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nobrokerassignment.ItemListModelItem
import com.example.nobrokerassignment.R
import com.example.nobrokerassignment.databinding.ItemlistItemLayoutBinding
import com.example.nobrokerassignment.views.viewholder.ItemListViewHolder

class ItemListAdapter(private val itemList: List<ItemListModelItem>, private var context: Context) :
    RecyclerView.Adapter<ItemListAdapter.ItemListViewHolder>() {

    inner class ItemListViewHolder(val binding: ItemlistItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val view =
            ItemlistItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        with(holder) {
            with(itemList[position]) {
                binding.tvTitle.text = this.title
                binding.tvDesc.text = this.subTitle
                Glide.with(context).load(image).into(binding.ivItemImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}