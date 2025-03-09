package com.demircandemir.relaysample.domain.use_cases.post_user_info

import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import javax.inject.Inject

class PostUserInfoToRemoteUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
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
        firebaseRepository.postUserInfoToRemote(
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