/**
 * Aquecimento
 * @author Ana Luiza Pacheco Leite
 */

public class Aquecimento{

   public static boolean isMaiuscula(char c){
	     return(c >='A' && c <='Z');
   }

 /**
   * Conta e retorna numero de caracteres maiusculos em uma string
   * @param string s
 */
   public static int contarLetrasMaiusculas(String s){
	    int resp=0;
	    for(int i=0;i<s.length();i++){
		    if(isMaiuscula(s.charAt(i))==true){
			    resp++;
		    }
 	   }
	  return resp;
   }//fim contarLetrasMaiusculas


  public static void main(String[]args){
	    String[]entrada=new String[1000];
	    String linha;
	    int numEntrada=0;
	    do{
		     entrada[numEntrada]=MyIO.readLine();
	    }while(entrada[numEntrada++].equals("FIM")==false);
	   numEntrada--;
    	for(int i=0;i<numEntrada;i++){
		      MyIO.println(contarLetrasMaiusculas(entrada[i]));
	   }
   }//fim main
}//fim Aquecimento
