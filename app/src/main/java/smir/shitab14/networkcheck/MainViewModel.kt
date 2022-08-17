package smir.shitab14.networkcheck

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import smir.shitab14.networkcheck.network_util.ConnectivityObserver
import smir.shitab14.networkcheck.network_util.NetworkConnectivityObserver

/**
 * Created by Shitab Mir on 16 August, 2022.
 * HungryNaki (Technology), Daraz Bangladesh Limited, Alibaba Group
 * mushfiq.islam@hungrynaki.com | shitabmir@gmail.com
 **/
class MainViewModel : ViewModel() {
    private lateinit var connectivityObserver: ConnectivityObserver
    var status: Flow<ConnectivityObserver.Status> = flow {
        ConnectivityObserver.Status.Unavailable
    }

    fun observeNetworkState(context: Context) {
        connectivityObserver = NetworkConnectivityObserver(context = context)
        viewModelScope.launch {
            connectivityObserver.observe().apply {
                status = this
            }
        }
    }


}