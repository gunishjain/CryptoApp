package com.gunishjain.cryptoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gunishjain.cryptoapp.data.db.dao.CoinsDao
import com.gunishjain.cryptoapp.data.models.Coin


@Database(
    entities = [Coin::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun getCoinsDao() : CoinsDao
}