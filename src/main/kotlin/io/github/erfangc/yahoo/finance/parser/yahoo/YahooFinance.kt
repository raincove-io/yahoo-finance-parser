package io.github.erfangc.yahoo.finance.parser.yahoo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.erfangc.raincove.sdk.models.Company
import io.github.erfangc.raincove.sdk.models.FinancialStatement
import java.time.Instant

val objectMapper = jacksonObjectMapper()
        .findAndRegisterModules()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)!!

data class YahooFinance(
        val ticker: String,
        val company: Company,
        val financials: List<FinancialStatement>,
        val lastUpdated: Instant
) {

    fun latestFinancial(): FinancialStatement {
        return financials.sortedByDescending { it.date }.first()
    }

    fun earliestFinancial(): FinancialStatement {
        return financials.sortedBy { it.date }.first()
    }

}