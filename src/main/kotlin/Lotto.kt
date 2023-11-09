data class Lotto(private val numbers: List<LottoNumber>) {

    fun getMaxNumber(): LottoNumber {
        return numbers.maxByOrNull { it.getValue() }!!
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
    }
}

@JvmInline
value class LottoNumber(private val number: Long) {
    fun getValue(): Long {
        return number
    }

    companion object {
        private const val MINIMUM_NUMBER = 1L
        private const val MAXIMUM_NUMBER = 45L
    }
}
