package com.mustafa.entity;

import com.mustafa.entity.enums.State;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BaseEntity {
    @Enumerated
    State state;
    Long createAt;
    Long updateAt;
}
