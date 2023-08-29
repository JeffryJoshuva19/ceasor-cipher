import java.util.*;

public class rsa {
    public static int isPrime(int x) {
        if (x <= 1) {
            return 0;
        } else if (x == 2) {
            return 1;
        } else {
            for (int i = 2; i*i <= x; i++) {
                if (x%i == 0) {
                    return 0;
                }
            }
            return 1;
        }
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
    
    public static int calculate_d(int e, int phi_n) {
        int d = 0;
        for (int i=2;i<phi_n;i++) {
            if ((e*i) % phi_n == 1) {
                d = i;
                break;
            }
        }
        return d;
    }

    public static long modularExponentiation(long b, int e, int m) {
        long r = 1;
        while (e>0) {
            if (e%2 == 1) {
                r = (r*b)%m;
            }
            b = (b*b)%m;
            e /= 2;
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a prime number P : ");
        int p = s.nextInt(); s.nextLine();
        System.out.print("Enter a prime number Q : ");
        int q = s.nextInt(); s.nextLine();
        if (isPrime(q) == 0 || isPrime(p) == 0) {
            System.out.println("P or Q is not prime");
            return;
        }
        int n = p*q, pi = (p-1) * (q-1);
        System.out.print("Enter the value of E : ");
        int e = s.nextInt(); s.nextLine();
        if (!((e>1 && e<pi) && (gcd(e, pi) == 1))) {
            System.out.println("Invalid Value for E");
            return;
        }
        System.out.print("Enter a message : ");
        int m = s.nextInt(); s.nextLine();

        int d = calculate_d(e, pi);

        // Encryption
        long cipher = modularExponentiation(m, e, n);
        System.out.println("Cipher Text : " + cipher);

        // Decryption
        long decrypted = modularExponentiation(cipher, d, n);
        System.out.println("Decrypted Message : " + decrypted);
        s.close();
    }
}
