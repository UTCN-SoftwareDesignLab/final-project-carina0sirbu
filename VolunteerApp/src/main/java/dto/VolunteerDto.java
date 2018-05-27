package dto;

import model.Category;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VolunteerDto {

    private Long id;

    private String name;

    @Size(min = 6, message = "Username must be at least 6 letters long!")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z0-9@$!%*#?&]+$", message = "Password must contain at least 1 letter, 1 number and 1 special character")
    private String password;

    private String phoneNumber;
    private Category category;
    private Role role;

    @Autowired
    public VolunteerDto(String name, String username, String password, String phoneNumber, Category category, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.role = role;
    }

    public VolunteerDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
