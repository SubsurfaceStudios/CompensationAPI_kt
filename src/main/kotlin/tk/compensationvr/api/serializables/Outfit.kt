package tk.compensationvr.api.serializables

import tk.compensationvr.api.db

class Outfit(
    var clothes: MutableList<db.items.ItemDataStructures.ItemSerializable>
) {
    fun serialize(): Map<String, String> {
        return clothes.associate { it.slot to (it.id + ':' + it.variant) }
    }
}
