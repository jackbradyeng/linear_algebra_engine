package main.kotlin.Model

import kotlin.random.Random

/** defines some pre-configured matrices for testing purposes. All values generated randomly.*/
class Generator {

    companion object {
        //defines an upper bound of the value of the integers generated
        private const val RANDOM_BOUND = 20
    }

    /** generates an array of random value integers subject to some upper limit. */
    @Throws(IllegalArgumentException::class)
    fun generateRandomMatrix(rows: Int, columns: Int): List<List<Double>> {
        require(!(rows < 1 || columns < 1)) { "Row and column counts must be greater than or equal to one." }

        return List(rows) {
            List(columns) { Random.nextInt(RANDOM_BOUND).toDouble() }
        }
    }

    /** generates an array of sequentially ordered values. */
    @Throws(IllegalArgumentException::class)
    fun generateSequentialMatrix(rows: Int, columns: Int): List<List<Double>> {
        require(!(rows < 1 || columns < 1)) { "Row and column counts must be greater than or equal to one." }

        return List(rows) { i ->
            List(columns) { j -> ((i * columns) + j + 1).toDouble() }
        }
    }
}
