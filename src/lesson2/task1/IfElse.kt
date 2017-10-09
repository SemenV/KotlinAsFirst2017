@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import java.lang.Math.*


/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val secondNumber = age / 10 % 10
    val lastNumber = age % 10
    return when {
        secondNumber == 1 -> "$age лет"
        lastNumber == 1 -> "$age год"
        lastNumber in 2..4 -> "$age года"
        else -> "$age лет"
    }

}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val x = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    return when {
        x <= t1 * v1 -> x / v1
        x <= (t1 * v1) + (t2 * v2) -> t1 + (x - t1 * v1) / v2
        else -> t1 + t2 + (x - (t1 * v1 + t2 * v2)) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val kingRook1 = (kingX == rookX1) || (kingY == rookY1)
    val kingRook2 = (kingX == rookX2) || (kingY == rookY2)
    return when {
        kingRook1 && kingRook2 -> 3
        kingRook1 -> 1
        kingRook2 -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val damageBishop = abs(bishopX - kingX) == abs(bishopY - kingY)
    val damageRook = (kingX == rookX) || (kingY == rookY)
    return when {
        damageBishop && damageRook -> 3
        damageBishop -> 2
        damageRook -> 1
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val maxNumber = maxOf(a, b, c)
    val minNumber = minOf(a, b, c)
    val middleSide = a + b + c - maxNumber - minNumber
    if (maxNumber - minNumber - middleSide > 0) return -1
    val sqrtXMin = pow(minNumber, 2.0) + pow(middleSide, 2.0)
    return when {
        sqr(maxNumber) == sqrtXMin -> 1
        sqr(maxNumber) < sqrtXMin -> 0
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    (b >= d) && (a <= d) && (a >= c) -> d - a
    (b >= d) && (a <= c) -> d - c
    (b <= d) && (b >= c) && (a >= c) -> b - a
    (b <= d) && (b >= c) && (a <= c) -> b - c
    else -> -1
}
