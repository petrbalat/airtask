package petrbalat.airtask.controller

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
    suspend fun suspend(@PathVariable id: Int): UserDto = coroutineScope {
        val user: Deferred<UserDto> = async { userService.fetchBasicDataById(id).awaitFirst() }
        val posts: Deferred<List<PostDto>> = async { userService.fetchPostsById(id).awaitFirst() }
        user.await().copy(posts = posts.await())
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
