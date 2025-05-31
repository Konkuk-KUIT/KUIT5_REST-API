package kuit.baemin.api;

import kuit.baemin.application.MenuService;
import kuit.baemin.dto.request.MenuRequest;
import kuit.baemin.dto.response.MenuResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurants/{restaurantId}/menus")
public class MenuApi {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity <List<MenuResponse>> getMenus(@PathVariable Long restaurantId){
        List<MenuResponse> menuResponses = menuService.getMenus(restaurantId);
        return ResponseEntity.ok(menuResponses);
    }

    @PostMapping
    public ResponseEntity<MenuResponse> registerMenu(@PathVariable Long restaurantId, @RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = menuService.registerMenu(restaurantId, menuRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuResponse);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long restaurantId, @PathVariable Long menuId, @RequestBody MenuRequest menuRequest) {
        MenuResponse menuResponse = menuService.updateMenu(restaurantId, menuId, menuRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuResponse);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponse> getMenuDetail(@PathVariable Long restaurantId,
                                                      @PathVariable Long menuId) {
        MenuResponse menuResponse = menuService.getMenuDetail(restaurantId, menuId);
        return ResponseEntity.ok(menuResponse);
    }
}
