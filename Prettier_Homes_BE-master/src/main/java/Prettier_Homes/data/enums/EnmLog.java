package Prettier_Homes.data.enums;

public enum EnmLog {


    CREATED("Advert is created and wait for approve"),//0
    UPDATED("Advert is updated"),//1
    DELETED("Advert is deleted"),
    DECLINED("Advert is declined by manager"),
    TOUR_REQUEST_CREATED("Tour request is created"),
    TOUR_REQUEST_ACCEPTED("Tour request is accepted"),
    TOUR_REQUEST_DECLINED("Tour request is declined"),
    TOUR_REQUEST_CANCELED("Tour request is canceled");

    private final String description;
    EnmLog(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
