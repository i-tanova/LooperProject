package com.tanovai.looperproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var simpleWorker: SimpleWorker? = null
    var customHandlerThread: UseHandlerThread? = null
    var androidHandlerThread: AndroidHandlerThread? = null

    val handler = object:Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            message.text = msg.obj.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tasks = listOf<Runnable>(getTask("Task 1"), getTask("Task 2"), getTask("Task 3"))
       // executeOnSimpleWorker(tasks)
        executeOnCustomHandler(tasks)
        //executeOnAndroidHandler(tasks)
    }

    private fun executeOnAndroidHandler(tasks: List<Runnable>) {
        androidHandlerThread = AndroidHandlerThread()
        tasks.forEach() {
            androidHandlerThread?.execute(it)
        }
    }

    private fun executeOnCustomHandler(tasks: List<Runnable>) {
        customHandlerThread = UseHandlerThread()
        tasks.forEach() {
            customHandlerThread?.execute(it)
        }
    }

    private fun getTask(name: String): Runnable{
        return object : Runnable {
            override fun run() {
                Thread.sleep(1000)
                val msg = Message.obtain()
                msg.obj = "$name completed"
                handler.sendMessage(msg)
            }
        }
    }

    private fun executeOnSimpleWorker(tasks: List<Runnable>) {
            simpleWorker = SimpleWorker()
            tasks.forEach(){
            simpleWorker?.execute(it)
        }
    }

    override fun onDestroy() {
        simpleWorker?.quit()
        customHandlerThread?.quit()
        androidHandlerThread?.quit()
        super.onDestroy()
    }
}
