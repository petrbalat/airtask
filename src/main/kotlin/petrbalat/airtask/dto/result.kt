package petrbalat.airtask.dto

/**
 * dto for user
 */
data class UserDto(val id: Int, val username: String, val email: String?, val posts: List<PostDto> = emptyList())

class PostDto(val id: Int, val title: String, val body: String)
