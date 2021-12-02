fun main() {
    fun countState(currentInput: Int, previousInput: Int): String {
        return if (currentInput > previousInput) {
            "increased"
        } else {
            "decreased"
        }
    }

    fun part1(input: List<String>): Int {
        val inputsInteger = input.map { it.toInt() }

        val mapOfIncrease = inputsInteger
            .foldIndexed(listOf<Pair<Int, String>>()) { index, acc, i ->
                if (index == 0) {
                    acc + Pair(i, "N/A - no previous measurement")
                } else {
                    acc + Pair(i, countState(i, inputsInteger[index - 1]))
                }
            }.groupBy { it.second }

        return mapOfIncrease["increased"]?.size ?: 0
    }

    fun part2(input: List<String>): Int {
        val inputsInteger = input.map { it.toInt() }
        val sums = inputsInteger.mapIndexed { index, i ->
            if (index + 2 <= inputsInteger.lastIndex) {
                i + inputsInteger[index + 1] + inputsInteger[index + 2]            
            } else {
                0
            }
            
        }.filter { it != 0 }
        
        val sumsIncreased = sums
            .foldIndexed(listOf<Pair<Int, String>>()) { index, acc, i ->
                if (index == 0) {
                    acc + Pair(i, "N/A - no previous measurement")
                } else {
                    acc + Pair(i, countState(i, sums[index - 1]))
                }
            }.groupBy { it.second }

        return sumsIncreased["increased"]?.size ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
