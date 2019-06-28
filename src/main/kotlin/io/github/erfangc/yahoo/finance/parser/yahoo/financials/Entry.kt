package io.github.erfangc.yahoo.finance.parser.yahoo.financials

import java.time.LocalDate

internal data class Entry(val date: LocalDate, val value: Any, val label: String)