package io.github.erfangc.yahoo.finance.parser

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import io.github.erfangc.raincove.sdk.apis.RaincoveSdk
import io.github.erfangc.raincove.sdk.models.CreateOrUpdateCompanyRequest
import io.github.erfangc.raincove.sdk.models.CreateOrUpdateFinancialStatementsRequest
import io.github.erfangc.yahoo.finance.parser.yahoo.YahooFinanceRetriever
import org.slf4j.LoggerFactory

class RunTicker : CliktCommand(name = "run-ticker") {

    private val logger = LoggerFactory.getLogger(CliktCommand::class.java)
    private val endpoint: String? by option(help = "The API end point to call when saving results")
    private val ticker: String by argument(name = "ticker")

    override fun run() {
        val retriever = YahooFinanceRetriever()
        endpoint?.let {
            logger.info("Setting API end point to {}", endpoint)
            RaincoveSdk.setEndpoint(it)
        }
        val finser = RaincoveSdk.finser()
        val yahooFinance = retriever.retrieve(ticker = ticker)
        val fsRequest = CreateOrUpdateFinancialStatementsRequest().apply {
            this.financialStatements = yahooFinance.financials
        }
        finser.createOrUpdateFinancialStatements(fsRequest, ticker)
        logger.info("Created financial statements {} to {}", ticker, endpoint)
        val companyRequest = CreateOrUpdateCompanyRequest().apply {
            this.company = yahooFinance.company
        }
        finser.createOrUpdateCompany(companyRequest)
        logger.info("Created company {} to {}", ticker, endpoint)
    }

}