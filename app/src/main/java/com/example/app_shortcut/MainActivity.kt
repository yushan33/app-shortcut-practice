package com.example.app_shortcut

import android.app.PendingIntent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import com.example.app_shortcut.shotcut.ShortcutSetting
import com.example.app_shortcut.shotcut.shortcut_message_id
import com.example.app_shortcut.shotcut.shortcut_website_id
import com.example.app_shotcut.R
import com.example.app_shotcut.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
            ShortcutSetting.setup(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            shortcutPins()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun shortcutPins() {
        binding.btnAddShortcutUrl.setOnClickListener {
            setShortcutPin(shortcut_website_id)
        }
        binding.btnAddShortcutMessage.setOnClickListener {
            setShortcutPin(shortcut_message_id)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setShortcutPin(shortcutId: String) {
        val shortcutManager: ShortcutManager? = getSystemService(ShortcutManager::class.java)

        if (shortcutManager?.isRequestPinShortcutSupported == true) {
            val pinShortcutInfo =
                ShortcutInfo.Builder(applicationContext, shortcutId).build()
            val pinnedShortcutCallbackIntent =
                shortcutManager.createShortcutResultIntent(pinShortcutInfo)
            val successCallback =
                PendingIntent.getBroadcast(
                    applicationContext,
                    0,
                    pinnedShortcutCallbackIntent,
                    PendingIntent.FLAG_IMMUTABLE
                )
            shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.intentSender)
        }
    }

}