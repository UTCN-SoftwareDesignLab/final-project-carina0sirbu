package service.volunteer;

import dto.VolunteerDto;
import model.Volunteer;
import org.springframework.stereotype.Service;

@Service
public interface VolunteerService {

   // boolean create(VolunteerDto volunteerDto);

    boolean create(VolunteerDto volunteerDto);

    boolean create(Volunteer volunteer);

    Volunteer findByUsername(String username);

    boolean update(VolunteerDto volunteerDto);
}
