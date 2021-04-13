package com.italom.kotlin.examples.generics

open class Device(open val name: String) {
    open fun turnOn() = println("Turning on Device")
}

data class Computer(override val name: String) : Device(name) {
    override fun turnOn() = println("Turning on Computer")
}

interface Auditable<out T> {
    val assets: List<T>
}

interface Identifiable {
    val name: String
}

data class Laboratory<out T : Device>(
    override val name: String,
    override val assets: List<T>
) : Auditable<T>, Identifiable, Iterable<T> by assets

inline fun <reified L, reified D : Device> audit(audited: L) where L : Auditable<D>, L : Identifiable =
    println("Auditing: '${L::class.simpleName}' with name '${audited.name}', containing assets of type: '${D::class.simpleName}'")

fun turnOnLabDevices(laboratory: Laboratory<Device>) = laboratory.forEach(Device::turnOn)

fun main() {
    val deviceLab = Laboratory(
        "DeviceLab",
        listOf(
            Device("device1"),
            Device("device2")
        )
    )

    audit(deviceLab)
    turnOnLabDevices(deviceLab)

    val computerLab = Laboratory(
        "ComputerLab",
        listOf(
            Computer("computer1"),
            Computer("computer2")
        )
    )

    audit(computerLab)
    turnOnLabDevices(computerLab)
}