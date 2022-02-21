package pjatk.sdrm.model.dto;

import io.micrometer.core.lang.NonNull;

import javax.validation.constraints.Pattern;

public class UserDtoRequest {
    @NonNull
    private String name;
    @NonNull
    private String lastName;

    @NonNull
    private String password;

    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    private String email;


    public UserDtoRequest(@NonNull String userName, @NonNull String lastName, @NonNull String userEmail) {
        this.name = userName;
        this.lastName = lastName;
        this.email = userEmail;
    }

    public void setUserName(@NonNull String userName) {
        this.name = userName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }
}
