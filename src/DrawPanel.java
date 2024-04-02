import javax.swing.*;
import java.awt.*;

enum PAINTING {
    paint, update, clear;
}

public class DrawPanel extends JPanel {
    public static final short PANEL_WIGHT = 800;
    public static final short PANEL_HEIGHT = 800;
    public static final short BLOCK_WIDTH = 800;
    public static final short BLOCK_HEIGHT = 800;
    public static final short BLOCK_SIZE = 8;
    public static final short COLOR_COUNT = 4;
    public static double angel = 0;
    public Point CENTER = new Point(BLOCK_WIDTH / 2,  PANEL_HEIGHT / 2);
    public static Color[][] map_color = new Color[PANEL_WIGHT][ PANEL_HEIGHT];
    public static PAINTING key = PAINTING.paint;
    public static Color[] bColors = new Color[COLOR_COUNT];

    DrawPanel(boolean key) {
        setPreferredSize(new Dimension(PANEL_WIGHT,  PANEL_HEIGHT));
        color_pick();
        if(key) generateMozaik();
    }

    public static void generateMozaik() {
        Point bPoint = new Point(0, 28);
        while (bPoint.getY() < PANEL_HEIGHT) {
            while (bPoint.getX() < PANEL_WIGHT) {
                Point[] bPoints = new Point[4];
                bPoints[0] = new Point(bPoint);
                bPoints[1] = new Point(bPoint.x + BLOCK_SIZE, bPoint.y);
                bPoints[2] = new Point(bPoint.x + BLOCK_SIZE, bPoint.y + BLOCK_SIZE);
                bPoints[3] = new Point(bPoint.x, bPoint.y + BLOCK_SIZE);
                map_color[bPoint.x][bPoint.y] = bColors[(int) (Math.random() * COLOR_COUNT)];
                bPoint.x += BLOCK_SIZE;
            }
            bPoint.x = 0;
            bPoint.y += BLOCK_SIZE;
        }
    }

    void color_pick() {
        bColors[0] = Color.RED;
        bColors[1] = Color.GREEN;
        bColors[2] = Color.BLUE;
        bColors[3] = Color.BLACK;
    }

    void rotate(double angle, Point point) {
        if (angel != 0 || angel != 360) {
            int tempx = (int) (CENTER.x + (Math.cos(Math.toRadians(angle)) *
                    (point.x - CENTER.x) - Math.sin(Math.toRadians(angle)) * (point.y - CENTER.y)));
            int tempy = (int) (CENTER.y + (Math.sin(Math.toRadians(angle)) *
                    (point.x - CENTER.x) + Math.cos(Math.toRadians(angle)) * (point.y - CENTER.y)));
            point.x = tempx;
            point.y = tempy;
        }
    }

    public void drawBlock(Graphics g, Color color, Point[] pos) {
        g.setColor(color);
        for (int i = 0; i < 4; i++) rotate(angel, pos[i]);
        int[] px = new int[4];
        int[] py = new int[4];
        for (int i = 0; i < 4; i++) px[i] = pos[i].x;
        for (int i = 0; i < 4; i++) py[i] = pos[i].y;
        g.fillPolygon(px, py, 4);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (key == PAINTING.paint) {
            Point bPoint = new Point(0, 28);
            while (bPoint.getY() < BLOCK_HEIGHT) {
                while (bPoint.getX() < BLOCK_WIDTH) {
                    Point[] bPoints = new Point[4];
                    bPoints[0] = new Point(bPoint);
                    bPoints[1] = new Point(bPoint.x + BLOCK_SIZE, bPoint.y);
                    bPoints[2] = new Point(bPoint.x + BLOCK_SIZE, bPoint.y + BLOCK_SIZE);
                    bPoints[3] = new Point(bPoint.x, bPoint.y + BLOCK_SIZE);
                    drawBlock(g, map_color[bPoint.x][bPoint.y], bPoints);
                    bPoint.x += BLOCK_SIZE;
                }
                bPoint.x = 0;
                bPoint.y += BLOCK_SIZE;
            }
        }
    }
}
