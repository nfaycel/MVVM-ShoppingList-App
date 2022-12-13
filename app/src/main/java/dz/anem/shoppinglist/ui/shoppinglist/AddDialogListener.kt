package dz.anem.shoppinglist.ui.shoppinglist

import dz.anem.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}