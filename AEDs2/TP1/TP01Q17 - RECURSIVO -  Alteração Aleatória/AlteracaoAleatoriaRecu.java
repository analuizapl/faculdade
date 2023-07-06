/**
 * Alteracao Aleatoria recursivo
 * @author Ana Luiza Pacheco Leite
 */

import java.util.*;
public class AlteracaoAleatoriaRecu{

/**
* retorna uma String depois de trocar duas letras
* @param string entrada, random gerador
*/
public static String aleatorio(String entrada,Random gerador,int c, int d,int x,int i,String nova){
	if(i<entrada.length()){
		x=(int) entrada.charAt(i);
		if(x==c){
			x=d;			//se o char for igual a primeira letra sorteada sera trocado pela segunda letra sorteada
		}
		nova+=(char) x;
		return aleatorio(entrada,gerador,c,d,x,i+1,nova);
	}else{
		return nova;
	}
}//fim aleatorio

/**
 * inicia a recursividade
 * @param string entrada
*/
public static String recursivo(String entrada,Random gerador,int c,int d,int x,int i,String nova){
	return aleatorio(entrada,gerador,c,d,x,0,nova);
}

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
		String[]entrada=new String[1000];
		int numEntrada=0;
		int c;
		int d;
		int x=0;
		int i;
		String nova="";
		String a="FIM";
		Random gerador =new Random();
		gerador.setSeed(4);
		c=(char)('a'+(Math.abs(gerador.nextInt())%26));
		d=(char)('a'+(Math.abs(gerador.nextInt())%26));
		do{
			entrada[numEntrada]=MyIO.readLine();
		}while (equals(entrada[numEntrada++],a)==false);
		numEntrada--;
		for(int j=0;j<numEntrada;j++){
			MyIO.println(aleatorio(entrada[j],gerador,c,d,x,0,nova));					//a cada entrada gera uma saida com uma letra aleatoria trocada por outra
		}
	}//fim main
}//fim alteracao aleatoria
