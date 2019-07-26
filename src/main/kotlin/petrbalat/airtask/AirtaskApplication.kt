package petrbalat.airtask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AirtaskApplication

fun main(args: Array<String>) {
	runApplication<AirtaskApplication>(*args)
}
