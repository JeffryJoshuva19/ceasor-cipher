import java.util.*;
public class RowColumnTranspositionCipher {
   
    // Function to encrypt the message using Row Column Transposition Cipher
    public static String encrypt(String message, String key) {
        int[] colOrder = new int[key.length()];
        char[] keyArr = key.toCharArray();
        char[] messageArr = message.toCharArray();
        char[][] matrix = new char[(int) Math.ceil((double) messageArr.length / keyArr.length)][keyArr.length];

        // Creating the matrix
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (k < messageArr.length) {
                    matrix[i][j] = messageArr[k];
                    k++;
                } else {
                    matrix[i][j] = 'X'; // Padding with 'X' if message length is not a multiple of key length
                }
            }
        }

        // Getting the order of columns based on the key
        for (int i = 0; i < keyArr.length; i++) {
            char minChar = Character.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < keyArr.length; j++) {
                if (keyArr[j] < minChar) {
                    minChar = keyArr[j];
                    minIndex = j;
                }
            }
            colOrder[i] = minIndex;
            keyArr[minIndex] = Character.MAX_VALUE;
        }

        // Reading the matrix column-wise to form the encrypted message
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < colOrder.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                encryptedMessage.append(matrix[j][colOrder[i]]);
            }
        }

        return encryptedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the message to encrypt: ");
        String message = sc.nextLine();

        System.out.print("Enter the key: ");
        String key = sc.nextLine();

        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);
System.out.println("Decrypted message: " + message);

        sc.close();
    }
}
//jeffry joshuva amalan j
