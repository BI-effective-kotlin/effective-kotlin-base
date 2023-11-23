package bin.jayden.lottospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LottoSpringApplication

fun main(args: Array<String>) {
    runApplication<LottoSpringApplication>(*args)

    // 1. 깃헙 브랜치는 최신화된 메인브랜치를 기준으로 따야한다
    // 2. http status코드를 어느정도는 외워둬야 한다
    //     - 200 : 성공 ok
    //     - 201 : 생성 create
    //     - 204 : 성공했지만 응답할 데이터가 없음 no content
    //     - 300 : 리다이렉션 redirect
    //     - 302 : 리다이렉션 redirect
    //     - 400 : 클라이언트 요청이 잘못됨 bad request
    //     - 401 : 인증이 필요함 unauthorized
    //     - 403 : 인증이 거부됨 forbidden
    //     - 404 : 요청한 리소스가 없음 not found
    //     - 405 : 요청한 메소드가 허용되지 않음 method not allowed
    //     - 409 : 요청이 충돌됨 conflict
    //     - 500 : 서버 에러 internal server error
    //     - 502 : 게이트웨이 에러 bad gateway
    //     - 503 : 서비스를 사용할 수 없음 service unavailable
    // 3. https://prohannah.tistory.com/156
    // 4. Path variable, query, body의 차이가 각각 알아야 한다
    // 5. Get, post, put, patch, delete의 차이점은 잘알고 각각을 잘 써야한다
    // 6. 동시성을 고려하라는게 syncronize를 쓰라는게 아니다 그거 쓰면 엄청 느려짐
    //      100명의 사람이 1개의 계좌에다가 돈을 보낼때 1명씩 보내는건 맞음
    //      syncronize를 쓸경우 100명의 사람이 100개의 계좌에 돈을 보낼때도 1명씩 보내야함
    //      DB의 ACID를 고려한 트랜잭션을 써보자
    // 7. 컨트롤러에 사용하는 DTO들은 전부 별도의 파일로 분리해야하며 모두 DTO로 래핑해야한다
    // 8. https://news.hada.io/topic?id=11989
    // 9. 이번주 한 내용정도의 난이도를 1주일에 해내는게 카카오 신입한테 요구하는 능력이다
    //     1. 3달 교육 1달 코틀린을 배웠고 그후 프로젝트 바로 시작
    //     2. 그러나 그때는 설계부터 시작

    // 대연님의 질문 : Chat GPT가 Transactional 별로라던데요? -> https://akasai.space/db/about_isolation 요거 말한게 아닐까 싶음
}
