package pjatk.sdrm.model.dto.spacedetail;

public class SpaceDetailDtoRequest {

    private String name;
    private String imageBase64;
    private long userId;

    public SpaceDetailDtoRequest(String name, String imageBase64, long userId) {
        this.name = name;
        this.imageBase64 = imageBase64;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public long getUserId() {
        return userId;
    }
}
