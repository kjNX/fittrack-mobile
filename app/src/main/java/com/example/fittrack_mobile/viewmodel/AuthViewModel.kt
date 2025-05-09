package com.example.fittrack_mobile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fittrack_mobile.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun signup(name: String, email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val userId = it.result?.user?.uid ?: return@addOnCompleteListener
                    val userModel = UserModel(name, email, userId)

                    firestore.collection("users").document(userId)
                        .set(userModel)
                        .addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                onResult(true, "")
                            } else {
                                onResult(false, "Error al guardar datos del usuario.")
                            }
                        }
                } else {
                    val errorMessage = getFriendlyError(it.exception)
                    onResult(false, errorMessage)
                }
            }
    }

    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    firestore.collection("users").document(userId).get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                onResult(true, "")
                            } else {
                                auth.signOut()
                                onResult(false, "Este usuario no está registrado en NutriHealth.")
                            }
                        }
                        .addOnFailureListener {
                            onResult(false, "No se pudo verificar el usuario en la base de datos.")
                        }
                } else {
                    val errorMessage = getFriendlyError(task.exception)
                    onResult(false, errorMessage)
                }
            }
    }

    // Traduce errores técnicos a mensajes más amigables
    private fun getFriendlyError(exception: Exception?): String {
        return when ((exception as? FirebaseAuthException)?.errorCode) {
            "ERROR_INVALID_EMAIL" -> "El correo electrónico no es válido."
            "ERROR_USER_NOT_FOUND" -> "No existe una cuenta con este correo."
            "ERROR_WRONG_PASSWORD" -> "La contraseña es incorrecta."
            "ERROR_USER_DISABLED" -> "Esta cuenta ha sido desactivada."
            "ERROR_TOO_MANY_REQUESTS" -> "Demasiados intentos fallidos. Inténtalo más tarde."
            else -> exception?.localizedMessage ?: "Error al iniciar sesión."
        }
    }
}
