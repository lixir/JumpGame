package GameObjects;

/**
 * Created by lixir on 27.04.2017.
 */
abstract class GameComponent implements GameObject{
    protected int x;
    protected int y;
    static int speed = 10;

    public GameComponent(int x, int y){
        this.x = x;
        this.y = y;
    }

    public GameComponent(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public int getX(){return x;}

    @Override
    public int getY(){return y;}

    public int getSpeed(){return speed;}

    public void setSpeed(int speed){this.speed = speed;}

    public static void stop(){
        speed = 0;
    }

    public static void start(){
        speed = 10;
    }
}
