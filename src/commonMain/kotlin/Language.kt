import kotlinx.serialization.Serializable

@Serializable
data class Language (
    val id: Int,
    val language: String
) {
    companion object {
        const val path = "/language"
    }
}