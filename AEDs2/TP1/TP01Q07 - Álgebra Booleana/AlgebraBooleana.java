/**
 * Algebra Booleana
 * @author Ana Luiza Pacheco Leite
 */ 

public class AlgebraBooleana{

/**
* le entrada
* 
*/ 
   public static void ler(){
      int i = MyIO.readInt();
      while(i != 0){
         int[] quant = arranjo(i);
         String entrada = MyIO.readLine();
         String entrada2 = altera(entrada, quant);
         MyIO.println(resolve(entrada2));
         i = MyIO.readInt();
      }
   }//fim ler

/**
* cria um arranjo com as expressoes booleanas
* @param int i
*/ 
   public static int[] arranjo(int i){
      int array[] = new int[i];
      for(int x = 0; x < i; x++){
         array[x] = MyIO.readInt();
      }
      return array;
   }//fim arranjo

/**
* resolve a entrada booleana
* @param string entrada2
*/ 
   public static char resolve(String entrada2){
      String[] opcao ={" ","not(0)","not(1)","and(0,0)","and(1,1)","and(0,1)", "and(1,0)","and(0,0,","and(1,1,","and(0,1,","and(1,0,","or(0,0)","or(1,1)","or(0,1)","or(1,0)",
         "or(0,0,","or(1,1,","or(0,1,","or(1,0,"};
      String[] opcao2 ={"","1","0","0","1","0","0","and(and(0,0),","and(and(1,1),","and(and(0,1),","and(and(1,0),","0","1","1","1","or(or(0,0),","or(or(1,1),",
         "or(or(0,1),","or(or(1,0),"};
      while(entrada2.charAt(0) != '0' && entrada2.charAt(0) != '1'){
         for(int i = 0; i < opcao.length; i++){
            entrada2 = substitui(entrada2, opcao[i], opcao2[i]);
         }
      }
      return entrada2.charAt(0);
   }//fim resolve

/**
* procura uma string na entrada
* @param string n, string procurado, int i
*/ 
   public static boolean iguais(String n, String procurado, int i){
      boolean is = false;
      int j = i;
      if(n.length() - i >= procurado.length() && n.charAt(i) == procurado.charAt(0)){
         while(j < n.length() && j - i < procurado.length() && n.charAt(j) == procurado.charAt(j - i)){
            j++;
         }
         is = (j - i) >= procurado.length();
      }
      return is;
   }//fim iguais

/**
* substitui as letras A,B,C por 0 ou 1
* @param string entrada, array quant
*/ 
   public static String altera(String entrada,int quant[]){
      String t = "";
      for(int i = 0; i < entrada.length(); i++){
         int x = 0;
         while(x < quant.length && (char)('A' + x) != entrada.charAt(i)){
            x++;
         }
         if(x >= quant.length){
            t+= entrada.charAt(i);
         }else{
            t+= (char) (quant[x] + '0');
         }
      }
      return t;
   }//fim alterar

/**
* substitui a entrada por seu resultado
* @param string n, string procurado, string posicao
*/ 
   public static String substitui(String n, String procurado, String posicao){
      String r = "";
      for(int i = 0; i < n.length(); i ++){
         if(iguais(n,procurado, i)){
            r += posicao;
            i += procurado.length() - 1;
         }
         else {
            r += n.charAt(i);
         }
      }//Fim for
      return r;
   }//Fim substitui



   public static void main(String[] args) {
      ler();
   }//fim main

}//fim algebrabooleana
