package kuit.baemin.domain.address;

public enum UserAddressType {
    HOME("집"),
    OFFICE("회사"),
    ETC("기타");

    private String krName;

    UserAddressType(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
