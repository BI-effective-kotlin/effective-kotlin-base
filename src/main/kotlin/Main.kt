

fun main(args: Array<String>) {
    // 2020년 4대 핫한 언어 swift, rust, go, kotlin
    // 자바와 상호 운영성

    // 코드 축약
    println("Hello World!")

    val a = readln().toInt()

    // var val
    // 변경가능성 최소화
    var b = 10
    val c = 11

    b = 20
    // c = 12

    // 타입 추론 특화
    var s = "Hello"

    // Optional을 대체할 nullable
    // NPE는 코틀린 개발자의 수치
    var str1: String? = null
    var str2: String = "Hello"

    // 함수형 프로그래밍 지원
    // SAM
    // val foo2 = ::foo

    // Getter setter 왜쓰지
    val test = Test(10, "Hello")
    test.a = 20
    println(test.a)

    // Switch case를 대체하는 when문
    when (test.a) {
        10 -> println("10")
        20 -> println("20")
        else -> println("else")
    }

    // if문과 when문은 statement(문)에서 expression(식)이 되었다
    var aaa = when (test.a) {
        10 -> println("10")
        20 -> println("20")
        else -> println("else")
    }
    // 스마트 캐스트

    var parent: Parent = FirstChild()

    if (parent is FirstChild) {
        println(parent.a)
    }

    when (parent) {
        is FirstChild -> println(parent.a)
        is SecondChild -> println(parent.b)
    }

    val string: String? = "hello"

    if (string != null) {
        println(string.length)
    }
    // for each문 지향
    //    Downto
    for (i in 0..10) {
        println(i)
    }

    for (i in 10 downTo 0) {
        println(i)
    }

    for (i in 10 downTo 0) {
        println("Hello")
    }

    // 인덱스가 필요 없으면 repeat를 지향
    repeat(10) {
        println("Hello")
    }

    // Try -catch finally
    var result2: Int? = null
    try {
        val num = readln().toInt()
        result2 = 10 / num
    } catch (e: Exception) {
        result2 = null
    } finally {
        println(result2)
    }
    val result = runCatching {
        val num = readln().toInt()
        10 / num
    }
    println(result.getOrNull())

    val int: Int = 10

    val int2: Int = int + 10

    val itoS = int.toString()

    // 빌드 과정이 조금더 오래걸림
    // 그러나 이마저도 K2 컴파일러에 의해 해결
    // 1.9.0
    // 2.0.0 K2

    val list: List<Int> = listOf()
    val mList: MutableList<Int> = mutableListOf()
    mList.add(10)

    // list.add()

    // backing property란 무엇인가
    val test2 = Test2(mutableListOf())
    println(test2.list.size)
    // test2.list.add(10)
}

class Test(
    _tempA: Int,
    val b: String,
) {

    var a = _tempA
        get() {
            return this_is_private
        }

    private var this_is_private = 10
}

class Test2(
    private val _backingList: MutableList<Int>,
) {
    val list: List<Int>
        get() = _backingList
}

open class Parent {
    open fun foo() {
        println("Parent")
    }
}
class FirstChild : Parent() {
    val a = 10
    override fun foo() {
        println("FirstChild")
    }
}

class SecondChild : Parent() {
    val b = 10
    override fun foo() {
        println("SecondChild")
    }
}
