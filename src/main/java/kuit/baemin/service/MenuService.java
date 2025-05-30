package kuit.baemin.service;

import kuit.baemin.dto.response.MenuResponse;
import kuit.baemin.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuResponse> getMenusByRestaurantId(Long restaurantId) {
        return menuRepository.findMenusByRestaurantId(restaurantId);
    }
}