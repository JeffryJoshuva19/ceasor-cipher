import java.util.*;
//code for ceasorcipher
class ceasor{  
    public static void main(String args[]){  
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.println("Enter your choice");
        System.out.println("1-Enter the plain text\n2-Enter the cipher text");
        n=sc.nextInt();
        if(n==1){
             System.out.println("Enter the plain text");
             sc.nextLine();
             String a=sc.nextLine();
             int k;
             System.out.println("Enter the key value");
             k=sc.nextInt();
             int l=a.length();
             for(int i=0;i<l;i++){
                 if(a.charAt(i)==' '){
                    System.out.print(" ");
                 }
                 else{
                 char b=a.charAt(i);
                 char m=Character.toLowerCase(b);  
                 int p=(int)m-97;
                 int c=(p+k)%26;
                 int d=(int)c+97;
                 char e=(char)d;
                 System.out.print(e);
                 }
             }
        }
        if(n==2){
             System.out.println("Enter the cipher text");
             
             sc.nextLine();
             String a=sc.nextLine();
             int k;
             System.out.println("Enter the key value");
             k=sc.nextInt();
             int l=a.length();
             for(int i=0;i<l;i++){
                 if(a.charAt(i)==' '){
                    System.out.print(" ");
                 }
                 else{
                 char b=a.charAt(i);
                 char m=Character.toLowerCase(b);  
                 int p=(int)m-97;
                 int c=(p-k)%26;
                 int d=(int)c+97;
                 char e=(char)d;
                 System.out.print(e);
                 }
             }
        }            
    }  
} 
