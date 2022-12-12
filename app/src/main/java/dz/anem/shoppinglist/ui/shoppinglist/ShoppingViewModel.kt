package dz.anem.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import dz.anem.shoppinglist.data.db.entities.ShoppingItem
import dz.anem.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    // dispatchers.Main we use it because room provide Main safety
    // dispatchers.IO normally we use it for db operations but, as room provide main safety
    // dispatchers.Default used for very long running and complex operations
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    // does not have to be used in coroutine because it's a read only operation.
    fun getAllShoppingItems() = repository.getAllShoppingItems()
}