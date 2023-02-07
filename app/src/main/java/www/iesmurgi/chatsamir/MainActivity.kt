package www.iesmurgi.chatsamir

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import www.iesmurgi.chatsamir.Constants.OPEN_CAMERA
import www.iesmurgi.chatsamir.Constants.OPEN_CONTACTS
import www.iesmurgi.chatsamir.Constants.OPEN_GOOGLE
import www.iesmurgi.chatsamir.Constants.OPEN_SEARCH
import www.iesmurgi.chatsamir.Constants.RECEIVE_ID
import www.iesmurgi.chatsamir.Constants.SEND_ID
import www.iesmurgi.chatsamir.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var bind : ActivityMainBinding
    var messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessagingAdapter
    private lateinit var idioma : String
    private val botList = listOf("Juan", "Marcos", "Lana", "Lola")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        idioma = Locale.getDefault().language

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customBotMessage(resources.getString(R.string.saludo) + " ${botList[random]}.\n"  + resources.getString(R.string.ayuda))
    }



    private fun clickEvents() {

        bind.btnSend.setOnClickListener {
            sendMessage()
        }

        bind.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    bind.rvMessages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }


    private fun recyclerView() {
        adapter = MessagingAdapter()
        bind.rvMessages.adapter = adapter
        bind.rvMessages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                bind.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = bind.etMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            bind.etMessage.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            bind.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                var response = BotResponse.basicResponses(message)
                if(idioma == "en"){
                    response = BotResponseEnglish.basicResponses(message)
                }

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                bind.rvMessages.scrollToPosition(adapter.itemCount - 1)

                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                    OPEN_CONTACTS -> {
                        val intent = Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
                        startActivity(intent)
                    }
                    OPEN_CAMERA -> {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivity(intent)
                    }

                }
            }
        }
    }

    private fun customBotMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                bind.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}