package petrbalat.airtask.util

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

val mapper: ObjectMapper by lazy {
	ObjectMapper().apply {
		val checker = serializationConfig.defaultVisibilityChecker
		setVisibility(
				checker.withFieldVisibility(JsonAutoDetect.Visibility.ANY).
						withGetterVisibility(JsonAutoDetect.Visibility.NONE).
						withSetterVisibility(JsonAutoDetect.Visibility.NONE).
						withIsGetterVisibility(JsonAutoDetect.Visibility.NONE).
						withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
		)

		registerModule(KotlinModule())
	}
}
