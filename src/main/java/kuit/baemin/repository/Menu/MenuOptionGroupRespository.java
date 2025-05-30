package kuit.baemin.repository.Menu;

import kuit.baemin.domain.MenuOptionGroup;

import java.util.List;

public interface MenuOptionGroupRespository {
    List<MenuOptionGroup> findAllByMenuId(Long menuId);
}
