package io.github.erfangc.yahoo.finance.parser

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import io.github.erfangc.raincove.sdk.apis.RaincoveSdk
import io.github.erfangc.raincove.sdk.models.CreateFinancialStatementsRequest
import io.github.erfangc.yahoo.finance.parser.yahoo.YahooFinanceRetriever
import org.slf4j.LoggerFactory

class YahooFinanceParser : CliktCommand(name = "yahoo-finance-parser") {
    override fun run() = Unit
}

class RunTicker : CliktCommand() {

    private val logger = LoggerFactory.getLogger(CliktCommand::class.java)
    private val endpoint: String? by option(help = "The API end point to call when saving results")
    private val ticker: String by argument(name = "ticker")

    override fun run() {
        val retriever = YahooFinanceRetriever()
        endpoint?.let {
            logger.info("Setting API end point to {}", endpoint)
            RaincoveSdk.setBaseUrl(it)
        }
        val companies = RaincoveSdk.companies()
        val financialStatements = RaincoveSdk.financialStatements()
        val yahooFinance = retriever.retrieve(ticker = ticker)
        val request = CreateFinancialStatementsRequest().apply {
            this.financialStatements = yahooFinance.financials
        }
        financialStatements.createFinancialStatements(request, ticker)
        logger.info("Created financial statements {} to {}", ticker, endpoint)
        companies.createCompany(yahooFinance.company)
        logger.info("Created company {} to {}", ticker, endpoint)
    }

}
