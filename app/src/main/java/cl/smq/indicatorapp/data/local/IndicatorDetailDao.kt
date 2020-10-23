package cl.smq.indicatorapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.smq.indicatorapp.data.entities.IndicatorDetail

@Dao
interface IndicatorDetailDao {

    @Query("SELECT * FROM indicatordetail WHERE code = :code")
    fun getIndicator(code: String): LiveData<IndicatorDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(indicator: IndicatorDetail)
}