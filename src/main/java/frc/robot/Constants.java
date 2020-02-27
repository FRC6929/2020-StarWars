package frc.robot;

public final class Constants {
    // Est-ce que les mechanismes sont la ?
    public static final boolean has_shooter = false;
    public static final boolean has_sensor = false;
    public static final boolean has_lifter = false;

    public static final class kInput {
        public static final int pilote_port = 1;
        public static final int co_pilote_port = 2;
    }


    public static final class kSensors{
        public static final int rec_port = 1; // Port du receveur infrarouge
    }

    public static final class kDrive{
        // Buttons
        public static final int drive_btn_id = 1;
        public static final int speedy_btn_id = 2;

        // Identifiants de Controlleur-Moteurs 
        public static final int l_master = 4;
        public static final int l_follower = 3;
        public static final int r_master = 1;
        public static final int r_follower = 2;
    }

    public static final class kShooter{
        // Controle
        public static final int shooter_btn_id = 1;

        // Moteurs
        public static final int test_motor = 0;
    }

}
