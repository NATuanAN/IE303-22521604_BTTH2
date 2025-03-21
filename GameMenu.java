import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {
    public GameMenu() {
        setTitle("Flappy Bird - Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setResizable(false);

        // Panel chứa nút bấm
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Nút New Game
        JButton btnNewGame = new JButton("New Game");
        btnNewGame.setFont(new Font("Arial", Font.BOLD, 18));
        btnNewGame.addActionListener(e -> startNewGame());

        // Nút Exit
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.addActionListener(e -> System.exit(0));

        // Thêm các nút vào panel
        panel.add(btnNewGame);
        panel.add(btnExit);

        // Thêm panel vào cửa sổ
        add(panel);

        setVisible(true);
    }

    // Hàm bắt đầu trò chơi mới
    private void startNewGame() {
        // Tích hợp mở cửa sổ game Flappy Bird ở đây
        new FlappyBirdWindow();
        dispose(); // Đóng menu
    }

    // Hàm tải game đã lưu

    public static void main(String[] args) {
        new GameMenu();
    }
}
