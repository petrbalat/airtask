package petrbalat.airtask.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import petrbalat.airtask.dto.PostDto
import petrbalat.airtask.dto.UserDto

@RestController
@RequestMapping("/api/user")
class UserController(private val client: WebClient) {

    @GetMapping("/suspend/{id}")
    suspend fun backends(@PathVariable id: Int): UserDto {
        val userAsync = client.get().uri("/users/$id").awaitExchange()
        val postsAsync = client.get().uri { builder -> builder.path("/posts").queryParam("userId", id).build() }
                .awaitExchange()

        val user: UserDto = userAsync.awaitBody()
        val posts: List<PostDto> = postsAsync.awaitBody()

        return user.copy(posts = posts)
    }
}