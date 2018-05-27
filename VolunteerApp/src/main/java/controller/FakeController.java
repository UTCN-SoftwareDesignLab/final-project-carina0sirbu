//package controller;
//
//import model.Admin;
//import model.Category;
//import model.Role;
//import model.Volunteer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import service.admin.AdminService;
//import service.volunteer.VolunteerService;
//
//@Controller
//public class FakeController {
//
//    private AdminService adminService;
//    private VolunteerService volunteerService;
//
//
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    @Autowired
//    public FakeController(AdminService adminService, VolunteerService volunteerService) {
//        this.adminService = adminService;
//        this.volunteerService = volunteerService;
//
//        createUser();
//    }
//
//    private void createUser() {
//
//        String password = encoder.encode("Parola123#");
//
//        Admin admin = new Admin("carina.sirbu96@gmail.com", password, Role.ADMIN);
//        Volunteer volunteer = new Volunteer("Carina Natalia", "sarbukarina@yahoo.com", password, "075514246", Category.ADMINISTRATIVE, Role.VOLUNTEER);
//
//        volunteerService.create(volunteer);
//        adminService.create(admin);
//    }
//
//}
