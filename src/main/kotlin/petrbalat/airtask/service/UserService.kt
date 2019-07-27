package petrbalat.airtask.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import petrbalat.airtask.dto.PostDto
import petrbalat.airtask.dto.UserDto
import reactor.core.publisher.Mono

@Service
class UserService(private val client: WebClient) {

    /**
     * retrieves user data without posts
     */
    fun fetchBasicDataById(id: Int): Mono<UserDto> = client.get().uri("/users/$id").retrieve().bodyToMono(UserDto::class.java)

    /**
     * retrieves user posts
     */
    fun fetchPostsById(id: Int): Mono<List<PostDto>> = client.get().uri { builder ->
        builder.path("/posts").queryParam("userId", id).build()
    }.retrieve().bodyToFlux(PostDto::class.java).collectList()


}
