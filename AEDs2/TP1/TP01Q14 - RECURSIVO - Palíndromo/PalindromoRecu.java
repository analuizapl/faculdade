/**
 * Palindromo recursivo
 * @author Ana Luiza Pacheco Leite
 */


public class PalindromoRecu{

	/**
	* verifica se uma string é palindromo ou nao
	* @param string entrada
	*/
	public static boolean palindromo(String entrada,int inicio,int posicao){
		boolean resp=true;
		if(inicio<entrada.length()/2){
			if(entrada.charAt(inicio)==entrada.charAt(posicao)){
				resp=palindromo(entrada,inicio+1,posicao-1);
			}else{
				resp= false;
			}
		}
 		return resp;
	}//fim palindromo

	/**
	 * inicia a recursividade
	 * @param string entrada
	*/
	public static boolean palindromo(String entrada){
		return  palindromo(entrada,0,entrada.length()-1);
	}//fim palindromo


	/**
	* para a leitura da entrada quando for igual a FIM
	* @param string entrada, string a
	*/
	public static boolean equals (String entrada, String a){
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
		String[] entrada = new String[1000];
		int numEntrada=0;
		String a="FIM";
		do{
			entrada[numEntrada]=MyIO.readLine();
		}while(equals(entrada[numEntrada++],a)==false);
		numEntrada--;
		for (int j=0;j<numEntrada;j++){
			if(palindromo(entrada[j],0,entrada[j].length()-1)==true){			//para cada entrada cria uma saida verificando se a string é palindromo ou nao
				MyIO.println("SIM");
			}else{
				MyIO.println("NAO");
			}
		}
	}//fim main
}//fim	palindromo
