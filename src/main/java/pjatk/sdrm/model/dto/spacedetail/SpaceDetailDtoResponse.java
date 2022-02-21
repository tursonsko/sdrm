package pjatk.sdrm.model.dto.spacedetail;

public class SpaceDetailDtoResponse {

    private Long idSpace;
    private String name;
    private String imageBase64;

    public SpaceDetailDtoResponse(Long idSpace, String name, String imageBase64) {
        this.idSpace = idSpace;
        this.name = name;
        this.imageBase64 = imageBase64;
    }

    public Long getIdSpace() {
        return idSpace;
    }

    public String getName() {
        return name;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceDetailDtoResponse that = (SpaceDetailDtoResponse) o;

        if (idSpace != null ? !idSpace.equals(that.idSpace) : that.idSpace != null)
            return true;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return true;
        if (imageBase64 != null ? !imageBase64.equals(that.imageBase64) : that.imageBase64 != null)
            return true;

        return false;
    }
}
