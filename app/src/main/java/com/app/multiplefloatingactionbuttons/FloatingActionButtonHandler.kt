package com.app.multiplefloatingactionbuttons

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.app.multiplefloatingactionbuttons.databinding.MultipleActionButtonBinding

class FloatingActionButtonHandler (private val context: Context, private val binding: MultipleActionButtonBinding) {

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close) }
    private val moveToBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.move_to_bottom) }
    private val moveFromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.move_from_bottom) }
    private var click = true


    fun onAddButtonClicked() {
        setVisibility()
        setAnimation()
        click = !click
    }

    private fun setVisibility() {
        if(click){
            binding.floatingActionButtonEvent.visibility = View.VISIBLE
            binding.floatingActionButtonTask.visibility = View.VISIBLE
            binding.floatingActionButtonEvent.isClickable = true
            binding.floatingActionButtonTask.isClickable = true
        } else {
            binding.floatingActionButtonEvent.visibility = View.INVISIBLE
            binding.floatingActionButtonTask.visibility = View.INVISIBLE
            binding.floatingActionButtonEvent.isClickable = false
            binding.floatingActionButtonTask.isClickable = false
        }
    }
    private fun setAnimation() {
        if(click){
            binding.floatingActionButtonEvent.startAnimation(moveFromBottom)
            binding.floatingActionButtonTask.startAnimation(moveFromBottom)
            binding.floatingActionButtonAdd.startAnimation(rotateOpen)
        } else {
            binding.floatingActionButtonEvent.startAnimation(moveToBottom)
            binding.floatingActionButtonTask.startAnimation(moveToBottom)
            binding.floatingActionButtonAdd.startAnimation(rotateClose)
        }
    }
}