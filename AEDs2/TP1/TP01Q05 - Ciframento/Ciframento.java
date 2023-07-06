/**
 * Ciframento
 * @author Ana Luiza Pacheco Leite
 */


public class Ciframento{

/**
* realiza o ciframento de césar em uma string
* @param string entrada
*/
	public static String ciframento(String entrada){
		char c;
		String s="";
		for(int i=0;i<entrada.length();i++){
			c=(char)((int)entrada.charAt(i)+3);   //soma 3 no char selecionado
			s+=c;
		}
		return s;
	}//fim ciframento

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
					fim = true;
				}else{
					fim=false;
					i=tamanho;
				}
			}
		}
		return fim;
	}//fim equals




	public static void main(String[]args){
		String[] entrada=new String [1000];
		int numEntrada=0;
		String a="FIM";
		do{
			entrada[numEntrada]=MyIO.readLine();
		}while(equals(entrada[numEntrada++],a)==false);
		numEntrada--; //desconsiderar ultima linha contendo FIM
		for(int i=0;i<numEntrada;i++){ 			//Para cada linha de entrada gera uma de saida com o ciframento de cesar pronto
			MyIO.println(ciframento(entrada[i]));
		}
	}//fim main
}//fim ciframento
