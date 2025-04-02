package Chess2.GUI;

import javax.swing.JFrame;

// Frame.java
public class Frame extends JFrame {
    public Frame() {
        setTitle("Simple Chess");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        add(new Panel());
        setVisible(true);
    }
}

