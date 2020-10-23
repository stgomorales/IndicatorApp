package cl.smq.indicatorapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.smq.indicatorapp.data.entities.Indicator

@Dao
interface IndicatorDao {

    @Query("SELECT * FROM indicator limit 1")
    fun getIndicators() : LiveData<Indicator>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(indicator: Indicator)

}