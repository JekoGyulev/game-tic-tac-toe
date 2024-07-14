package my_github_projects.tictactoeproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
        int boardWidth = 600;
        int boardHeight = 650;
         JFrame frame = new JFrame("Tic-Tac-Toe");
         JLabel textLabel = new JLabel();
         JPanel textPanel = new JPanel();
         JPanel boardPanel = new JPanel();
         JButton[][] board = new JButton[3][3];
         String playerX = "X";
         String playerO = "O";
         String currentPlayer = playerX; // it will keep track of moves

         boolean gameOver = false;
         int turns = 0; //player moves total
        public TicTacToe() {

            frame.setVisible(true);
            frame.setSize(boardWidth, boardHeight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            textLabel.setBackground(Color.gray);
            textLabel.setForeground(Color.white);
            textLabel.setFont(new Font("Arial", Font.BOLD, 50));
            textLabel.setHorizontalAlignment(JLabel.CENTER);
            textLabel.setText("Tic-Tac-Toe");
            textLabel.setOpaque(true);

            textPanel.setLayout(new BorderLayout());
            textPanel.add(textLabel);
            frame.add(textPanel,BorderLayout.NORTH);

            boardPanel.setLayout(new GridLayout(3,3));
            boardPanel.setBackground(Color.gray);
            frame.add(boardPanel);

            for(int r = 0; r < 3; r++) {
                for(int col = 0; col < 3; col++) {
                    JButton tile = new JButton();
                    board[r][col] = tile;
                    boardPanel.add(tile);
                    tile.setBackground(Color.darkGray);
                    tile.setForeground(Color.white);
                    tile.setFont(new Font("Arial",Font.BOLD, 120));
                    tile.setFocusable(false);

                    tile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameOver) {
                                return;
                            }

                            JButton tile = (JButton) e.getSource();
                            if (tile.getText().isEmpty()) {
                                tile.setText(currentPlayer);
                                turns++;
                                checkWinner();

                                if (!gameOver) {
                                    currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                    textLabel.setText(currentPlayer + "'s turn.");
                                }
                            }
                        }
                    });
                }
            }
        }
        private void checkWinner() {
            //let's first check the horizontal
            for (int r = 0; r < 3; r++) {
                if(board[r][0].getText().isEmpty()){
                    continue;
                }

                if (board[r][0].getText().equals(board[r][1].getText())
                        && board[r][1].getText().equals(board[r][2].getText())) {

                    for(int i = 0; i < 3; i++) {
                        setWinner(board[r][i]);
                    }
                    gameOver = true;
                    return;
                }
            }

            //let's check horizontal
            for (int col = 0; col < 3; col++) {
                if (board[0][col].getText().isEmpty()) {
                    continue;
                }

                if (board[0][col].getText().equals(board[1][col].getText())
                    && board[1][col].getText().equals(board[2][col].getText())) {

                    for(int i = 0; i < 3; i++) {
                        setWinner(board[i][col]);
                    }
                    gameOver = true;
                    return;
                }

            }

            //let's check diagonally
            //top left:
            if (board[0][0].getText().equals(board[1][1].getText())
                 && board[1][1].getText().equals(board[2][2].getText())) {
                for(int i = 0; i < 3; i++) {
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
            }
            //top-right -> bottom-left
            if (board[0][2].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][0].getText())
                && !board[0][2].getText().isEmpty()) {

                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;
            }


            if (turns == 9) {
                for(int r = 0; r < 3; r++) {
                    for(int c = 0; c < 3; c++) {
                        setTie(board[r][c]);
                    }
                }
            gameOver = true;
            }
        }
    private void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }

    private void setWinner(JButton button) {
            button.setForeground(Color.green);
            button.setBackground(Color.gray);
            textLabel.setText(currentPlayer + " is the winner!");
    }
}
