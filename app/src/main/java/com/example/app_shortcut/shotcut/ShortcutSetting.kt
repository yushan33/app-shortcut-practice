package com.example.app_shortcut.shotcut

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.app_shortcut.MainActivity
import com.example.app_shortcut.MessageActivity
import com.example.app_shotcut.R

const val shortcut_website_id = "shortcut_id_website"
const val shortcut_message_id = "shortcut_id_message"

@RequiresApi(Build.VERSION_CODES.N_MR1)
object ShortcutSetting {

    fun setup(context: Context) {
        val shortcutManager: ShortcutManager? =
            getSystemService<ShortcutManager>(context, ShortcutManager::class.java)

        val shortcutWebsite: ShortcutInfo =
            ShortcutInfo.Builder(context, shortcut_website_id)
                .setShortLabel("Website")
                .setLongLabel("Open the Website!")
                .setIcon(Icon.createWithResource(context, R.drawable.baseline_android_24))
                .setIntent(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=pnPZox3lXM8")
                    )
                )
                .build()

        val intents = arrayOf(
            Intent(Intent.ACTION_VIEW, null, context, MainActivity::class.java),
            Intent(Intent.ACTION_VIEW, null, context, MessageActivity::class.java)
        )
        val shortcutMessage: ShortcutInfo =
            ShortcutInfo.Builder(context, shortcut_message_id)
                .setShortLabel("Message")
                .setLongLabel("Send a message!")
                .setIcon(Icon.createWithResource(context, R.drawable.baseline_message_24))
                .setIntents(intents)
                .build()

        shortcutManager?.dynamicShortcuts = listOf(shortcutWebsite, shortcutMessage)
    }
}