package com.jnr.cestadefrutas.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jnr.cestadefrutas.models.Fruit
import com.jnr.cestadefrutas.repositories.FruitRepository
import com.jnr.cestadefrutas.utils.FruitRoomDatabase
import kotlinx.coroutines.launch

class FruitsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FruitRepository
    val allFruits: LiveData<List<Fruit>>

    init {

        val fruitDao = FruitRoomDatabase.getDatabase(application).fruitDao()
        repository = FruitRepository(fruitDao)
        allFruits = repository.allFruits
    }

    fun delete(fruit: Fruit) = viewModelScope.launch {
        repository.delete(fruit)
    }

    fun insert(fruit: Fruit) = viewModelScope.launch {
        repository.insert(fruit)
    }
}