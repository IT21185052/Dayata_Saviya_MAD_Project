package com.example.myapplication2.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R

class NoticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        // Get the user inputs from the Intent extras
        val transportMethod = intent.getStringExtra("transportMethod")
        val startingFrom = intent.getStringExtra("startingFrom")
        val destination = intent.getStringExtra("destination")

        // Calculate the ticket price based on the user inputs
        val ticketPrice = calculateTicketPrice(transportMethod, startingFrom, destination)

        // Display the ticket price in the activity_notice.xml layout
        val ticketPriceTextView = findViewById<TextView>(R.id.tvTicketPrice)
        ticketPriceTextView.text = "Ticket price: $ticketPrice"
    }

    private fun calculateTicketPrice(
        transportMethod: String?,
        startingFrom: String?,
        destination: String?
    ): Int {
        return when (transportMethod) {
            "train" -> {
                when (startingFrom) {
                    "Matara" -> {
                        when (destination) {
                            "Galle" -> 150
                            "Kaluthara" -> 200
                            "Kollupitiya" -> 250
                            "Colombo" -> 300
                            else -> 0
                        }
                    }
                    "Galle" -> {
                        when (destination) {
                            "Kaluthara" -> 100
                            "Kollupitiya" -> 150
                            "Colombo" -> 200
                            else -> 0
                        }
                    }
                    else -> 0
                }
            }
            "bus" -> {
                when (startingFrom) {
                    "Matara" -> {
                        when (destination) {
                            "Galle" -> 300
                            "Kaluthara" -> 350
                            "Kollupitiya" -> 400
                            "Colombo" -> 450
                            else -> 0
                        }
                    }
                    "Galle" -> {
                        when (destination) {
                            "Kaluthara" -> 300
                            "Kollupitiya" -> 350
                            "Colombo" -> 400
                            else -> 0
                        }
                    }
                    else -> 0
                }
            }
            else -> 0
        }
    }
}
