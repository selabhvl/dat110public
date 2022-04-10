## Project 4 - Part A: Hardware/software co-design of an access control device

This part is to be solved using the TinkerCAD: https://www.tinkercad.com/ simulator as introduced in the lectures and is to be implemented on an Arduino board in the simulator.

Start by creating an account on TinkerCAD if you do not already have one. Create an ordinary user account using any of your email addresses.

If you have an Arduino board available, then you may choose to build the actual physical IoT access control device. Even so, it may be a good idea to prototype the design in TinkerCAD as a first step.

### Sensors

You will be using the following **sensors** with *digital input*

- A passive infrared-sensor (PIR) for detecting the approach of a person (motion sensor)

![](assets/markdown-img-paste-20181028082134355.png)

- Two pushbuttons for entering an entry code. The order in which the buttons are pushed represents the entry code

![](assets/markdown-img-paste-20181028082117798.png)

![](assets/markdown-img-paste-20181028082117798.png)


### Actuators

You will be using the following **actuators** with *digital output*

- Three LEDs (red,yellow,green) for signalling the state of the device.

![](assets/redled.png)

![](assets/yellowled.png)

![](assets/greenled.png)

### Functional and behavioral requirements

The sensors and actuators are to be controlled by embedded software running on the microprocesser. The embedded control software is required to implement the following behaviour

1. Initially, the system is in the state `LOCKED`.

- The red LED is on when the system is in the `LOCKED` state.

- When motion is detected (via the PIR/motion sensor), the yellow LED must be switched on; and the system enters a `WAITING` state. In this state, the user is to do two pushes on the buttons. After each push, the yellow LED should blink shortly.

   - If the order that the buttons are pushed in is *correct*, i.e., matches the order to gain access, then system will become `UNLOCKED`, and the green LED is switched on.

   - If the order it not correct, then the red LED should blink and the system return to the `LOCKED` state.

After a certain amount of time in the `UNLOCKED` state, the system should automatically enter the `LOCKED` state.

### Think before you start to program: Make a finite-state machine model!

Start the assignment by drawing a [finite state machine](https://en.wikipedia.org/wiki/Finite-state_machine) that formally specifies the states of the device, and the transitions that may take place causing the device to change its state. The state machine should be derived based on the description of the functional requirements above.

**Note:** To simplify the design, you can just hardcode the correct button order for access into the software. In a real system, there would also be a state in which the correct order can be configured, but we omit this part.

The finite-state machine model (and a description of it) will also be required for Part C - the project report. The figure can be drawn by hand or using a suitable UML modelling tool.

### Hardware/software co-design and implementation

With the overall design in place in the form of the finite-state machine model, you may now start to perform the wiring of sensors and actuators on the TinkerCAD Arduino board and implement the embedded control software.

**Hint** A systematic way of implementing the finite-state machine model in software is to have an integer variable that is used to keep track of the current state of the system. In the first part of the `loop()`-function, the software reads the values of the sensors. The reading of the sensors is then followed by a switch-statement on the current state of the system with a case for each possible state of the system as per the finite-state machine model. Each case must then implement the behaviour (including any state change required) given the input from sensors and the current state of the system.

Test your design and implementation using simulation in TinkerCAD. Make sure that you have covered the important cases, e.g., both correct and incorrect access code.

**Optional challenge:** Do some research on Arduino programming and find a solution such that if the user has not pushed two buttons within a certain amount of time in the `WAITING` state, then system should timeout and return to the `LOCKED` state again.

### Resources

- Arduino language reference https://www.arduino.cc/reference/en
- Arduino board layout https://www.arduino.cc/en/Reference/Board   
