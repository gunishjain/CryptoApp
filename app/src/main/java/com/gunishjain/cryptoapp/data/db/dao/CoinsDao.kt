package com.gunishjain.cryptoapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gunishjain.cryptoapp.data.models.Coin


@Dao
interface CoinsDao {

    @Query("SELECT * FROM coins WHERE name LIKE :searchQuery")
    suspend fun searchCoinsByName(searchQuery: String) : List<Coin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<Coin>)



}