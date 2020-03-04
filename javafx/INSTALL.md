### Installing and configuring JavaFX.

If using Java 11 SDK (or later), then you will have to download JavaFX for your platform and then configure the project containing the JavaFX application that you want to run. For Java 8/9/10 JavaFX is included as part of JDK.

The instructions below are based on: https://openjfx.io/openjfx-docs/#install-javafx which contains further information and also some troubleshooting hints.

1. Download the 11.0.2 distribution from https://gluonhq.com/products/javafx/ (remember to download for the correct platform - Mac/Linux/Windows)

2. Unpack the downloaded file. The steps below has been performed in Windows with the javafx files being unpacked in a directory with the path `c:\work\javafx-sdk-11.0.2`. *NOTE*: The specification of paths in the steps below will have to be adapted depending on where you unpack the installation. What is being downloaded is a set of jar-files that constitute the JavaFX library.

3. When opening an Eclipse-project where the classes uses/refers to JavaFX classes, you will see a number of compile errors. This is due to teh fact that we have not yet added the JavaFX library jar-files to the buildpath of the project so that the java compiler can find them when computing the classes.

   - Right click on the project, *Select Properties*, Select *Java Build Path*, and then the *Libraries* tab. If there is already a JavaFX entry in the Classpath, then select it and delete.

   - Select *Classpath*, *Add Library*, *User Library*, *User Libraries*, and the *New*... Give the library the name *JavaFX* and select *Add External Jars*. If there is already a JavaFX user library, then delete it first.

   - Navigate to `c:\work\javafx-sdk-11.0.2` (the place where you unpacked the JavaFX library) and then to the `lib` folder. Select all the jar-files to include them in the user library. Click *OK*, *Apply* and *Close*, and then *Finish*. Now the JavaFX libraries is on the build path of the project.

   - Click *Apply and Close*. The compiler errors should now go away.

4. Now that the project compiles, we need to setup a Launch Configuration to allow us to start the JavaFX program with the correct options.

   - Select the class that contains the main method (ChApp.java for project 2) and then Run As ... and then Run configuration

   - Create a new launch configuration (top most icon with +). The name should be filled automatically with the classname.

   - Go to arguments. Here you need to add the following to the VM arguments: `--module-path "C:\work\javafx\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml` (remeber to adapt the path to match your installation)

   - Make sure that `Use the -XstartOnFirstThread argument when launching with SWT` is **not** checked.
