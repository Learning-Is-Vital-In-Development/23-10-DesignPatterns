package code.choi.ch02.pushobserver;

public class CurrentConditionDisplay implements Observer, DisplayElement {

        private float temperature;
        private float humidity;
        private Subject weatherData;

        public CurrentConditionDisplay(Subject weatherData) {
            this.weatherData = weatherData;
            weatherData.registerObserver(this);
        }

        @Override
        public void update(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            display();
        }

        @Override
        public void display() {
            System.out.println("현재 상태: 온도 " + temperature + "F, 습도 " + humidity + "%");
        }

}
