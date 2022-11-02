import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int,
    val title: String,
    var author: Author? = null,
    val videoUrl: String,
    val year: String,
    var watched: Boolean,
    var language: Language? = null,
    var categories: List<Category>? = null
) {
    companion object {
        const val path = "/video"
    }
}