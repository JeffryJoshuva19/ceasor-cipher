import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class aes{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Plain Text: ");
        String p_t = s.nextLine();
        System.out.print("Enter the key: ");
        String key = s.nextLine();
        if(key.length()<16)
            key = String.format("%-16s",key).replace(' ','0');
        try{
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keys = new SecretKeySpec(keyByte,"AES");
            Cipher ci = Cipher.getInstance("AES/ECB/PKCS5Padding");
            ci.init(Cipher.ENCRYPT_MODE, keys);
            byte[] encByte = ci.doFinal(p_t.getBytes());
            String encHex = BytetoHex(encByte);
            String encText = encHex.replaceAll("[^a-zA-Z0-9]","");
            System.out.println("Encrypted Text = "+encText);
            ci.init(Cipher.DECRYPT_MODE, keys);
            byte[] decByte = ci.doFinal(HextoByte(encText));
            String decText = new String(decByte,StandardCharsets.UTF_8);
            System.out.println("Decrypted Text= "+decText);
        }catch(Exception e){
            System.out.println("An error occured"+e.getMessage());
        }finally {
            s.close();
        }
    }
    public static String BytetoHex(byte[] Byte){
        StringBuilder strBuild = new StringBuilder();
        for(byte i:Byte)
            strBuild.append(String.format("%02X",i));
        // System.out.println(strBuild);
        return strBuild.toString();
    }
    public static byte[] HextoByte(String hex){
        int len = hex.length();
        byte[] data = new byte[len/2];
        for(int i=0;i<len;i+=2)
            data[i/2] = (byte)((Character.digit(hex.charAt(i), 16)<<4)+ Character.digit(hex.charAt(i+1), 16));
        return data;
    }
}
