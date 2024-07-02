package org.javanangcao.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TetrisPanel extends JPanel {
    private final int GRID_WIDTH = 15; // Chiều rộng bản đồ chơi
    private final int GRID_HEIGHT = 25; // Chiều cao bản đồ chơi
    private final int CELL_SIZE = 30; // Kích thước 1 ô
    public int[][] binMap;
    private int currentCol = -1;
    private int currentRow = -1;

    // Khai báo các màu sắc dưới dạng hằng số
    private final Color COLOR_FILLED = new Color(76, 175, 80, 200); // Màu xanh với một ít trong suốt
    private final Color COLOR_EMPTY = Color.decode("#F3F7EC");
    private final Color COLOR_GRID_LINE = Color.decode("#006989");
    private final Color COLOR_HIGHLIGHT = Color.decode("#FF204E");
    private final Color COLOR_LIGHT = new Color(102, 187, 106, 200); // Màu sáng hơn để tạo hiệu ứng 3D với một ít trong suốt
    private final Color COLOR_DARK = new Color(56, 142, 60, 200); // Màu tối hơn để tạo hiệu ứng 3D với một ít trong suốt

    public int getMapWidth() {
        return GRID_WIDTH;
    }

    public int getMapHeight() {
        return GRID_HEIGHT;
    }

    public int[][] getMap() {
        return deepCopy(binMap);
    }

    public TetrisPanel() {
        binMap = new int[GRID_WIDTH][GRID_HEIGHT];
        setPreferredSize(new Dimension(GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE));

        // Thêm MouseMotionListener để bắt sự kiện di chuyển chuột
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                int col = mouseX / CELL_SIZE; // Tính số cột của ô
                int row = mouseY / CELL_SIZE; // Tính số hàng của ô

                // Chỉ cập nhật màu nếu tọa độ thay đổi
                if (col != currentCol || row != currentRow) {
                    currentCol = col;
                    currentRow = row;
                    repaint();
                }
            }
        });

        // Thêm MouseListener để bắt sự kiện nhấp chuột
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                int col = mouseX / CELL_SIZE; // Tính số cột của ô
                int row = mouseY / CELL_SIZE; // Tính số hàng của ô

                // Cập nhật giá trị trong binMap khi click vào ô
                if (col >= 0 && col < GRID_WIDTH && row >= 0 && row < GRID_HEIGHT) {
                    binMap[col][row] = 1 - binMap[col][row]; // Đổi từ 0 thành 1 và ngược lại
                    System.out.printf("Cell [x: %d, y: %d]\n", col, row);
                    repaint();
                }
            }
        });
    }

    // Phương thức để xóa tất cả các ô trong binMap
    public void clearMap() {
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                binMap[x][y] = 0;
            }
        }
    }

    // Phương thức để bật một ô tại vị trí x, y trong binMap
    public void turnOnCell(int x, int y) {
        if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
            binMap[x][y] = 1;
        }
    }

    // Phương thức để tắt một ô tại vị trí x, y trong binMap
    public void turnOffCell(int x, int y) {
        if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
            binMap[x][y] = 0;
        }
    }

    // Phương thức để lấy giá trị của ô tại vị trí x, y trong binMap
    public int getValueAt(int x, int y) {
        if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
            return binMap[x][y];
        }
        System.console().printf("Error: Đầu vào toạ độ không đúng.");
        return -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Kích hoạt anti-aliasing cho đồ họa mịn hơn
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ tất cả các ô và viền màu xám
        for (int y = 0; y < GRID_HEIGHT; y++) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (binMap[x][y] == 1) {
                    g2d.setColor(COLOR_FILLED);
                    // Vẽ ô với bo góc nhẹ
                    g2d.fillRoundRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, 10, 10);
                    // Vẽ viền 3D đậm
                    g2d.setColor(COLOR_LIGHT);
                    g2d.drawLine(x * CELL_SIZE, y * CELL_SIZE, (x + 1) * CELL_SIZE - 1, y * CELL_SIZE);
                    g2d.drawLine(x * CELL_SIZE, y * CELL_SIZE, x * CELL_SIZE, (y + 1) * CELL_SIZE - 1);
                    g2d.setColor(COLOR_DARK);
                    g2d.drawLine(x * CELL_SIZE, (y + 1) * CELL_SIZE - 1, (x + 1) * CELL_SIZE - 1, (y + 1) * CELL_SIZE - 1);
                    g2d.drawLine((x + 1) * CELL_SIZE - 1, y * CELL_SIZE, (x + 1) * CELL_SIZE - 1, (y + 1) * CELL_SIZE - 1);

                    // Vẽ viền 3D nhạt hơn
                    g2d.setColor(COLOR_LIGHT);
                    g2d.drawLine(x * CELL_SIZE + 1, y * CELL_SIZE + 1, (x + 1) * CELL_SIZE - 2, y * CELL_SIZE + 1);
                    g2d.drawLine(x * CELL_SIZE + 1, y * CELL_SIZE + 1, x * CELL_SIZE + 1, (y + 1) * CELL_SIZE - 2);
                    g2d.setColor(COLOR_DARK);
                    g2d.drawLine(x * CELL_SIZE + 1, (y + 1) * CELL_SIZE - 2, (x + 1) * CELL_SIZE - 2, (y + 1) * CELL_SIZE - 2);
                    g2d.drawLine((x + 1) * CELL_SIZE - 2, y * CELL_SIZE + 1, (x + 1) * CELL_SIZE - 2, (y + 1) * CELL_SIZE - 2);
                } else {
                    g2d.setColor(COLOR_EMPTY);
                    g2d.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g2d.setColor(COLOR_GRID_LINE);
                    g2d.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // Vẽ viền cho ô hiện tại
        if (currentCol != -1 && currentRow != -1) {
            g2d.setColor(COLOR_HIGHLIGHT);
            g2d.drawRect(currentCol * CELL_SIZE, currentRow * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    // Phương thức deep copy mảng hai chiều
    private int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        int[][] result = new int[GRID_WIDTH][GRID_HEIGHT];
        for (int i = 0; i < GRID_WIDTH; i++) {
            System.arraycopy(original[i], 0, result[i], 0, GRID_HEIGHT);
        }
        return result;
    }
}
