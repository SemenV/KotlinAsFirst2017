@file:Suppress("UNUSED_PARAMETER")
package lesson6.task2

import java.lang.Math.abs
import javax.swing.text.Segment

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String = if (!inside()) "" else "${'a' + column - 1}$row"
}

/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    if (notation.length != 2) throw IllegalArgumentException()
    val column = notation[0] - 'a' + 1
    val row = notation[1] - '0'
    val point = Square(column, row)
    if (!point.inside()) throw IllegalArgumentException()
    return point
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int = when {
    !start.inside() || !end.inside() -> throw IllegalArgumentException()
    start == end -> 0
    start.column == end.column || start.row == end.row -> 1
    else -> 2
}


/**
 * Средняя
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> {
    var listTraject = mutableListOf(start)
    if (start.column != end.column) listTraject.add(Square(end.column, start.row))
    if (start.row != end.row) listTraject.add(Square(end.column, end.row))
    return listTraject.toList()
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int = when {
    !start.inside() || !end.inside() -> throw IllegalArgumentException()
    start == end -> 0
    (start.column + end.column) % 2 != (start.row + end.row) % 2 -> -1
    abs(end.row - start.row) == abs(end.column - start.column) -> 1
    else -> 2
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> = TODO()

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int =
        if ((start.inside() && end.inside())) findWay(start, end, KingGraph()).size - 1 else throw IllegalArgumentException()

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> = findWay(start, end, KingGraph())

/**
 * Сложная
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */
fun knightMoveNumber(start: Square, end: Square): Int =
        if ((start.inside() && end.inside())) findWay(start, end, KnightGraph()).size - 1 else throw IllegalArgumentException()


/**
 * Очень сложная
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> = findWay(start, end, KnightGraph())


fun findWay(start: Square, end: Square, smartGrph: Graph): List<Square> {
    smartGrph.addVertex(start.notation())
    var lastPositions = mutableListOf(start.notation())
    while (end.notation() !in smartGrph.getVerticesNames()) { //цикл создания вершин. Проверка наличия end
        var newVertS = mutableListOf<String>()
        for (element in lastPositions) {                //перебираем все недавние позиции
            var possibleWay = smartGrph.unusedSteps(square(element))
            possibleWay.forEach {
                //добавление новых вершинн для соответствующего елемента, и соеденинение их
                smartGrph.addVertex(it)
                smartGrph.connect(it, element)
            }
            newVertS.addAll(possibleWay)
        }
        lastPositions = newVertS
    }
    var stepList = mutableListOf(end)
    var stepElement = end
    while (start !in stepList) {
        var b = smartGrph.getParent(stepElement.notation())[0]
        stepElement = square(b)
        stepList.add(stepElement)
    }
    return stepList.reversed()
}

abstract class Graph {
    protected data class Vertex(val name: String) {
        val parentVertex = mutableListOf<Vertex>()
    }

    protected val vertices = mutableMapOf<String, Vertex>()

    protected operator fun get(name: String) = vertices[name] ?: throw IllegalArgumentException()

    fun connect(first: String, second: String) = connect(this[first], this[second])

    protected fun connect(child: Vertex, parent: Vertex) {
        child.parentVertex.add(parent)
    }

    fun getParent(name: String) = vertices[name]?.parentVertex?.map { it.name } ?: listOf()

    fun getVerticesNames() = this.vertices.keys

    fun addVertex(name: String) {
        vertices[name] = Vertex(name)
    }

    abstract fun unusedSteps(position: Square): List<String>
}

class KnightGraph : Graph() {
    override fun unusedSteps(position: Square): List<String> {
        val step1 = Square(position.column + 2, position.row + 1)
        val step2 = Square(position.column + 2, position.row - 1)
        val step3 = Square(position.column + 1, position.row + 2)
        val step4 = Square(position.column + 1, position.row - 2)
        val step5 = Square(position.column - 1, position.row - 2)
        val step6 = Square(position.column - 1, position.row + 2)
        val step7 = Square(position.column - 2, position.row + 1)
        val step8 = Square(position.column - 2, position.row - 1)
        var finishList = mutableListOf<String>()
        var someList = mutableListOf(step1, step2, step3, step4, step5, step6, step7, step8)
        for (i in someList) {
            if (i.inside() && (i.notation() !in vertices.keys)) finishList.add(i.notation())
        }
        return finishList.toList()
    }
}

class KingGraph : Graph() {
    override fun unusedSteps(position: Square): List<String> {
        val step1 = Square(position.column, position.row + 1)
        val step2 = Square(position.column, position.row - 1)
        val step3 = Square(position.column + 1, position.row + 1)
        val step4 = Square(position.column + 1, position.row - 1)
        val step5 = Square(position.column + 1, position.row)
        val step6 = Square(position.column - 1, position.row + 1)
        val step7 = Square(position.column - 1, position.row - 1)
        val step8 = Square(position.column - 1, position.row)
        var finishList = mutableListOf<String>()
        var someList = mutableListOf(step1, step2, step3, step4, step5, step6, step7, step8)
        for (i in someList) {
            if (i.inside() && (i.notation() !in vertices.keys)) finishList.add(i.notation())
        }
        return finishList.toList()
    }
}


