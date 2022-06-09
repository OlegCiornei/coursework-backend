package com.usm.i2002.dreamteam.coursework.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Arrays.stream;

@Getter
@RequiredArgsConstructor
public enum AgeCategory {
    CHILD(0, 10), TEEN(11, 20), YOUNG(21, 40), GROWN(41, 100);

    public final int min;
    public final int max;

    public static AgeCategory getAgeCategory(final int age) {
        return stream(AgeCategory.values())
                .filter(ageCategory -> ageCategory.getMin() <= age && ageCategory.getMax() >= age).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Illegal age"));
    }
}
