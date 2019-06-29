package io.github.erfangc.yahoo.finance.parser

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import io.github.erfangc.raincove.sdk.apis.RaincoveSdk
import io.github.erfangc.raincove.sdk.models.CreateFinancialStatementsRequest
import io.github.erfangc.raincove.sdk.operations.Companies
import io.github.erfangc.raincove.sdk.operations.FinancialStatements
import io.github.erfangc.yahoo.finance.parser.yahoo.YahooFinanceRetriever
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

class YahooFinanceParser : CliktCommand(name = "yahoo-finance-parser") {
    override fun run() = Unit
}

class RunTicker : CliktCommand(name = "run-ticker") {

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

class RunUniverse : CliktCommand(name = "run-universe") {
    private val logger = LoggerFactory.getLogger(RunUniverse::class.java)
    private val endpoint: String? by option(help = "The API end point to call when saving results")
    private val universe: String by argument(name = "universe", help = "The universe is an exchange and can be: NYSE or NASDAQ")

    data class ProcessTickerRequest(
            val ticker: String,
            val name: String? = null,
            val sector: String? = null,
            val industry: String? = null
    )

    private val yahooFinanceRetriever = YahooFinanceRetriever()

    override fun run() {

        endpoint?.let {
            logger.info("Setting API end point to {}", endpoint)
            RaincoveSdk.setBaseUrl(it)
        }
        val companies = RaincoveSdk.companies()
        val financialStatements = RaincoveSdk.financialStatements()
        //
        // we can choose from either NYSE or NASDAQ
        //
        if (!setOf("NYSE", "NASDAQ").contains(universe)) {
            logger.error("Please specify a valid universe")
            System.exit(1)
        }
        val inputStream = RunUniverse::class.java.classLoader.getResourceAsStream("$universe.csv")
        val csvParser = CSVParser(inputStream.bufferedReader(), CSVFormat.DEFAULT.withFirstRecordAsHeader())
        csvParser.forEach {
            val ticker = it.get("Symbol")
            val name = it.get("Name")
            val sector = it.get("Sector")
            val industry = it.get("industry")
            if (sector != "n/a") {
                val request = ProcessTickerRequest(ticker = ticker, name = name, sector = sector, industry = industry)
                processTicker(request, financialStatements, companies)
            }
        }
        logger.info("Finished processing all companies in the universe {}", universe)
        csvParser.close()
    }

    private fun processTicker(request: ProcessTickerRequest, financialStatements: FinancialStatements, companies: Companies) {
        val (ticker, name, sector, _) = request
        try {
            val start = System.nanoTime()
            val yahooFinance = yahooFinanceRetriever.retrieve(ticker)
            val end = System.nanoTime()
            val createFinancialStatementsRequest = CreateFinancialStatementsRequest().apply {
                this.financialStatements = yahooFinance.financials
            }
            financialStatements.createFinancialStatements(createFinancialStatementsRequest, ticker)
            logger.info("Created financial statements {} to {}", ticker, endpoint)
            val company = yahooFinance.company
            company.name = name
            companies.createCompany(company)
            logger.info("Created company {} to {}", ticker, endpoint)
            logger.info(
                    "Finished processing $ticker ($name) in ${
                    TimeUnit.MILLISECONDS.convert(
                            end - start,
                            TimeUnit.NANOSECONDS
                    )
                    } ms"
            )
        } catch (e: Exception) {
            logger.error("Error while processing $ticker ($name), sector=$sector: ${e.message}")
        }
    }

}