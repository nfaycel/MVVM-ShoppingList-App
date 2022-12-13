package dz.anem.shoppinglist.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dz.anem.shoppinglist.data.db.entities.ShoppingItem
import dz.anem.shoppinglist.databinding.ShoppingItemBinding
import dz.anem.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        with(holder.binding) {
            with(curShoppingItem) {

                tvName.text = name
                tvAmount.text = "${amount}"

                ivDelete.setOnClickListener {
                    viewModel.delete(curShoppingItem)
                }
                ivPlus.setOnClickListener {
                    amount++
                    viewModel.upsert(this)
                }
                ivMinus.setOnClickListener {
                    if (amount > 0) {
                        amount--
                        viewModel.upsert(this)
                    }
                }

            }
        }
    }

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}