import java.io.RandomAccessFile;
import java.util.*;
/**
 * Arquivo
 * @author Ana Luiza Pacheco Leite
 */
 public class ArquivoJava{

 public static void ler(int n,RandomAccessFile r) throws Exception{
   double k;
   for(int i = 0; i < n; i++){
     r.writeDouble(MyIO.readDouble());        //le o arquivo
   }
   long j = r.getFilePointer();
   r.close();                             //fecha o arquivo
   r = new RandomAccessFile("tp.txt","r");
   j = j-8;
   while(j >= 0){
     r.seek(j);         //posiciona para leitura
     k = r.readDouble();

     if(k - (int)k == 0){         //condicao caso o numero seja inteiro
       MyIO.println((int)k);
     }
     else{
       MyIO.println(k);         //e caso nao seja
     }
     j = j-8;                 //prox linha
   }
   r.close();             //fecha o arquivo
 }

 	public static void main(String[] args) throws Exception{
 		String entrada;
    int n=0;
 		RandomAccessFile r = new RandomAccessFile("tp.txt","rw");
 		n = MyIO.readInt();
 		ler(n,r);
 	}// fim main
  
}
