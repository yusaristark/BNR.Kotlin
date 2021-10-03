import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
var volume : Double = 5.0

fun main() {
    repeat(12) {
        placeOrder("shandy,Dragon's Breath,5.91")
        println()
    }
//    placeOrder("elixir,Shirley's Temple,4.12")
}

private fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0, indexOfApostrophe)
    println("Madrigal speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    if (performPurchase(price.toDouble())) {
        val message = "Madrigal buys a $name ($type) for $price."
        println(message)



        val phrase = if (name == "Dragon's Breath") {
            "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name!")}"
        } else {
            "Madrigal says: Thanks for the $name."
        }
        println(phrase)
    } else {
        println("You don\'t have enough money")
    }
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }

fun performPurchase(price: Double): Boolean {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")
    if (totalPurse >= price) {
        val remainingBalance = totalPurse - price
        println("Remaining balance: ${"%.2f".format(remainingBalance)}")

        val remainingGold = remainingBalance.toInt();
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remainingGold
        playerSilver = remainingSilver

        volume -= 0.125
        println("Volume remaining: $volume")
        displayBalance()
        return true
    } else {
        return false
    }
}

private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}