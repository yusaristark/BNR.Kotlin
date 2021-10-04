import java.io.File

class Player(_name: String,
             var healthPoints: Int = 100,
             val isBlessed: Boolean,
             private val isImmortal: Boolean) {
    var name = _name
    get() = "${field.capitalize()} of $hometown"
    set(value) {
        field = value.trim()
    }

    constructor(name: String) : this(name,
            isBlessed = true,
            isImmortal = false) {
        if (name.lowercase() == "kar") healthPoints = 40
    }

    val hometown by lazy { selectHometown() }

    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero." })
        require(name.isNotBlank(), { "Player must have a name." })
    }

    fun castFireball(numFireballs: Int = 2) =
        println("A glass of Fireball springs into existence. (x$numFireballs)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        return if (auraVisible) "GREEN" else "NONE"
    }

    fun formatHealthStatus() =
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

    private fun selectHometown() = File("data\\towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()
        .replace("\r", "")
}