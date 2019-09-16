package pl.deesoft.cv.data.mapper

interface Mapper<T1, T2> {

    fun transform(input: T1): T2

    fun reverse(input: T2): T1 {
        throw NotImplementedError()
    }

    fun transform(inputList: List<T1>): List<T2> {
        return inputList.map {
            transform(it)
        }
    }

    fun reverse(inputList: List<T2>): List<T1> {
        return inputList.map {
            reverse(it)
        }
    }
}