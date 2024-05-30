package com.demircandemir.relaysample.domain.use_cases.post_user_info

import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.UserInfo

class PostUserInfoUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        id: Int,
        name: String,
        image: String,
        goal: String,
        weight: String,
        height: String,
        age: String,
        gender: String,
        exerciseDayInAWeek: String,
        weightGoal: String,
        timeFrame: String,
        diet_type: String
    ) {
        repository.postUserInfo(
            id = id,
            name = name,
            image = image,
            goal = goal,
            weight = weight,
            height = height,
            age = age,
            gender = gender,
            exerciseDayInAWeek = exerciseDayInAWeek,
            weightGoal = weightGoal,
            timeFrame = timeFrame,
            diet_type = diet_type
        )
    }
}