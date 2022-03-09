package tk.compensationvr.api.items

import tk.compensationvr.api.serializables.ItemEconState
import tk.compensationvr.api.util.LazyThreadedValue
import kotlinx.serialization.*;


@Serializable
class Item {
    // general info
    lateinit var name: String
    lateinit var tags: List<String>
    lateinit var description: String

    // internal info
    var id: String // @rose you will use proper ids IMMEDIATELY
    var variant: String = "NONE" // default is none :))
    // in api, serialize as:
    // id: $id:$variant
    // ^^^ so cool @rose you love



    // Econ info
    @SerialName("")
    lateinit var econState: ItemEconState

    val isInitialized by LazyThreadedValue {
        // woah! get things from db here
        // this runs in a seperate thread, so we can get the clothing item from cache/db
        // without blocking or using coroutines.

        econState = ItemEconState(canPurchase = true, canTransfer = true, canRefund = true, canGift = true,
            buyPrice = 10, refundPrice = 10, giftPrice = 8)

        name = "PLACEHOLDER ITEM"
        tags = listOf("cat_ears", "purple")
        description = "Placeholder Item, if you're seeing this then something went wrong (or right?)"


        return@LazyThreadedValue true;

    }

    constructor(id: String)
    {
        this.id = id
    }
    fun serialize(): ItemSerial {
        if (this.isInitialized)
        {
            return ItemSerial(
                name, tags, description, id+":"+variant,
                canGift = econState.canGift,
                canPurchase = econState.canPurchase,
                canRefund = econState.canRefund,
                canTransfer = econState.canTransfer,
                giftPrice = econState.giftPrice,
                refundPrice = econState.refundPrice,
                buyPrice = econState.buyPrice)
        }
        return Item("PLACEHOLDER").serialize() // FIXME: this could cause a loop. not good.
    }
    companion object {
        fun getFromDb(itemId: String): ClothingItem {
            return ClothingItem("PLACEHOLDER") // TODO: this relies on db, not implemented yet
        }
//        private CACHE =
    }
}
@Serializable
data class ItemSerial(
    var name: String,
    var tags: List<String>,
    var description: String,
    var id: String,
    var canPurchase: Boolean,
    var canTransfer: Boolean,
    var canRefund: Boolean,
    var canGift: Boolean,
    var buyPrice: Int,
    var refundPrice: Int,
    var giftPrice: Int
)