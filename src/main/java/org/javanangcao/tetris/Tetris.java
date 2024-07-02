package org.javanangcao.tetris;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Tetris extends JFrame {

    private String playerName = "";
    private int score = 0;
    private JLabel scoreLabel;
    private JLabel playerLabel;
    private TetrisPanel tetrisPanel;
    private static final Color COLOR_BACKGROUND = Color.decode("#F3F7EC"); // Màu nền
    private static final Color COLOR_TEXT = Color.decode("#006989"); // Màu chữ


    public Tetris() {
        setTitle("JAVANANGCAO\uD83C\uDF3C");
        //Tao lưới
        tetrisPanel = new TetrisPanel();

        // Thiết lập layout chính của JFrame
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(COLOR_BACKGROUND);
        // Tạo và thêm tiêu đề
        JLabel titleLabel = new JLabel("Tetris", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        titleLabel.setForeground(COLOR_TEXT);
        topPanel.add(titleLabel);

        // Tạo panel cho thông tin người chơi và điểm
        JPanel infoPanel = new JPanel(new GridLayout(1, 2));

        // Tạo và thêm label người chơi
        playerLabel = new JLabel("Player:" + playerName);
        playerLabel.setForeground(COLOR_TEXT);

        infoPanel.add(playerLabel);

        // Tạo và thêm label điểm
        scoreLabel = new JLabel("Score: "+score, SwingConstants.RIGHT);
        scoreLabel.setForeground(COLOR_TEXT);
        infoPanel.add(scoreLabel);
        infoPanel.setBorder(new EmptyBorder(0, 16, 0, 16));
        infoPanel.setBackground(COLOR_BACKGROUND);
        topPanel.add(infoPanel);
        add(topPanel, BorderLayout.NORTH);

        // Thêm panel lưới chơi
        add(tetrisPanel, BorderLayout.CENTER);

        // Cài đặt hành động khi đóng cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Thiết lập kích thước cửa sổ
        setSize(400, 800);

        // Hiển thị cửa sổ
        setVisible(true);

        pack();
        setResizable(false);
    }

    public void repaintMap(){
        tetrisPanel.repaint();
    }

    /**
     * Lấy chiều rộng bản đồ
     * @return Chiều rộng bản đồ
     * */
    public int getMapWidth(){
        return tetrisPanel.getMapWidth();
    }

    /**
     * Lấy chiều cao bản đồ
     * @return Chiều cao bản đồ
     * */
    public int getMapHeight(){
        return tetrisPanel.getMapHeight();
    }

    /**
     * Tắt toàn bộ cảc điểm trên bản đồ
     * */
    public void clearMap(){
        if(tetrisPanel != null){
            tetrisPanel.clearMap();
        }
    }

    /**
     * Lấy giá trị điểm ở toạ độ được chỉ định
     * @param x Toạ độ x
     * @param y Toạ độ y
     * @return Số nguyên đại diện cho trạng thái bật tắt của điểm, bật (1) và tắt (0)
     * ; -1 Khi có lỗi xảy ra
     * */
    public int getCell(int x, int y){
        if(tetrisPanel != null){
            return tetrisPanel.getValueAt(x, y);
        }
        return -1;
    }

    /**
     * Lấy bản đồ tại thời điểm hiện tại
     * @return Mảng hai chiều chứa toạ độ các điểm được bật (1) và tắt (0)
     * */
    public int[][] getMap(){
        if(tetrisPanel != null){
            return tetrisPanel.getMap();
        }
        return null;
    }

    /**
     * Bật sáng ô được chỉ định
     * @param x Toạ độ x
     * @param y Toạ độ y
     * */
    public void turnOnCell(int x, int y){
        if(tetrisPanel != null){
            tetrisPanel.turnOnCell(x, y);
        }
    }

    /**
     * Tắt sáng ô được chỉ định
     * @param x Toạ độ x
     * @param y Toạ độ y
     * */
    public void turnOffCell(int x, int y){
        if(tetrisPanel != null){
            tetrisPanel.turnOffCell(x, y);
        }
    }

    /**
     * Bật sáng ô được chỉ định
     * @return Tên người chơi hiện tại
     * */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gán tên người chơi hiện tại.
     * @param   playerName Tên người chơi
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        this.playerLabel.setText("Player: "+playerName);
    }

    /**
     * Lấy điểm hiện tại
     * @return Điểm
     * */
    public int getScore() {
        return score;
    }

    /**
     * Gán điểm hiện tại
     * @param score Điểm
     * */
    public void setScore(int score) {
        this.score = score;
        this.scoreLabel.setText("Score: "+score);
    }

}