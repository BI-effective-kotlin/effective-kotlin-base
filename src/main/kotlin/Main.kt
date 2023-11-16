fun main(args: Array<String>) {
    // 타입 이레이져 때문에 발생하는 문제가 매우 많다

    // 참고 https://github.com/next-step/kotlin-blackjack/blob/konempty/src/test/kotlin/dsl/builder/PersonBuilderTest.kt
    // kotlin jdsl을 쓰면 아래와 같이 예쁜 코드를 만들수 있다
    /*
    *            val person = PersonBuilder {
                    name("사용자")
                    company("회사명")
                    skills {
                        soft("A passion for problem solving")
                        hard("Kotlin")
                    }
                    languages {
                        "Korean" level 5
                        "English" level 1
                    }
                }.build()
    * */

    // 만약 위 코드를 빌더 패턴을 써서 만들면 아래와 같다
    /*
    * val person = PersonBuilder()
                .name("사용자")
                .company("회사명")
                .skills (
                    soft("A passion for problem solving"),
                    hard("Kotlin")
                )
                .languages (
                    "Korean" level 5,
                    "English" level 1
                )
                .build()
     */

    // 코틀린 DSL 가끔 쓰며 스프링 개발자들은 마주할 일이 많을것이다
    // Kotlin jdsl이 예시이며 우린 그걸 쓸예정
    // https://kotlin-jdsl.gitbook.io/docs/

    // 이외에도 jpa criteria, query dsl 등등 쿼리를 객체지향적으로 짜게 해주는 라이브러리가 있다

    // 코루틴은 쓰레드처럼 병렬 혹은 비동기 연산을 위해 존재한다
    // RxJava, RxSwift, Mono-Flux,  Actor 등 의 여러가지 비동기 프레임 워크의 단점인 성능을 크게 높혀준 기능

    // 그러나 쓰레드와는 다르게 컨텍스트 스위치 비용이 값싸며 쓰레드보다 빠르다
    // 프로세스 -> 쓰레드 -> 코루틴

    // 쓰레드는 OS가 관리하는 반면 코루틴은 어플리케이션에서 관리한다

    // JDK 21부터는 virtual thread가 나올 예정이다
}

fun <T> isList(a: List<T>): Boolean {
    //  이 코드는 비교가 불가능하다
    // 책에서 나온것처럼 자바에서는 없을수도 있기때문에 컴파일러가 막는다
    return a is List<String>
}

inline fun <reified T> isList2(a: List<T>): Boolean {
    // 레이파이드를 사용하면 막을수 있다 대신 iline이라는 제한이 생김
    return a is List<T>
}

class MyClass<in P, out R> {
    // 공변성 반공변성 말로 하면 어렵고 return타입에는 out 파라페터 타입에는 in을 쓰면 된다
}
