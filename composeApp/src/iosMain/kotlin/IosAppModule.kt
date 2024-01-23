import com.example.Database

class IosAppModule : AppModule {
    private val db by lazy {
        Database(
            driver = DriverFactory().createDriver()
        )
    }

    override fun provideDataSource() = DataSource(db)
}