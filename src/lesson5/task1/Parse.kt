@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

import java.text.SimpleDateFormat
import java.util.*

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    try {
        var partsLine = str.split(" ")
        var month = strMonthToInt(partsLine[1])
        return String.format("%02d.%02d.%d", partsLine[0].toInt(), month, partsLine[2].toInt())
    } catch (e: Exception) {
        return ""
    }
}

fun strMonthToInt(strMonth: String): Int = when (strMonth) {
    "января" -> 1
    "февраля" -> 2
    "марта" -> 3
    "апреля" -> 4
    "мая" -> 5
    "июня" -> 6
    "июля" -> 7
    "августа" -> 8
    "сентября" -> 9
    "октября" -> 10
    "ноября" -> 11
    "декабря" -> 12
    else -> throw Exception("$strMonth")
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String { //здесь тоже переделать?
    try {
        var partsLine = digital.split(".")
        if (partsLine.size != 3) return ""
        var month = strIntToMonth(partsLine[1])
        return String.format("%d %s %d", partsLine[0].toInt(), month, partsLine[2].toInt())
    } catch (e: Exception) {
        return ""
    }
}

fun strIntToMonth(strMonth: String): String = when (strMonth) {
    "01" -> "января"
    "02" -> "февраля"
    "03" -> "марта"
    "04" -> "апреля"
    "05" -> "мая"
    "06" -> "июня"
    "07" -> "июля"
    "08" -> "августа"
    "09" -> "сентября"
    "10" -> "октября"
    "11" -> "ноября"
    "12" -> "декабря"
    else -> throw Exception("Неправильно введен месяц")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val y = (Regex("""\[""").findAll(phone, 0).count() > 1) || (Regex("""\]""").findAll(phone, 0).count() > 1)
    if (phone.contains(Regex("""[^\+\d \(\)-]""")) || (!phone.contains(Regex("""\d""")))) return ""
    val newPhone = phone.replace(Regex("""[^\+\d]"""), "")
    if ((Regex("""\+""").findAll(newPhone, 1).count() > 0)) return ""
    else return newPhone
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (jumps.contains(Regex("""[^\d \%-]"""))) return -1
    val partsLine = jumps.split(" ")
    var max = -1
    for (i in 0 until partsLine.size)
        if ((partsLine[i].contains(Regex("""\d"""))) && (max < partsLine[i].toInt())) max = partsLine[i].toInt()
    return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var str = jumps
    if (jumps.contains(Regex("""[^\d \%\+\-]"""))) return -1
    str = str.replace(Regex("""%"""), "")
    var list = str.split(" ")
    var max = -1
    for (i in 1 until list.size) {
        if ((list[i] == "+") && (list[i - 1].toInt() > max)) max = list[i - 1].toInt()
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (expression.contains(Regex("""[^\+\d\- ]"""))) throw IllegalArgumentException("")
    try {
        var str = expression.split(" ")
        var sum = str[0].toInt()
        for (i in 1 until str.size) {
            when (str[i]) {
                "+" -> sum += str[i + 1].toInt()
                "-" -> sum -= str[i + 1].toInt()
            }
        }
        return sum
    } catch (e: Exception) {
        throw IllegalArgumentException("")
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var vocList = str.toLowerCase().split(" ")
    var vocLength = 0
    for (i in 0 until vocList.size - 1) {
        if ((vocList[i]) == vocList[i + 1]) return vocLength + i
        vocLength += vocList[i].length
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    return try {
        var feedList = description.split(Regex(""" |; """))
        var max = 0.0
        var element = 0
        for (i in 1..feedList.size step 2)
            if (max < feedList[i].toDouble()) {
                max = feedList[i].toDouble()
                element = i - 1
            }
        feedList[element]
    } catch (e: Exception) {
        ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    if (commands.contains(Regex("""[^\[\]\+\->< ]"""))) throw IllegalArgumentException()
    var count = 0
    for (i in 0 until commands.length) {
        if (commands[i] == '[') count++
        if (commands[i] == ']') count--
        if (count < 0) break
    }
    if (count != 0) throw IllegalArgumentException()
    var cellsList = MutableList(cells, { 0 })
    var numberList = cells / 2
    var limitCount = 0
    var numberOfCommand = -1
    while ((numberOfCommand + 1 < commands.length) && (limitCount < limit)) {
        numberOfCommand++
        limitCount++
        when (commands[numberOfCommand]) {
            '>' -> numberList++
            '<' -> numberList--
            '-' -> cellsList[numberList]--
            '+' -> cellsList[numberList]++
            ' ' -> {
            }
            '[' -> {
                if (cellsList[numberList] == 0) {
                    numberOfCommand = findEndBacket(commands, numberOfCommand)
                }
            }
            ']' -> {
                if (cellsList[numberList] != 0) {
                    numberOfCommand = findStartBacket(commands, numberOfCommand)
                }
            }
        }
        if (numberList !in 0 until cells) throw IllegalStateException("$numberList")
    }
    return cellsList.toList()
}

fun findEndBacket(str: String, startIndex: Int): Int {
    var count = 1
    var i = startIndex
    while (count != 0) {
        i++
        if (i > str.length) throw IllegalArgumentException()
        if (str[i] == '[') count++
        if (str[i] == ']') count--
    }
    return i
}

fun findStartBacket(str: String, startIndex: Int): Int {
    var count = 1
    var i = startIndex
    while (count != 0) {
        i--
        if (i < 0) throw IllegalArgumentException()
        if (str[i] == ']') count++
        if (str[i] == '[') count--
    }
    return i
}

