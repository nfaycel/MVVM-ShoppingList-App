package dz.anem.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import dz.anem.shoppinglist.data.db.entities.ShoppingItem
import dz.anem.shoppinglist.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) { //inherit from appcompatdialog

    private lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(getLayoutInflater());
        setContentView(binding.root)


        with(binding) {
            tvAdd.setOnClickListener {
                val name = etName.text.toString()
                val amount = etAmount.text.toString()

                if (name.isEmpty() || amount.isEmpty()) {
                    Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val item = ShoppingItem(name, amount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }

            tvCancel.setOnClickListener {
                cancel()
            }

        }
    }
}