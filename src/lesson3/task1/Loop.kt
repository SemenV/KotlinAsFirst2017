@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import lesson1.task1.sqr
import java.util.*
import java.lang.Math.*

fun powInt(n: Int,x: Int): Int {
    return (pow(n.toDouble(),x.toDouble())).toInt()
}


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10,m) + digitCountInNumber(n % 10,m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var r1: Int = n
    var i = 0
    do{
        i++
        r1 /= 10

    }while (r1 != 0)
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if (n <= 2) 1 else fib(n - 2) + fib(n - 1)

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var maxNumber = Math.max(m,n)
    while ((maxNumber % m > 0) || (maxNumber % n > 0)){
        maxNumber++
    }
    return maxNumber
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var del = 2
    while (n % del > 0){
        del++
    }
    return del
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var del = 1
    for (i in 1..(n / 2)){
        if (n % i == 0) del = i
    }
    return del
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..Math.max(m,n)){
        if ((m % i == 0) && (n % i == 0)) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in 0..(min(m,n) + 1)){
        var iDouble = i.toDouble()
        if (sqr(iDouble) in min(m,n)..max(m,n)) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var x1 = x
    while (x1 > 2 * PI) x1 -= 2 * PI
    while (x1 < 2 * PI) x1 += 2 * PI

    var k = 1
    var sign = 1
    var sum = 0.0
    while (pow(x1,k.toDouble()) / factorial(k) >= eps){
        var SeqMemb = sign * pow(x1,k.toDouble()) / factorial(k)
        k += 2
        sign *= -1
        sum += SeqMemb
    }
    return sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var varx = x
    while (varx > 2 * PI) varx -= 2 * PI
    while (varx < 2 * PI) varx += 2 * PI

    var k = 2
    var sign = -1
    var sum = 1.0
    while (pow(varx,k.toDouble()) / factorial(k) >= eps){
        var SeqMemb = sign * pow(varx,k.toDouble()) / factorial(k)
        k += 2
        sign *= -1
        sum += SeqMemb
    }
    return sum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var varn = n
    var newn = 0
    while (varn != 0){
        newn = newn * 10 + varn % 10
        varn /= 10
    }
    return newn
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var varn = n
    var revertn = revert(varn)
    while (revertn != 0){
        if ((revertn % 10) != (varn % 10)) return false
        revertn /= 10
        varn /= 10
    }
    return true
}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var newn = n
    while (newn != 0){
       if (n % 10 != newn % 10) return true
        newn /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var number = 1
    var sizeNumbers = 0
    var numberSquare = 0
    while (sizeNumbers  < n){
        numberSquare = number * number
        sizeNumbers  += digitNumber(numberSquare)
        number ++
    }

    return numberSquare / (powInt(10,(sizeNumbers  - n))) % 10
}
/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var number = 1
    var sizeNumbers = 0
    while (sizeNumbers < n) {
        sizeNumbers += digitNumber(fib(number))
        number++
    }
    val numberFib = fib(number - 1)
    return numberFib / (powInt(10, (sizeNumbers - n))) % 10
}
