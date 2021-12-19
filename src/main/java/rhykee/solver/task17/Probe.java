package rhykee.solver.task17;

public class Probe {

    private final Coordinate position;
    private final Coordinate currentVelocity;

    public Probe(Coordinate startingVelocity) {
        currentVelocity = startingVelocity;
        position = new Coordinate(0, 0);
    }

    public Coordinate move() {
        position.setX(position.getX() + currentVelocity.getX());
        position.setY(position.getY() + currentVelocity.getY());
        currentVelocity.setY(currentVelocity.getY() - 1);
        currentVelocity.setX(currentVelocity.getX() == 0 ? 0 : currentVelocity.getX() - 1);
        return new Coordinate(position.getX(), position.getY());
    }
}
