package tk.compensationvr.api.serializables

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class EquipBody(
    @SerialName("item_id")
    var id: String
)
