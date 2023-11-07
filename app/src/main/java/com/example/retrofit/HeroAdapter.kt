package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemHeroBinding
import com.example.retrofit.model.DataHero
import com.squareup.picasso.Picasso

class HeroAdapter(private var listSuperHero: List<DataHero>, private val onClickHero: (DataHero) -> Unit) :
    RecyclerView.Adapter<HeroAdapter.ItemHeroViewHolder>() {

    inner class ItemHeroViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataHero) {
            with(binding) {
                txtIdHero.text = data.idHero.toString()
                txtNamaHero.text = data.titleHero

                // Gunakan library seperti Picasso atau Glide untuk mengunduh dan menampilkan gambar
                Picasso.get()
                    .load(data.imageHero)
                    .error(R.drawable.default_img) // Gambar placeholder jika terjadi kesalahan
                    .into(ImgPahlawan)

                itemView.setOnClickListener {
                    onClickHero(data)
                }
            }
        }
    }

    fun setData(newData: List<DataHero>) {
        listSuperHero = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHeroViewHolder {
        val binding = ItemHeroBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemHeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHeroViewHolder, position: Int) {
        holder.bind(listSuperHero[position])
    }

    override fun getItemCount(): Int = listSuperHero.size
}
