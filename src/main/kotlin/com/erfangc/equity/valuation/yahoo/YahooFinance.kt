package com.erfangc.equity.valuation.yahoo

import com.erfangc.equity.valuation.yahoo.summary.Summary
import io.github.erfangc.raincove.sdk.models.GaapFinancialStatement
import java.time.Instant

data class YahooFinance(
    val ticker: String,
    val summary: Summary,
    val financials: List<GaapFinancialStatement>,
    val lastUpdated: Instant
) {

    fun latestFinancial(): GaapFinancialStatement {
        return financials.sortedByDescending { it.date }.first()
    }

    fun earliestFinancial(): GaapFinancialStatement {
        return financials.sortedBy { it.date }.first()
    }

}