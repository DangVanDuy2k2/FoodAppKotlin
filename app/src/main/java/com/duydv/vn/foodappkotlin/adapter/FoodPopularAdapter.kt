package com.duydv.vn.foodappkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.databinding.ItemFoodPopularBinding
import com.duydv.vn.foodappkotlin.listener.IOnClickItemFoodListener
import com.duydv.vn.foodappkotlin.model.Food
import com.duydv.vn.foodappkotlin.utils.GlideUtils

class FoodPopularAdapter(private val context : Context, private val mListFoodPopular : MutableList<Food>,
                         private val mIOnClickItemFoodListener: IOnClickItemFoodListener) : RecyclerView.Adapter<FoodPopularAdapter.FoodPopularViewHolder>() {
    class FoodPopularViewHolder(val mItemFoodPopularBinding: ItemFoodPopularBinding) : ViewHolder(mItemFoodPopularBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodPopularViewHolder {
        val mItemFoodPopularBinding = ItemFoodPopularBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodPopularViewHolder(mItemFoodPopularBinding)
    }

    override fun getItemCount(): Int {
        return mListFoodPopular.size
    }

    override fun onBindViewHolder(holder: FoodPopularViewHolder, position: Int) {
        val foodPopular = mListFoodPopular[position]

        GlideUtils.loadUrl(foodPopular.image,holder.mItemFoodPopularBinding.imgFoodPopular)

        val strSale = context.getString(R.string.sale) + " " + foodPopular.sale + " " + context.getString(R.string.percent)
        holder.mItemFoodPopularBinding.txtSale.text = strSale

        holder.mItemFoodPopularBinding.itemFoodPopular.setOnClickListener {
            mIOnClickItemFoodListener.onClickItemFood(foodPopular)
        }
    }
}