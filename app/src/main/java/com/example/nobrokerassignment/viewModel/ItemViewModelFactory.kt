package com.example.nobrokerassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nobrokerassignment.repositories.ItemRepository

class ItemViewModelFactory(private val itemListRepository: ItemRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemViewModel(itemListRepository) as T
    }

}