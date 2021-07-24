package com.example.nobrokerassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nobrokerassignment.repositories.ItemListRepository
import com.example.nobrokerassignment.room.ItemEntity

class ItemListViewModel(private val itemListRepository: ItemListRepository) : ViewModel() {


    fun getItemList(): LiveData<List<ItemEntity>> {
        return itemListRepository.getAllItem()
    }

    fun addItem(itemListEntity: ItemEntity) {
        itemListRepository.addItem(itemListEntity)
    }


}