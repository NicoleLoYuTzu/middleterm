package com.nicole.middlerterm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nicole.middlerterm.PublishDetail
import com.nicole.middlerterm.databinding.ItemPublishBinding

class MainAdapter: ListAdapter<PublishDetail, MainAdapter.PublishDetailViewHolder>(DiffCallback){
    class PublishDetailViewHolder(private var binding: ItemPublishBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //        fun bind(product: Product, viewModel: DetailViewModel) {
        fun bind(publishDetail: PublishDetail, itemCount: Int) {

            binding.publishDetail = publishDetail
            binding.editTextCategory.text = publishDetail.category
            binding.editTextTitle.text = publishDetail.title
            binding.editTextName.text = publishDetail.author.name
//            binding.editTextTime.text = publishDetail.createdTime.toString()
            binding.editTextContent.text = publishDetail.content

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PublishDetail>() {
        override fun areItemsTheSame(oldItem: PublishDetail, newItem: PublishDetail): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PublishDetail, newItem: PublishDetail): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublishDetailViewHolder {
        return PublishDetailViewHolder(
            ItemPublishBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PublishDetailViewHolder, position: Int) {
        val product = getItem(position)
        product?.let {
            holder.bind(product, itemCount)
        }
    }

}

