package petrbalat.airtask.service

import org.junit.jupiter.api.Test
import petrbalat.airtask.config.jsonplaceholderWebClient
import petrbalat.airtask.dto.PostDto
import petrbalat.airtask.dto.UserDto
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserServiceTest {

    private val service = UserService(jsonplaceholderWebClient)

    @Test
    fun fetchBasicDataById()  {
        val userDto: UserDto = service.fetchBasicDataById(2).block()!!
        assertEquals(2, userDto.id)
        assertEquals("Antonette", userDto.username)
        assertEquals("Shanna@melissa.tv", userDto.email)
        assertTrue(userDto.posts.isEmpty())
    }

    @Test
    fun fetchPostsById()  {
        val posts: List<PostDto> = service.fetchPostsById(3).block()!!
        assertEquals(10, posts.size)
        assertEquals("asperiores ea ipsam voluptatibus modi minima quia sint", posts.first().title)
    }
}
