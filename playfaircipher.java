import java.util.*;
//this code performs the playfair cipher

public class PlayfairCipher {
    private static final int SIZE = 5;
    private static final char FILLER_CHAR = 'X';

    private char[][] keyMatrix;

    public PlayfairCipher(String key) {
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
        key = key.replace("J", "I");
        boolean[] visited = new boolean[26];

        keyMatrix = new char[SIZE][SIZE];
        int row = 0, col = 0;

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!visited[ch - 'A']) {
                keyMatrix[row][col] = ch;
                visited[ch - 'A'] = true;
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J')
                continue;
            if (!visited[ch - 'A']) {
                keyMatrix[row][col] = ch;
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private int[] findPosition(char ch) {
        int[] position = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (keyMatrix[i][j] == ch) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }

    public String encrypt(String plaintext) {
        plaintext = plaintext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        plaintext = plaintext.replace("J", "I");

        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char ch1 = plaintext.charAt(i);
            char ch2;
            if (i + 1 < plaintext.length())
                ch2 = plaintext.charAt(i + 1);
            else
                ch2 = FILLER_CHAR;

            int[] pos1 = findPosition(ch1);
            int[] pos2 = findPosition(ch2);

            if (pos1[0] == pos2[0]) { // Same row
                encryptedText.append(keyMatrix[pos1[0]][(pos1[1] + 1) % SIZE]);
                encryptedText.append(keyMatrix[pos2[0]][(pos2[1] + 1) % SIZE]);
            } else if (pos1[1] == pos2[1]) { // Same column
                encryptedText.append(keyMatrix[(pos1[0] + 1) % SIZE][pos1[1]]);
                encryptedText.append(keyMatrix[(pos2[0] + 1) % SIZE][pos2[1]]);
            } else { // Different row and column
                encryptedText.append(keyMatrix[pos1[0]][pos2[1]]);
                encryptedText.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String ciphertext) {
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "").toUpperCase();
        ciphertext = ciphertext.replace("J", "I");

        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char ch1 = ciphertext.charAt(i);
            char ch2 = ciphertext.charAt(i + 1);

            int[] pos1 = findPosition(ch1);
            int[] pos2 = findPosition(ch2);

            if (pos1[0] == pos2[0]) { // Same row
                decryptedText.append(keyMatrix[pos1[0]][(pos1[1] - 1 + SIZE) % SIZE]);
                decryptedText.append(keyMatrix[pos2[0]][(pos2[1] - 1 + SIZE) % SIZE]);
            } else if (pos1[1] == pos2[1]) { // Same column
                decryptedText.append(keyMatrix[(pos1[0] - 1 + SIZE) % SIZE][pos1[1]]);
                decryptedText.append(keyMatrix[(pos2[0] - 1 + SIZE) % SIZE][pos2[1]]);
            } else { // Different row and column
                decryptedText.append(keyMatrix[pos1[0]][pos2[1]]);
                decryptedText.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        PlayfairCipher cipher = new PlayfairCipher(key);

        System.out.println("\nPlayfair Cipher Encryption/Decryption");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter the plaintext: ");
                String plaintext = scanner.nextLine();
                String encryptedText = cipher.encrypt(plaintext);
                System.out.println("Encrypted Text: " + encryptedText);
                break;
            case 2:
                System.out.print("Enter the ciphertext: ");
                String ciphertext = scanner.nextLine();
                String decryptedText = cipher.decrypt(ciphertext);
                System.out.println("Decrypted Text: " + decryptedText);
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }

        scanner.close();
    }
}
