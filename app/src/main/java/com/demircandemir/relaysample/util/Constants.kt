package com.demircandemir.relaysample.util

object Constants {
    const val BASE_URL = "https://eatwise-148a03647111.herokuapp.com/"

    const val MEAL_ITEMS_PER_PAGE = 10

    const val EAT_WISE_DATABASE_NAME = "eat_wise_database"
    const val MEAL_DATABASE = "meal_table"
    const val USER_INFO_TABLE = "user_info_table"
    const val MEAL_REMOTE_KEYS_DATABASE = "meal_remote_keys_table"

    const val MEAL_ID_ARGUMENT_KEY = "mealId"

    const val PREFERENCES_NAME = "eatWise_preferences"
    const val PREFERENCES_KEY = "survey_completed"

    // AI Models
    const val GEMINI_1_5_FLASH = "gemini-1.5-flash"
    const val GEMINI_PRO = "gemini-pro"
    const val GEMINI_API_KEY = "AIzaSyB-jQQfGJBtdejHm2I6bEZaNcWw96fMZgI"

    // AI Prompts
    const val AI_SYSTEM_PROMPT = "Sen bir yemek uzmanısın. Sadece yemek tarifleri, malzemeler, besin değerleri ve yemekle ilgili diğer konular hakkında cevap ver. Başka konularla ilgili sorulara yanıt verme."
    const val AI_DEFAULT_IMAGE_PROMPT = "Analyze this image"
    
    // Error Messages
    const val ERROR_EMPTY_RESPONSE_GEMINI = "Empty response from Gemini"
    const val ERROR_EMPTY_RESPONSE_GPT = "Empty response from GPT"
    const val ERROR_GPT_NO_IMAGE = "GPT doesn't support image messages"
    const val ERROR_GENERIC = "An error occurred"
    const val UNAUTHORIZED_ACCESS = "Unauthorized access"
    const val RESOURCE_NOT_FOUND = "Resource not found"
    const val API_CALL_FAILED = "API call failed with code: "
    const val SEND_MESSAGE_FAILED = "Failed to send message"
    const val USER_NAME_FAILED = "Failed to get user name"
    
    // API Flow Error Messages
    const val ERROR_OCCURRED = "An error occurred"
    const val RESPONSE_NULL = "Response is null"
    const val API_REQUEST_LIMIT = "API request limit reached. Please try again later."
    const val UNKNOWN_HTTP_ERROR = "An unknown HTTP error occurred"

    // Firebase
    const val FIREBASE_SERVER_CLIENT_ID = "563764091341-t9a05eghkq37ctf3hv309imodi7um0m0.apps.googleusercontent.com"

    const val SURVEY_CONTENT_ANIMATION_DURATION = 300

    // Chat
    const val MAX_PHOTOS_WARNING = "En fazla 4 fotoğraf ekleyebilirsiniz"
    const val PHOTO_SELECTED = "Fotoğraf seçildi"
    const val PHOTO_LOAD_ERROR = "Fotoğraf yüklenirken bir hata oluştu"
    const val PHOTO_CAPTURED = "Fotoğraf çekildi"
    const val CAMERA_PERMISSION_REQUIRED = "Kamera kullanımı için izin gerekli"
    const val GALLERY_OPEN_ERROR = "Galeri açılırken bir hata oluştu"
    const val MESSAGE_HINT = "Mesajınızı yazın..."
    const val ADD_BUTTON = "Ekle"
    const val DELETE_PHOTO = "Fotoğrafı sil"
    const val CAPTURED_PHOTO = "Çekilen fotoğraf"
    const val CAMERA_BUTTON = "Kamera"
    const val SEND_BUTTON = "Gönder"

    // SignIn
    const val SIGN_IN_SUCCESSFUL = "Sign in successful"
}