import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.call.*
import kotlinx.browser.window
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

val endpoint = window.location.origin + "/api"

val jsonClient = HttpClient {
    install(ContentNegotiation){
        json()
    }
}

suspend fun getVideos(): List<Video> {
    return jsonClient.get(endpoint + Video.path).body()
}

suspend fun getAuthors(): List<Author> {
    return jsonClient.get(endpoint + Author.path).body()
}

suspend fun getCategories(): List<Category> {
    return jsonClient.get(endpoint + Category.path).body()
}

suspend fun getLanguages(): List<Language> {
    return jsonClient.get(endpoint + Language.path).body()
}

suspend fun addAuthor(author: Author) {
    jsonClient.post(endpoint + Author.path){
        contentType(ContentType.Application.Json)
        setBody(author)
    }
}

suspend fun addCategory(category: Category) {
    jsonClient.post(endpoint + Category.path){
        contentType(ContentType.Application.Json)
        setBody(category)
    }
}

suspend fun addVideo(video: Video) {
    jsonClient.post(endpoint + Video.path){
        contentType(ContentType.Application.Json)
        setBody(video)
    }
}

suspend fun updateWatchVideo(video: Video) {
    jsonClient.put(endpoint + Video.path){
        contentType(ContentType.Application.Json)
        setBody(video)
    }
}