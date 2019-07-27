package petrbalat.airtask.controller

import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import org.springframework.http.MediaType
import petrbalat.airtask.AbstractIntegrationTests
import petrbalat.airtask.dto.UserDto
import kotlin.system.measureTimeMillis
import kotlin.test.Test

class UserControllerIntegrationTest : AbstractIntegrationTests() {

    //    @Test
    fun performUserIdForStandartMvcTest() {
        // inspired by my project https://github.com/petrbalat/kd4smt :-)
//        mockMvc.get("/api/user/reactor/{id}", 1) {
//            accept = APPLICATION_JSON
//        }.andExpect {
//            status { isOk }
//            content { contentType(APPLICATION_JSON) }
//            jsonPath("$.username") { value("Antonette") }
//        }
    }

    @Test
    fun performUserId() {
        client.get().uri("/api/user/reactor/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserDto::class.java)

    }

    /**
     * perform
     */
    @Test
    fun performanceUserId() = runBlocking(newFixedThreadPoolContext(100, "test")) {
        // warm
        client.get().uri("/api/user/reactor/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

        val time = measureTimeMillis {
            (1..100).map {
                launch {
                    val id = if(it > 100) 1 else it
                    client.get().uri("/api/user/reactor/{id}", id)
                            .accept(MediaType.APPLICATION_JSON)
                            .exchange()
                }
            }.forEach {
                it.join()
            }
        }

        println("Time for perform get $time millisec")
//        assertTrue(time <= 1000)
    }
}


