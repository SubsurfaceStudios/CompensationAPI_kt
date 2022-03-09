package tk.compensationvr.api.serializables

import kotlinx.serialization.SerialName

data class PubUserData(
    val username: String,
    val nickname: String,
    val tag: String?,
    val ownedRooms: List<Any>,
    val moderatedRooms: List<Any>,
    @SerialName("outfit")
    val currOutfit: Outfit
) {
}