package code.choi.ch01;

import code.choi.ch01.duck.MallardDuck;
import code.choi.ch01.duck.ModelDuck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ch01Config {

    @Bean
    public MallardDuck mallardDuck() {
        return new MallardDuck();
    }

    @Bean
    public ModelDuck modelDuck() {
        return new ModelDuck();
    }

}
