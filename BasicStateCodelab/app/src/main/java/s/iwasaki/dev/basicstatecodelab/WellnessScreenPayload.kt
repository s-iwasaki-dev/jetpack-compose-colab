package s.iwasaki.dev.basicstatecodelab

import kotlinx.serialization.Serializable

@Serializable
data class WellnessScreenPayload(
    val screenName: String
)
