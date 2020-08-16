/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point point;
    private Collidable object;

    /**
     * Sets collision.
     *
     * @param collision the collision
     * @param cObject   the c object
     */
    public void setCollision(Point collision, Collidable cObject) {
        this.point = collision;
        this.object = cObject;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.object;
    }
}