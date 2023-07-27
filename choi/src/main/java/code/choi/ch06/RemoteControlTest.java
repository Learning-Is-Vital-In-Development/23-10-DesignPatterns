package code.choi.ch06;

import code.choi.ch06.remotecontroller.Light;
import code.choi.ch06.remotecontroller.LightOnCommand;
import code.choi.ch06.remotecontroller.SimpleRemoteControl;

public class RemoteControlTest {

    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl(); // invoker
        Light light = new Light("aa");  // receiver
        LightOnCommand lightOn = new LightOnCommand(light); // command object

        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }

}
