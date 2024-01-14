package com.demircandemir.relaysample.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.demircandemir.relaysample.domain.model.MealInfo

@Dao
interface MealDao {


    @Query("SELECT * FROM meal_table ORDER BY id ASC")
    fun getAllMeals(): PagingSource<Int, MealInfo>


    @Query("SELECT * FROM meal_table WHERE id=:mealId")
    fun getSelectedMeal(mealId: Int): MealInfo
}