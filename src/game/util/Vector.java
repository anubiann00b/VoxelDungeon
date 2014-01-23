package game.util;

public class Vector {
    
    private float x;
    private float y;
    private float z;
    
    public float x() { return x; }
    public float y() { return y; }
    public float z() { return z; }
    
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setZ(float z) { this.z = z; }
    
    public Vector() {
        this(0,0,0);
    }
    
    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector add(Vector other) {
        return new Vector(this.x+other.x,this.y+other.y,this.z+other.z);
    }

    public void set(Vector other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other==null)
            return false;
        if (!other.getClass().isInstance(this))
            return false;
        Vector otherVector = (Vector) other;
        return otherVector.x==x && otherVector.y==y && otherVector.z==z;
    }
    
    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}