package com.jnr.cestadefrutas.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "table_fruit")
class Fruit (
    @PrimaryKey @ColumnInfo(name = "fruit_name") val name: String = ""
)

@Dao
interface FruitDao {

    @Query("select * from table_fruit")
    fun getFruits(): LiveData<List<Fruit>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fruit: Fruit)

    @Delete
    suspend fun delete(fruit: Fruit)

}