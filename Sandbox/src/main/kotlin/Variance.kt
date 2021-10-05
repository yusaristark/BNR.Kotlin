class Barrel<in T>(item: T)

fun main() {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a generic-looking fedora", 15))
    var lootBarrel: Barrel<Loot> = Barrel((Coin(15)))

    fedoraBarrel = lootBarrel
}