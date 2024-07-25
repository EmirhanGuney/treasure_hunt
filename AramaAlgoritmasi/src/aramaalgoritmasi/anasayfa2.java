
package aramaalgoritmasi;

import com.sun.tools.javac.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.PINK;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class anasayfa2 {
    public static JButton[][] buttons;
    public static int rows;
    public static int cols;
    public static int redX = 0;
    public static int redY = 0;
    public static int[] greenX = new int[20];
    public static int[] greenY = new int[20];
    public static ArrayList<Point> currentPath;
    public static int currentGreenIndex = 0;

    // Define the colors
    public static final Color WALL_COLOR = Color.BLACK;
    public static final Color EMPTY_COLOR = Color.WHITE;
    public static final Color START_COLOR = Color.RED;
    public static final Color[] END_COLORS = {Color.YELLOW, new Color(255, 192, 203), new Color(128, 0, 128), new Color(0, 191, 255)};
    public static final String[] END_NAMES = {"altın sandık", "bakır sandık", "gümüş sandık", "zümrüt sandık"};
    public static final Color PATH_COLOR = Color.YELLOW;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setBounds(0, 0, 700, 720); // Width increased to accommodate the new panel
        JButton buton = new JButton();
        buton.setBounds(200, 400, 100, 40);
        buton.setOpaque(false);
        ImageIcon icon = new ImageIcon(
                "C:\\Users\\Emirhan Guney\\Documents\\NetBeansProjects\\AramaAlgoritmasi\\src\\aramaalgoritmasi\\maze.jpg");
        JLabel label = new JLabel();
        label.setIcon(icon);
        label.setBounds(0, 0, 700, 700);
        JTextField field = new JTextField();
        String code = "#e5c057";
        Color color = Color.decode(code);
        field.setBackground(color);
        field.setBounds(240, 560, 200, 100);
        frame.add(label);
        frame.add(field);
        frame.add(buton);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                "clickButton");
        buton.getActionMap().put("clickButton", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                int a = Integer.parseInt(field.getText());
                JFrame anaFrame = new JFrame();
                rows = a;
                cols = a;
                anaFrame.setTitle("PUTE");
                anaFrame.setSize(1300, 800);
                
                // Main panel to hold the buttons and the green points list
         JPanel mainPanel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Get the image
        Image imgANA = new ImageIcon("C:\\Users\\Emirhan Guney\\Documents\\NetBeansProjects\\AramaAlgoritmasi\\src\\aramaalgoritmasi\\anasayfa.jpg").getImage();
        // Calculate the scaling factors
        double scaleX = (double)getWidth() / imgANA.getWidth(null);
        double scaleY = (double)getHeight() / imgANA.getHeight(null);
        // Choose the scaling factor that keeps the aspect ratio
        double scale = Math.min(scaleX, scaleY);
        // Calculate the new width and height
        int scaledWidth = (int)(imgANA.getWidth(null) * scale);
        int scaledHeight = (int)(imgANA.getHeight(null) * scale);
        // Calculate the x and y position to center the image
        int x = (getWidth() - scaledWidth) / 2;
        int y = (getHeight() - scaledHeight) / 2;
        // Draw the image
        g.drawImage(imgANA, x, y, scaledWidth, scaledHeight, null);
    }
};
                mainPanel.setLayout(null);

                JPanel panel = new JPanel(new BorderLayout());
                panel.setLayout(new GridLayout(rows, cols));
                panel.setBounds(0, 0,800, 800);

                
                 
     
             
                
                
                
                anaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainPanel.setBounds(0, 0, 1300, 800); // Adjusted to accommodate the new panel
                mainPanel.setBackground(color);
                
                String HexCode="#c9a566";
                Color col= Color.decode(HexCode);
                
                
                JButton butonSis=new JButton("Sisle");
                butonSis.setBounds(900,700,150,50);
                butonSis.setBackground(col);
                mainPanel.add(butonSis);
                
                 JButton butonBasla=new JButton("Basla");
                 butonBasla.setBackground(col);
                butonBasla.setBounds(1060,700,150,50);
                mainPanel.add(butonBasla);
                
                ImageIcon icon=new ImageIcon("C:\\Users\\Emirhan Guney\\Documents\\NetBeansProjects\\AramaAlgoritmasi\\src\\aramaalgoritmasi\\wall.jpg");
                
                butonSis.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                      for(int i=0;i<rows;i++){
                          for(int j=0;j<cols;j++){
                              buttons[i][j].setIcon(icon);
                          }
                      }
                      
                      
                  }  
                    
                });
          
                 butonBasla.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                      
                      // Find the shortest path from red to the first green
                currentPath = findShortestPath(new Point(redX, redY),
                        new Point(greenX[currentGreenIndex], greenY[currentGreenIndex]));

                // Create a timer and open the yellow path with 0.5 second intervals
                Timer timer = new Timer(30, new ActionListener() {
                    private Iterator<Point> iterator = currentPath.iterator();

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (iterator.hasNext()) {
                            Point point = iterator.next();
                            if (!(point.x == redX && point.y == redY) && !isGreenPoint(point)) {
                                buttons[point.x][point.y].setBackground(PATH_COLOR);
                               
                                for(int i=0;i<rows;i++){
                            for(int j=0;j<cols;j++){
                                buttons[i][j].setIcon(null);
                            }
                        }
                                
                            }
                            
                            
                        } 
                        
                       
                        
                        
                        else {
                            
                            
                            
                            ((Timer) e.getSource()).stop(); // Stop the timer
                            // After drawing the yellow path, continue
                            findNextClosestGreen();
                        }
                    }
                });
                timer.start();
                      
                  }  
                    
                });
                // Layout settings
                anaFrame.setLayout(null);

                // Button matrix
                buttons = new JButton[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        buttons[i][j] = new JButton();
                        buttons[i][j].setBackground(EMPTY_COLOR); // Set all buttons to white
                        panel.add(buttons[i][j]);
                    }
                }
                mainPanel.add(panel);
                anaFrame.add(mainPanel);

                // Set the red point
                buttons[redX][redY].setBackground(START_COLOR);

                // Set the green points
                Random random = new Random();
                for (int i = 0; i < 20; i++) {
                    greenX[i] = random.nextInt(rows);
                    greenY[i] = random.nextInt(cols);
                    while (greenX[i] == redX && greenY[i] == redY) { // Green point should be different from the red
                                                                     // point
                        greenX[i] = random.nextInt(rows);
                        greenY[i] = random.nextInt(cols);
                    }
                    buttons[greenX[i]][greenY[i]].setBackground(END_COLORS[i / 5]); // Set color based on type
                }

                // Set the wall points
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (buttons[i][j].getBackground() == EMPTY_COLOR && random.nextDouble() < 0.2) {
                            buttons[i][j].setBackground(WALL_COLOR);
                        }
                    }
                }

             
                
                // Panel to display green points
                JPanel greenPointsPanel = new JPanel();
                greenPointsPanel.setLayout(new GridLayout(20, 1));
                
                greenPointsPanel.setBackground(col);
                // Create a JScrollPane for the green points panel
                JScrollPane scrollPane = new JScrollPane(greenPointsPanel);
                scrollPane.setBounds(800,30,150, 350); // Set bounds for the scroll pane
                
                
                
                 JLabel label = new JLabel("Toplanan Ödüller");
                 label.setForeground(col);
                 label.setBounds(800, 8, 100, 30); // x, y, genişlik, yükseklik
                 
                 JLabel label1 = new JLabel("Altın Sandık >> Sarı");
                 JLabel label2 = new JLabel("Gümüş Sandık >> Mor");
                 JLabel label3 = new JLabel("Zümrüt Sandık >> Mavi");
                 JLabel label4 = new JLabel("Bakır Sandık >> Pembe");
                 
                 label1.setBounds(1050,70, 150, 30); // x, y, genişlik, yükseklik
                 label2.setBounds(1050,94, 150, 30); // x, y, genişlik, yükseklik
                 label3.setBounds(1050,118, 150, 30); // x, y, genişlik, yükseklik
                 label4.setBounds(1050,142, 150, 30); // x, y, genişlik, yükseklik
                 
                 label1.setOpaque(true);
                 label2.setOpaque(true);
                 label3.setOpaque(true);
                 label4.setOpaque(true);
                 
                 label1.setBackground(col);
                 label2.setBackground(col);
                 label3.setBackground(col);
                 label4.setBackground(col);
                 
                
                mainPanel.add(label);
                mainPanel.add(label1);
                mainPanel.add(label2);
                mainPanel.add(label3);
                mainPanel.add(label4);

                mainPanel.add(scrollPane); // Add the scroll pane to the main panel

                anaFrame.setVisible(true);

                // Set a new timer to update the label with the coordinates of the green point reached by the yellow path
                Timer greenTimer = new Timer(500, new ActionListener() {
                    private int greenIndex = 0; // Initialize the index to iterate over green points

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (currentGreenIndex < 20) {
                            JLabel greenLabel = new JLabel("(" + greenX[greenIndex] + "," + greenY[greenIndex] + ") - " + END_NAMES[greenIndex / 5]);
                            greenPointsPanel.add(greenLabel); // Add the label to the panel
                            anaFrame.revalidate(); // Revalidate the frame to update the layout
                            anaFrame.repaint(); // Repaint the frame to reflect changes
                            greenIndex++; // Move to the next green point
                        }
                    }
                });
                greenTimer.start();
            }
        });
    }

    public static ArrayList<Point> findShortestPath(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Map<Point, Point> parent = new HashMap<>();
        ArrayList<Point> path = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(end)) {
                while (!current.equals(start)) {
                    path.add(current);
                    current = parent.get(current);
                }
                Collections.reverse(path);
                return path;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                Point next = new Point(nx, ny);

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited.contains(next)
                        && buttons[nx][ny].getBackground() != WALL_COLOR) {
                    visited.add(next);
                    parent.put(next, current);
                    queue.add(next);
                }
            }
        }
        return path;
    }

    public static boolean isGreenPoint(Point point) {
        for (int i = 0; i < 20; i++) {
            if (point.x == greenX[i] && point.y == greenY[i]) {
                return true;
            }
        }
        return false;
    }

    public static void findNextClosestGreen() {
        currentGreenIndex++;
        if (currentGreenIndex < 20) {
            currentPath = findShortestPath(new Point(greenX[currentGreenIndex - 1], greenY[currentGreenIndex - 1]),
                    new Point(greenX[currentGreenIndex], greenY[currentGreenIndex]));
            // Create a timer and open the yellow path with 0.5 second intervals
            Timer timer = new Timer(30, new ActionListener() {
                private Iterator<Point> iterator = currentPath.iterator();

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (iterator.hasNext()) {
                        Point point = iterator.next();
                        if (!isGreenPoint(point)) {
                            buttons[point.x][point.y].setBackground(PATH_COLOR);
                        }
                    } else {
                        ((Timer) e.getSource()).stop(); // Stop the timer
                        // After drawing the yellow path, continue
                        findNextClosestGreen();
                    }
                }
            });
            timer.start();
        }
    }
}
