/**
 * Aquecimento em C
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

bool isMaiuscula(char c){
  return(c >='A' && c <='Z');
}

/**
  * Conta e retorna numero de caracteres maiusculos em uma string
  * @param string s
*/
int contarLetrasMaiusculas(char*s){
  int resp=0;
  for(int i=0;i<strlen(s);i++){
    if(isMaiuscula(s[i])==true){
      resp++;
    }
  }
  return resp;
}
bool isFim(char*s){
  return (strlen(s)>=3 && s[0]=='F' && s[1]=='I' && s[2]=='M');
}
int main(int argc,char**argv){
  char entrada[NUMENTRADA][TAMLINHA];
  int numEntrada=0;
  do{
    fgets(entrada[numEntrada],TAMLINHA,stdin);
  }while(isFim(entrada[numEntrada++])==false);
  numEntrada--;
  for(int i=0;i<numEntrada;i++){
    printf("%i\n",contarLetrasMaiusculas(entrada[i]));
  }
}//fim main
