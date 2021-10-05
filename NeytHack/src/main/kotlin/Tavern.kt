import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val patronList: MutableList<String> = mutableListOf("Eli", "Mordoc", "Sophie")
val uniquePatrons: Set<String> = generateSequence {
    val first = patronList.random()
    val last = lastName.random()
    "$first $last"
}.distinct().take(9).toSet()

val menuList = File("data\\tavern-menu-items.txt")
    .readText()
    .split("\n")
val patronGold: MutableMap<String, Double> = uniquePatrons.associateWith { 6.0 }.toMutableMap()

fun main() {
    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }
    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }

    println(uniquePatrons)
    println(patronGold)

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.random(), menuList.random())
        orderCount++
    }

    displayPatronBalances()

    val valuesToAdd = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)
    println(valuesToAdd.filter { it > 4 }.chunked(2).map { it[0] * it[1] }.fold(0) {acc, i -> acc + i })
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0, indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for ${price.replace("\r", "")}."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }
    println(phrase)
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

private fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}