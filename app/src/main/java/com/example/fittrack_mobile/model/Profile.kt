package com.example.fittrack_mobile.model

data class UserProfile(
    val name: String,
    val email: String,
    val avatarRes: Int,
    val currentWeight: Int,
    val goalWeight: Int,
    val progressPercent: Int
)

data class Milestone(
    val title: String,
    val status: MilestoneStatus,
    val description: String = ""
)

enum class MilestoneStatus {
    COMPLETED,
    IN_PROGRESS,
    LOCKED
}

data class NutritionPlan(
    val dailyCalories: Int,
    val proteinGrams: Int,
    val carbsGrams: Int,
    val fatGrams: Int
)

data class Preferences(
    val useMetric: Boolean,
    val notificationsEnabled: Boolean
)
