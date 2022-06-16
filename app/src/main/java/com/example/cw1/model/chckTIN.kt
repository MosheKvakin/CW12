package com.example.cw1.model

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class chckTIN {

    val MULT_N1 = arrayOf(7, 2, 4, 10, 3, 5, 9, 4, 6, 8)
    val MULT_N2 = arrayOf(3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8)
    val MULT_N = arrayOf(2, 4, 10, 3, 5, 9, 4, 6, 8)


    fun checkTinSc(innStr: String) =  GlobalScope.async {
        var valid = checkTin(innStr)
        return@async valid
    }

    fun checkTin(innStr: String): Boolean {
        val valid: Boolean
        val inn = stringToIntArray(innStr)
        val innSize = inn.size
        valid = when (innSize) {
            12 -> {
                val N1 = getChecksum(inn, MULT_N1)
                val N2 = getChecksum(inn, MULT_N2)
                inn[inn.size - 1] == N2 && inn[inn.size - 2] == N1
            }
            10 -> {
                val N = getChecksum(inn, MULT_N)
                inn[inn.size - 1] == N
            }
            else -> false
        }
        return valid
    }

    private fun stringToIntArray(src: String): Array<Int> {
        val chars = src.toCharArray()
        val digits = ArrayList<Int>()
        for (aChar in chars) {
            digits.add(Character.getNumericValue(aChar))
        }
        return digits.toTypedArray()
    }

    private fun getChecksum(digits: Array<Int>, multipliers: Array<Int>): Int {
        var checksum = 0
        for (i in multipliers.indices) {
            checksum += digits[i] * multipliers[i]
        }
        return checksum % 11 % 10
    }

}