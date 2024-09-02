package frc.robot.Subsystems.Intake;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{

    private final IntakeIO io; 
    private final IntakeIOInputsAutoLogged inputs = new IntakeIOInputsAutoLogged();
    private final SimpleMotorFeedforward FF;
    private final PIDController FeedBackController;

    public Intake(IntakeIO io){

         this.io = io;
         
        switch (Constants.currentMode) {

         case REAL:
         case REPLAY:
             FeedBackController = new PIDController(ConstantsIntake.IntakeConstants.kp, 0, 0.0);
             FF = new SimpleMotorFeedforward(0, ConstantsIntake.IntakeConstants.kv);
             break;
         case SIM:
             FeedBackController = new PIDController(0, 0, 0);
             FF = new SimpleMotorFeedforward(0, 0);
             break;
      default:
          FeedBackController = new PIDController(0, 0, 0);
           FF = new SimpleMotorFeedforward(0, 0);
           break;

        }
         setBrakeMode(true);
    }

    public void periodic(){
        io.updateInputs(inputs);
        Logger.processInputs("Intake", inputs);
        
    }

    public void setIntake(double setpointRPM){
        io.setIntake(FF.calculate(Units.rotationsPerMinuteToRadiansPerSecond(setpointRPM)) + FeedBackController.calculate(inputs.IntakeVelocityRadPerSec, Units.rotationsPerMinuteToRadiansPerSecond(setpointRPM)));
    }
    
    public void stop() {
        io.setIntake(0.0);
      }

     public void setBrakeMode(boolean enabled) {
        io.setIntakeBrakeMode(enabled);
      }

} 
