// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climb;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants.ClimbConstants.ClimberInformation;
import org.littletonrobotics.junction.Logger;

public class Climber {
  private final ClimberIO io;
  private final String name;
  private final ClimberIOInputsAutoLogged inputs = new ClimberIOInputsAutoLogged();

  public Climber(ClimberIO climberIO, ClimberInformation info) {
    this.io = climberIO;
    name = info.name + " Climber";
  }

  public void updateInputs() {
    io.updateInputs(inputs);
    Logger.processInputs(name, inputs);
  }

  public void run() {
    io.run();
  }

  public void changeSetpoint(double height) {
    io.changeSetpoint(height);
  }

  public boolean atSetpoint() {
    return inputs.atSetpoint;
  }

  public Pose3d get3DPose() {
    return new Pose3d(0, 0, Units.inchesToMeters(inputs.position), new Rotation3d(0, 0, 0));
  }
}
