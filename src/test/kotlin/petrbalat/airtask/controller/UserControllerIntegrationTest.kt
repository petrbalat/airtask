package petrbalat.airtask.controller

import org.springframework.http.MediaType
import petrbalat.airtask.AbstractIntegrationTests
import petrbalat.airtask.dto.UserDto
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
}


