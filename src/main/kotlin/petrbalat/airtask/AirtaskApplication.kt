package petrbalat.airtask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import petrbalat.airtask.config.jsonWebClient
import petrbalat.airtask.controller.UserController
import petrbalat.airtask.service.UserService
import petrbalat.airtask.util.mapper

@SpringBootApplication
class AirtaskApplication

fun main(args: Array<String>) {
	val userId: Int? = args.getOrNull(0)?.substringAfter("--userId=")?.toIntOrNull()
	if (userId != null) {
		//TODO disable logging
		val result = UserController(UserService(jsonWebClient)).reactor(userId).map {
			mapper.writeValueAsString(it)
		}.block()
		println(result)
	} else {
		runApplication<AirtaskApplication>(*args)
	}
}
