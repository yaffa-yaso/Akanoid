import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    /**
     * Add collidable.
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r;
        if (collidables.size() == 0) {
            return null;
        }
        Collidable closestObject = collidables.get(0);
        double minD, tempD;
        Point tempP, closestPoint = null;
        for (Collidable c : collidables) {
            closestObject = c;
            closestPoint = trajectory.closestIntersectionToStartOfLine(closestObject.getCollisionRectangle());
            if (closestPoint != null) {
                break;
            }
        }
        if (closestPoint == null) {
            return null;
        } else {
            minD = trajectory.start().distance(closestPoint);
        }
        for (Collidable c : collidables) {
            r = c.getCollisionRectangle();
            tempP = trajectory.closestIntersectionToStartOfLine(r);
            if (tempP != null) {
                tempD = trajectory.start().distance(tempP);
                if (tempD < minD) {
                    System.out.println(r.getUpperLeft().getX() + r.getUpperLeft().getY());
                    closestObject = c;
                    closestPoint = tempP;
                    minD = tempD;
                }
            }
        }
        CollisionInfo info = new CollisionInfo();
        info.setCollision(closestPoint, closestObject);
        return info;
    }
}