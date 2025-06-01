package kuit.baemin.domain.address;

public enum AddressStatus {
    ACTIVE("사용 가능"),
    INACTIVE("사용 중지"),
    DELETED("삭제된");

    private String description;

    private AddressStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}