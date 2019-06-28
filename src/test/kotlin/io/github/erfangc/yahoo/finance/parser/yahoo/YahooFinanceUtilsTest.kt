package io.github.erfangc.yahoo.finance.parser.yahoo

import org.junit.Assert.assertEquals
import org.junit.Test

class YahooFinanceUtilsTest {

    @Test
    fun cashflowStatementItems() {
        assertEquals("netIncome",
            YahooFinanceUtils.formatLabel("Net Income")
        )
        assertEquals("depreciation",
            YahooFinanceUtils.formatLabel("Depreciation")
        )
        assertEquals("adjustmentsToNetIncome",
            YahooFinanceUtils.formatLabel("Adjustments To Net Income")
        )
        assertEquals("changesInAccountsReceivables",
            YahooFinanceUtils.formatLabel("Changes In Accounts Receivables")
        )
        assertEquals("changesInLiabilities",
            YahooFinanceUtils.formatLabel("Changes In Liabilities")
        )
        assertEquals("changesInInventories",
            YahooFinanceUtils.formatLabel("Changes In Inventories")
        )
        assertEquals(
            "changesInOtherOperatingActivities",
            YahooFinanceUtils.formatLabel("Changes In Other Operating Activities")
        )
        assertEquals(
            "totalCashFlowFromOperatingActivities",
            YahooFinanceUtils.formatLabel("Total Cash Flow From Operating Activities")
        )
        assertEquals("capitalExpenditures",
            YahooFinanceUtils.formatLabel("Capital Expenditures")
        )
        assertEquals("investments",
            YahooFinanceUtils.formatLabel("Investments")
        )
        assertEquals(
            "otherCashFlowsFromInvestingActivities",
            YahooFinanceUtils.formatLabel("Other Cash flows from Investing Activities")
        )
        assertEquals(
            "totalCashFlowsFromInvestingActivities",
            YahooFinanceUtils.formatLabel("Total Cash Flows From Investing Activities")
        )
        assertEquals("dividendsPaid",
            YahooFinanceUtils.formatLabel("Dividends Paid")
        )
        assertEquals("netBorrowings",
            YahooFinanceUtils.formatLabel("Net Borrowings")
        )
        assertEquals(
            "totalCashFlowsFromFinancingActivities",
            YahooFinanceUtils.formatLabel("Total Cash Flows From Financing Activities")
        )
        assertEquals(
            "changeInCashAndCashEquivalents",
            YahooFinanceUtils.formatLabel("Change In Cash and Cash Equivalents")
        )
    }

    @Test
    fun incomeStatementItems() {
        assertEquals("totalRevenue",
            YahooFinanceUtils.formatLabel("Total Revenue")
        )
        assertEquals("costOfRevenue",
            YahooFinanceUtils.formatLabel("Cost of Revenue")
        )
        assertEquals("grossProfit",
            YahooFinanceUtils.formatLabel("Gross Profit")
        )
        assertEquals("researchDevelopment",
            YahooFinanceUtils.formatLabel("Research Development")
        )
        assertEquals(
            "sellingGeneralAndAdministrative",
            YahooFinanceUtils.formatLabel("Selling General and Administrative")
        )
        assertEquals("totalOperatingExpenses",
            YahooFinanceUtils.formatLabel("Total Operating Expenses")
        )
        assertEquals("operatingIncomeOrLoss",
            YahooFinanceUtils.formatLabel("Operating Income or Loss")
        )
        assertEquals("totalOtherIncomeOrExpensesNet",
            YahooFinanceUtils.formatLabel("Total Other Income/Expenses Net")
        )
        assertEquals(
            "earningsBeforeInterestAndTaxes",
            YahooFinanceUtils.formatLabel("Earnings Before Interest and Taxes")
        )
        assertEquals("interestExpense",
            YahooFinanceUtils.formatLabel("Interest Expense")
        )
        assertEquals("incomeBeforeTax",
            YahooFinanceUtils.formatLabel("Income Before Tax")
        )
        assertEquals("incomeTaxExpense",
            YahooFinanceUtils.formatLabel("Income Tax Expense")
        )
        assertEquals("netIncomeFromContinuingOps",
            YahooFinanceUtils.formatLabel("Net Income From Continuing Ops")
        )
        assertEquals("netIncome",
            YahooFinanceUtils.formatLabel("Net Income")
        )
        assertEquals(
            "netIncomeApplicableToCommonShares",
            YahooFinanceUtils.formatLabel("Net Income Applicable To Common Shares")
        )
    }

    @Test
    fun balanceSheetItems() {
        assertEquals("cashAndCashEquivalents",
            YahooFinanceUtils.formatLabel("Cash And Cash Equivalents")
        )
        assertEquals("shortTermInvestments",
            YahooFinanceUtils.formatLabel("Short Term Investments")
        )
        assertEquals("netReceivables",
            YahooFinanceUtils.formatLabel("Net Receivables")
        )
        assertEquals("inventory",
            YahooFinanceUtils.formatLabel("Inventory")
        )
        assertEquals("otherCurrentAssets",
            YahooFinanceUtils.formatLabel("Other Current Assets")
        )
        assertEquals("totalCurrentAssets",
            YahooFinanceUtils.formatLabel("Total Current Assets")
        )
        assertEquals("longTermInvestments",
            YahooFinanceUtils.formatLabel("Long Term Investments")
        )
        assertEquals("propertyPlantAndEquipment",
            YahooFinanceUtils.formatLabel("Property Plant and Equipment")
        )
        assertEquals("otherAssets",
            YahooFinanceUtils.formatLabel("Other Assets")
        )
        assertEquals("totalAssets",
            YahooFinanceUtils.formatLabel("Total Assets")
        )
        assertEquals("accountsPayable",
            YahooFinanceUtils.formatLabel("Accounts Payable")
        )
        assertEquals("shortOrCurrentLongTermDebt",
            YahooFinanceUtils.formatLabel("Short/Current Long Term Debt")
        )
        assertEquals("otherCurrentLiabilities",
            YahooFinanceUtils.formatLabel("Other Current Liabilities")
        )
        assertEquals("totalCurrentLiabilities",
            YahooFinanceUtils.formatLabel("Total Current Liabilities")
        )
        assertEquals("longTermDebt",
            YahooFinanceUtils.formatLabel("Long Term Debt")
        )
        assertEquals("otherLiabilities",
            YahooFinanceUtils.formatLabel("Other Liabilities")
        )
        assertEquals("totalLiabilities",
            YahooFinanceUtils.formatLabel("Total Liabilities")
        )
        assertEquals("commonStock",
            YahooFinanceUtils.formatLabel("Common Stock")
        )
        assertEquals("retainedEarnings",
            YahooFinanceUtils.formatLabel("Retained Earnings")
        )
        assertEquals("treasuryStock",
            YahooFinanceUtils.formatLabel("Treasury Stock")
        )
        assertEquals("otherStockholderEquity",
            YahooFinanceUtils.formatLabel("Other Stockholder Equity")
        )
        assertEquals("totalStockholderEquity",
            YahooFinanceUtils.formatLabel("Total Stockholder Equity")
        )
        assertEquals("netTangibleAssets",
            YahooFinanceUtils.formatLabel("Net Tangible Assets")
        )
    }
}