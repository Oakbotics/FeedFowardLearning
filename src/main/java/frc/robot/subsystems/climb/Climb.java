// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.climb;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import frc.robot.Constants.ClimbConstants.ClimberInformation;

public class Climb extends SubsystemBase {

  private final Climber leftClimber;
  private final Climber rightClimber;

  public Climb(ClimberIO[] climberIOs) {
    this.leftClimber = new Climber(climberIOs[0], ClimberInformation.kLeftClimber);
    this.rightClimber = new Climber(climberIOs[1], ClimberInformation.kRightClimber);
  }

  @Override
  public void periodic() {

    leftClimber.updateInputs();
    rightClimber.updateInputs();

    leftClimber.run();
    rightClimber.run();
  }

  public Command changeSetpoint(double setpoint) {
    return this.runOnce(
        () -> {
          leftClimber.changeSetpoint(
              MathUtil.clamp(setpoint, ClimbConstants.kMinHeight, ClimbConstants.kMaxHeight));
          rightClimber.changeSetpoint(
              MathUtil.clamp(setpoint, ClimbConstants.kMinHeight, ClimbConstants.kMaxHeight));
        });
  }

  public Pose3d[] get3DPoses() {
    return new Pose3d[] {leftClimber.get3DPose(), rightClimber.get3DPose()};
  }

  public boolean atSetpoint() {
    return leftClimber.atSetpoint() && rightClimber.atSetpoint();
  }
}
