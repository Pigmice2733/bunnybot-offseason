// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  TalonFX backright = new TalonFX(10);
  TalonFX frontright = new TalonFX(11);
  TalonFX frontleft = new TalonFX(12);
  TalonFX backleft = new TalonFX(13);
  XboxController controller = new XboxController(0);
  double speed = 0.5;

  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    backright.setInverted(false);
    frontright.setInverted(false);
    backleft.setInverted(true);
    frontleft.setInverted(true);
  }

  @Override
  public void periodic() {
    double drivespeed = controller.getLeftY() * speed;
    double turnspeed = controller.getRightX() * speed;
    if (Math.abs(drivespeed) < 0.1 && Math.abs(turnspeed) < 0.1) {
      turnspeed = 0;
      drivespeed = 0;
    }
    backright.set(ControlMode.PercentOutput, drivespeed + turnspeed);
    frontright.set(ControlMode.PercentOutput, drivespeed + turnspeed);
    frontleft.set(ControlMode.PercentOutput, drivespeed - turnspeed);
    backleft.set(ControlMode.PercentOutput, drivespeed - turnspeed);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
