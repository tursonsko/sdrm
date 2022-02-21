package pjatk.sdrm.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "space")  //single space => room
public class SpaceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference(value="spacedetail")
    private UserData userData;

    @OneToMany(fetch = FetchType.EAGER)
    private List<PlaceDetails> placeDetails = new ArrayList<>();

    public SpaceDetail() {
    }

    public SpaceDetail(String name, String imageBase64, UserData userData) {
        this.name = name;
        this.imageBase64 = imageBase64;
        this.userData = userData;
        this.placeDetails = null;
    }

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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<PlaceDetails> getPlaceDetails() {
        return placeDetails;
    }

    public void setPlaceDetails(List<PlaceDetails> placeDetails) {
        this.placeDetails = placeDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceDetail that = (SpaceDetail) o;
        return Objects.equals(id, that.id);
    }
}
