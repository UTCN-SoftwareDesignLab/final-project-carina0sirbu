package dto;

import model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AdminDto {

    private Long id;

    @Size(min = 6, message = "Username must be at least 6 letters long!")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z0-9@$!%*#?&]+$", message = "Password must contain at least 1 letter, 1 number and 1 special character")
    private String password;

    private Role role;

    @Autowired
    public AdminDto(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AdminDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
