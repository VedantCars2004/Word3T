package com.example.word3t;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private final EditText[][] starting_board = new EditText[3][3];
    private TextView winnerMessage;
    private Button restartButton;
    private boolean over = false;
    private class TextWatcher implements android.text.TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // check for win after every time text is changed
            CheckWin();
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starting_board[0][0] = findViewById(R.id.cell00);
        starting_board[0][1] = findViewById(R.id.cell01);
        starting_board[0][2] = findViewById(R.id.cell02);
        starting_board[1][0] = findViewById(R.id.cell10);
        starting_board[1][1] = findViewById(R.id.cell11);
        starting_board[1][2] = findViewById(R.id.cell12);
        starting_board[2][0] = findViewById(R.id.cell20);
        starting_board[2][1] = findViewById(R.id.cell21);
        starting_board[2][2] = findViewById(R.id.cell22);
        // new TextWatcher to automatically check for if there is a win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                starting_board[i][j].setTextColor(getResources().getColor(R.color.dark_blue));
                starting_board[i][j].addTextChangedListener(new TextWatcher());
            }
        }
        winnerMessage = findViewById(R.id.winnerTextView);
        restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> restartGame());
//        Button checkWinButton = findViewById(R.id.checkWinButton);
//        checkWinButton.setOnClickListener(v->CheckWin());
    }

    @SuppressLint("SetTextI18n")
    private void CheckWin() {
        // check if each word exists in the dictionary
        // change win message and end game
        // end game when board full
        // player 1 and 2 distinction
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j]=starting_board[i][j].getText().toString();
            }
        }
        try {
            // check rows
            if (hasCommon(board[0][0], board[0][1], board[0][2])) {
                winnerMessage.setText("Winner: Row 1");
                disableBoard();
            } else if (hasCommon(board[1][0], board[1][1], board[1][2])) {
                winnerMessage.setText("Winner: Row 2");
                disableBoard();
            } else if (hasCommon(board[2][0], board[2][1], board[2][2])) {
                winnerMessage.setText("Winner: Row 3");
                disableBoard();
            } // check columns
            else if (hasCommon(board[0][0], board[1][0], board[2][0])) {
                winnerMessage.setText("Winner: Column 1");
                disableBoard();
            } else if (hasCommon(board[0][1], board[1][1], board[2][1])) {
                winnerMessage.setText("Winner: Column 2");
                disableBoard();
            } else if (hasCommon(board[0][2], board[1][2], board[2][2])) {
                winnerMessage.setText("Winner: Column 3");
                disableBoard();
            } // check diagonals
            else if (hasCommon(board[0][0], board[1][1], board[2][2])) {
                winnerMessage.setText("Winner: Diagonal 1");
                disableBoard();
            } else if (hasCommon(board[0][2], board[1][1], board[2][0])) {
                winnerMessage.setText("Winner: Diagonal 2");
                disableBoard();
            } else {
                winnerMessage.setText("No winner yet");
            }
        } catch (Exception e) {
            e.printStackTrace();
            winnerMessage.setText("An error has occurred");

        }
    }

    private boolean hasCommon(String worda, String wordb, String wordc) {
        worda = worda.toLowerCase();
        wordb = wordb.toLowerCase();
        wordc = wordc.toLowerCase();
        if (worda.length() > 4 || wordb.length() > 4 || wordc.length() > 4) {
            throw new IllegalArgumentException("Atleast one of the words exceeds maximum length");
        }
        for (char ca : worda.toCharArray()) {
            for (char cb : wordb.toCharArray()) {
                for (char cc: wordc.toCharArray()) {
                    if (ca == cb && cb == cc) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void restartGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                starting_board[i][j].setText("");
                starting_board[i][j].setEnabled(true);
            }
        }
        over = false;
        winnerMessage.setText("");
        restartButton.setVisibility(View.GONE);
    }

    private void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                starting_board[i][j].setEnabled(false);
            }
        }
        restartButton.setVisibility(View.VISIBLE);
    }

}

