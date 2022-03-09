package tk.compensationvr.api.serializables

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import tk.compensationvr.api.items.ClothingItem
import tk.compensationvr.api.items.ClothingSlot
import java.io.Serial

class Outfit(
    var clothes: MutableList<ClothingItem>
) {
    fun serialize(): Map<String, String> {
        return clothes.associate { it.slot.slotName to (it.id + ':' + it.variant) }
    }
}
