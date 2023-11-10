package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemHeroBinding
import com.example.retrofit.model.DataHero
import com.squareup.picasso.Picasso

class HeroAdapter(private var listSuperHero: List<DataHero>) :
    RecyclerView.Adapter<HeroAdapter.ItemHeroViewHolder>() {

    //tambah buat internet
    inner class ItemHeroViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //binding data dengan tampilan
        fun bind(data: DataHero) {
            with(binding) {
                txtIdHero.text = data.idHero.toString()
                txtNamaHero.text = data.titleHero

                //library Picasso atau Glide untuk mengunduh dan menampilkan gambar
                Picasso.get()
                    .load(data.imageHero)
                    .error(R.drawable.default_img) // cadangan gambar
                    .into(ImgPahlawan)
            }
        }
    }

    //buat adaptor set renew data baru tiap recycle view dari retrofit gitu
    fun setData(newData: List<DataHero>) {
        listSuperHero = newData
        notifyDataSetChanged()
    }

    //kembaliin data itemHeroBinding di atas biar bisa ditampung sama adaptor recycle view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHeroViewHolder {
        val binding = ItemHeroBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemHeroViewHolder(binding)
    }

    //binding data dari list sumbernya
    override fun onBindViewHolder(holder: ItemHeroViewHolder, position: Int) {
        holder.bind(listSuperHero[position])
    }

    //untuk cek jumlah total item nya
    override fun getItemCount(): Int = listSuperHero.size
}
