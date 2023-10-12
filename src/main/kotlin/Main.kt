
fun main() {
    // 코틀린은 많은 컬렉션 팩토리 함수를 지원한다
    val list = arrayListOf(1, 2, 3)

    // To 는 pair를 생성해주는 역할이며 point같은거 나타낼때 사용
    val map = hashMapOf("foo" to 1, "bar" to 2)

    val pair = 1 to "one"

    val point = Pair(1, 2) // 1 to 2 와 동일하다
    val info = listOf(Pair("name", "kotlin"), Pair("age", "1"))
    info[0].first == "name"
    info[0].second == "kotlin"

    // 이름 붙인 인자
    // 동일한 타입의 인자가 여러개 일때 유용하다

   /* val james = People("james", "seoul", "google")
    val jayden = People(office = "kakao", address = "seoul", name = "jayden", age = 25)

    // default parameter 와도 많이 쓰인다
    val jason = People("jason", "seoul", "kakao", age = 1)
*/
    /*
    fun foo(a: Int, b: String= "") {
    }

    */
    //자바에는 디폴트 파라메터가 없어서 코틀린으로 짜서 디컴파일 하면 여러개의 함수로 보인다
    fun foo(a: Int, b: String) {
    }

    fun foo(a: Int) {
        foo(a, "")
    }
    // 코틀린, 스위프트, go, rust

    // 최상위 함수와 프로퍼티는 유용하지만 객체 지향적 측면에서는 캡슐화를 깨는 특성이 있어서 잘써야 한다
    // 책에서 나온것처럼 실제로는 클래스의 속해있다

    // Main_kt.bar()

    // 확장 함수와 확장 프로퍼티는 진짜 많이 쓴다
    fun String.lastChar(): Char = this[this.length - 1]

    // 중위 호출은 나중에 배울 DSL과 엮어서 많이 쓴다
    1 something 2

    // 코틀린의 모든 클래스와 메소드는 기본적으로 닫혀 있기 때문에 open키워드로 열어줘야 한다
    // 불변성 선호

    open class People(val name: String, val address: String, val office: String, val age: Int = 20)

    // class Child:People(){}

    // 자바에 없던 internal이라는 키워드 추가 됐다
    // 중첩 클래스도 가끔 쓴다

    class People2 {

        inner class Office {
            fun foo() = "Welcome to Kotlin"
        }
    }
    val office = People2().Office()

    // 봉인된 클래스
    // 상속을 한정 지을수 있어서 when문과 궁합이 좋다

    val partner = Partner(AContract())

    val contractInfo = when (partner.contract) {
        is AContract -> "A"
        is BContract -> "B"
    }

    // 주 생성자와 부생성자가 있으며 체이닝으로 선언할수도 있다
    val somthing = Something("a", "b", "c", "d", "e")

    // 백킹 필드(뒷받침 필드)와 게터 세터의 의미가 4장에서 나왔다

    // Data 클래스

    val food1 = Food("chicken", 10000)
    val food2 = Food("chicken", 10000)
    // 두개의 객체는 동일한가?
    // class의 경우 아니다 => 주소값 비교

    // 따라서 equals의 재정의 및 hashCode의 재정의가 필요하다
    // 또한 toString도 지원하며 spring의 롬복에 있던 @Data를 코틀린은 네이티브로 받아들였다
    // copy()메소드도 만들어 준다 (단, 얕은 복사를 사용하기에 컬렉션이 존재한다면 사용자가 다시 정의해야한다)
    println(food1 == food2)
    println(food1.toString())
    val food3 = food1.copy(price = 20000)
    println(food3)

    // 위임 패턴도 많이 쓴다

    val 게으름뱅이 = 게으름뱅이(listOf("a", "b", "c"))
    게으름뱅이.iterator()

    val map2 = DelegateMap(mutableMapOf("a" to "this is a", "b" to "this is b"))
    println(map2.a)

    // Object 키워드는 싱글톤을 만들때 많이 사용하며

    // Companion object는 자바의 static과 비슷하다
    println(CompanionClass.name)
}

class Partner(
    val contract: Contract,
)

sealed interface Contract

class AContract : Contract

class BContract : Contract

fun bar() {
}

infix fun Any.something(any: Any) {
}

// 주 생성자와 부생성자가 있으며 체이닝으로 선언할수도 있다
data class Something constructor( // 주 생성자
    val bar1: String,
    val bar2: String,
    val bar3: String,
) {
    init {
        println("hello I'm 3")
    }

    // 부 생성자
    constructor(a: String, b: String, c: String, d: String) : this(a, b + c, d) {
        println("hello I'm 4")
    }

    // 부 생성자
    constructor(a: String, b: String, c: String, d: String, e: String) : this(a, b, c, d + e) {
        println("hello I'm 5")
    }

    companion object {
        fun factory(a: String, b: String, c: String, d: String) =
            Something(a, b + c, d)
    }
}

// lombok
// @Getter
// @HashCodeAndEquals
// @ToString
// @Data
data class Food(val name: String, val price: Int, val ingredients: List<String> = listOf())

class 게으름뱅이(
    private val list: List<String>,
) : Iterable<String> by list

class DelegateMap(
    private val map: MutableMap<String, String> = mutableMapOf(),
) {
    val a: String by map
}

object Singleton {
    val name: String = "singleton"
}

// 싱글톤이 대규모 프로그램에 좋지 않은 이유 => 내부의 값이 변함
object Singleton2 {
    var name: String = "singleton"
}

// class Singleton2_class{
// static Singleton2_class instance = new Singleton2_class();
// String name = "singleton"
// }

class CompanionClass {
    companion object {
        val name = "companion"
        val name2 = "companion2"
    }
}

// class CompanionClass{
// static String name2 = "companion2"
// static Companion companion = new Companion();
// static classs Companion{
//      String name = "companion"
// }
// }
// CompanionClass.companion.name
