package service.volunteer;

import dto.VolunteerDto;
import model.Category;
import model.Volunteer;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VolunteerService {

   // boolean create(VolunteerDto volunteerDto);

    boolean create(VolunteerDto volunteerDto);

    boolean create(Volunteer volunteer);

    Volunteer findByUsername(String username);

    boolean update(VolunteerDto volunteerDto);

    List<Volunteer> findAllByCategory(Category category);
}
