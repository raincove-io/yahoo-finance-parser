package io.github.erfangc.yahoo.finance.parser

import com.github.ajalt.clikt.core.subcommands

class AppKt {
    fun main(args: Array<String>) = App().subcommands(RunTicker()).main(args)
}

