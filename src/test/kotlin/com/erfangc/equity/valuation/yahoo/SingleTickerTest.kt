package com.erfangc.equity.valuation.yahoo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun main() {
    val retriever = YahooFinanceRetriever()
    val msft = retriever.retrieve("MSFT")
    val objectMapper = jacksonObjectMapper()
        .findAndRegisterModules()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(msft))
}