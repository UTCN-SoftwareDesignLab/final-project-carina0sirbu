package service.admin;

import model.Admin;

public interface AdminService {

    boolean create(Admin admin);

    Admin findByUsername(String username);
}
