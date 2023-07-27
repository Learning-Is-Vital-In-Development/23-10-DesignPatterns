package code.choi.ch06;

import code.choi.ch06.remotecontroller.Light;
import code.choi.ch06.remotecontroller.LightOffCommand;
import code.choi.ch06.remotecontroller.LightOnCommand;
import code.choi.ch06.remotecontroller.RemoteControl;
import code.choi.ch06.remotecontroller.RemoteControlWithUndo;
import code.choi.ch06.remotecontroller.Stereo;
import code.choi.ch06.remotecontroller.StereoOffCommand;
import code.choi.ch06.remotecontroller.StereoOnWithCDCommand;

public class RemoteLoader {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Stereo stereo = new Stereo("Living Room");
//        Stereo stereo1 = new Stereo("dining");

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOff = new StereoOffCommand(stereo);

        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
        remoteControl.setCommand(2, stereoOnWithCD, stereoOff);
//        remoteControl.setCommand(3, () -> {
//            stereo1.on();
//            stereo1.setCD();
//            stereo1.setVolume(11);
//        }, stereo1::off);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);

        RemoteControlWithUndo remoteControlWithUndo = new RemoteControlWithUndo();

        Light livingRoomLight1 = new Light("Living Room");
        LightOnCommand livingRoomLightOn1 = new LightOnCommand(livingRoomLight1);
        LightOffCommand livingRoomLightOff1 = new LightOffCommand(livingRoomLight1);

        remoteControlWithUndo.setCommand(0, livingRoomLightOn1, livingRoomLightOff1);

        remoteControlWithUndo.onButtonWasPushed(0);
        remoteControlWithUndo.offButtonWasPushed(0);
        System.out.println(remoteControlWithUndo);
        remoteControlWithUndo.undoButtonWasPushed();
        remoteControlWithUndo.offButtonWasPushed(0);
        remoteControlWithUndo.onButtonWasPushed(0);
        System.out.println(remoteControlWithUndo);
        remoteControlWithUndo.undoButtonWasPushed();
    }
}
