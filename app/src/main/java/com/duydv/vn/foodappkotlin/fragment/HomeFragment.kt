package com.duydv.vn.foodappkotlin.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.duydv.vn.foodappkotlin.MyApplication
import com.duydv.vn.foodappkotlin.R
import com.duydv.vn.foodappkotlin.activity.FoodDetailActivity
import com.duydv.vn.foodappkotlin.activity.MainActivity
import com.duydv.vn.foodappkotlin.adapter.FoodAdapter
import com.duydv.vn.foodappkotlin.adapter.FoodPopularAdapter
import com.duydv.vn.foodappkotlin.constant.Constant
import com.duydv.vn.foodappkotlin.constant.GlobalFunction
import com.duydv.vn.foodappkotlin.databinding.FragmentHomeBinding
import com.duydv.vn.foodappkotlin.listener.IOnClickItemFoodListener
import com.duydv.vn.foodappkotlin.model.Food
import com.duydv.vn.foodappkotlin.utils.StringUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeFragment : BaseFragment() {
    private var mFragmentHomeBinding: FragmentHomeBinding? = null
    private var mListFoods : MutableList<Food>? = null
    private var mListFoodPopular : MutableList<Food>? = null
    private val mHandler = Handler(Looper.getMainLooper())
    private val mRunnable = Runnable {
        if(mListFoodPopular == null || mListFoodPopular?.isEmpty() == true){
            return@Runnable
        }

        val current = mFragmentHomeBinding?.viewpager?.currentItem ?: 0
        mFragmentHomeBinding?.viewpager?.currentItem = if(current == mListFoodPopular?.size!! - 1) 0 else current + 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)

        getListFoodFromFirebase("")

        initListener()

        return mFragmentHomeBinding?.root
    }

    private fun initListener(){
        mFragmentHomeBinding?.imgSearch?.setOnClickListener { onClickSearchFood() }

        mFragmentHomeBinding?.edtSearch?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val strSearch = p0.toString()
                if(strSearch == ""){
                    searchFood("")
                    mFragmentHomeBinding?.layoutFoodPopular?.visibility = View.VISIBLE
                    mFragmentHomeBinding?.edtSearch?.setHint(R.string.hint_food)
                }
            }

        })
    }

    private fun getListFoodFromFirebase(key: String) {
        (activity as MainActivity).progressDialog?.show()

        MyApplication.get(activity)?.getFoodReferences()
            ?.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mListFoods = mutableListOf()

                    for(item in snapshot.children){
                        val food = item.getValue(Food::class.java) ?: return

                        if(StringUtils.isEmpty(key)){
                            mListFoods?.add(0,food)
                        }else{
                            if(!StringUtils.isEmpty(food.name) && food.name.lowercase().trim().contains(key.lowercase().trim())){
                                mListFoods?.add(0,food)
                            }
                        }
                    }

                    displayListFood();
                    displayListFoodPopular();
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, R.string.msg_onCancelled,Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun displayListFood() {
        val gridLayoutManager = GridLayoutManager(activity,2)
        mFragmentHomeBinding?.rcvFood?.layoutManager = gridLayoutManager

        val foodAdapter = activity?.let { FoodAdapter(it,mListFoods!!, object : IOnClickItemFoodListener{
            override fun onClickItemFood(food: Food?) {
                goToFoodDetail(food)
            }
        }) }
        mFragmentHomeBinding?.rcvFood?.adapter = foodAdapter
        (activity as MainActivity).progressDialog?.dismiss()
        mFragmentHomeBinding?.layoutContent?.visibility = View.VISIBLE
    }

    private fun getListFoodPopular() : MutableList<Food>{
        mListFoodPopular = mutableListOf()
        if(mListFoods?.isEmpty() == true || mListFoods == null){
            return mListFoodPopular!!
        }

        for(item in mListFoods!!){
            if(item.popular){
                mListFoodPopular?.add(0,item)
            }
        }

        return mListFoodPopular!!
    }

    private fun displayListFoodPopular(){
        val mFoodPopularAdapter = activity?.let { FoodPopularAdapter(it,getListFoodPopular(),object : IOnClickItemFoodListener{
            override fun onClickItemFood(food: Food?) {
                goToFoodDetail(food)
            }
        }) }
        mFragmentHomeBinding?.viewpager?.adapter = mFoodPopularAdapter
        mFragmentHomeBinding?.indicator3?.setViewPager(mFragmentHomeBinding?.viewpager)

        mFragmentHomeBinding?.viewpager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mHandler.removeCallbacks(mRunnable)
                mHandler.postDelayed(mRunnable,3000)
            }
        })
    }

    private fun searchFood(key : String){
        if(mListFoods != null){
            mListFoods?.clear()
        }
        getListFoodFromFirebase(key)
    }

    private fun onClickSearchFood(){
        val strSearchFood = mFragmentHomeBinding?.edtSearch?.text.toString().trim()
        searchFood(strSearchFood)

        if(StringUtils.isEmpty(strSearchFood)){
            mFragmentHomeBinding?.layoutFoodPopular?.visibility = View.VISIBLE
        }else{
            mFragmentHomeBinding?.layoutFoodPopular?.visibility = View.GONE
        }
        activity?.let { GlobalFunction.hideSoftKeyboard(it) }
    }

    private fun goToFoodDetail(food: Food?){
        val intent = Intent(activity,FoodDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(Constant.KEY_OBJECT,food)
        intent.putExtras(bundle)
        activity?.startActivity(intent)
    }

    override fun showToolBar() {
        activity?.let {
            (it as MainActivity).showToolbar(true,"")
        }
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(mRunnable)
    }
    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(mRunnable,3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentHomeBinding = null
    }
}