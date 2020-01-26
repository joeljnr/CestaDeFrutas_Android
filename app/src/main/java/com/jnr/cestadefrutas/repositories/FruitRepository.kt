package com.jnr.cestadefrutas.repositories

import androidx.lifecycle.LiveData
import com.jnr.cestadefrutas.models.Fruit
import com.jnr.cestadefrutas.models.FruitDao

class FruitRepository(private val fruitDao: FruitDao) {
    val allFruits: LiveData<List<Fruit>> = fruitDao.getFruits()

    suspend fun delete(fruit: Fruit) {
        fruitDao.delete(fruit)
    }

    suspend fun insert(fruit: Fruit) {
        fruitDao.insert(fruit)
    }
}