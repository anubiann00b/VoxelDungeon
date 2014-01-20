package game.world;

import game.resource.ResourceLibrary;
import java.io.IOException;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Block {
    
    public static final byte GRASS = 0;
    public static final byte STONE = 1;
    public static final byte BLANK = 2;
    
    private Texture textureTop;
    private Texture textureSide;
    private boolean isTransparent;
    private Chunk chunk;
    private byte type;
    
    public boolean isTransparent() { return isTransparent; }
    
    public Block(byte blockName) throws IOException {
        type = blockName;
        switch (blockName) {
            case GRASS:
                isTransparent = false;
                textureTop = ResourceLibrary.getTexture(ResourceLibrary.GRASS_TOP);
                textureSide = ResourceLibrary.getTexture(ResourceLibrary.GRASS_SIDE);
                break;
            case STONE:
                isTransparent = false;
                textureTop = ResourceLibrary.getTexture(ResourceLibrary.STONE_TOP);
                textureSide = ResourceLibrary.getTexture(ResourceLibrary.STONE_SIDE);
                break;
            case BLANK:
                isTransparent = true;
                textureTop = null;
                textureSide = null;
                break;
            default:
                throw new IOException("Unhandled case @ Block constructor.");
        }
    }
    
    public void setMyChunk(Chunk chunk) {
        this.chunk = chunk;
    }
    
    private void addFloats(float x, float y, float z, float r, float g, float b,
            ArrayList<Float> vertices, ArrayList<Float> colorVertices) {
        vertices.add(Float.valueOf(x));
        vertices.add(Float.valueOf(y));
        vertices.add(Float.valueOf(z));
        colorVertices.add(r);
        colorVertices.add(g);
        colorVertices.add(b);
    }
    
    public void render(int x, int y, int z, ArrayList<Float> vertices, ArrayList<Float> colorVertices) {
        if (isTransparent)
            return;
        
        float r = 0;
        float g = 0;
        float b = 0;
        
        if (type == GRASS) {
            r = 0.09f;
            g = 0.9f;
            b = 0.09f;
        } else if (type == STONE) {
            r = 0.4f;
            g = 0.4f;
            b = 0.4f;
        }
        
        // Top.
        if (!chunk.isBlock(x,y,z+1)) {
            addFloats(-0.5f+x,0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            GL11.glEnd();
        }
        
        if (type == GRASS) {
            r = 0.06f;
            g = 0.6f;
            b = 0.06f;
        } else if (type == STONE) {
            r = 0.2f;
            g = 0.2f;
            b = 0.2f;
        }
        
        // Side.
        if (!chunk.isBlock(x,y+1,z)) {
            addFloats(-0.5f+x,-0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,-0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
        }
        
        // Opposite side to above.
        if (!chunk.isBlock(x,y-1,z)) {
            addFloats(-0.5f+x,-0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,-0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
        }
        
        // Side.
        if (!chunk.isBlock(x+1,y,z)) {
            addFloats(0.5f+x,-0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,-0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            GL11.glEnd();
        }
        
        // Opposite side to above.
        if (!chunk.isBlock(x-1,y,z)) {
            addFloats(-0.5f+x,-0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,-0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
        }
        
        // Bottom.
        if (!chunk.isBlock(x,y,z-1)) {
            addFloats(-0.5f+x,-0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,-0.5f+z,-0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(0.5f+x,-0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
            addFloats(-0.5f+x,-0.5f+z,0.5f+y,r,g,b,vertices,colorVertices);
        }
    }
}
