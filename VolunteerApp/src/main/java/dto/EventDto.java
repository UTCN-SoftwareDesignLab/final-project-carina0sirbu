package dto;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class EventDto {

    private Long id;

    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String location;
    private String organizer;
    private int noOfVolunteers;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Autowired
    public EventDto(String name, Date date, String location, String organizer, int noOfVolunteers, Category category) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.organizer = organizer;
        this.noOfVolunteers = noOfVolunteers;
        this.category = category;
    }

    public EventDto() {}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getNoOfVolunteers() {
        return noOfVolunteers;
    }

    public void setNoOfVolunteers(int noOfVolunteers) {
        this.noOfVolunteers = noOfVolunteers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    @Override
    public String
    toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", organizer='" + organizer + '\'' +
                ", noOfVolunteers=" + noOfVolunteers +
                ", category=" + category +
                '}';
    }
}

