package com.erfangc.equity.valuation.yahoo

import io.github.erfangc.raincove.sdk.apis.RaincoveSdk

fun main() {
    RaincoveSdk.setBaseUrl("http://localhost:8080")
    val fs = RaincoveSdk.financialStatements()
    val response = fs.getFinancialStatements("MSFT")
    println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response))

    val companies = RaincoveSdk.companies()
    val msft = companies.getCompany("MSFT")
    println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(msft))
}