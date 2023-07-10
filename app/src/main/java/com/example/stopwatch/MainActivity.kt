package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val startButton: Button = findViewById(R.id.start_button)
		val stopButton: Button = findViewById(R.id.stop_button)
		val resetButton: Button = findViewById(R.id.reset_button)

		startButton.setOnClickListener { onClickStart() }
		stopButton.setOnClickListener { onClickStop() }
		resetButton.setOnClickListener { onClickReset() }

		runTimer()
	}

	private var seconds = 0
	private var running = false

	private fun onClickStart() {
		running = true
	}

	private fun onClickStop() {
		running = false
	}

	private fun onClickReset() {
		running = false
		seconds = 0
	}

	private fun runTimer() {
		val timeView: TextView = findViewById(R.id.time_view)
		val handler = Handler()

		handler.post(object : Runnable {
			override fun run() {
				val hours = seconds / 3600
				val minutes = seconds % 3600 / 60
				val secs = seconds % 60
				val time = String.format(
					Locale.getDefault(),
					"%d:%02d:%02d", hours, minutes, secs
				)
				timeView.text = time
				if (running)
					seconds++
				handler.postDelayed(this, 1000)
			}
		})
	}
}
