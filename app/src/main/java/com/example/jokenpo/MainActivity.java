package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button; // Importe a classe Button

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Componentes da UI (referências aos elementos do XML)
    private TextView textViewPlayerScore;
    private TextView textViewComputerScore;
    private TextView textViewComputerChoice;
    private TextView textViewResult;
    private ImageButton buttonPedra;
    private ImageButton buttonPapel;
    private ImageButton buttonTesoura;
    private Button restartGame;

    // Variáveis para a pontuação
    private int playerScore = 0;
    private int computerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar os componentes da UI
        textViewPlayerScore = findViewById(R.id.textViewPlayerScore);
        textViewComputerScore = findViewById(R.id.textViewComputerScore);
        textViewComputerChoice = findViewById(R.id.textViewComputerChoice);
        textViewResult = findViewById(R.id.textViewResult);
        buttonPedra = findViewById(R.id.buttonRock);
        buttonPapel = findViewById(R.id.buttonPaper);
        buttonTesoura = findViewById(R.id.buttonScissors);
        restartGame = findViewById(R.id.buttonRestartGame);


        buttonPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("Pedra");
            }
        });

        buttonPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("Papel");
            }
        });

        buttonTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame("Tesoura");
            }
        });

        restartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerScore = 0;
                computerScore = 0;
                textViewPlayerScore.setText(String.valueOf(playerScore));
                textViewComputerScore.setText(String.valueOf(computerScore));
                textViewComputerChoice.setText("Computador: "); // Limpar a jogada do computador também
                textViewResult.setText("Faça sua jogada!");
                textViewResult.setTextColor(getResources().getColor(android.R.color.holo_blue_dark)); // Retornar à cor padrão
            }
        });
    }


    private void playGame(String playerChoice) {
        //gerar escolha do computador aleatoriamente
        String[] options = {"Pedra", "Papel", "Tesoura"};
        Random random = new Random();
        int computerChoiceIndex = random.nextInt(options.length); // Gera 0, 1 ou 2
        String computerChoice = options[computerChoiceIndex];


        textViewComputerChoice.setText("Computador: " + computerChoice);

        //logica condicional para vitória, derrota ou empate
        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "Empate!";
            textViewResult.setTextColor(getResources().getColor(android.R.color.darker_gray));
        } else if (
                (playerChoice.equals("Pedra") && computerChoice.equals("Tesoura")) ||
                        (playerChoice.equals("Papel") && computerChoice.equals("Pedra")) ||
                        (playerChoice.equals("Tesoura") && computerChoice.equals("Papel"))
        ) {
            result = "Você venceu!";
            playerScore++;
            textViewResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            result = "Você perdeu!";
            computerScore++;
            textViewResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        //Atualizar placar
        textViewPlayerScore.setText(String.valueOf(playerScore));
        textViewComputerScore.setText(String.valueOf(computerScore));

        //Exibir o resultado da rodada
        textViewResult.setText(result);
    }
}