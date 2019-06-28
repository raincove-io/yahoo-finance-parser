package io.github.erfangc.yahoo.finance.parser.yahoo

import io.github.erfangc.raincove.sdk.apis.RaincoveSdk


fun main() {
    RaincoveSdk.setBaseUrl("http://localhost:8080")
    val fs = RaincoveSdk.financialStatements()
    fs.deleteFinancialStatements("MSFT")
}