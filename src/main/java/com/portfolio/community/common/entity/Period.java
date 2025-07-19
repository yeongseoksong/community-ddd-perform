package com.portfolio.community.common.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Period {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;


    public Period(LocalDateTime endDateTime, LocalDateTime startDateTime) {
        if(startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("startDateTime must be before endDateTime");
        }

        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;
        return Objects.equals(getStartDateTime(), period.getStartDateTime()) && Objects.equals(getEndDateTime(), period.getEndDateTime());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getStartDateTime());
        result = 31 * result + Objects.hashCode(getEndDateTime());
        return result;
    }
}
