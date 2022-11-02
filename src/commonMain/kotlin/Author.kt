import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: Int,
    val fullName: String
) {
    companion object {
        const val path = "/author"
    }
}
