package io.github.erfangc.yahoo.finance.parser.yahoo.summary

data class Summary(
    val ticker: String,
    val name: String,
    val marketCap: Double?,
    val beta3YMonthly: Double?,
    val previousClose: Double?,
    val peRatio: Double?,
    val eps: Double?,
    val industry: String,
    val sector: String
)