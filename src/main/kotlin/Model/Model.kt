package main.kotlin.Model

data class Model(var size: Int, var scaleFactorOne: Int = 1, var scaleFactorTwo: Int = 1) {

    /** Creates a generator instance as well as matrix one, matrix two, and identity matrix instances. The model is only
     * designed to handle square matrices, therefore the two matrix instances can share a single identity. */
    var generator = Generator()
    var matrixOne = Matrix(generator.generateRandomMatrix(size, size))
    var matrixTwo = Matrix(generator.generateRandomMatrix(size, size))
    var matrixThree: Matrix? = null
    var identityMatrix = Matrix(matrixOne.identity)

    init {
        require(size in 1..9) { "Matrix size must be between 0 and 9." }
    }
}
