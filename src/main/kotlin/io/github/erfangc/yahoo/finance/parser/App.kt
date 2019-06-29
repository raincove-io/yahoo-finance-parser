package io.github.erfangc.yahoo.finance.parser

import com.github.ajalt.clikt.core.subcommands

fun main(args: Array<String>) = YahooFinanceParser()
    .subcommands(RunTicker(), RunUniverse())
    .main(args)

