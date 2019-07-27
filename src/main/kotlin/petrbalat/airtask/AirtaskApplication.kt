package petrbalat.airtask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import petrbalat.airtask.config.jsonplaceholderWebClient
import petrbalat.airtask.controller.UserController
import petrbalat.airtask.service.UserService
import petrbalat.airtask.util.mapper

@SpringBootApplication
class AirtaskApplication

fun main(args: Array<String>) {
	val userId: Int? = args.getOrNull(0)?.substringAfter("--userId=")?.toIntOrNull()
	if (userId != null) {
		val result: String? = UserController(UserService(jsonplaceholderWebClient)).reactor(userId).map {
			mapper.writeValueAsString(it)
		}.block()
		println(result)
	} else {
		runApplication<AirtaskApplication>(*args)
	}
}
