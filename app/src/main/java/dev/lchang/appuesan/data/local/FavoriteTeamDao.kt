package dev.lchang.appuesan.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTeamDao {

    @Query("SELECT * FROM favorite_teams ORDER BY saved_at DESC")
    fun getAll(): Flow<List<FavoriteTeamEntity>>

    @Query("SELECT id FROM favorite_teams")
    fun getAllIds(): Flow<List<Int>>

    @Query("SELECT * FROM favorite_teams WHERE id = :id")
    suspend fun getById(id: Int): FavoriteTeamEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: FavoriteTeamEntity)

    @Query("DELETE FROM favorite_teams WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM favorite_teams")
    suspend fun deleteAll()
}
