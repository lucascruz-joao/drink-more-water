package com.example.drink_more_water.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.drink_more_water.R

class WaterTimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_water_timer, container, false)

        createChannel(
            getString(R.string.water_notification_channel),
            getString(R.string.water_notification_channel_name)
        )

        return view

//        val binding: FragmentWaterTimerBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_water_timer, container, false
//        )

//        val viewModel = ViewModelProvider(this)[WaterTimerViewModel::class.java]

//        binding.waterTimerViewModel = viewModel
//        binding.lifecycleOwner = this.viewLifecycleOwner

//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageButton>(R.id.imageButtonConfig).setOnClickListener {
            findNavController().navigate(R.id.action_WaterTimerFragment_to_ConfigTimerFragment)
        }

        val viewModel = ViewModelProvider(this)[WaterTimerViewModel::class.java]
        viewModel.isAlarmOn.value = true
        viewModel.timeSelection.value = 0

        val buttonSetAlarm = view.findViewById(R.id.buttonArrombado) as Button
        buttonSetAlarm.setOnClickListener {
            viewModel.setAlarm(true)
        }
    }

    private fun createChannel(channelId: String, channelName: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                    setShowBadge(false)
            }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_channel_description)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

    companion object {
        fun newInstance() = WaterTimerFragment()
    }
}