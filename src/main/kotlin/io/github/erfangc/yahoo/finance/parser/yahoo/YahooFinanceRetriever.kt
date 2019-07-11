package io.github.erfangc.yahoo.finance.parser.yahoo

import io.github.erfangc.raincove.sdk.models.Company
import io.github.erfangc.yahoo.finance.parser.yahoo.financials.FinancialsRetriever
import io.github.erfangc.yahoo.finance.parser.yahoo.summary.SummaryRetriever
import java.time.Instant

class YahooFinanceRetriever {

    fun retrieve(ticker: String): YahooFinance {
        val summaryRetriever = SummaryRetriever()
        val financialsRetriever = FinancialsRetriever()
        val summary = summaryRetriever.retrieveSummary(ticker)
        val financials = financialsRetriever.retrieveFinancials(ticker)
        val company = Company().apply {
            this.country = "US"
            this.id = summary.ticker
            this.idType = "ticker" // TODO use a assetId generator service
            this.sector = summary.sector
            this.industry = summary.industry
            this.description = summary.name
            this.ticker = summary.ticker
            this.marketCap = summary.marketCap
            this.previousClose = summary.previousClose
            this.createdBy = System.getProperty("user.name")
            this.createdOn = Instant.now().toString()
        }
        return YahooFinance(
                ticker = ticker,
                company = company,
                financials = financials,
                lastUpdated = Instant.now()
        )
    }

}