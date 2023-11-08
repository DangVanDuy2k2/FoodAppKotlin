package com.duydv.vn.foodappkotlin.activity

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.constant.Constant
import com.duydv.vn.foodappkotlin.database.FoodDatabase
import com.duydv.vn.foodappkotlin.databinding.ActivityFoodDetailBinding
import com.duydv.vn.foodappkotlin.model.Food
import com.duydv.vn.foodappkotlin.utils.GlideUtils

class FoodDetailActivity : BaseActivity() {
    private lateinit var mActivityFoodDetailBinding: ActivityFoodDetailBinding
    private lateinit var mFood : Food
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityFoodDetailBinding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(mActivityFoodDetailBinding.root)

        showToolbar()
        getDataIntent()
        initListener()

    }

    private fun showToolbar(){
        mActivityFoodDetailBinding.toolbar.txtTitle.text = getString(R.string.food_detail)
        mActivityFoodDetailBinding.toolbar.imgBack.visibility = View.VISIBLE
        mActivityFoodDetailBinding.toolbar.imgCart.visibility = View.VISIBLE
    }

    private fun getDataIntent() {
        val bundle = intent.extras ?: return
        val food = bundle.get(Constant.KEY_OBJECT) as Food
        mFood = food

        GlideUtils.loadUrl(food.image, mActivityFoodDetailBinding.imgFoodDetail)

        mActivityFoodDetailBinding.txtFoodNameDetail.text = food.name

        val strSale = getString(R.string.sale) + " " + food.sale + " " + getString(R.string.percent)
        mActivityFoodDetailBinding.txtSaleDetail.text = strSale

        if(food.sale > 0){
            mActivityFoodDetailBinding.txtOldPriceDetail.visibility = View.VISIBLE
            val strOldPrice = food.price.toString() + " " + getString(R.string.food_vnd)
            mActivityFoodDetailBinding.txtOldPriceDetail.text = strOldPrice
            mActivityFoodDetailBinding.txtOldPriceDetail.paintFlags = mActivityFoodDetailBinding.txtOldPriceDetail.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            mActivityFoodDetailBinding.txtOldPriceDetail.visibility = View.GONE
        }

        val strRealPrice = food.getRealPrice().toString() + " " + getString(R.string.food_vnd)
        mActivityFoodDetailBinding.txtRealPriceDetail.text = strRealPrice

        mActivityFoodDetailBinding.txtDescription.text = food.description
    }

    private fun initListener(){
        mActivityFoodDetailBinding.toolbar.imgBack.setOnClickListener { onBackPressed() }

        mActivityFoodDetailBinding.txtAddToCart.setOnClickListener { onClickAddToCart() }
    }

    private fun onClickAddToCart(){
        FoodDatabase.getInstance(this).foodDAO().insertFood(mFood)
    }
}