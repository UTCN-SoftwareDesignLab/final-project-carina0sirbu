package service.volunteer;

import dto.VolunteerDto;
import model.Category;
import model.Role;
import model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService, Persistable<Volunteer> {

    private VolunteerRepository volunteerRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public boolean create(VolunteerDto volunteerDto) {

        Category category = null;
        String requestedCategory = volunteerDto.getCategory().toString();
        String administrative = Category.ADMINISTRATIVE.toString();

        switch (requestedCategory) {
//            case String.valueOf(Category.ADMINISTRATIVE.toString()):
            case "ADMINISTRATIVE":
                category = Category.ADMINISTRATIVE;
                break;
            case "CHARITY":
                category = Category.CHARITY;
                break;
            case "TEACHING":
                category = Category.TEACHING;
                break;
        }

        String password = encoder.encode(volunteerDto.getPassword());

        Volunteer volunteer = new Volunteer(volunteerDto.getName(), volunteerDto.getUsername(), password, volunteerDto.getPhoneNumber(), category, Role.VOLUNTEER);

        volunteerRepository.save(volunteer);

        return volunteerRepository.findById(volunteer.getId()).isPresent();
    }

    @Override
    public boolean create(Volunteer volunteer) {

        volunteerRepository.save(volunteer);



        return volunteerRepository.findById(volunteer.getId()).isPresent();
    }

    @Override
    public Volunteer findByUsername(String username) {

        return volunteerRepository.findByUsername(username);
    }

    @Override
    public boolean update(VolunteerDto volunteerDto) {

        Volunteer volunteer = volunteerRepository.findById(volunteerDto.getId()).get();

        volunteer.setName(volunteerDto.getName());
        volunteer.setCategory(volunteerDto.getCategory());
        volunteer.setUsername(volunteerDto.getUsername());
        volunteer.setPassword(encoder.encode(volunteerDto.getPassword()));
        volunteer.setPhoneNumber(volunteerDto.getPhoneNumber());

        volunteerRepository.save(volunteer);

        return volunteerRepository.findById(volunteer.getId()).isPresent();
    }

    @Override
    public List<Volunteer> findAllByCategory(Category category) {
        return volunteerRepository.findAllByCategory(category);
    }

    @Override
    public Volunteer getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
