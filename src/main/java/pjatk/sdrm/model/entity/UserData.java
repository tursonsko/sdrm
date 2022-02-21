package pjatk.sdrm.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.micrometer.core.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "userdata")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String userName;
    @NonNull
    private String lastName;

    @NonNull
    private String userPassword;

    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    private String userEmail;

    @OneToMany(
            mappedBy = "userData",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(value = "placedetails_u")
    private List<PlaceDetails> placeDetailsList = new ArrayList<>();

    @OneToMany(
            mappedBy = "userData",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(value = "spacedetail")
    private List<SpaceDetail> spaceDetails = new ArrayList<>();

    @OneToMany(
            mappedBy = "userData",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference(value = "timeslot")
    private List<TimeSlot> bookedTimeSlots = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public UserData(@NonNull String userName, @NonNull String lastName, @NonNull String userPassword, @NonNull String userEmail) {
        this.userName = userName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public UserData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(@NonNull String userPassword) {
        this.userPassword = userPassword;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NonNull String userEmail) {
        this.userEmail = userEmail;
    }

    public List<PlaceDetails> getPlaceDetailsList() {
        return placeDetailsList;
    }

    public void setPlaceDetailsList(List<PlaceDetails> placeDetailsList) {
        this.placeDetailsList = placeDetailsList;
    }

    public List<SpaceDetail> getSpaceDetails() {
        return spaceDetails;
    }

    public void setSpaceDetails(List<SpaceDetail> spaceDetails) {
        this.spaceDetails = spaceDetails;
    }

    public List<PlaceDetails> getReservationDetailsList() {
        return placeDetailsList;
    }

    public void setReservationDetailsList(List<PlaceDetails> placeDetailsList) {
        this.placeDetailsList = placeDetailsList;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(userEmail, userData.userEmail);
    }
}
