package ru.zhogin.passwordsholder.passwords.data

import ru.zhogin.passwordsholder.passwords.domain.Entrance
import ruzhogin.EntranceEntity

fun EntranceEntity.toEntrance() : Entrance {
    return Entrance(
        id = id,
        pass = pass,
    )
}