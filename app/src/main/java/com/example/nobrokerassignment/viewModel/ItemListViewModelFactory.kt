package com.example.nobrokerassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nobrokerassignment.repositories.ItemListRepository

class ItemListViewModelFactory(private val itemListRepository: ItemListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemListViewModel(itemListRepository) as T
    }

}