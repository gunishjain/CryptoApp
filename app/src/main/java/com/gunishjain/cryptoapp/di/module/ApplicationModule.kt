package com.gunishjain.cryptoapp.di.module

import android.content.Context
import androidx.room.Room
import com.gunishjain.cryptoapp.data.api.NetworkService
import com.gunishjain.cryptoapp.data.db.CoinDatabase
import com.gunishjain.cryptoapp.di.BaseUrl
import com.gunishjain.cryptoapp.utils.AppConstant
import com.gunishjain.cryptoapp.utils.AppConstant.COIN_DB_NAME
import com.gunishjain.cryptoapp.utils.DefaultDispatcherProvider
import com.gunishjain.cryptoapp.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstant.BASE_URL

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }


    @Provides
    @Singleton
    fun provideCoinsDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        CoinDatabase::class.java,
        COIN_DB_NAME,
    ).build()

    @Singleton
    @Provides
    fun getCoinsDao(db:CoinDatabase ) =  db.getCoinsDao()

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()


}