package code.choi.ch01.duck;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DuckType {
    MALLARD("청둥오리"),
    MODEL("모형오리");

    private final String name;
}
