@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import com.sun.jdi.CharValue
import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (i in 0 until v.size){
        var k = 1.0
        sum = pow(v[i],k)
        k++
    }
    return sum
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    return list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = mean(list)
    for (i in 0 until list.size){
        list[i] -= average
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if ((a.isEmpty()) && (b.isEmpty())) return 0.0
    var sum = 0.0
    for(element in 0 until a.size){
        sum += a[element] * b[element]
    }
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) return 0.0
    var sum = 0.0
    for(element in 0 until p.size){
        sum += p[element] * pow(x,element.toDouble())
    }
    return sum

}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    var sum = list[0]
    for (i in 1 until list.size){
        sum += list[1]
        list.add(sum)
        list.removeAt(1)
    }
      return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var newn = n
    var a = mutableListOf<Int>()
    var number = 2
    while (number <= newn){
        if (newn % number == 0){
            a.add(number)
            newn /= number
            number--

        }
        number++
    }
    return a.sorted().toList()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var newn = n
    var a = mutableListOf<Int>()

    do{
        a.add(0,newn % base)
        newn /= base

    }while (newn > base)
    if (newn != 0)  a.add(0,newn)
    return a.toList()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var converList = convert(n,base)
    var str = ""
    for (element in 0 until converList.size) {
        if (converList[element] > 9) str += '0' + converList[element] + 39
        else str += '0' + converList[element]
    }
    return str
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var sum = digits[digits.size - 1]
    var power = digits.size - 1
    for (element in 0..digits.size - 2){
        sum += digits[element] * powInt(base,power)
        power--
    }
    return sum
}
fun powInt(a: Int, b: Int) = (pow(a.toDouble(),b.toDouble())).toInt()

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var a = mutableListOf<Int>()
    for (i in 0 until str.length){
        a.add(str[i].toInt())
        if (a[i] > 59) a[i] -= 87 else a[i] -=48
    }
    return decimal(a,base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var str = ""
    val firt3Number = hundred(n / 100000) + dozensAndUnits(n / 1000 % 1000,1)
    //собираем 1xx xxx, x11 xxx, xx1 xxx Причем x11 xxx, xx1 xxx - зависят друг от друга
    val last3Number = hundred(n / 100 % 10) + dozensAndUnits(n % 1000,2)
    //то же самое, только для последних 3ех элементов

    if (n / 1000 > 0) {
        if (n / 1000 % 10 != 0)
            str = firt3Number + tisa4i(n / 1000 % 10) + last3Number
        else
            str = firt3Number + " тысяч" + last3Number
    }
    else str = last3Number

    return str.trim()
}
fun dozensAndUnits(b: Int,c: Int): String{
    var str = ""
    val first = b / 100
    val second = b / 10 % 10
    val thread = b % 10
    //if ((first == 0) && (second == 0) && (thread == 1)) str + " один"
    //if ((first == 0) && (second == 0) && (thread == 2)) str + " два"
    return when{
        (second ==1) && (thread == 0) -> str + " десять"
        (second ==1) && (thread != 0) -> str + units_1_(thread)
        (c == 2) && (first == 0) && (second == 0) && (thread == 1) -> str + " один"
        (c == 2) && (first == 0) && (second == 0) && (thread == 2) -> str + " два"
        (second == 0) -> str + units(thread)
        else -> str +dozens(second) + units(thread)
    }
}
fun units(a2: Int): String{
    return when(a2){
        1 -> " одна"
        2 -> " две"
        3 -> " три"
        4 -> " четыре"
        5 -> " пять"
        6 -> " шесть"
        7 -> " семь"
        8 -> " восемь"
        9 -> " девять"
        else -> ""
    }
}
fun dozens(b: Int): String {
    return when (b) {
        2 -> " двадцать"
        3 -> " тридцать"
        4 -> " сорок"
        5 -> " пятдесят"
        6 -> " шестьдесят"
        7 -> " семьдесят"
        8 -> " восемьдесят"
        9 -> " девяносто"
        else -> ""
    }
}

fun units_1_(a1: Int): String{
    return when(a1){
        1 -> " одинадцать"
        2 -> " двенадцать"
        3 -> " тридцать"
        4 -> " четырнадцать"
        5 -> " птнадцать"
        6 -> " шестнадцать"
        7 -> " семнадцать"
        8 -> " восемнадцать"
        9 -> " девятнадцать"
        else -> ""
    }
}
fun hundred(b: Int): String{
    return when(b){
        1 -> " сто"
        2 -> " двести"
        3 -> " триста"
        4 -> " четыресто"
        5 -> " пятьсот"
        6 -> " шетьсот"
        7 -> " семьсот"
        8 -> " восемьсот"
        9 -> " девятьсот"
        else -> ""
    }
}
fun tisa4i(first: Int): String{
    return when(first){
        1 -> " тысяча"
        2 -> " тысячи"
        3 -> " тысячи"
        4 -> " тысячи"
        5 -> " тысяч"
        6 -> " тысяч"
        7 -> " тысяч"
        8 -> " тысяч"
        9 -> " тысяч"
        else -> " тысяч"
    }
}