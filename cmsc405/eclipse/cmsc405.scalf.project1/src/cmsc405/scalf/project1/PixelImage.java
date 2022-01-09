package cmsc405.scalf.project1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public enum PixelImage {
  PIKACHU(new int[][] {
    {0,0,0,1,1, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,0,0,1,1, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,1,1},
    {0,0,1,1,1, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,1,1,1,1},
    {0,1,1,1,1, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 1,4,1,1,1},
    {1,1,1,1,1, 1,0,0,0,0, 0,0,0,0,0, 0,0,0,1,1, 4,4,1,1,1},
    
    {1,4,4,4,4, 1,0,0,0,0, 0,0,0,0,0, 0,0,1,4,4, 4,4,1,1,0},
    {1,4,4,4,4, 1,0,1,1,1, 1,1,1,0,0, 1,1,4,4,4, 4,4,1,1,0},
    {1,1,4,4,4, 1,1,4,4,4, 4,4,4,1,1, 4,4,4,4,4, 4,4,1,0,0},
    {1,1,4,4,4, 4,4,4,4,4, 4,4,4,4,4, 4,4,4,4,4, 4,4,1,0,0},
    {0,1,4,4,4, 4,4,4,4,4, 4,4,4,4,4, 4,4,4,4,4, 4,1,0,0,0},
    
    {0,1,4,1,4, 4,4,4,4,4, 4,4,4,4,4, 4,4,4,1,4, 1,0,0,0,0},
    {0,0,1,4,4, 4,4,4,4,4, 4,4,4,4,4, 4,4,4,4,1, 0,0,0,0,0},
    {0,0,1,4,4, 4,4,4,4,4, 4,4,4,4,4, 4,4,4,4,1, 0,0,0,0,0},
    {0,1,4,4,4, 1,1,4,4,4, 4,4,4,4,4, 1,1,4,4,4, 1,0,0,0,0},
    {0,1,4,4,1, 0,1,1,4,4, 4,4,4,4,1, 0,1,1,4,4, 1,0,0,0,0},
    
    {0,1,4,4,1, 1,1,1,4,4, 4,4,4,4,1, 1,1,1,4,4, 1,0,0,0,0},
    {0,1,4,4,4, 1,1,4,4,4, 1,1,4,4,4, 1,1,4,4,4, 1,0,0,0,0},
    {1,4,4,3,3, 4,4,4,4,4, 4,4,4,4,4, 4,4,3,3,4, 4,1,0,0,0},
    {1,4,3,3,3, 3,4,4,1,4, 1,1,4,1,4, 4,3,3,3,3, 4,1,0,0,0},
    {1,4,3,3,3, 3,4,4,4,1, 3,3,1,4,4, 4,3,3,3,3, 4,1,0,0,0},
    
    {0,1,4,3,3, 4,4,4,4,1, 3,3,1,4,4, 4,4,3,3,4, 1,0,0,0,0},
    {0,0,1,4,4, 4,4,4,4,1, 3,3,1,4,4, 4,4,4,4,1, 0,0,0,0,0},
    {0,0,0,1,1, 4,4,4,4,4, 1,1,4,4,4, 4,4,1,1,0, 0,0,0,0,0},
    {0,0,0,0,0, 1,1,1,4,4, 4,4,4,4,1, 1,1,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,1,1, 1,1,1,1,0, 0,0,0,0,0, 0,0,0,0,0}
  }),
  ALPACA(new int[][] {
    {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,1, 1,1,1,1,1, 1,0,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,1,1,1, 0,0,0,0,0, 1,1,1,0,0, 0,0,0,0,0},
    
    {0,0,0,1,1, 1,1,1,0,0, 0,0,0,0,0, 0,0,1,1,1, 1,1,0,0,0},
    {0,0,0,1,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,1,0,0,0},
    {0,0,0,1,1, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 1,1,0,0,0},
    {0,0,0,0,1, 1,1,0,1,1, 0,0,1,0,0, 1,1,0,1,1, 1,0,0,0,0},
    {0,0,0,0,0, 0,1,8,1,1, 0,1,1,1,0, 1,1,8,1,0, 0,0,0,0,0},
    
    {0,0,0,0,0, 0,1,8,8,0, 0,0,0,0,0, 0,8,8,1,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,1,0,0,0, 0,0,0,0,0, 0,0,0,1,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,1,1,0,0, 0,0,0,0,0, 0,0,1,1,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,1,1,3, 3,1,1,1,3, 3,1,1,1,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,1,3, 1,4,4,4,1, 3,0,1,0,1, 0,0,0,0,0},
    
    {0,0,0,0,0, 0,0,0,1,0, 1,4,4,4,1, 0,0,0,1,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,1,0, 1,4,1,4,1, 0,0,0,0,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,1,1, 0,1,1,1,0, 0,0,0,0,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,1, 0,0,0,0,0, 0,0,0,0,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,1, 0,1,1,1,0, 1,1,1,0,1, 0,0,0,0,0},
    
    {0,0,0,0,0, 0,0,0,0,1, 1,1,0,1,1, 1,0,1,1,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,1, 1,0,0,1,1, 0,0,0,1,1, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
  }),
  NOODLES(new int[][] {
    {0,0,0,1,1, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {1,1,0,1,9, 1,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {1,9,1,1,9, 9,1,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {1,9,9,1,1, 9,9,1,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0},
    {0,1,9,9,1, 1,9,9,1,7, 7,7,7,7,7, 7,0,0,0,0, 0,0,0,0,0},
    
    {0,0,1,9,9, 1,1,9,7,4, 4,4,7,7,4, 7,7,7,0,0, 0,0,0,0,0},
    {0,0,0,1,9, 9,1,1,7,7, 7,7,7,7,7, 4,4,7,7,7, 7,7,0,0,0},
    {0,0,0,0,7, 7,7,7,7,4, 4,7,4,4,4, 7,7,7,7,7, 7,7,7,0,0},
    {0,0,0,7,4, 4,4,4,4,4, 7,7,7,7,7, 7,7,7,4,4, 4,4,4,7,0},
    {0,0,7,4,7, 7,7,7,7,7, 7,7,4,4,4, 4,4,4,7,7, 7,7,7,7,0},
    
    {0,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1},
    {0,1,0,3,0, 3,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,2,1},
    {0,1,3,3,3, 3,3,0,0,0, 3,0,0,0,3, 0,0,0,3,0, 0,0,3,2,1},
    {0,1,3,3,3, 3,3,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,2,2,2,1},
    {0,1,0,3,3, 3,0,0,3,0, 0,0,3,0,0, 0,3,0,0,0, 3,2,2,2,1},
    
    {0,1,0,0,3, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,2, 2,2,2,2,1},
    {0,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1},
    {0,0,1,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 2,2,2,1,0},
    {0,0,1,0,0, 0,1,1,0,0, 0,0,0,0,0, 0,0,0,1,1, 2,2,2,1,0},
    {0,0,0,1,0, 1,1,0,1,0, 0,0,0,0,0, 0,2,1,0,1, 1,2,1,0,0},
    
    {0,0,0,1,0, 1,1,1,1,0, 0,0,0,0,0, 2,2,1,1,1, 1,2,1,0,0},
    {0,0,0,0,1, 0,1,1,0,0, 0,0,0,2,2, 2,2,2,1,1, 2,1,0,0,0},
    {0,0,0,0,0, 1,8,8,0,0, 0,2,2,2,2, 2,2,2,8,8, 1,0,0,0,0},
    {0,0,0,0,0, 1,1,2,2,2, 2,2,2,2,2, 2,2,2,2,1, 1,0,0,0,0},
    {0,0,0,0,0, 0,1,1,1,1, 1,1,1,1,1, 1,1,1,1,1, 0,0,0,0,0}
  });
  
  private final static int IMGSIZEX = 25;
  private final static int IMGSIZEY = 25;
  private final static Color BROWN = new Color(102, 51, 0);
  private final static Color[] colors =
  {
    Color.WHITE, Color.BLACK, Color.GRAY, Color.RED, Color.YELLOW,
    Color.BLUE, Color.CYAN, Color.ORANGE, Color.PINK, BROWN
  };
  
  private int[][] colorMap;
  
  PixelImage(int[][] colorMap) {
    this.colorMap = colorMap;
  }
  
  public int[][] getColorMap() {
    return this.colorMap;
  }
  
  public BufferedImage getImage() {
    BufferedImage image = new BufferedImage(IMGSIZEX, IMGSIZEY, BufferedImage.TYPE_INT_RGB);
    int rgb = colors[0].getRGB();
    for (int row = 0; row < IMGSIZEY; row++) {
      for (int col = 0; col < IMGSIZEX; col++) {
        rgb = colors[colorMap[row][col]].getRGB();
        image.setRGB(col, row, rgb);
      }
    }
    return image;
  }
}