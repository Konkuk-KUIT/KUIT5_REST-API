package kuit.baemin.service;

import kuit.baemin.dto.response.CategoryResponse;
import kuit.baemin.dto.response.RestaurantResponse;
import kuit.baemin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAllActive();
    }

    @Transactional
    public List<RestaurantResponse> getRestaurantsByCategory(Long categoryId) {
        return categoryRepository.findRestaurantsByCategoryId(categoryId);
    }
}
