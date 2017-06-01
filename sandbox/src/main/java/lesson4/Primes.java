package lesson4;

public class Primes {

    public static boolean isPrime(int n) {
        for(int i = 2; i < n; i = i + 1) {
            if (n % i == 0) {
                 return false;
            }
        }
        return true;
    }

    public static boolean isPrimeWhile(int n) {
        int i = 2;
        while (i < n && n % i != 0) {
            i = i + 1;
        }
            return i == n;
    }

    public static boolean isPrime(long n) {
        for(long i = 2; i < n; i = i + 1) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFast(int n) {
        int m = (int) Math.sqrt(n);
        for(int i = 2; i < m; i = i + 1) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
