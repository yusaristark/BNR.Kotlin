private const val MAX_DURMAN = 50

fun main(args: Array<String>) {
    val name = "Madrigal"
    var healthPoints = 89
    val isBlessed = true
    val isImmortal = false
    var durmanStatus = 0

    val auraColor = auraColor(isBlessed, healthPoints, isImmortal)

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
    durmanStatus = castFireball(durman = durmanStatus)
}

private fun printPlayerStatus(
    auraColor: String,
    isBlessed: Boolean,
    name: String,
    healthStatus: String
) {
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, healthPoints: Int, isImmortal: Boolean) =
    if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean) =
   when (healthPoints) {
        100 -> "is in excellent condition!"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly!"
        } else {
            "has some mino wounds."
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition!"
    }

private fun castFireball(numFireballs: Int = 2, durman: Int = 0) : Int {
    println("A glass of Fireball springs into existence. (x$numFireballs)" +
    " condition: ${
        when (durman) {
            in 1..10 -> "Tipsy"
            in 11..20 -> "Sloshed"
            in 21..30 -> "Soused"
            in 33..40 -> "Stewed"
            in 41..50 -> "..t0aSt3d"
            else -> "Tipsy"
        }
    }")
    val currentDurman = durman + numFireballs
    return if (currentDurman >= MAX_DURMAN) MAX_DURMAN else currentDurman
}

