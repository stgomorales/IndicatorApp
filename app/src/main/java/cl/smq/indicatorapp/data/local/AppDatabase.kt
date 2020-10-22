package cl.smq.indicatorapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.smq.indicatorapp.data.entities.Indicator
import cl.smq.indicatorapp.data.entities.IndicatorDetail

@Database(entities = [IndicatorDetail::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun indicatorDetailDao(): IndicatorDetailDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "indicatorDb")
                .fallbackToDestructiveMigration()
                .build()
    }
}