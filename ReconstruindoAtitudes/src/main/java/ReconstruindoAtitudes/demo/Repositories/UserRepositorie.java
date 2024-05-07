package ReconstruindoAtitudes.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ReconstruindoAtitudes.demo.Models.UserModel;

public interface UserRepositorie extends JpaRepository<UserModel, Long> {

}
