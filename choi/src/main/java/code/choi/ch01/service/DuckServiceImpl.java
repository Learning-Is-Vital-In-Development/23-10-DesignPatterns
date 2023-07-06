package code.choi.ch01.service;

import code.choi.ch01.duck.Duck;
import code.choi.ch01.duck.DuckType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuckServiceImpl implements DuckService{

    private final List<Duck> ducks;

    @Override
    public String getDuckFly(DuckType duckType) {
        Duck duck = findDuck(duckType);
        return duck.performFly();
    }

    private Duck findDuck(DuckType duckType) {
        return ducks.stream()
            .filter(duck -> duck.getDuckType().equals(duckType))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 오리가 없습니다."));
    }
}
