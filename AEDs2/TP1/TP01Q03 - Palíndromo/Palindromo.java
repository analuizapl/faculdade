/**
 * Palindromo
 * @author Ana Luiza Pacheco Leite
 */

public class Palindromo{
/**
* verifica se uma string é palindromo ou nao
* @param string entrada
*/
	public static  boolean palindromo(String entrada){
		String inverso="";
		boolean resposta=true;

		for(int i=entrada.length()-1 ; i>=0 ; i--){				//invertendo a string entrada
			inverso+=entrada.charAt(i);
		}

		for(int i=0;i<entrada.length();i++){			//compara a string original com a invertida para verificar se é palindromo
			if(entrada.charAt(i)!=inverso.charAt(i)){
				resposta=false;
			}
		}
		return resposta;
	}//fim palindromo


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
		String[] entrada=new String[1000];
		int numEntrada=0;
		String a="FIM";

		do{
			entrada[numEntrada] = MyIO.readLine();
		}while(equals(entrada[numEntrada++],a)==false);
		numEntrada--; //Desconsiderar ultima linha contendo a palavra FIM

		for(int i=0;i<numEntrada;i++){					//gera uma saida contendo SIM caso seja palindromo e NAO caso nao seja para cada entrada
			if(palindromo(entrada[i])==true){
				MyIO.println("SIM");
			}else{
				MyIO.println("NAO");
			}
		}
	}//fim main
}//fim palindromo
