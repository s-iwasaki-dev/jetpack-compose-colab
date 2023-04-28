package s.iwasaki.dev.basicstatecodelab

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.parseToString(): String = Json.encodeToString(this)
inline fun <reified T> String.parseToPayload(): T = Json.decodeFromString(this)
