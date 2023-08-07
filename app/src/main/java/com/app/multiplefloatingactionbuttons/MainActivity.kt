package com.app.multiplefloatingactionbuttons

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.app.multiplefloatingactionbuttons.databinding.ActivityMainBinding
import com.app.multiplefloatingactionbuttons.databinding.MultipleActionButtonBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var includedBindingButtons: MultipleActionButtonBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val currentDate = Calendar.getInstance()
        binding.calendarView.date = currentDate.timeInMillis

        handleFloatingActionButton()
        GlobalScope.launch(Dispatchers.Main) {
            includedBindingButtons.floatingActionButtonTask.setOnClickListener { showAction("Task") }
            includedBindingButtons.floatingActionButtonEvent.setOnClickListener { showAction("Event") }
        }
    }

    private fun showAction(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun handleFloatingActionButton() {
        includedBindingButtons = binding.multipleActionButton
        val handler = FloatingActionButtonHandler(this.applicationContext, includedBindingButtons)
        includedBindingButtons.floatingActionButtonAdd.setOnClickListener { handler.onAddButtonClicked() }
    }
}