package frc.robot.commands.ShooterCommands.Turret;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter.Turret.Turret;
import frc.robot.util.SmartTurret3472;
import frc.robot.util.HPPMathLib;

public class AlignTurret extends Command{

    private final Turret turret;
    private final Double setpoint;
    boolean isFinished;


    //con enncoder
    public AlignTurret(Turret turret, Double setpoint){

        this.turret = turret;
        this.setpoint = setpoint;
        addRequirements(turret);
    }
 
    @Override
    public void initialize(){}
    @Override
    public void execute(){
        turret.runTurret(setpoint);            
    }
    
    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

     @Override
    public boolean isFinished(){

        /*if(setpoint != null){
          
            if((turret.getYaw() -  HPPMathLib.coterminal(setpoint)) >= - 0.010)  &&  (turret.getYaw().getRadians() -  HPPMathLib.coterminalradianes(new Rotation2d(setpoint).getRadians())<=  0.010)){
                isFinished = true;
                //System.out.println("Tonoto");
                return isFinished;
            }else{
                isFinished = false;
                return isFinished;
            }
        }else{
            isFinished = false;
            //System.out.println("Adios tonotos");
            return false;
        }* */
        return false;
    }
    
}