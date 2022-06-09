package com.usm.i2002.dreamteam.coursework.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class TestResult {
    final Gender gender;
    final Integer age;
    final Map<Category, Integer> testResults;
}
