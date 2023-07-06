/**
 * Arquivo em C
 * @author Ana Luiza Pacheco Leite
 */

 #include <stdio.h>
 #include <stdlib.h>


 void ler(int n,FILE* file);
 /**
  * abre o arquivo e lê os numeros de trás para frente reescrevendo-os em outro arquivo
  * @author Ana Luiza Pacheco Leite
  */
 void ler(int n,FILE* file){
   file = fopen("tp.txt","wb+");
   double j;
   double k;
   for(int i = 0;i < n;i++){			//le o arquivo
     scanf("%lf",&j);
     fwrite(&j,sizeof(double),1,file);
   }
   fclose(file);
   file = fopen("tp.txt","rb+");
   n--;
   while(n >= 0){
     fseek(file,n*sizeof(double),SEEK_SET);
     fread(&k,sizeof(double),1,file);
     if(k - (int)k == 0){
       printf("%i\n",(int)k);		        //condicao caso o numero seja inteiro
     }
     else{
       printf("%g\n",k);          //e caso nao seja
     }
     n--;			//prox linha
   }
   fclose(file);
 }


 int main(){
 	int n;
 	FILE* file;
 	scanf("%i",&n);
 	ler(n,file);
 	return 0;
 }// fim main
