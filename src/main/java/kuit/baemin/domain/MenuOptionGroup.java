package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuOptionGroup {
    private Long id;
    private Long menuId;
    private String name;
    private String optionType;
    private Boolean isRequired;
}
