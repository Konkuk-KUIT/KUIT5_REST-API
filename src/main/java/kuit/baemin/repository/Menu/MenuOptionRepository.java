package kuit.baemin.repository.Menu;

import kuit.baemin.domain.MenuOption;

import java.util.List;

public interface MenuOptionRepository {
    List<MenuOption> findAllByOptionGroupId(Long optionGroupId);
}
