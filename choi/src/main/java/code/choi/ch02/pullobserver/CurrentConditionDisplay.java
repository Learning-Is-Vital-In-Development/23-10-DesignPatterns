package code.choi.ch02.pullobserver;

public class CurrentConditionDisplay implements Observer, DisplayElement {

        private float temperature;
        private float humidity;
        private Subject weatherData;

        public CurrentConditionDisplay(Subject weatherData) {
            this.weatherData = weatherData;
            weatherData.registerObserver(this);
        }

        @Override
        public void update() {
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }

        @Override
        public void display() {
            System.out.println("현재 상태: 온도 " + temperature + "F, 습도 " + humidity + "%");
        }

}
