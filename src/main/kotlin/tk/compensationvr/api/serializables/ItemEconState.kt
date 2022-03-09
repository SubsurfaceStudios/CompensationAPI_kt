package tk.compensationvr.api.serializables


@kotlinx.serialization.Serializable
data class ItemEconState(
    var canPurchase: Boolean,
    var canTransfer: Boolean,
    var canRefund: Boolean,
    var canGift: Boolean,
    var buyPrice: Int,
    var refundPrice: Int,
    var giftPrice: Int

)
