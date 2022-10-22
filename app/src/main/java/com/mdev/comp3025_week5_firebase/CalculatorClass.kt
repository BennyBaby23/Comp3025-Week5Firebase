package com.mdev.comp3025_week5_firebase

import android.widget.TextView

data class CalculatorClass (

    // Instance members of the MainActivity class
    var ResultLabel: TextView? = null,
    var result: Float = 0.0f,
    var lhs: String = "",
    var rhs: String = "",
    var haveLHS: Boolean = false,
    var haveRHS: Boolean = false,
    var operation: String = "",
    var inputReady: Boolean = true

)