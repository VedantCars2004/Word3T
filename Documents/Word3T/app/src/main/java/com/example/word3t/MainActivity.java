package com.example.word3t;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final EditText[][] starting_board = new EditText[3][3];
    private TextView winnerMessage;

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

        winnerMessage = findViewById(R.id.winnerTextView);
        Button checkWinButton = findViewById(R.id.checkWinButton);
        checkWinButton.setOnClickListener(v->CheckWin());
    }

    @SuppressLint("SetTextI18n")
    private void CheckWin() {
         // check for if words are valid (length 1-4)
        // check after each word is done being typed
        // change win message and end game
        // end game also if board is full
        // player 1 and 2 distinction
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j]=starting_board[i][j].getText().toString();
            }
        }
        // check rows
        if (hasCommon(board[0][0], board[0][1], board[0][2])) {
            winnerMessage.setText("Winner: Row 1");
        } else if (hasCommon(board[1][0], board[1][1], board[1][2])) {
            winnerMessage.setText("Winner: Row 2");
        } else if (hasCommon(board[2][0], board[2][1], board[2][2])) {
            winnerMessage.setText("Winner: Row 3");
        } // check columns
        else if (hasCommon(board[0][0], board[1][0], board[2][0])) {
            winnerMessage.setText("Winner: Column 1");
        } else if (hasCommon(board[0][1], board[1][1], board[2][1])) {
            winnerMessage.setText("Winner: Column 2");
        } else if (hasCommon(board[0][2], board[1][2], board[2][2])) {
            winnerMessage.setText("Winner: Column 3");
        } // check diagonals
        else if (hasCommon(board[0][0], board[1][1], board[2][2])) {
            winnerMessage.setText("Winner: Diagonal 1");
        } else if (hasCommon(board[0][2], board[1][1], board[2][0])) {
            winnerMessage.setText("Winner: Diagonal 2");
        } else {
            winnerMessage.setText("No winner yet");
        }
    }

    private boolean hasCommon(String worda, String wordb, String wordc) {
        worda = worda.toLowerCase();
        wordb = wordb.toLowerCase();
        wordc = wordc.toLowerCase();
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
}