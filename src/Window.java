import javax.swing.*;
import java.awt.*;



public class Window extends JFrame {

    private DrawPanel pMozaik;

    public Window() {
        setSize(DrawPanel.BLOCK_WIDTH, DrawPanel.BLOCK_HEIGHT + 70);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMozaik(true);

        JButton updateButton = new JButton("Update");
        JButton rotateButton = new JButton("rotate");
        updateButton.addActionListener((arg0) -> {
            DrawPanel.generateMozaik();
            createMozaik(false);
            repaint();
        });
        rotateButton.addActionListener((arg0) ->{
            DrawPanel.angel += 15;
            createMozaik(false);
            repaint();
        } );
        JPanel panel = new JPanel();
        panel.add(updateButton, BorderLayout.SOUTH);
        panel.add(rotateButton, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
        validate();
    }

    public void createMozaik(boolean key) {
        pMozaik = new DrawPanel(key);
        add(pMozaik, BorderLayout.CENTER);
    }
}