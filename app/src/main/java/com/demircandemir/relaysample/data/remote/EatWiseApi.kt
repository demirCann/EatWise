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

    @GET("/meals")
    suspend fun getAllMeals(): MealsResponse

    @GET("/meals/")
    suspend fun getSelectedMeals(
        @Query("meal_id") id: String
    ): MealResponse

    @GET("/meals/search")
    suspend fun searchMeals(
        @Query("meal_name") name: String
    ): MealsResponse


}