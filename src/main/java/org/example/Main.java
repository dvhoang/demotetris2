package org.example;

import org.javanangcao.tetris.Tetris;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main{
    private Tetris tetris;
    public static void main(String[] args) {
        System.out.println("JAVANANGCAO\uD83C\uDF3C Hé lô mấy phen");
        SwingUtilities.invokeLater(()-> new Main().createGame()); // Khởi tạo game
    }

    /**
     * Tạo game với các setting ban đầu.
     */
    public void createGame(){
        // Tạo game
        this.tetris = new Tetris();
        // Setting FPS game
        int fps = 5;
        // Setting bàn phím, lắng nghe các phím được bấm.
        tetris.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        //Start game loop
        Timer timer = new Timer(1000 / fps, e -> {
            // Vẽ lại giao diện mỗi vòng lặp
            tetris.repaintMap();
            runGameLoop();
        });
        timer.start();
    }

    /**
     * <p>Tạo và bắt đầu game loop</p>
     * <p>Trò chơi được viết tiếp trong hàm này.</p>
     */
    public void runGameLoop(){

    }

    /**
     * Phương thức xử lý sự kiện nhấn phím
     */
    public void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("W key pressed");
                // Thực hiện hành động khi nhấn W
                break;
            case KeyEvent.VK_A:
                System.out.println("A key pressed");
                // Thực hiện hành động khi nhấn A
                break;
            case KeyEvent.VK_S:
                System.out.println("S key pressed");
                // Thực hiện hành động khi nhấn S
                break;
            case KeyEvent.VK_D:
                System.out.println("D key pressed");
                // Thực hiện hành động khi nhấn D
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Space key pressed");
                // Thực hiện hành động khi nhấn Space
                break;
            default:
                break;
        }
    }

    /**
     * Lấy chiều rộng bản đồ
     * @return Chiều rộng bản đồ
     * */
    public int getMapWidth(){
        return tetris.getMapWidth();
    }

    /**
     * Lấy chiều cao bản đồ
     * @return Chiều cao bản đồ
     * */
    public int getMapHeight(){
        return tetris.getMapHeight();
    }

    /**
     * Tắt toàn bộ cảc điểm trên bản đồ
     * */
    public void clearMap(){
        tetris.clearMap();
    }

    /**
     * Lấy giá trị điểm ở toạ độ được chỉ định
     * @param x Toạ độ x
     * @param y Toạ độ y
     * @return Số nguyên đại diện cho trạng thái bật tắt của điểm, bật (1) và tắt (0)
     * ; -1 Khi có lỗi xảy ra
     * */
    public int getCell(int x, int y){
        return tetris.getCell(x, y);
    }

    /**
     * <p>Lấy bản đồ tại thời điểm hiện tại</p>
     * <p>Chiều rộng và chiều cao của bản đồ lấy từ hàm {@link #getMapWidth()} và {@link #getMapHeight()}</p>
     * @return Mảng hai chiều chứa toạ độ các điểm được bật (1) và tắt (0)
     * */
    public int[][] getMap(){
        return tetris.getMap();
    }

    /**
     * Bật sáng ô được chỉ định
     * @param x Toạ độ x
     * @param y Toạ độ y
     * */
    public void turnOnCell(int x, int y){
        tetris.turnOnCell(x, y);
    }

    /**
     * Tắt sáng ô được chỉ định
     * @param x Toạ độ x
     * @param y Toạ độ y
     * */
    public void turnOffCell(int x, int y){
        tetris.turnOffCell(x, y);
    }

    /**
     * Bật sáng ô được chỉ định
     * @return Tên người chơi hiện tại
     * */
    public String getPlayerName() {
        return tetris.getPlayerName();
    }

    /**
     * Gán tên người chơi hiện tại.
     * @param   playerName Tên người chơi
     */
    public void setPlayerName(String playerName) {
        tetris.setPlayerName(playerName);
    }

    /**
     * Lấy điểm hiện tại
     * @return Điểm
     * */
    public int getScore() {
        return tetris.getScore();
    }

    /**
     * Gán điểm hiện tại
     * @param score Điểm
     * */
    public void setScore(int score) {
        tetris.setScore(score);
    }
}