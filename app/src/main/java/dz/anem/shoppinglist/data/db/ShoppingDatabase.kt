package dz.anem.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dz.anem.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    // companion in kotlin == static in java
    // create an instance of database called singlton
    companion object {
        @Volatile //make db only initialed by one thread at a time.
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // operator fun in combination with invoke, means every instantiation will execute this fun
        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) { // synchronized: only on thread can access this instance, "also means set instance to it after createdb...
                instance ?: createDatabase(context).also { instance = it }
            }

        // fun to instantiate a database.
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db"
            ).build()
    }
}