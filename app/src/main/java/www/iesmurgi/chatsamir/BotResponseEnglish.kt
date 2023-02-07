package www.iesmurgi.chatsamir
import www.iesmurgi.chatsamir.Constants.OPEN_CAMERA
import www.iesmurgi.chatsamir.Constants.OPEN_CONTACTS
import www.iesmurgi.chatsamir.Constants.OPEN_GOOGLE
import www.iesmurgi.chatsamir.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
object BotResponseEnglish {

    fun basicResponses(message: String): String {


        val _message = message.lowercase()

        return when {


            _message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it came up $result"
            }

            //Math calculations
            _message.contains("calculate") -> {
                val equation: String? = _message.substringAfterLast("calculate")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "I can't solve that."
                }
            }

            _message.contains("hello") -> {
                when ((0..2).random()) {
                    0 -> "Hello!"
                    1 -> "Hi"
                    2 -> "I'm glad you wrote to me!"
                    else -> "error" }
            }


            _message.contains("how are you") -> {
                when ((0..2).random()) {
                    0 -> "I'm fine thanks"
                    1 -> "I'm hungry"
                    2 -> "I'm cold bro."
                    else -> "error"
                }
            }

            _message.contains("joke") -> {
                when ((0..9).random()) {
                    0 -> "Why do birds fly south in winter? Because it's too far to walk."
                    1 -> "Why do fish sleep with one eye open? Because they have sand-man."
                    2 -> "Why can't you hear a broccoli talking? Because it has a mute shoot."
                    3 -> "Why do cows have bells? Because their hands are busy."
                    4 -> "Why do squids go to the gym? To make tentacles."
                    5 -> "Why do zombies love horror movies? Because they like to see humans scared."
                    6 -> "Why don't cats speak human? Because they prefer to say \"meow\" in their feline language."
                    7 -> "Why do bees wear boots? To not step on the comb."
                    8 -> "Why didn't dinosaurs use mobile phones? Because their fingers were too big to dial."
                    9 -> "Why don't birds go to the dentist? Because they have a gold beak."
                    else -> "error"
                }
            }

            message.contains("time") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            message.contains("help") -> {
                "Command list\n" +
                        "\uD83C\uDF0E browser\n" +
                        "⌚ time\n" +
                        "\uD83E\uDE99 coin\n" +
                        "\uD83E\uDD21 joke\n" +
                        "\uD83D\uDD0E search\n" +
                        "➗ calculate <num1 (+/-*) num2>\n" +
                        " ☎️ agenda\n"+
                        "\uD83D\uDCF7 camera"
            }


            message.contains("browser")-> {
                OPEN_GOOGLE
            }


            message.contains("search")-> {
                OPEN_SEARCH
            }

            message.contains("agenda")-> {
                OPEN_CONTACTS
            }

            message.contains("camera")->{
                OPEN_CAMERA
            }


            else -> {
                when ((0..5).random()) {
                    0 -> "I don't understand..."
                    1 -> "Ask me something else"
                    2 -> "I don't know"
                    3 -> "Your comment has touched my soul..."
                    4 -> "Tell me more"
                    5 -> "Keep talking"
                    else -> "error"
                }
            }
        }
    }
}
