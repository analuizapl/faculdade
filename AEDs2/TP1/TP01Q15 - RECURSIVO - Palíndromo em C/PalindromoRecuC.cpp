/**
 * Palindromo recursivo em C
 * @author Ana Luiza Pacheco Leite
 */
 #include <stdio.h>
 #include <stdlib.h>
 #include <string.h>



 #define bool short
 #define true 1
 #define false 0
 #define equals(a,b)(((strcmp(a,b)==0) ? true:false))
 #define NUMENTRADA 1000
 #define TAMLINHA 1000

 bool isFim(char* entrada);
 bool palindromo(char* entrada);
 bool palindromorec(char* entrada,int i,int j,bool resp);



 bool palindromo(char* entrada){
 	return palindromorec(entrada,0,strlen(entrada)-1,false);
 }// fim palindromo


/**
* verifica se uma string é palindromo ou nao
* @param string entrada
*/

bool palindromorec(char* entrada,int inicio,int fim,bool resp){
	if(inicio<fim && fim>inicio){
		if(entrada[inicio] == entrada[fim]){
			resp = true;
			resp = palindromorec(entrada,inicio+1,fim-1,resp);
		}else{
			return false;
		}
	}
	return resp;
}// fim palindromo


bool isFim(char* s){
	return(strlen(s)>=3 && s[0]=='F' && s[1]=='I' && s[2]=='M');
}

int main(){
	char entrada[10000];
	for(scanf("%[^\n]\n",entrada);!isFim(entrada);scanf("%[^\n]\n",entrada)){
		bool resp = palindromo(entrada);
		if(resp == true){
			printf("SIM\n");
		}else{
			printf("NAO\n");
		}
  }
}//fim main
