package pjatk.sdrm.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "space_coordinates")
public class SpaceCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String top_c;

    private String right_c;

    private String bottom_c;

    private String left_c;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="place_id")
    private PlaceDetails placeDetails;

    public SpaceCoordinates(String top_c, String right_c, String bottom_c, String left_c, PlaceDetails placeDetails) {
        this.top_c = top_c;
        this.right_c = right_c;
        this.bottom_c = bottom_c;
        this.left_c = left_c;
        this.placeDetails = placeDetails;
    }

    public SpaceCoordinates() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTop_c() {
        return top_c;
    }

    public void setTop_c(String top_c) {
        this.top_c = top_c;
    }

    public String getRight_c() {
        return right_c;
    }

    public void setRight_c(String right_c) {
        this.right_c = right_c;
    }

    public String getBottom_c() {
        return bottom_c;
    }

    public void setBottom_c(String bottom_c) {
        this.bottom_c = bottom_c;
    }

    public String getLeft_c() {
        return left_c;
    }

    public void setLeft_c(String left_c) {
        this.left_c = left_c;
    }

    public PlaceDetails getPlaceDetails() {
        return placeDetails;
    }

    public void setPlaceDetails(PlaceDetails placeDetails) {
        this.placeDetails = placeDetails;
    }
}
