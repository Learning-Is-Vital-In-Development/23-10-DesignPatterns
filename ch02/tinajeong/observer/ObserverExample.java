package ch02.tinajeong.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverExample {

    public static void main(String[] args) {
        YoutubeChannel channel = new YoutubeChannel();
        YoutubeSubscriber subscriber1 = new YoutubeSubscriber(channel);
        YoutubeSubscriber subscriber2 = new YoutubeSubscriber(channel);

        channel.setNewVideoName("[대박] 아이바오 쌍둥이 출산~ 찌금 바로 보러가자");
    }
}

interface Observer {
    public void update();
}

interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

class YoutubeChannel implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String latestVideoTitle;

    public YoutubeChannel() {
        this.latestVideoTitle = "";
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
       for(Observer o : observers) {
           o.update();
       }
    }

    // Business logic
    public void uploadVideo() {
        notifyObservers();
    }

    public void setNewVideoName(String newVideoName) {
        this.latestVideoTitle = newVideoName;
        uploadVideo();
    } 

    public String getLatestVideoTitle() {
        return this.latestVideoTitle;
    }
}

class YoutubeSubscriber implements Observer {
        private YoutubeChannel youtubeChannel;
        private String latestVideoTitle = "";
        
        public YoutubeSubscriber(YoutubeChannel youtubeChannel) {
            this.youtubeChannel = youtubeChannel;
            youtubeChannel.registerObserver(this);
        }
        
        @Override
        public void update() {
            this.latestVideoTitle = youtubeChannel.getLatestVideoTitle();
            System.out.println(this.latestVideoTitle);
        }
}
