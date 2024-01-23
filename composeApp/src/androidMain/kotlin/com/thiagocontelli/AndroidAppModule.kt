package com.thiagocontelli

import AppModule
import DataSource
import DriverFactory
import android.content.Context
import com.example.Database

class AndroidAppModule(
    private val context: Context,
) : AppModule {
    private val db by lazy {
        Database(
            driver = DriverFactory(context).createDriver()
        )
    }

    override fun provideDataSource() = DataSource(db)
}