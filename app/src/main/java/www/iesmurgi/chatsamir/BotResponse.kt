package www.iesmurgi.chatsamir


import www.iesmurgi.chatsamir.Constants.OPEN_CAMERA
import www.iesmurgi.chatsamir.Constants.OPEN_CONTACTS
import www.iesmurgi.chatsamir.Constants.OPEN_GOOGLE
import www.iesmurgi.chatsamir.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {


        val message =_message.lowercase()

        return when {


            message.contains("moneda") -> {
                val r = (0..1).random()
                val result = if (r == 0) "cara" else "cruz"

                "Lancé una moneda y salió $result"
            }

            //Math calculations
            message.contains("calcula") -> {
                val equation: String? = message.substringAfterLast("calcula")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "No puedo resolver eso."
                }
            }

            message.contains("hola") -> {
                when ((0..2).random()) {
                    0 -> "Hola!"
                    1 -> "Buenas"
                    2 -> "Me alegro de que me escribas!"
                    else -> "error" }
            }


            message.contains("mo estas") -> {
                when ((0..2).random()) {
                    0 -> "Estoy bien gracias"
                    1 -> "Tengo hambre"
                    2 -> "Tengo frío socio."
                    else -> "error"
                }
            }

            message.contains("chiste") -> {
                when ((0..9).random()) {
                    0 -> "¿Por qué los pájaros vuelan hacia el sur en invierno? Porque es demasiado lejos para caminar."
                    1 -> "¿Por qué los peces duermen con un ojo abierto? Porque tienen pecepálido."
                    2 -> "¿Por qué no puedes oír a un broccoli hablar? Porque tiene un brote mudo."
                    3 -> "¿Por qué las vacas tienen campanas? Porque sus manos están ocupadas."
                    4 -> "¿Por qué los calamares van al gimnasio? Para hacer tentáculos."
                    5 -> "¿Por qué los zombis aman las películas de terror? Porque les gusta ver cómo los humanos se asustan."
                    6 -> "¿Por qué los gatos no hablan humano? Porque prefieren decir \"miau\" en su lenguaje gatuno."
                    7 -> "¿Por qué las abejas usan botas? Para no pisar el panal."
                    8 -> "¿Por qué los dinosaurios no usaban teléfonos móviles? Porque tenían los dedos demasiado grandes para marcar."
                    9 -> "¿Por qué los pájaros no van al dentista? Porque tienen pico de oro."
                    else -> "error"
                }
            }


            message.contains("hora") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            message.contains("ayuda") -> {
                "Lista de comandos\n" +
                "\uD83C\uDF0E navegador\n" +
                "⌚ hora\n" +
                "\uD83E\uDE99 moneda\n" +
                "\uD83E\uDD21 chiste\n" +
                "\uD83D\uDD0E busca\n" +
                "➗ calcula  <num1 (+/-*) num2>\n" +
                " ☎️ agenda\n"+
                "\uD83D\uDCF7 camara"
            }


            message.contains("navegador")-> {
                OPEN_GOOGLE
            }


            message.contains("busca")-> {
                OPEN_SEARCH
            }

            message.contains("agenda")-> {
                OPEN_CONTACTS
            }

            message.contains("camara")->{
                OPEN_CAMERA
            }


            else -> {
                when ((0..5).random()) {
                    0 -> "No comprendo..."
                    1 -> "Preguntame algo distinto"
                    2 -> "No lo sé"
                    3 -> "Me ha llegado al alma tu comentario..."
                    4 -> "Dime más"
                    5 -> "Sigue hablando"
                    else -> "error"
                }
            }
        }
    }
}