package com.donovanSergeAimenHatim.uniroute.animation

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

class anim {
    fun createFadeInAnimation(): Animation {
        return AlphaAnimation(0.0f, 1.0f).apply {
            duration = 300 // Durée en millisecondes
        }
    }

    fun createFadeOutAnimation(): Animation {
        return AlphaAnimation(1.0f, 0.0f).apply {
            duration = 300 // Durée en millisecondes
        }
    }

    fun createSlideInAnimation(): Animation {
        return TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f).apply {
            duration = 300 // Durée en millisecondes
        }
    }

    fun createSlideOutAnimation(): Animation {
        return TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f).apply {
            duration = 300 // Durée en millisecondes
        }
    }

}