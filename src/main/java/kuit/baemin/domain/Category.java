package kuit.baemin.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Category {
    private Long id;
    private String name;
    private String status;  // 'active', 'deleted', 'dormant'
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
