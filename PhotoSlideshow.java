import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class PhotoSlideshow {

    private JFrame frame;
    private JLabel photoLabel;
    private Timer timer;
    private int currentIndex = 0;
    private final String[] photoPaths = {
            "D:/Documents/Coding stuff/MoonPhaseClock/1.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/2.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/3.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/4.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/5.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/6.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/7.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/8.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/9.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/10.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/11.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/12.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/13.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/14.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/15.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/16.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/17.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/18.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/19.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/20.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/21.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/22.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/23.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/24.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/25.jpg",            
            "D:/Documents/Coding stuff/MoonPhaseClock/26.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/27.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/28.jpg",
            "D:/Documents/Coding stuff/MoonPhaseClock/29.jpg"
    };

    public PhotoSlideshow(int delay) {
        frame = new JFrame("Moon Phase Clock");
        frame.setResizable(true);  // Allow resizing
        //frame.setUndecorated(true); // Removes the title bar and the Java logo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 730);
        frame.setLayout(new BorderLayout());

        /* THis is commented out, but allows a non-resizable, custom title bar 
        //Custom title bar panel
        JPanel titleBar = new JPanel();
        titleBar.setBackground(Color.black);
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Create custom buttons
        JButton minimizeButton = new JButton("_");
        JButton maximizeButton = new JButton("[ ]");
        JButton closeButton = new JButton("X");

        // Customize button appearance
        minimizeButton.setFocusPainted(false);
        maximizeButton.setFocusPainted(false);
        closeButton.setFocusPainted(false);

        minimizeButton.setBackground(Color.black);
        maximizeButton.setBackground(Color.black);
        closeButton.setBackground(Color.RED);

        minimizeButton.setForeground(Color.WHITE);
        maximizeButton.setForeground(Color.WHITE);
        closeButton.setForeground(Color.WHITE);

        // Add action listeners for the buttons
        minimizeButton.addActionListener(e -> frame.setState(Frame.ICONIFIED)); // Minimize the window
        maximizeButton.addActionListener(e -> {
            if (frame.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                frame.setExtendedState(JFrame.NORMAL); // Restore to normal size
            } else {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
            }
        });
        closeButton.addActionListener(e -> System.exit(0)); // Close the application

        // Add buttons to the title bar
        titleBar.add(minimizeButton);
        titleBar.add(maximizeButton);
        titleBar.add(closeButton);

        // Add the custom title bar to the top of the frame
        frame.add(titleBar, BorderLayout.NORTH);*/

        photoLabel = new JLabel();
        photoLabel.setOpaque(true);  // Make the label opaque to show its background color
        photoLabel.setBackground(Color.BLACK);  // Set the background color to black
        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        photoLabel.setVerticalAlignment(JLabel.CENTER);

        frame.add(photoLabel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Resize listener to handle window resizing
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                displayCurrentPhoto();
            }
        });

        // Use the timer to control the slideshow        
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNextPhoto();
            }
        });
        timer.start(); // Start the time
        
    /* 
     // Start the slideshow loop
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < photoPaths.length; i++) {
                        currentIndex = i;
                        displayCurrentPhoto();
                        try {
                            Thread.sleep(2000); // 2 seconds delay
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();   */
        
     // Immediately display all photos in sequence without delay
     /*    for (int i = 0; i < photoPaths.length; i++) {
            currentIndex = i;
            displayCurrentPhoto();
            try {
                Thread.sleep(2000); // Optional: add a small delay between images for visibility
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    private void displayCurrentPhoto() {
        if (photoPaths.length > 0 && currentIndex < photoPaths.length) {
            String path = photoPaths[currentIndex];
            File imgFile = new File(path);
            if (imgFile.exists()) {
                ImageIcon imageIcon = new ImageIcon(path);
                Image image = imageIcon.getImage();

                // Get the aspect ratio
                int originalWidth = image.getWidth(null);
                int originalHeight = image.getHeight(null);
                double aspectRatio = (double) originalWidth / originalHeight;

                // Get the label size
                int labelWidth = photoLabel.getWidth();
                int labelHeight = photoLabel.getHeight();

                // Calculate the new dimensions while keeping the aspect ratio
                int newWidth = labelWidth;
                int newHeight = (int) (labelWidth / aspectRatio);
                if (newHeight > labelHeight) {
                    newHeight = labelHeight;
                    newWidth = (int) (labelHeight * aspectRatio);
                }

                Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                photoLabel.setIcon(new ImageIcon(scaledImage));

                // Print the display duration to the console
                System.out.println("Displaying image " + (currentIndex + 1) + " for " + (double) timer.getDelay() / 1000 + " seconds.");


            } else {
                System.err.println("File not found: " + path);
            }
        }
    }

    private void displayNextPhoto() {
        currentIndex = (currentIndex + 1) % photoPaths.length;
        displayCurrentPhoto();
    }

    public static void main(String[] args) {
        // Calculate the total delay in milliseconds for 29.5 days
        long totalMilliseconds = (long) (29.5 * 24 * 60 * 60 * 1000);
        // Calculate the delay per photo
        int delay = (int) (totalMilliseconds / 29);

        // Create and start the slideshow with the calculated delay
        new PhotoSlideshow(delay);
    }
}
