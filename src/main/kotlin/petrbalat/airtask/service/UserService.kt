package petrbalat.airtask.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import petrbalat.airtask.dto.PostDto
import petrbalat.airtask.dto.UserDto
import reactor.core.publisher.Mono

@Service
class UserService(private val client: WebClient) {

    /**
     * retrieves user data without posts
     */
    suspend fun fetchBasicDataByIdAsync(id: Int): UserDto = userRrequest(id).awaitExchange().awaitBody()

    /**
     * retrieves user data without posts
     */
    fun fetchBasicDataById(id: Int): Mono<UserDto> = userRrequest(id).retrieve().bodyToMono(UserDto::class.java)

    /**
     * retrieves user posts
     */
    suspend fun fetchPostsByIdAsync(id: Int): List<PostDto> = postsRequest(id).awaitExchange().awaitBody()

    /**
     * retrieves user posts
     */
    fun fetchPostsById(id: Int): Mono<List<PostDto>> = postsRequest(id).retrieve().bodyToFlux(PostDto::class.java).collectList()


    private fun userRrequest(id: Int) = client.get().uri("/users/$id")

    private fun postsRequest(id: Int) =
            client.get().uri { builder -> builder.path("/posts").queryParam("userId", id).build() }

}
