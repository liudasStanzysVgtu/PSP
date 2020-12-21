package ds;

public class Brick extends Block {
    public Brick(int x, int y) {
        super(x, y);
        loadImage("image/corona.png");
        getImageDimensions();
        setType(1);
        setHealth(1);
    }
}
