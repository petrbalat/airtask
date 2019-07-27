package petrbalat.airtask.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import petrbalat.airtask.config.jsonplaceholderWebClient
import petrbalat.airtask.dto.PostDto
import petrbalat.airtask.dto.UserDto
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserServiceTest {

    private val service = UserService(jsonplaceholderWebClient)

    @Test
    fun fetchBasicDataById() = runBlocking {
        val userDto: UserDto = service.fetchBasicDataByIdAsync(2)
        assertEquals(2, userDto.id)
        assertEquals("Antonette", userDto.username)
        assertEquals("Shanna@melissa.tv", userDto.email)
        assertTrue(userDto.posts.isEmpty())

        val userDto1 = service.fetchBasicDataById(2).block()
        assertEquals(userDto1, userDto)
    }

    @Test
    fun fetchPostsByIdAsync() = runBlocking {
        val posts: List<PostDto> = service.fetchPostsByIdAsync(3)
        assertEquals(10, posts.size)
        assertEquals("asperiores ea ipsam voluptatibus modi minima quia sint", posts.first().title)

        val posts2 = service.fetchPostsById(3).block()
        assertEquals(posts2, posts)
    }
}
