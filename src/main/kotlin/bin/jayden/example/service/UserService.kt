package bin.jayden.example.service

import bin.jayden.example.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findByName(name: String) = userRepository.findByName(name) ?: throw Exception("User Not Found")

    fun foo() = userRepository.findById(1)
}
