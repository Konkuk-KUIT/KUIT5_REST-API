package kuit.baemin.dto;

import kuit.baemin.domain.MenuOptionGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OptionGroupResponse {
    private Long groupId;
    private String name;
    private String type;
    private Boolean isRequired;
    private List<OptionResponse> options;

    public static OptionGroupResponse of(MenuOptionGroup group, List<OptionResponse> options) {
        return new OptionGroupResponse(
                group.getId(),
                group.getName(),
                group.getOptionType(),
                group.getIsRequired(),
                options
        );
    }
}
