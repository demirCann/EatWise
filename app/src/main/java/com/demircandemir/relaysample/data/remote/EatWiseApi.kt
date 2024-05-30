package com.demircandemir.relaysample.data.remote

import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.domain.model.MealsResponse
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.model.UserResponse
import kotlinx.serialization.Serializable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface EatWiseApi {
    @GET("/users/")
    suspend fun getUserInfo(
        @Query("user_id") id: Int = 1,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): UserInfo

    @Headers("Content-Type: application/json")
    @POST("/users/")
    suspend fun postUserInfo(
        @Query("user_id") id: Int,
        @Query("name") name: String,
        @Query("goal") goal: String,
        @Query("weight") weight: String,
        @Query("height") height: String,
        @Query("age") age: String,
        @Query("gender") gender: String,
        @Query("exercise_amount") exerciseDayInAWeek: String,
        @Query("weight_goal") weight_goal: String,
        @Query("time_frame") time_frame: String,
        @Query("diet_type") diet_type: String,
        @Body body: RequestBody = "{}".toRequestBody("application/json".toMediaTypeOrNull()),
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): Response<ResponseBody>

//    @PUT("/diet_plan/")
//    suspend fun updateDietPlan(
//        @Query("user_id") userId: Int,
//        @Query("repast") repast: String,
//        @Body mealsRequest: MealsRequest
//    ): Response<ResponseBody>

    @GET("/meals/page/")
    suspend fun getAllMeals(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): MealsResponse




    @GET("/meals/repast/")
    suspend fun getMealsForSelection(
        @Query("repast") repast: String = "Breakfast",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): MealsResponse

    @GET("/meals/")
    suspend fun getSelectedMeal(
        @Query("meal_id") id: Int,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): MealResponse


    @PUT("/diet_plan/")
    suspend fun updateDietPlan(
        @Query("user_id") userId: Int,
        @Query("repast") repast: String,
        @Body mealsRequest: MealsRequest,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): Response<ResponseBody>



    @GET("/diet_plan/")
    suspend fun getDietPlan(
        @Query("user_id") userId: Int = 1,
        @Query("repast") repast: String,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): MealsResponse2




    @GET("/meals/search/")
    suspend fun searchMeals(
        @Query("meal_name") name: String,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): MealsResponse





    @POST("diet_plan/")
    fun postDietPlan(
        @Query("user_id") userId: Int,
        @Query("repast") repast: String,
        @Query("meals") meals: String,
        @Query("api_key") apiKey: String = "650d30e3c8d88e5a6e38c2db3425406c7800944e",
    ): Call<ResponseBody>





}


@Serializable
data class MealsResponse2(
    val meals: List<MealInfo>
)
