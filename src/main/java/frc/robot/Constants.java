package frc.robot;

public final class Constants {
    // Est-ce que les mechanismes sont la ?
    public static final boolean has_shooter = false;
    public static final boolean has_sensor = false;
    public static final boolean has_lifter = false;

    // Mannettes
    public static final int pilote_port = 1;
    public static final int co_pilote_port = 2;

    // Buttons
    // Pilote
    public static final int drive_btn_id = 1;
    public static final int speedy_btn_id = 2;

    // Co-Pilote
    public static final int shooter_btn_id = 1;

    // Ports
    public static final int ir_rec_port = 1; // IR receiver port
    
    public static final class DriveConstants{
        public static final int kLeftMaster = 4;
        public static final int kLeftFollower = 3;
        public static final int kRightMaster = 1;
        public static final int kRightFollower = 2;
    }

    public static final class ShooterConstants{
        public static final int kTestMotor = 0;

    }

}
