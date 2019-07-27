package petrbalat.airtask.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

val mapper: ObjectMapper by lazy {
	ObjectMapper().apply {
		registerModule(KotlinModule())
	}
}
