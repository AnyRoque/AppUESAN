package dev.lchang.appuesan.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.lchang.appuesan.data.remote.apifootball.Team

// Tabla SQLite: favorite_teams
@Entity(tableName = "favorite_teams")
data class FavoriteTeamEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val logo: String,
    val country: String,
    val founded: Int?,
    @ColumnInfo(name = "saved_at") val savedAt: Long = System.currentTimeMillis()
)

fun Team.toEntity() = FavoriteTeamEntity(
    id = id,
    name = name,
    logo = logo,
    country = country,
    founded = founded
)
