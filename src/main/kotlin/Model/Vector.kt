package Model

/**
 * Mathematically defined as a quantity with direction as well as magnitude.
 */
class Vector(vector: ArrayList<Int?>) {
    //instance variables
    var vector: MutableList<Int?>
        private set

    //public access methods
    var dimensions: Int = 0
        private set

    //constructor method
    init {
        validate(vector)
        this.vector = vector
        setDimensions()
    }

    /** validation check to ensure than the given vector is not empty. */
    private fun validate(vector: ArrayList<Int?>): Boolean {
        return vector.isNotEmpty()
    }

    /** returns the number of dimensions in a given vector (zero if null). */
    private fun setDimensions() {
        var count = 0
        for (i in vector.indices) {
            count++
        }
        dimensions = count
    }

    fun setVector(newVector: ArrayList<Int?>) {
        validate(newVector)
        vector = newVector
        setDimensions()
    }
}
