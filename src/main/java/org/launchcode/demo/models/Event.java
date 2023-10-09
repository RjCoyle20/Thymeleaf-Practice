package org.launchcode.demo.models;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1;
    @NotBlank(message = "Name is required")
    @Size(min =3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;
    @Size(max = 500, message = "Description too long!")
    private String description;
//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @NotBlank(message = "Location is required")
    @NotNull(message = "Please enter valid location")
    private String location;

    @AssertTrue(message = "Must be attendable")
    private boolean attendable;

    @Positive(message = "Must be a number over 0")
    private int capacity;

    public Event(String name, String description, String contactEmail, String location, boolean attendable, int capacity) {
        this();
        this.name = name;
        this.description= description;
        this.contactEmail= contactEmail;
        this.location = location;
        this.attendable = attendable;
        this.capacity = capacity;
    }
    public Event(){
        this.id= nextId;
        nextId++;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContractEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAttendable() {
        return attendable;
    }

    public void setAttendable(boolean attendable) {
        this.attendable = attendable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
