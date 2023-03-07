package com.example.airbnbpractice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TempEnum {
    Test1("test"),
    Test2("sdd");

    private final String value;

    @JsonValue
    public String toValue() {
        return value;
    }

    public static TempEnum forCode(String value) {
        for (TempEnum element : values()) {
            if (element.value.equals(value)) {
                return element;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
