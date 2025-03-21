import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class FlappyBirdWindow extends JFrame {
    private int birdY = 100;
    private int pipeX = 340;
    private int pipeHeight;
    private int score = 0;
    Random random = new Random();

    private Timer timer;

    public FlappyBirdWindow() {
        setTitle("Flappy Bird");
        setSize(360, 640);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pipeHeight = random.nextInt(200) + 100;
        // 100-299 //
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon("image/flappybirdbg.png");
                g.drawImage(bg.getImage(), 0, 0, 360, 640, this);

                ImageIcon bird = new ImageIcon("image/flappybird.png");
                g.drawImage(bird.getImage(), 50, birdY, 34, 24, this);

                ImageIcon toppipe = new ImageIcon("image/toppipe.png");
                g.drawImage(toppipe.getImage(), pipeX, pipeHeight - 350, 100, 350, this);
                ImageIcon botpipe = new ImageIcon("image/bottompipe.png");
                g.drawImage(botpipe.getImage(), pipeX, pipeHeight + 250, 100, 350, this);
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 36));
                if (checkEnd()) {
                    g.drawString("Gà Vc", 125, 320);
                }
                if (true) {
                    g.drawString(String.valueOf(score), 25, 40);
                }
            }
        };
        setContentPane(panel);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                birdY += 5;
                pipeX -= 2;

                if (pipeX <= -100) {
                    pipeX = 360;
                    pipeHeight = random.nextInt(200) + 100;
                    score++;
                }

                if (checkEnd()) {
                    JButton btnNewGame = new JButton("Replay");
                    btnNewGame.setFont(new Font("Arial", Font.BOLD, 18));
                    btnNewGame.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));
                    btnNewGame.addActionListener(ev -> newGame());

                    // Thêm nút vào giữa màn hình
                    JPanel panel = new JPanel();
                    panel.add(btnNewGame);
                    add(panel, BorderLayout.CENTER);

                    revalidate();
                    timer.stop();

                }
                repaint();
            }
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    birdY -= 100;
                }
            }
        });
        setFocusable(true);
        requestFocus();
        setVisible(true);
    }

    public boolean checkEnd() {
        if (birdY <= 0 || birdY >= 640 + 24)
            return true;
        if ((pipeX <= 84 && pipeX >= -16 && birdY <= pipeHeight)
                || (pipeX <= 84 && pipeX >= -16 && birdY >= pipeHeight + 220))
            return true;
        return false;
    }

    public void newGame() {
        new GameMenu();
        dispose();
    }

    public static void main(String[] args) {
        new FlappyBirdWindow();
    }
}
