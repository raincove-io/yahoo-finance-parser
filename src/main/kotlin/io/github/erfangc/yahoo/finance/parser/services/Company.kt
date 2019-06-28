package io.github.erfangc.yahoo.finance.parser.services

import io.github.erfangc.yahoo.finance.parser.yahoo.YahooFinance

data class Company(
    val ticker: String,
    val name: String,
    val sector: String? = null,
    val industry: String? = null,
    val yahooFinance: YahooFinance? = null
)
