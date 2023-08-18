import java.util.*;
public class hillcipher {
    static int checker(char c) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; i++)
            if (c == alpha.charAt(i))
                return i;
        return 0;
    }
    static int[] Multiply(int[] ar, int[][] key) {
        int[] br = new int[3];
        br[0] = (ar[0] * key[0][0]) + (ar[1] * key[0][1]) + (ar[2] * key[0][2]);
        br[1] = (ar[0] * key[1][0]) + (ar[1] * key[1][1]) + (ar[2] * key[1][2]);
        br[2] = (ar[0] * key[2][0]) + (ar[1] * key[2][1]) + (ar[2] * key[2][2]);
        return br;
    }
    static int Determinant(int[][] key) {
        int first = (key[0][0]) * ((key[1][1] * key[2][2]) - (key[2][1] * key[1][2]));
        int second = (-1 * key[0][1]) * ((key[1][0] * key[2][2]) - (key[2][0] * key[1][2]));
        int third = (key[0][2]) * ((key[1][0] * key[2][1]) - (key[2][0] * key[1][1]));
        return (first + second + third) % 26;
    }
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 1;
    }
    static int[][] adjA(int[][] key) {
        int[][] adjA = new int[3][3];
        adjA[0][0] = (key[1][1] * key[2][2]) - (key[2][1] * key[1][2]);
        adjA[1][0] = (-1) * ((key[1][0] * key[2][2]) - (key[2][0] * key[1][2]));
        adjA[2][0] = (key[1][0] * key[2][1]) - (key[2][0] * key[1][1]);

        adjA[0][1] = (-1) * ((key[0][1] * key[2][2]) - (key[2][1] * key[0][2]));
        adjA[1][1] = (key[0][0] * key[2][2]) - (key[0][2] * key[2][0]);
        adjA[2][1] = (-1) * ((key[0][0] * key[2][1]) - (key[0][1] * key[2][0]));

        adjA[0][2] = (key[0][1] * key[1][2]) - (key[1][1] * key[0][2]);
        adjA[1][2] = (-1) * ((key[0][0] * key[1][2]) - (key[1][0] * key[0][2]));
        adjA[2][2] = (key[0][0] * key[1][1]) - (key[0][1] * key[1][0]);
        return adjA;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("Enter key values");
        int[][] key = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                key[i][j] = Integer.parseInt(s.next());
        System.out.println("1.Encrytion\n2.Decryption\nEnter Your choice: ");
        int choice = Integer.parseInt(s.next());
        s.nextLine();
        if (choice == 1) {
            System.out.print("Enter Plain text: ");
            String p_t = s.nextLine();
            int[] ar = new int[p_t.length()];
            int[] br = new int[p_t.length()];
            if (p_t.length() % 3 == 0) {
                int t = 3;
                for (int i = 0; i < p_t.length(); i += 3) {
                    for (int j = i; j < t && j < p_t.length(); j++)
                        ar[j - i] = checker(p_t.charAt(j));
                    br = Multiply(ar, key);
                    for (int j = i; j < t && j < p_t.length(); j++)
                        System.out.print(alpha.charAt(br[j - i] % 26));
                    t += 3;
                }
            } else
                System.out.println("Wrong input Length");
        } else if (choice == 2) {
            System.out.print("Enter Ciper text: ");
            String p_t = s.nextLine();
            int[] ar = new int[p_t.length()];
            int[] br = new int[p_t.length()];
            if (p_t.length() % 3 == 0) {
                int t = 3, x = 1;
                int deter = Determinant(key);
                int[][] adjoint = adjA(key);
                int detInverse = modInverse(deter, 26);
                for (int i = 1; i <= 26; i++) {
                    if ((deter * i) % 26 == 1) {
                        x = i;
                        break;
                    }
                }
                for (int i = 0; i < p_t.length(); i += 3) {
                    for (int j = i; j < t && j < p_t.length(); j++)
                        ar[j - i] = checker(p_t.charAt(j));
                    br = Multiply(ar, adjoint);
                    for (int j = i; j < t && j < p_t.length(); j++) {
                        int temp = (br[j - i] * x) % 26;
                        if (temp < 0)
                            temp += 26;
                        System.out.print(alpha.charAt(temp));
                    }
                    t += 3;
                }
            } else
                System.out.println("Wrong input Length");
        } else
            System.out.println("Wrong choice");
    }
}
//by jeffry joshuva amalan
