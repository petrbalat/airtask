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
import petrbalat.airtask.service.UserService
import reactor.core.publisher.Mono
import petrbalat.airtask.util.*

/**
 * TODO paralel branch, test + integration test
 */
@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @GetMapping("/suspend/{id}")
    suspend fun suspend(@PathVariable id: Int): UserDto {
        val user: UserDto = userService.fetchBasicDataByIdAsync(id)
        val posts: List<PostDto> = userService.fetchPostsByIdAsync(id)
        return user.copy(posts = posts)
    }

    @GetMapping("/reactor/{id}")
    fun reactor(@PathVariable id: Int): Mono<UserDto> {
        val userMono: Mono<UserDto> = userService.fetchBasicDataById(id)
        val postsMono: Mono<List<PostDto>> = userService.fetchPostsById(id)
        return userMono.zipWith(postsMono).map { (user, posts) ->
            user.copy(posts = posts)
        }
    }
}
