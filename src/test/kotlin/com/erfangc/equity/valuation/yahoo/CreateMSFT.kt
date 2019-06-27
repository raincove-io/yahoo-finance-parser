package com.erfangc.equity.valuation.yahoo

import io.github.erfangc.raincove.sdk.apis.RaincoveSdk
import io.github.erfangc.raincove.sdk.models.CreateFinancialStatementsRequest

fun main() {
    val retriever = YahooFinanceRetriever()
    val msft = retriever.retrieve("MSFT")
    println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(msft))
    RaincoveSdk.setBaseUrl("http://localhost:8080")
    val fs = RaincoveSdk.financialStatements()
    val request = CreateFinancialStatementsRequest()
    request.financialStatements = msft.financials
    fs.createFinancialStatements(request, "MSFT")
}