package com.twistedblizzard.cardsagainsthumanity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Random;


public class GameActivity extends Activity{

    public static final String TAG = "GameActivity";
    public Resources res;
    public int currentRound;
    public int currentPlayer;
    public int rounds;
    public int players;
    public int judgePlayer;
    public String[] blackCards;
    public String[] whiteCards;
    public String[] player1;
    public String[] player2;
    public String[] player3;
    public String[] player4;
    public String[] player5;
    public String[] player6;
    public String[] player7;
    public String[] player8;
    public int[] blackCardsId;
    public LinearLayout layout;
    public TextView textView;
    public TextView cardText;
    public Button button;
    public Button debug;
    public int setup;
    public int pick;
    public boolean fail;
    public ListView listView;

    Random rnd = new Random();
    public int getRandom(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        res = getResources();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        currentRound = 0;
        currentPlayer = 0;
        rounds = sharedPref.getInt(getString(R.string.key_setup_rounds, ""), 0);
        players = sharedPref.getInt(getString(R.string.key_setup_players, ""), 0);
        blackCards = new String[rounds];
        blackCardsId = new int[rounds];

        layout = new LinearLayout(this);
        listView = new ListView(this);
        textView = new TextView(this);
        cardText = new TextView(this);
        button = new Button(this);
        debug = new Button(this);

        layout.setOrientation(LinearLayout.VERTICAL);

        whiteCards = res.getStringArray(R.array.white_us);

        setupBlackCards();

        setupPlayer1();
        setupPlayer2();

        if (players > 2) {
            setupPlayer3();
        }

        if (players > 3) {
            setupPlayer4();
        }

        if (players > 4) {
            setupPlayer5();
        }

        if (players > 5) {
            setupPublic6();
        }

        if (players > 6) {
            setupPlayer7();
        }

        if (players > 7) {
            setupPlayer8();
        }

        judgePlayer = 1;

        nextBlackCard();
        Log.v(TAG, "Display Black Card");
    }

    public void setupBlackCards() {
        String[] blackCards1 = res.getStringArray(R.array.black_us_1);
        String[] blackCards2 = res.getStringArray(R.array.black_us_2);
        String[] blackCards3 = res.getStringArray(R.array.black_us_3);

        int nBlackCards1 = blackCards1.length;
        int nBlackCards2 = blackCards2.length;
        int nBlackCards3 = blackCards3.length;

        int min = 0;
        int max = (nBlackCards1 + nBlackCards2 + nBlackCards3) - 1;

        setup = 0;
        while (setup < rounds ) {
            int i1 = getRandom(min, max);

            if (i1 < nBlackCards1) {
                blackCards[setup] = blackCards1[i1];
                blackCardsId[setup] = 1;
            }
            else if (i1 < nBlackCards2 + nBlackCards1) {
                blackCards[setup] = blackCards2[i1 - nBlackCards1];
                blackCardsId[setup] = 2;
            }
            else {
                blackCards[setup] = blackCards3[i1 - (nBlackCards1 + nBlackCards2)];
                blackCardsId[setup] = 3;
            }

            int setup2 = 0;
            while (setup2 < setup) {
                if (blackCards[setup2].equals(blackCards[setup])) {
                    fail = true;
                }
                setup2 ++;
            }

            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer1() {
        player1 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player1[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player1[setup2].equals(player1[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer2() {
        player2 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player2[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player2[setup2].equals(player2[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player2[setup].equals(player1[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer3() {
        player3 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player3[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player3[setup2].equals(player3[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player3[setup].equals(player1[setup3]) ||
                        player3[setup].equals(player2[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer4() {
        player4 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player4[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player4[setup2].equals(player4[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player4[setup].equals(player1[setup3]) ||
                        player4[setup].equals(player2[setup3]) ||
                        player4[setup].equals(player3[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer5() {
        player5 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player5[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player5[setup2].equals(player5[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player5[setup].equals(player1[setup3]) ||
                        player5[setup].equals(player2[setup3]) ||
                        player5[setup].equals(player3[setup3]) ||
                        player5[setup].equals(player4[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPublic6() {
        player6 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player6[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player6[setup2].equals(player6[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player6[setup].equals(player1[setup3]) ||
                        player6[setup].equals(player2[setup3]) ||
                        player6[setup].equals(player3[setup3]) ||
                        player6[setup].equals(player4[setup3]) ||
                        player6[setup].equals(player5[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer7() {
        player7 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player7[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player7[setup2].equals(player7[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player7[setup].equals(player1[setup3]) ||
                        player7[setup].equals(player2[setup3]) ||
                        player7[setup].equals(player3[setup3]) ||
                        player7[setup].equals(player4[setup3]) ||
                        player7[setup].equals(player5[setup3]) ||
                        player7[setup].equals(player6[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void setupPlayer8() {
        player8 = new String[10];
        setup = 0;
        while (setup < 10) {
            int i = getRandom(0, whiteCards.length - 1);
            player8[setup] = whiteCards[i];
            int setup2 = 0;
            while (setup2 > setup) {
                if (player8[setup2].equals(player8[setup])){
                    fail = true;
                }
                setup2 ++;
            }
            int setup3 = 0;
            while (setup3 < 10) {
                if (player8[setup].equals(player1[setup3]) ||
                        player8[setup].equals(player2[setup3]) ||
                        player8[setup].equals(player3[setup3]) ||
                        player8[setup].equals(player4[setup3]) ||
                        player8[setup].equals(player5[setup3]) ||
                        player8[setup].equals(player6[setup3]) ||
                        player8[setup].equals(player7[setup3])) {
                    fail = true;
                }
                setup3 ++;
            }
            if (!fail) {
                setup ++;
            }

            else {
                fail = false;
            }
        }
    }

    public void nextBlackCard() {
        Log.v(TAG, "Displaying Next Black Card");
        currentPlayer = 0;
        layout.removeAllViews();

        if (currentRound < rounds) {
            layout.addView(textView);
            layout.addView(button);
            layout.addView(debug);

            textView.setText(blackCards[currentRound]);
            textView.setTextSize(28);

            button.setText(getString(R.string.ok));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextPlayer();
                    Log.v(TAG, "Black Card Button Clicked");
                }
            });

            debug.setText(getString(R.string.title_activity_debug));
            debug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int setup = 0;
                    while (setup < rounds) {
                        Log.v(TAG, blackCards[setup]);
                        if (!fail) {
                            setup++;
                        } else {
                            fail = false;
                        }
                    }
                }
            });

            setContentView(layout);
        }

        else {
            layout.addView(textView);
            layout.addView(button);
        }
    }

    public void nextPlayer() {
        currentPlayer ++;
        if (currentPlayer == judgePlayer) {
            currentPlayer ++;
        }
        Log.v(TAG, String.valueOf(currentPlayer));
        if (currentPlayer > players) {
            currentRound ++;
            nextBlackCard();
        }

        pick  = blackCardsId[currentRound];

        layout.removeAllViews();
        layout.addView(textView);
        layout.addView(listView);

        textView.setText(blackCards[currentRound] + "\n" +
                getString(R.string.player) + " " + currentPlayer + "\n" +
                getString(R.string.round) + " " + (currentRound + 1) + "\n" +
                getString(R.string.pick) + " " + String.valueOf(pick));
        textView.setTextSize(14);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getCurrentPlayerCards(currentPlayer, pick));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layout.removeAllViews();
                layout.addView(textView);
                layout.addView(button);

                textView.setText(blackCards[currentRound] + "\n" +
                        getString(R.string.player) + " " + currentPlayer + "\n" +
                        getCurrentPlayerCards(currentPlayer, pick)[position]);
            }
        });

        setContentView(layout);
    }

    public String[] getCurrentPlayerCards(int currentPlayer, int pick) {
        if (pick == 3) {
            if (currentPlayer == 1) {
                return playerSpecial(player1);
            }
            if (currentPlayer == 2) {
                return playerSpecial(player2);
            }
            if (currentPlayer == 3) {
                return playerSpecial(player3);
            }
            if (currentPlayer == 4) {
                return playerSpecial(player4);
            }
            if (currentPlayer == 5) {
                return playerSpecial(player5);
            }
            if (currentPlayer == 6) {
                return playerSpecial(player6);
            }
            if (currentPlayer == 7) {
                return playerSpecial(player7);
            } else {
                return playerSpecial(player8);
            }
        }

        else {
            if (currentPlayer == 1) {
                return player1;
            }
            if (currentPlayer == 2) {
                return player2;
            }
            if (currentPlayer == 3) {
                return player3;
            }
            if (currentPlayer == 4) {
                return player4;
            }
            if (currentPlayer == 5) {
                return player5;
            }
            if (currentPlayer == 6) {
                return player6;
            }
            if (currentPlayer == 7) {
                return player7;
            } else {
                return player8;
            }
        }
    }

    public String[] playerSpecial(String[] player) {
        String[] playerSpecial = new String[12];

        setup = 0;
        while (setup < 10) {
            playerSpecial[setup] = player[setup];
            setup ++;
        }

        fail = false;
        while (setup < 12) {
            int i = getRandom(0, whiteCards.length - 1);
            playerSpecial[setup] = whiteCards[i];

            int setup2 = 0;
            while (setup2 < 10) {
                if (playerSpecial[setup].equals(player1[setup2]) ||
                        playerSpecial[setup].equals(player2[setup2]) ||
                        playerSpecial[setup].equals(player3[setup2]) ||
                        playerSpecial[setup].equals(player4[setup2]) ||
                        playerSpecial[setup].equals(player5[setup2]) ||
                        playerSpecial[setup].equals(player6[setup2]) ||
                        playerSpecial[setup].equals(player7[setup2]) ||
                        playerSpecial[setup].equals(player8[setup2])) {
                    fail = true;
                }
                setup2 ++;
            }
            if (!fail) {
                setup ++;
            }
            else {
                fail = false;
            }
        }
        return playerSpecial;
    }
}
