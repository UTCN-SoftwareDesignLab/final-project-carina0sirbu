package repository;

import model.Category;
import model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Volunteer findByUsername(String username);

    List<Volunteer> findAllByCategory(Category category);
}
