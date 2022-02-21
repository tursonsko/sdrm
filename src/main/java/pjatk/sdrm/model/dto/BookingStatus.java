package pjatk.sdrm.model.dto;

import java.util.Date;

public class BookingStatus {
    private String message;
    private Date dateDetails;


    public BookingStatus(String message, Date dateDetails) {
        this.message = message;
        this.dateDetails = dateDetails;

    }
    public String getMessage() {
        return message;
    }

    public Date getDateDetails() {
        return dateDetails;
    }

}
