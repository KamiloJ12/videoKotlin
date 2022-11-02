import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int? = null,
    val category: String
) {
    companion object {
        const val path = "/category"
    }
}
