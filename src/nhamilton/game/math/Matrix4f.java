/**
 * 
 */
package nhamilton.game.math;

import java.text.DecimalFormat;

/**
 * @author Nicholas Hamilton
 *
 */
public class Matrix4f
{   
    private static DecimalFormat fmt = new DecimalFormat("0.00");
    
    private float m[][];
    
    public Matrix4f() 
    {
        m = new float[4][4];
    }
    
    public Matrix4f(float[][] m) 
    {
        this.m = m;
    }
    
    public Matrix4f initIdentity() 
    {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f;
        
        return this;
    }
    
    public Matrix4f initTranslation(float tx, float ty, float tz) 
    {
        m[0][0] = 1f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = tx;
        m[0][1] = 0f; m[1][1] = 1f; m[2][1] = 0f; m[3][1] = ty;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = tz;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f;
        
        return this;
    }
    
    public Matrix4f initScale(float sx, float sy, float sz) 
    {
        m[0][0] = sx; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = 0f;
        m[0][1] = 0f; m[1][1] = sy; m[2][1] = 0f; m[3][1] = 0f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = sz; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f;
        
        return this;
    }
    
    public Matrix4f initScreenTransform(float width, float height) 
    {
        m[0][0] = width/2f; m[1][0] = 0f; m[2][0] = 0f; m[3][0] = width/2f;
        m[0][1] = 0f; m[1][1] = -height/2f; m[2][1] = 0f; m[3][1] = height/2f;
        m[0][2] = 0f; m[1][2] = 0f; m[2][2] = 1f; m[3][2] = 0f;
        m[0][3] = 0f; m[1][3] = 0f; m[2][3] = 0f; m[3][3] = 1f;
        
        return this;
    }
    
    public Matrix4f mul(final Matrix4f r) 
    {
        float res[][] = new float[4][4];
        
        for(int x = 0; x < 4; x++) 
            for(int y = 0; y < 4; y++) 
            {
                res[x][y] = m[x][0] * r.m[0][y] + 
                            m[x][1] * r.m[1][y] + 
                            m[x][2] * r.m[2][y] + 
                            m[x][3] * r.m[3][y];
            }
        
        return new Matrix4f(res);
    }
    
    public Vector4f mul(final Vector4f r) 
    {
        return new Vector4f(r.getX()*m[0][0] + r.getY()*m[1][0] + r.getZ()*m[2][0] + r.getW()*m[3][0],
                            r.getX()*m[0][1] + r.getY()*m[1][1] + r.getZ()*m[2][1] + r.getW()*m[3][1],
                            r.getX()*m[0][2] + r.getY()*m[1][2] + r.getZ()*m[2][2] + r.getW()*m[3][2],
                            r.getX()*m[0][3] + r.getY()*m[1][3] + r.getZ()*m[2][3] + r.getW()*m[3][3]);
    }
    
    public float get(int x, int y) 
    {
        return m[x][y];
    }
    
    public void set(int x, int y, float val) 
    {
        m[x][y] = (float)val;
    }
    
    //TODO: better copy
    public Matrix4f copy() 
    {
        Matrix4f r = new Matrix4f();
        r.m = m;
        return r;
    }
    
    public String toString() 
    {
        String str = "Mat4[";
        
        for(int y = 0; y < 4; y++) 
        {
            if(y != 0) str += "     ";
            str +=  "x=" + fmt.format(m[0][y]);
            str += ",y=" + fmt.format(m[1][y]);
            str += ",z=" + fmt.format(m[2][y]);
            str += ",w=" + fmt.format(m[3][y]);
            if(y != 3) str += "\n";
            else str += "]";
        }
        
        return str;
    }
}
