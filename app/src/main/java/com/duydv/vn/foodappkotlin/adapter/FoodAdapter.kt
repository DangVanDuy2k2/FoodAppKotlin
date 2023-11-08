package com.duydv.vn.foodappkotlin.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.databinding.ItemFoodBinding
import com.duydv.vn.foodappkotlin.listener.IOnClickItemFoodListener
import com.duydv.vn.foodappkotlin.model.Food
import com.duydv.vn.foodappkotlin.utils.GlideUtils

class FoodAdapter(private val context : Context, private val mListFoods : List<Food>,
                  private val mIOnClickItemFoodListener: IOnClickItemFoodListener) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(val mItemFoodBinding: ItemFoodBinding) : ViewHolder(mItemFoodBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val mItemFoodBinding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodViewHolder(mItemFoodBinding)
    }

    override fun getItemCount(): Int {
        return mListFoods.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = mListFoods[position]

        holder.mItemFoodBinding.txtFoodName.text = food.name
        GlideUtils.loadUrl(food.image,holder.mItemFoodBinding.imgFood)

        val strSale = context.getString(R.string.sale) + " " + food.sale + " " + context.getString(R.string.percent)
        holder.mItemFoodBinding.txtSale.text = strSale

        if(food.sale > 0){
            holder.mItemFoodBinding.txtOldPrice.visibility = View.VISIBLE
            val strOldPrice = food.price.toString() +  " " + context.getString(R.string.food_vnd)
            holder.mItemFoodBinding.txtOldPrice.text = strOldPrice
            holder.mItemFoodBinding.txtOldPrice.paintFlags = holder.mItemFoodBinding.txtOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            holder.mItemFoodBinding.txtOldPrice.visibility = View.GONE
        }

        val strRealPrice = food.getRealPrice().toString() + " " + context.getString(R.string.food_vnd)
        holder.mItemFoodBinding.txtRealPrice.text = strRealPrice

        holder.mItemFoodBinding.imgFood.setOnClickListener { mIOnClickItemFoodListener.onClickItemFood(food) }
    }
}