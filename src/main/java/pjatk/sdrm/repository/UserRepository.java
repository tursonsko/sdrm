package pjatk.sdrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.sdrm.model.entity.UserData;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    UserData findUserDataByUserName(String username);
    Optional<UserData> findUserDataByUserEmail(String email);
    Optional<UserData> findUserDataByUserNameOrUserEmail(String username, String email);
    Boolean existsUserDataByUserName(String username);
    Boolean existsUserDataByUserEmail(String email);
}
