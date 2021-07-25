package com.example.nobrokerassignment.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nobrokerassignment.repositories.ItemRepository
import com.example.nobrokerassignment.room.ItemEntity

/**
 * pass the repository through the constructor so that we can get the methods that are called in
 * the repository and extend with viewmodel class.
 */
class ItemViewModel(private val itemListRepository: ItemRepository) : ViewModel() {


    fun getItemList(): LiveData<List<ItemEntity>> {
        return itemListRepository.getAllItem()
    }

    fun getAllList(context: Context){
        itemListRepository.getItemList(context)
    }

    fun addItem(itemListEntity: ItemEntity) {
        itemListRepository.addItem(itemListEntity)
    }

    fun getSearchItem(search : String): LiveData<List<ItemEntity>> {
        return itemListRepository.getSearchItem(search)
    }


}