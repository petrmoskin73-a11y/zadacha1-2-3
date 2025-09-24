import java.util.Scanner;
import java.util.Random;

public class HangmanGame {
    private static final String[] WORDS = {
            "сенла", "чебупеля", "кибердром",
            "окак", "база", "абоидно",
            "переменная", "функция", "объект", "класс"
    };

    private static final int MAX_LIVES = 6;
    private String secretWord;
    private char[] guessedLetters;
    private int remainingLives;
    private StringBuilder usedLetters;

    public HangmanGame() {
        Random random = new Random();
        secretWord = WORDS[random.nextInt(WORDS.length)];
        guessedLetters = new char[secretWord.length()];
        remainingLives = MAX_LIVES;
        usedLetters = new StringBuilder();

        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    public void startGame() {
        System.out.println("Добро пожаловать в игру 'Виселица'!");
        System.out.println("Угадайте слово. У вас " + MAX_LIVES + " жизней.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (remainingLives > 0 && !isWordGuessed()) {
            displayGameStatus();
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Пожалуйста, введите одну букву!");
                continue;
            }

            char letter = input.charAt(0);

            if (usedLetters.toString().indexOf(letter) != -1) {
                System.out.println("Вы уже вводили эту букву!");
                continue;
            }

            usedLetters.append(letter).append(" ");
            processLetter(letter);
            System.out.println();
        }

        displayFinalResult();
        scanner.close();
    }

    private void processLetter(char letter) {
        boolean found = false;

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedLetters[i] = letter;
                found = true;
            }
        }

        if (!found) {
            remainingLives--;
            System.out.println("Буквы '" + letter + "' нет в слове!");
            System.out.println("Осталось жизней: " + remainingLives);
            drawHangman();
        } else {
            System.out.println("Отличная буква!");
        }
    }

    private boolean isWordGuessed() {
        for (char c : guessedLetters) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private void displayGameStatus() {
        System.out.print("Слово: ");
        for (char c : guessedLetters) {
            System.out.print(c + " ");
        }
        System.out.println("\nИспользованные буквы: " + usedLetters.toString());
        System.out.println("Жизни: " + remainingLives + "/" + MAX_LIVES);
    }

    private void drawHangman() {
        switch (remainingLives) {
            case 5:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                break;
            case 4:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |    |");
                System.out.println(" |");
                System.out.println(" |");
                break;
            case 3:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|");
                System.out.println(" |");
                System.out.println(" |");
                break;
            case 2:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |");
                System.out.println(" |");
                break;
            case 1:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |   /");
                System.out.println(" |");
                break;
            case 0:
                System.out.println("  ____");
                System.out.println(" |    |");
                System.out.println(" |    O");
                System.out.println(" |   /|\\");
                System.out.println(" |   / \\");
                System.out.println(" |");
                break;
        }
        System.out.println();
    }

    private void displayFinalResult() {
        System.out.println("\n=== ИГРА ОКОНЧЕНA ===");

        if (isWordGuessed()) {
            System.out.println("Угадал слово: " + secretWord);
        } else {
            System.out.println("К сожалению, ты проиграл(");
            System.out.println("Загаданное слово было: " + secretWord);
            drawHangman();
        }
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame.java();
        game.startGame();
    }

    private static class java extends HangmanGame {
    }
}