/**
 * Ciframento recursivo
 * @author Ana Luiza Pacheco Leite
 */


public class CiframentoRecu{

/**
* realiza o ciframento de césar em uma string
* @param string entrada
*/
public static String ciframento(String entrada,int i){
	char c;
	String s="";
	if(i<entrada.length()){
		c=(char)(((int) entrada.charAt(i))+3);
		s+=c;
		return s+=ciframento(entrada,i+1);
	}else
		return "";
}//fim ciframento

/**
 * inicia a recursividade
 * @param string entrada
*/
public static String ciframento(String entrada){
	return ciframento(entrada,0);
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
		String[]entrada=new String[1000];
		String a="FIM";
		int numEntrada=0;
		int i=0;
		do{
			entrada[numEntrada]=MyIO.readLine();
		}while(equals(entrada[numEntrada++],a)==false);
		numEntrada--;
		for(int j=0;j<numEntrada;j++){
			MyIO.println(ciframento(entrada[j],i));
		}
	}//fim main
}//fim ciframento
