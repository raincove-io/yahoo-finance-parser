package io.github.erfangc.yahoo.finance.parser.yahoo.financials

import io.github.erfangc.yahoo.finance.parser.yahoo.TableParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import io.github.erfangc.raincove.sdk.models.BalanceSheet
import io.github.erfangc.raincove.sdk.models.CashflowStatement
import io.github.erfangc.raincove.sdk.models.GaapFinancialStatement
import io.github.erfangc.raincove.sdk.models.IncomeStatement
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.slf4j.LoggerFactory
import us.codecraft.xsoup.Xsoup
import java.time.Instant
import java.time.format.DateTimeFormatter.BASIC_ISO_DATE

class FinancialsRetriever {

    private val logger = LoggerFactory.getLogger(FinancialsRetriever::class.java)

    private val objectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false)

    fun retrieveFinancials(ticker: String): List<GaapFinancialStatement> {
        val incomeStatement = TableParser().parseTableWithColumnsAsDates(getIncomeStatementTable(ticker))
        val cashflowStatement = TableParser().parseTableWithColumnsAsDates(getCashflowStatementTable(ticker))
        val balanceSheet = TableParser().parseTableWithColumnsAsDates(getBalanceSheetTable(ticker))
        return incomeStatement.map { (date, metrics) ->
            val incomeStmt = objectMapper.treeToValue<IncomeStatement>(
                objectMapper.valueToTree(
                    metrics.mapValues { it.value.toString().toDouble() * 1000.0 }
                )
            )
            val bs = objectMapper.treeToValue<BalanceSheet>(
                objectMapper.valueToTree(
                    balanceSheet.getOrDefault(
                        date,
                        emptyMap()
                    ).mapValues { it.value.toString().toDouble() * 1000.0 })
            )
            val cfStmt = objectMapper.treeToValue<CashflowStatement>(
                objectMapper.valueToTree(
                    cashflowStatement.getOrDefault(date, emptyMap()).mapValues {
                        it.value.toString().toDouble() * 1000.0
                    }
                )
            )
            val ret = GaapFinancialStatement()
            ret.cashflowStatement = cfStmt
            ret.incomeStatement = incomeStmt
            ret.balanaceSheet = bs
            ret.createdBy = System.getProperty("user.name")
            ret.createdOn = Instant.now().toString()
            ret.filingType = "10K"
            ret.date = date.toString()
            ret.companyId = ticker
            ret.id = "${date.format(BASIC_ISO_DATE)}-10K"
            ret
        }
    }

    private fun getIncomeStatementTable(ticker: String): Element {
        logger.info("Retrieving income statement for $ticker ...")
        val incomeStatementDocument = Jsoup.connect("https://finance.yahoo.com/quote/$ticker/financials").get()
        return Xsoup
            .compile("//*[@id=\"Col1-1-Financials-Proxy\"]/section/div/table")
            .evaluate(incomeStatementDocument)
            .elements
            .first()
    }

    private fun getCashflowStatementTable(ticker: String): Element {
        logger.info("Retrieving cashflow statement for $ticker ...")
        val incomeStatementDocument = Jsoup.connect("https://finance.yahoo.com/quote/$ticker/cash-flow").get()
        return Xsoup
            .compile("//*[@id=\"Col1-1-Financials-Proxy\"]/section/div/table")
            .evaluate(incomeStatementDocument)
            .elements
            .first()
    }

    private fun getBalanceSheetTable(ticker: String): Element {
        logger.info("Retrieving balance sheet for $ticker ...")
        val incomeStatementDocument = Jsoup.connect("https://finance.yahoo.com/quote/$ticker/balance-sheet").get()
        return Xsoup
            .compile("//*[@id=\"Col1-1-Financials-Proxy\"]/section/div/table")
            .evaluate(incomeStatementDocument)
            .elements
            .first()
    }
}