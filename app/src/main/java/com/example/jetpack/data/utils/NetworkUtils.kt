package com.example.jetpack.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService
import java.io.IOException

abstract class NetworkUtils {

    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    )?:false
                }else{

                    @Suppress("DEPRECATION")
                    return this.activeNetworkInfo?.isConnected ?: false
                }
            }
        }

    }
}