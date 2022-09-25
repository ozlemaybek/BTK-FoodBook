package com.ozlem.foodbookkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozlem.foodbookkotlin.R
import com.ozlem.foodbookkotlin.viewmodel.FoodDetailViewModel
import com.ozlem.foodbookkotlin.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.food_recycler_row.*
import kotlinx.android.synthetic.main.fragment_food_detail.*

class FoodDetailFragment : Fragment() {

    // Bir FoodDetailViewModel view model oluşturalım:
    private lateinit var viewModel : FoodDetailViewModel

    // Defining:
    private var foodID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    // Biz ekledik:
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // providersOf kullanarak başlatalım:
        viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
        viewModel.getRoomData()

        // argument'ları alalım:
        // argument'lar varsa bize it içerisinde nullable olmadan veriliyor:
        arguments?.let {
            // FoodDetailFragmentArgs: navigation framework'ünün bizim için otomatik olarak oluşturduğu sınıf
            // fromBundle: Hangi bohçadan alacak (Bundle bohça/demet demek)
            foodID = FoodDetailFragmentArgs.fromBundle(it).foodArgumentID
            println(foodID)
        }

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner , Observer{ Food->
            Food?.let{
                foodName_id.text = it.name
                foodCalorie_id.text = it.calorie
                foodCarbohydrate_id.text = it.carbohydrate
                foodOil_id.text = it.oil
                foodProtein_id.text = it.protein
            }
        })
    }
}