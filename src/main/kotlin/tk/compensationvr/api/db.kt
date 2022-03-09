package tk.compensationvr.api

import kotlinx.serialization.*
import tk.compensationvr.api.items.Item
import tk.compensationvr.api.serializables.Outfit

object db {
    internal object BinaryProvider {} // firebase wrapper
    internal object DataProvider {} // mongodb + cache wrapper
    object account {
        object AccountDataStructures {
            /*
            * Account data structures
            * UserLoginPayload
             */
            @Serializable
            data class UserLoginPayload(val userId: String, val username: String, @SerialName("accessToken") val token: String)
            @Serializable
            open class UserInformationPayload(
                open val nickname: String,
                open val username: String,
                open val tag: String,
                open val ownedRooms: List<String>, // TODO: once rooms are implemented, change this to a list of room ids
                open val moderatedRooms: List<String>, // TODO: once rooms are implemented, change this to a list of room ids,
                open val outfit: Map<String, String>, // Serializable Outfit (outfit.serialize()),
                open val pronouns: String,
                open val bio: String

            )
            @Serializable
            class PrivateUserInformationPayload(
                @Transient override val nickname: String = "",
                @Transient override val username: String = "",
                @Transient override val tag: String = "",
                @Transient override val ownedRooms: List<String> = listOf(),
                @Transient override val moderatedRooms: List<String> = listOf(),
                @Transient override val outfit: Map<String, String> = mapOf(),
                @Transient override val pronouns: String = "",
                @Transient override val bio: String = "",

                // now for private data
                val availableTags: List<String>,
                val inventory: Map<String, Int>, // item id to quantity
                val friends: List<String>,
                val favoriteFriends: List<String>,
                val friendRequestSent: List<String>,
            ) : UserInformationPayload(nickname, username, tag, ownedRooms, moderatedRooms, outfit, pronouns, bio)
        }
        fun login(username: String, password: String): AccountDataStructures.UserLoginPayload? { // UserState
            TODO()
        }
        fun tryRegister(username: String, password: String, nickname: String): AccountDataStructures.UserLoginPayload? {
            TODO()
        }
        fun edit(username: String, password: String, nickname: String): Any? {
            TODO()
        }
        fun get(username: String, isAuthenticated: Boolean): AccountDataStructures.UserInformationPayload? {
            // placeholder information for proof of concept
            return AccountDataStructures.PrivateUserInformationPayload(
                nickname = "placeholder",
                username = "iLoveWomen",
                tag = "placeholder value",
                ownedRooms = listOf("will be changed once rooms are implemented"),
                moderatedRooms = listOf("will be changed once rooms are implemented"),
                outfit = mapOf("hat1" to "cat_ears:purple"),
                pronouns = "it/its",
                bio = "my account doesnt exist, so i'm just a placeholder",
                availableTags = listOf("available tags thingy"),
                inventory = mapOf("tomato_soup:unopened" to 9999, "tomato_soup:opened" to 1),
                friends = listOf("kotlin"),
                favoriteFriends = listOf("jai"),
                friendRequestSent = listOf("Rose932")
            )
        }
        fun resolve(username: String): String? { // resolve username to uid
            TODO()
        }
        fun getByUid(uid: String): Any? {
            TODO()
        }

    }
    object items {
        fun all() : List<Item> = TODO()
        fun get(id: String) : Item = TODO()

    }

}