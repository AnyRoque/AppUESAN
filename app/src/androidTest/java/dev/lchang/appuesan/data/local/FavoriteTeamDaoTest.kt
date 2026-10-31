package dev.lchang.appuesan.data.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteTeamDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: FavoriteTeamDao

    private val alianza = FavoriteTeamEntity(1, "Alianza Lima", "logo1", "Peru", 1901)
    private val universitario = FavoriteTeamEntity(2, "Universitario", "logo2", "Peru", 1924)

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.favoriteTeamDao()
    }

    @After
    fun tearDown() = db.close()

    @Test
    fun insertarYLeer() = runBlocking {
        dao.insert(alianza)
        dao.insert(universitario)
        assertEquals(2, dao.getAll().first().size)
        assertEquals("Alianza Lima", dao.getById(1)?.name)
    }

    @Test
    fun insertarDuplicadoReemplaza() = runBlocking {
        dao.insert(alianza)
        dao.insert(alianza.copy(name = "Club Alianza Lima"))
        val all = dao.getAll().first()
        assertEquals(1, all.size)
        assertEquals("Club Alianza Lima", all[0].name)
    }

    @Test
    fun eliminarPorIdYTodos() = runBlocking {
        dao.insert(alianza)
        dao.insert(universitario)
        dao.deleteById(1)
        assertNull(dao.getById(1))
        assertEquals(listOf(2), dao.getAllIds().first())
        dao.deleteAll()
        assertTrue(dao.getAll().first().isEmpty())
    }
}
