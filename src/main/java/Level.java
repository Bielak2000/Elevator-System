public class Level {
    private int level;
    private boolean status;

    public Level(int level1, boolean status1){
        this.level=level1;
        this.status=status1;
    }

    public int getLevel() {
        return level;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString(){
        return this.level + "";
    }
}
