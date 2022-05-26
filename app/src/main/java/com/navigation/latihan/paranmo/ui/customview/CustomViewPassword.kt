package com.navigation.latihan.paranmo.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.navigation.latihan.paranmo.R

class CustomViewPassword : TextInputEditText, View.OnTouchListener {

    private lateinit var passwordIcon: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        showPasswordButton()
        setBackgroundResource(R.drawable.stroke)
        textSize = 15f
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        passwordIcon = ContextCompat.getDrawable(context, R.drawable.ic_eye_off) as Drawable // x button

        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do nothing.
                if (s.toString().length < 6) showErrorPassword()
            }

            override fun afterTextChanged(s: Editable) {
                // check input

            }
        })
    }

    private fun showErrorPassword() {
        error = context.getString(R.string.failed_password)
    }

    private fun showPasswordButton() {
        setButtonDrawables(endOfTheText = passwordIcon)
    }

    private fun hideEyeButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val eyeButtonStart: Float
            val eyeButtonEnd: Float
            var isEyeButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                eyeButtonEnd = (passwordIcon.intrinsicWidth + paddingStart).toFloat()
                if (event.x < eyeButtonEnd) isEyeButtonClicked = true
            } else {
                eyeButtonStart = (width - paddingEnd - passwordIcon.intrinsicWidth).toFloat()
                if (event.x > eyeButtonStart) isEyeButtonClicked = true
            }

            if (isEyeButtonClicked) {
                return when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        hideEyeButton()
                        if (transformationMethod.equals(HideReturnsTransformationMethod.getInstance())) {
                            transformationMethod = PasswordTransformationMethod.getInstance() // hide password
                            passwordIcon = ContextCompat.getDrawable(context, R.drawable.ic_eye_off) as Drawable
                            showPasswordButton()
                        } else {
                            transformationMethod = HideReturnsTransformationMethod.getInstance() // show password
                            passwordIcon = ContextCompat.getDrawable(context, R.drawable.ic_eye) as Drawable
                            showPasswordButton()
                        }
                        true
                    }
                    else -> false
                }
            } else return false
        }
        return false
    }
}