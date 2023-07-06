/**
 * Aquecimento recursivo
 * @author Ana Luiza Pacheco Leite
 */
public class AquecimentoRecu{


	public static boolean isMaiuscula(char c){
		return(c>='A'&&c<='Z');
	}//fim isMaiuscula

/**
 * inicia a recursividade
 * @param string entrada
*/
	public static int recursivo(String entrada){
		return contarLetrasMaiusculas(entrada,0);
	}//fim recursivo


/**
 * Conta e retorna numero de caracteres maiusculos em uma string
 * @param string s
*/
	public static int contarLetrasMaiusculas(String entrada,int i){
		if(i<entrada.length()){
			if(isMaiuscula(entrada.charAt(i))==true){
				return 1+contarLetrasMaiusculas(entrada,i+1);
			}else{
				return contarLetrasMaiusculas(entrada,i+1);
			}
		}
			else return 0;
   	}//fim contarLetrasMaiusculas


/**
* para a leitura da entrada quando for igual a FIM
* @param string entrada, string a
*/
	public static boolean equals(String entrada,String a){
		boolean fim=false;
		int tamanho=entrada.length();
		int tamanho2=a.length();
		if(tamanho==tamanho2){
			for(int j=0;j<tamanho;j++){
				if(entrada.charAt(j)==a.charAt(j)){
					fim=true;
				}else{
					fim=false;
					j=tamanho;
				}
			}
		}
		return fim;
	}//fim equals


	public static void main(String[]args){
		String[]entrada=new String[1000];
		int numEntrada=0;
		String a="FIM";
		int i=0;
		do{
			entrada[numEntrada]=MyIO.readLine();
		}while(equals(entrada[numEntrada++],a)==false);
		numEntrada--;
		for( int j=0;j<numEntrada;j++){                 //Para cada entrada cria uma saida com a quantidade de letras maiusculas
			MyIO.println(contarLetrasMaiusculas(entrada[j],i));
		}
	}//fim main
}//fim AquecimentoRecu
