package rs.raf.ispit.djordje_veljkovic_rn4615.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.ispit.djordje_veljkovic_rn4615.data.models.weather.WeatherEntity

@Dao
abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: WeatherEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<WeatherEntity>): Completable

    @Query("DELETE FROM weather")
    abstract fun  deleteAll()

//    @Query("SELECT * FROM weather")
//    abstract fun findAll(): Observable<List<WeatherEntity>>
    @Query("SELECT * FROM weather")
    abstract fun findAll(): Observable<List<WeatherEntity>>

    @Query("DELETE FROM weather WHERE city LIKE :city")
    abstract fun deleteByCity(city: String)

    @Transaction
    open fun deleteAndInsertAll(entities: List<WeatherEntity>) {
//        deleteAll()
        deleteByCity(entities[0].city)
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM weather WHERE city LIKE :city")
    abstract fun filterWeather(city:String):Observable<List<WeatherEntity>>
}