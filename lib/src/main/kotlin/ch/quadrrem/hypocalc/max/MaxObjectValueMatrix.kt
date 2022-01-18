package ch.quadrrem.hypocalc.max

import ch.quadrrem.hypocalc.GrossIncome
import ch.quadrrem.hypocalc.OwnFunds

class MaxObjectValueMatrix(val rows: List<Row>) {
    data class Row(val grossIncome: GrossIncome, val col: List<Column>)
    data class Column(val ownFunds: OwnFunds, val maxObjectValue: MaxObjectValue)
}