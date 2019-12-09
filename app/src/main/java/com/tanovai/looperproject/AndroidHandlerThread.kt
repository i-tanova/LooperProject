package com.tanovai.looperproject

import android.os.Handler
import android.os.HandlerThread

class AndroidHandlerThread: HandlerThread {

    private var handler: Handler? = null

    constructor(): super(TAG){
        start()
        handler = Handler(looper)
    }

    fun execute(r: Runnable){
        handler?.post(r)
    }

    companion object{
        val TAG = "HandlerThreadWorker"
    }

}