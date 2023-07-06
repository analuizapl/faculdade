#include <cstdlib>
#include <iostream>
#include <string>
#include <math.h>
#include <iomanip>


using namespace std;

class Grafo{
    
public:
    
    int x;
    int y;
    int pai;
    
    Grafo(){
        x = 0;
        y = 0;
        pai = -1;
    }
    
};

void prim(Grafo Vertice[], double **adj, int n);

int main(int argc, char** argv) {
    int c, n;
    cin >> c;
    while(c > 0){
        cin >> n;
        Grafo Vertice[n];
        double **Adjacencias;
        double peso = 0;
        //Aloca vetor de adjacencias
        Adjacencias = (double **) malloc(n * sizeof(double *));
        for(int i = 0; i < n; i++){
            Adjacencias[i] = (double *)malloc(n * sizeof(double));
            for(int j = 0; j < n; j++){
                Adjacencias[i][j] = 0.0;
            }
        }
        //Lê as coordenadas de cada pessoa
        for(int i = 0; i < n; i++){
            cin >> Vertice[i].x;
            cin >> Vertice[i].y;
        }
        //Determina a distância entre cada pessoa
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Adjacencias[i][j] = sqrt(pow((Vertice[i].x-Vertice[j].x),2)+pow((Vertice[i].y-Vertice[j].y),2));
            }
        }
        prim(Vertice,Adjacencias, n);
        //Acumula o peso total das arestas
        for(int i = 0; i < n; i++){
            peso += Adjacencias[i][Vertice[i].pai];
        }
        cout << fixed << setprecision(2);
        cout << peso/100 << endl;
        cout << endl;
        c--;
    }    
    return 0;
}

void prim(Grafo Vertice[], double **adj, int n){
    int primeiro, pai, filho;
    pai = 0;
    double menorPeso = 100000;
    Vertice[0].pai = 0;
    while(true){
        primeiro = 1;
        for(int i = 0; i < n; i++){
            if(Vertice[i].pai != -1){
                for(int j = 0; j < 5; j++){
                    if((i != j) && Vertice[j].pai == -1){
                        if(primeiro){
                            menorPeso = adj[i][j];
                            pai = i;
                            filho = j;
                            primeiro = 0;
                        }else{
                            if(menorPeso > adj[i][j]){
                                menorPeso = adj[i][j];
                                pai = i;
                                filho = j;
                            }
                        }
                    }
                }
            }
                
        }
        if(primeiro == 1){ break; }
        Vertice[filho].pai = pai;
    }
}