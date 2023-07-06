/**
 * Alteracao Aleatoria
 * @author Ana Luiza Pacheco Leite
 */ 

import java.util.*;
public class AlteracaoAleatoria{

/**
* retorna uma String depois de trocar duas letras
* @param string entrada, random gerador
*/ 
	public static String aleatorio(String entrada,Random gerador){
		int c;
		int d;
		int x;
		String nova="";
		c=(char)('a'+(Math.abs(gerador.nextInt())%26));		//gera primeira letra aleatoria
		d=(char)('a'+(Math.abs(gerador.nextInt())%26));		//gera segunda letra aleatoria
		for(int i=0;i<entrada.length();i++){
			x=(int) entrada.charAt(i);
			if(x==c){
				x=d;			//se o char for igual a primeira letra sorteada sera trocado pela segunda letra sorteada
			}
			nova+=(char) x;
		}
		return nova;
	}//fim aleatorio

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
		String a="FIM";
		Random gerador =new Random();
		gerador.setSeed(4);

		do{
			entrada[numEntrada]=MyIO.readLine();
		}while (equals(entrada[numEntrada++],a)==false);
		numEntrada--;


		for(int i=0;i<numEntrada;i++){			//a cada entrada gera uma saida com uma letra aleatoria trocada por outra
			MyIO.println(aleatorio(entrada[i],gerador));
		}

	}//fim main
}//fim alteracao aleatoria
