package com.demircandemir.relaysample.data.remote

import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsResponse
import com.demircandemir.relaysample.domain.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EatWiseApi {
    @GET("/users/")
    suspend fun getUserInfo(
        @Query("id") id: String
    ): UserResponse
    @POST("/users/")
    suspend fun postUserInfo(
        @Query("id") id: String
    )
    @GET("/meals/page/")
    suspend fun getAllMeals(
        @Query("page") page: Int = 1
    ): MealsResponse
    @GET("/meals/repast/")
    suspend fun getMealsForSelection(
        @Query("repast") repast: String = "Breakfast",
        @Query("page") page: Int = 1
    ): MealsResponse
    @GET("/meals/")
    suspend fun getSelectedMeal(
        @Query("meal_id") id: Int
    ): MealResponse
    @GET("/meals/search")
    suspend fun searchMeals(
        @Query("meal_name") name: String
    ): MealsResponse
}