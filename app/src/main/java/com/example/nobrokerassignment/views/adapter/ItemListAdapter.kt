package com.example.nobrokerassignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nobrokerassignment.R
import com.example.nobrokerassignment.clickListeners.ItemClickListener
import com.example.nobrokerassignment.databinding.ItemlistItemLayoutBinding
import com.example.nobrokerassignment.room.ItemEntity
import com.example.nobrokerassignment.views.viewholder.ItemListViewHolder

class ItemListAdapter(
    private val itemList: List<ItemEntity>,
    private var context: Context,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<ItemListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemlist_item_layout, parent, false)
        return ItemListViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val itemEntity = itemList[position]
        holder.setData(itemEntity)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}