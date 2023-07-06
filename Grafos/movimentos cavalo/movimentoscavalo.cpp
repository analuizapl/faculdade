
#include <bits/stdc++.h>
using namespace std;

struct grafo{
    int x, y;
    int dis;
    grafo() {}
    grafo(int x, int y, int dis) : x(x), y(y), dis(dis) {}
};


bool isInside(int x, int y, int N){
    if (x >= 1 && x <= N && y >= 1 && y <= N)
        return true;
    return false;
}


int minStepToReachTarget(int knightPos[], int targetPos[],int N){
    int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
    int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};

    queue<grafo> q;

    q.push(grafo(knightPos[0], knightPos[1], 0));

    grafo t;
    int x, y;
    bool visit[N + 1][N + 1];

    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= N; j++)
            visit[i][j] = false;

    visit[knightPos[0]][knightPos[1]] = true;

    while (!q.empty()){
        t = q.front();
        q.pop();

        if (t.x == targetPos[0] && t.y == targetPos[1])
            return t.dis;

        for (int i = 0; i < 8; i++){
            x = t.x + dx[i];
            y = t.y + dy[i];

            if (isInside(x, y, N) && !visit[x][y]) {
                visit[x][y] = true;
                q.push(grafo(x, y, t.dis + 1));
            }
        }
    }
    return 0;
}

int returnVal(char x){
    return (int)x - 87;
}
int returnVal2(char x){
    return (int)x - 48;
}

int main(){
    int N = 30;
    int cont = 8;
    while (cont>0) {
          string s1;
          cin>>s1;
          char firstLetter1 = s1.at(0);
          char last = s1.at(1);

          int letter1;
          letter1=returnVal(firstLetter1);
          int number1;
          number1=returnVal2(last);

          string s2;
          cin>>s2;
          int letter2;
          char firstLetter2 = s2.at(0);
          char last2 = s2.at(1);
          letter2=returnVal(firstLetter2);
          int number2;
          number2=returnVal2(last2);

          int knightPos[] = {letter1, number1};
          int targetPos[] = {letter2, number2};
          cout << "To get from " << s1 << " to " << s2 << " takes " << minStepToReachTarget(knightPos, targetPos, N) << " knight moves." << "\n";
          cont--;
  }
    return 0;
}
