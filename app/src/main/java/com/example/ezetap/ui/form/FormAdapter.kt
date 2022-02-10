package com.example.ezetap.ui.form

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ezetap.databinding.ItemButtonBinding
import com.example.ezetap.databinding.ItemEditTextBinding
import com.example.ezetap.databinding.ItemLabelBinding
import com.example.ezetap.model.domain.UiData
import com.example.ezetap.utils.enum.ViewType

class FormAdapter(val clickListener: IFormButtonClickListener): ListAdapter<UiData, RecyclerView.ViewHolder>(DiffUtils) {
    companion object DiffUtils : DiffUtil.ItemCallback<UiData>() {
        override fun areItemsTheSame(
            oldItem: UiData,
            newItem: UiData
        ): Boolean {
            return oldItem.uiType == newItem.uiType
        }

        override fun areContentsTheSame(
            oldItem: UiData,
            newItem: UiData
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class LabelViewHolder(val binding: ItemLabelBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: UiData){
            binding.data = data
            binding.executePendingBindings()
        }
    }

    inner class ButtonViewHolder(val binding: ItemButtonBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: UiData, clickListener: IFormButtonClickListener){
            binding.data = data
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    inner class EditTextViewHolder(val binding: ItemEditTextBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(data: UiData){
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            1 -> LabelViewHolder(ItemLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            2 -> EditTextViewHolder(ItemEditTextBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ButtonViewHolder(ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (holder) {
            is LabelViewHolder -> holder.onBind(getItem(position))
            is EditTextViewHolder -> holder.onBind(getItem(position))
            is ButtonViewHolder -> holder.onBind(getItem(position), clickListener)
            else -> (holder as ButtonViewHolder).onBind(getItem(position), clickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position).uiType){
            ViewType.LABEL -> 1
            ViewType.EDITTEXT -> 2
            ViewType.BUTTON -> 3
        }
//        return super.getItemViewType(position)
    }
}