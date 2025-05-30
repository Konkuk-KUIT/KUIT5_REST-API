package kuit.baemin.domain;

import kuit.baemin.exception.BusinessException;
import kuit.baemin.utils.BaseResponseStatus;

public enum UserGrade {
    VALUED("고마운분"),
    HONORED("귀한분"),
    PREMIER("더귀한분"),
    VIP("천생연분");

    private String krName;

    private UserGrade(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }

    // 한글 이름으로 상수 찾아주기
    public static UserGrade fromKrName(String krName) {
        for (UserGrade grade : UserGrade.values()) {
            if (grade.getKrName().equals(krName)) {
                return grade;
            }
        }
        throw new BusinessException(BaseResponseStatus.GRADE_NOT_FOUND);
    }
}
