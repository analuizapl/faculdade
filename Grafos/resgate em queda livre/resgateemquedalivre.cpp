#include <cstdlib>
#include <iostream>
#include <string>
#include <iomanip>
#include <math.h>

using namespace std;

class Grafo{
public:
    int x;
    int y;
    int z;
    Grafo(){
        x = 0;
        y = 0;
        z = -1;
    }

};


void acumula(Grafo grafo[], double **adj, int n);
int main() {
    int c, n;
    cin >> c;
    while(c > 0){
        cin >> n;
        Grafo grafo[n];
        double **adjacencies;
        double pesoarestas = 0;

        adjacencies = (double **) malloc(n * sizeof(double *));                   //criando lista/vetor de adjacencias
        for(int i = 0; i < n; i++){
            adjacencies[i] = (double *)malloc(n * sizeof(double));
            for(int j = 0; j < n; j++){
                adjacencies[i][j] = 0.0;
            }
        }

        for(int i = 0; i < n; i++){                                                     //lendo as coordenadas das pessoas
            cin >> grafo[i].x;
            cin >> grafo[i].y;
        }


        for(int i = 0; i < n; i++){                                                                   //determinando a distância entre as pessoas
            for(int j = 0; j < n; j++){
                adjacencies[i][j] = sqrt(pow((grafo[i].x-grafo[j].x), 2) + pow((grafo[i].y - grafo[j].y), 2));
            }
        }


        acumula(grafo,adjacencies, n);


        for(int i = 0; i < n; i++){
            pesoarestas += adjacencies[i][grafo[i].z];              // calcula o peso total das arestas
        }

        cout << fixed << setprecision(2);
        cout << pesoarestas/100 << endl;
        cout << endl;
        c--;

    }

    return 0;
}

void acumula(Grafo grafo[], double **adj, int n){
  int d, e, f;
      e = 0;
      double menor = 1000;
      grafo[0].z = 0;
      while(true){
          d = 1;
          for(int i = 0; i < n; i++){
              if(grafo[i].z != -1){
                  for(int j = 0; j < 5; j++){
                      if((i != j) && grafo[j].z == -1){
                          if(d){
                              menor = adj[i][j];
                              e = i;
                              f = j;
                              d = 0;
                          }else{
                              if(menor > adj[i][j]){
                                  menor = adj[i][j];
                                  e = i;
                                  f = j;
                              }
                          }
                      }
                  }
              }

          }
          if(d == 1){ break; }
          grafo[f].z = e;

    }
}
