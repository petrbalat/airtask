package petrbalat.airtask.controller

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import petrbalat.airtask.dto.PostDto
import petrbalat.airtask.dto.UserDto
import petrbalat.airtask.service.UserService
import petrbalat.airtask.util.component1
import petrbalat.airtask.util.component2
import reactor.core.publisher.Mono

/**
 * TODO paralel branch,
 */
@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @GetMapping("/suspend/{id}")
    suspend fun suspend(@PathVariable id: Int): UserDto {
        val user: UserDto = userService.fetchBasicDataById(id).awaitFirst()
        val posts: List<PostDto> = userService.fetchPostsById(id).awaitFirst()
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
