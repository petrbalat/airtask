package petrbalat.airtask.util

import reactor.util.function.Tuple2

operator fun <A, B> Tuple2<A, B>.component1(): A = t1
operator fun <A, B> Tuple2<A, B>.component2(): B = t2
