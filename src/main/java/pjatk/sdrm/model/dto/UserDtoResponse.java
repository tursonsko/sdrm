package pjatk.sdrm.model.dto;

import java.util.Objects;

public class UserDtoResponse {

    private String username;
    private String lastname;
    private String email;

    public UserDtoResponse(String username, String lastname, String email) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoResponse that = (UserDtoResponse) o;
        return Objects.equals(email, that.email);
    }

}
