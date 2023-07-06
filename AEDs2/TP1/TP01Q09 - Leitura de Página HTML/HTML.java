/**
 * Leitura HTML
 * @author Ana Luiza Pacheco Leite
 */
import java.io.*;
import java.net.*;
public class HTML{
/**
* le HTML
*/
public static String getHtml(String endereco){
        String address = endereco;
        String line = "";
        String resp = "";

        try {
            URL url = new URL(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            line = br.readLine();
            while (line != null) {
                resp += line;
                line = br.readLine();
            }
            br.close();
        } catch (MalformedURLException excecao) {
            excecao.printStackTrace();
        } catch (IOException excecao) {
            excecao.printStackTrace();
        }
        return resp;
      }


public static int  a(String  html ){
      int cont=0;
      for(int i=0;i<html.length();i++){
         if(html.charAt(i)=='a'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim a

   public static int  e(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='e'){
            cont++;
         }
      }
      return cont;
   }//fim e

   public static int  i(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='i'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim i

   public static int  o(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='o'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim o

   public static int  u(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='u'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim u

   public static int  aagudo(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim a

   public static int eagudo(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim e

   public static int  iagudo(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim i

   public static int  oagudo(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim o

   public static int  uagudo(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim u

   public static int  acrase(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim a

   public static int ecrase(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim e

   public static int  icrase(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim i

   public static int  ocrase(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim o

   public static int  ucrase(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim u

   public static int  atil(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim a

   public static int  otil(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim o

   public static int  achapeu(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim a

   public static int echapeu(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim e

   public static int  ichapeu(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim i

   public static int  ochapeu(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim o

   public static int  uchapeu(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)=='�'){
            cont++;
         }
      }//fim for
      return cont;
   }//fim u

   public static int  consoantes(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)>'a' && html.charAt(i)<='z'){
            if(html.charAt(i)!='e' && html.charAt(i)!='i' && html.charAt(i)!='o' && html.charAt(i)!='u'){
               cont++;
            }
         }
      }
      return cont;
   }//fim consoantes

   public static int  br(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)==('<')){
            if( html.charAt(i+1)==('b')){
               if( html.charAt(i+2)==('r')){
                  if( html.charAt(i+3)==('>')){
                     cont++;
                  }
               }
            }
         }
      }
      return cont;
   } //fim br

   public static int  table(String  html ){
      int cont=0;
      for(int i=0;i< html.length();i++){
         if( html.charAt(i)==('<')){
            if( html.charAt(i+1)==('t')){
               if( html.charAt(i+2)==('a')){
                  if( html.charAt(i+3)==('b')){
                     if( html.charAt(i+4)==('l')){
                        if( html.charAt(i+5)==('e')){
                           if( html.charAt(i+6)==('>')){
                              cont++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return cont;
   }//fim table


 /**
* para a leitura da entrada quando for igual a FIM
* @param string entrada, string a
*/
	public static boolean equals(String entrada,String a){
		boolean fim=false;
		int tamanho=entrada.length();
		int tamanho2=a.length();
		if(tamanho==tamanho2){
			for(int i=0;i<tamanho;i++){
				if(entrada.charAt(i)==a.charAt(i)){
					fim=true;
				}else{
					fim=false;
					i=tamanho;
				}
			}
		}
		return fim;
	}//fim equals


public static void main(String[]args){
      MyIO.setCharset("ISO-8859-1");
      String entrada;
      String endereco;
      String html;
      String a="FIM";
      entrada=MyIO.readLine();
        do{
         endereco=MyIO.readLine();
         html=getHtml(endereco);
         int cont=table(html);
         int cont2=br(html);
         MyIO.print("a("+(a(html)-(1*cont))+")");
         MyIO.print(" e("+(e(html)-(1*cont))+")");
         MyIO.print(" i("+i(html)+")");
         MyIO.print(" o("+o(html)+")");
         MyIO.print(" u("+u(html)+")");
         MyIO.print(" �("+aagudo(html)+")");
         MyIO.print(" �("+eagudo(html)+")");
         MyIO.print(" �("+iagudo(html)+")");
         MyIO.print(" �("+oagudo(html)+")");
         MyIO.print(" �("+uagudo(html)+")");
         MyIO.print(" �("+acrase(html)+")");
         MyIO.print(" �("+ecrase(html)+")");
         MyIO.print(" �("+icrase(html)+")");
         MyIO.print(" �("+ocrase(html)+")");
         MyIO.print(" �("+ucrase(html)+")");
         MyIO.print(" �("+atil(html)+")");
         MyIO.print(" �("+otil(html)+")");
         MyIO.print(" �("+achapeu(html)+")");
         MyIO.print(" �("+echapeu(html)+")");
         MyIO.print(" �("+ichapeu(html)+")");
         MyIO.print(" �("+ochapeu(html)+")");
         MyIO.print(" �("+uchapeu(html)+")");
         MyIO.print(" consoante("+(consoantes(html)-(cont*3)-(cont2*2))+")");
         MyIO.print(" <br>("+br(html)+")");
         MyIO.print(" <table>("+table(html)+")");
         MyIO.print(" "+entrada+"\n");
         entrada=MyIO.readLine();
      } while(equals(entrada,a)==false);

   }//fim main
}//fim html
