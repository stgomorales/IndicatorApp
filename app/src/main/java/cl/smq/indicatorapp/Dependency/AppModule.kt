package cl.smq.indicatorapp.Dependency

import android.content.Context
import cl.smq.indicatorapp.BuildConfig
import cl.smq.indicatorapp.data.local.AppDatabase
import cl.smq.indicatorapp.data.local.IndicatorDao
import cl.smq.indicatorapp.data.local.IndicatorDetailDao
import cl.smq.indicatorapp.data.remote.IndicatorRemoteDataSource
import cl.smq.indicatorapp.data.remote.IndicatorService
import cl.smq.indicatorapp.data.repository.IndicatorRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideIndicatorService(retrofit: Retrofit): IndicatorService = retrofit.create(IndicatorService::class.java)

    @Singleton
    @Provides
    fun provideIndicatorRemoteDataSource(indicatorService: IndicatorService) = IndicatorRemoteDataSource(indicatorService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideIndicatorDetailDao(db: AppDatabase) = db.indicatorDetailDao()

    @Singleton
    @Provides
    fun provideIndicatorDao(db: AppDatabase) = db.indicatorDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: IndicatorRemoteDataSource,
                          localDetailDataSource: IndicatorDetailDao,
                          localIndicatorDataSource: IndicatorDao) =
        IndicatorRepository(remoteDataSource, localDetailDataSource, localIndicatorDataSource)
}
