package kuit.baemin.repository.Menu;

import kuit.baemin.domain.MenuOption;

import java.util.List;
import java.util.Optional;

public interface MenuOptionRepository {
    List<MenuOption> findAllByOptionGroupId(Long optionGroupId);
    Optional<MenuOption> findById(Long id);
    List<MenuOption> findAllByIds(List<Long> optionIds);
}
