package com.example.fittrack_mobile.data

import com.example.fittrack_mobile.R
import com.example.fittrack_mobile.model.Activity
import com.example.fittrack_mobile.model.Contact
import com.example.fittrack_mobile.model.Dish
import com.example.fittrack_mobile.model.Food
import kotlin.time.Duration

val activityList = listOf(
    Activity(
        title = "Carrera 5k",
        description = "Entrenamiento inicial",
        time = Duration.parse("30m")
    ),
    Activity(
        title = "Carrera 10k",
        description = "Entrenamiento básico",
        time = Duration.parse("60m")
    ),
    Activity(
        title = "Carrera 15k",
        description = "Entrenamiento intermedio",
        time = Duration.parse("90m")
    ),
    Activity(
        title = "Carrera 20k",
        description = "Entrenamiento avanzado",
        time = Duration.parse("120m")
    ),
    Activity(
        title = "Carrera 40k",
        description = "Maratón",
        time = Duration.parse("240m")
    )
)

val foodList = listOf(
    Food(
        name = "Pizza",
        calories = 300,
        protein = 100,
        carbs = 100,
        fat = 100
    ),
    Food(
        name = "Hamburguesa",
        calories = 250,
        protein = 100,
        carbs = 100,
        fat = 100
    ),
    Food(
        name = "Ensalada",
        calories = 100,
        protein = 100,
        carbs = 100,
        fat = 100
    )
)

val dishHistory = listOf(
    Dish(foodList[0], 2),
    Dish(foodList[1], 1),
    Dish(foodList[2], 3)
)

val contactList = listOf(
    Contact(
        image = R.drawable.ic_launcher_foreground,
        name = "Dra. Maria Lopez",
        role = "Nutricionista"
    ),
    Contact(
        image = R.drawable.ic_launcher_foreground,
        name = "Carlos Rodriguez",
        role = "Entrenador personal"
    )
)