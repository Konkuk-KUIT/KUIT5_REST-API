package kuit.baemin.service;

import kuit.baemin.domain.Menu;
import kuit.baemin.domain.MenuOptionGroup;
import kuit.baemin.dto.MenuDetailResponse;
import kuit.baemin.dto.OptionGroupResponse;
import kuit.baemin.dto.OptionResponse;
import kuit.baemin.repository.Menu.MenuOptionGroupRepositoryV1;
import kuit.baemin.repository.Menu.MenuOptionRepositoryV1;
import kuit.baemin.repository.Menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuOptionGroupRepositoryV1 groupRepository;
    private final MenuOptionRepositoryV1 optionRepository;

    public MenuDetailResponse getMenuDetail(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."));

        List<MenuOptionGroup> groups = groupRepository.findAllByMenuId(menuId);

        List<OptionGroupResponse> groupResponses = groups.stream()
                .map(group -> {
                    List<OptionResponse> options = optionRepository.findAllByOptionGroupId(group.getId())
                            .stream()
                            .map(OptionResponse::from)
                            .toList();
                    return OptionGroupResponse.of(group, options);
                })
                .toList();

        return MenuDetailResponse.of(menu, groupResponses);
    }
}
