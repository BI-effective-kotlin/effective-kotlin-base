package bin.jayden.example.repository

import bin.jayden.example.entity.QUsers
import bin.jayden.example.entity.Users
import com.linecorp.kotlinjdsl.QueryFactory
import com.linecorp.kotlinjdsl.QueryFactoryImpl
import com.linecorp.kotlinjdsl.listQuery
import com.linecorp.kotlinjdsl.query.creator.CriteriaQueryCreatorImpl
import com.linecorp.kotlinjdsl.query.creator.SubqueryCreatorImpl
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository :
    JpaRepository<Users, Long>,
    UserCustomCriteriaRepository,
    UserCustomQuerydslRepository,
    UserCustomJdslRepository {
    fun findByName(name: String): Users?

    fun findByNameAndAddressOrderByEmail(name: String, address: String): Users?

    @Query("SELECT u FROM Users u WHERE u.name = :name")
    fun findByNameJPQL(name: String): Users?
}
// 1. Predefined function 사용
//    * ex) findById()
// 2. 메소드 네이밍 사용
// 3. Kotlin jdsl
// 4. QueryDsl
// 5. CriteriaAPI
// 6. JPQL

interface UserCustomCriteriaRepository {
    fun findByNameCriteria(name: String): Users
}

class UserCustomCriteriaRepositoryImpl(private val em: EntityManager) : UserCustomCriteriaRepository {
    override fun findByNameCriteria(name: String): Users {
        val cb = em.criteriaBuilder
        val cq = cb.createQuery(Users::class.java)
        val root = cq.from(Users::class.java)
        cq.select(root)
        cq.where(cb.equal(root.get<String>("name"), name))
        return em.createQuery(cq).singleResult
    }
}

@Configuration
class JpaQueryFactoryConfig { // 스터디때 빼먹었는데 QueryDSL도 요게 필요하다고 하네요 ㅋㅋ
    @Bean
    fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}

interface UserCustomQuerydslRepository {
    fun findByNameQuerydsl(name: String): List<Users>
}

class UserCustomQuerydslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserCustomQuerydslRepository {
    override fun findByNameQuerydsl(name: String): List<Users> =
        jpaQueryFactory.selectFrom(QUsers.users)
            .where(QUsers.users.name.eq(name))
            .fetch()
}

class UserCustomJdslRepositoryImpl(private val queryFactory: QueryFactory) : UserCustomJdslRepository {
    override fun findByNameJdsl(name: String): List<Users> = queryFactory.listQuery {
        select(entity(Users::class)) // select *
        // select(col(Users::address)) // select address
        from(entity(Users::class))
        where(col(Users::name).equal(name))
    }
}

@Configuration
class QueryFactoryConfig {
    @Bean
    fun queryFactory(entityManager: EntityManager): QueryFactory {
        return QueryFactoryImpl(
            criteriaQueryCreator = CriteriaQueryCreatorImpl(entityManager),
            subqueryCreator = SubqueryCreatorImpl(),
        )
    }
}

interface UserCustomJdslRepository {
    fun findByNameJdsl(name: String): List<Users>
}
