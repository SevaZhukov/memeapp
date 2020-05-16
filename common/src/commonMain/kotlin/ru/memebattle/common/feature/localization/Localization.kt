package ru.memebattle.common.feature.localization

import kotlinx.serialization.Serializable

@Serializable
enum class Localization {

    AUTH_PASSWORD_NOT_VALID_FIELD_ERROR,
    AUTH_LOGIN_NOT_VALID_FIELD_ERROR,
    AUTH_WRONG_LOGIN_ERROR_MESSAGE,
    AUTH_WRONG_PASSWORD_ERROR_MESSAGE,
    AUTH_USER_NOT_REGISTERED_ERROR_MESSAGE,
    AUTH_USER_EXIST_ERROR_MESSAGE,
    AUTH_NETWORK_ERROR_MESSAGE,
    AUTH_LOGIN_HINT_TEXT,
    AUTH_PASSWORD_HINT_TEXT,
    AUTH_SIGN_IN_BUTTON_TEXT,
    AUTH_SIGN_UP_BUTTON_TEXT,

    GAME_WAIT_NEXT_ROUND_TEXT,
    GAME_SHARE_IMAGE_TEXT,
    GAME_SAVE_IMAGE_TEXT,
    GAME_NETWORK_ERROR,
    GAME_RETRY_BUTTON,

    ONBOARDING_TEXT,
    ONBOARDING_TITLE,
    ONBOARDING_OK_BTN_TEXT,

    SETTINGS_LOGOUT_TEXT,
    SETTINGS_RATE_TEXT,
    SETTINGS_SHARE_TEXT,
    SETTINGS_HASH_TAG_TEXT,
    SETTINGS_SIGN_UP_BUTTON,
    SETTINGS_SIGN_IN_BUTTON,

    MAIN_MENU_MEMES,
    MAIN_MENU_RATING,
    MAIN_MENU_SETTINGS,

    ERROR_LOADING_TEXT,
    ERROR_LOADING_BUTTON_TEXT,

    FIRST_WIN_AUTH_TEXT,
    FIRST_WIN_SKIP_TEXT,
    FIRST_WIN_SECOND_TEXT,
    FIRST_WIN_FIRST_TEXT,
    FIRST_WIN_TITLE,

    SHARE_APP_TEXT,
    SHARE_APP_TITLE,

    FATAL_ERROR_DIALOG_TEXT,
    FATAL_ERROR_DIALOG_BTN_TEXT,

    SIGN_OUT_DIALOG_TEXT,
    SIGN_OUT_DIALOG_BUTTON
}