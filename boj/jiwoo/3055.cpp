#include<iostream>
#include<queue>
#include <tuple>
using namespace std;
 
int n, m;
int water_visited[50][50];
char arr[50][50];
bool visited[50][50];
 
int dx[] = { 0, 0, 1, -1 };
int dy[] = { 1, -1, 0, 0 };
 
pair<int, int> target;
pair<int, int> start;
queue<tuple<int,int,int>> q;
queue<pair<int,int>> wq;


int main() {
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            water_visited[i][j] = 1000;
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> arr[i][j];
            if (arr[i][j] == 'S')  {
                start = make_pair(i,j);
            } else if (arr[i][j] == 'D') {
                target = make_pair(i,j);
            } else if (arr[i][j] == '*') {
                water_visited[i][j] = 0;
                wq.push(make_pair(i, j));
            }
        }
    }

    while (!wq.empty()) {
        int size = wq.size();
        for (int i = 0; i < size; i++) {
            int x,y;
            tie(x,y) = wq.front();
            wq.pop();
 
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (arr[nx][ny] == '.') {
                        if (water_visited[nx][ny] > water_visited[x][y] + 1) {
                            water_visited[nx][ny] = water_visited[x][y] + 1;
                            wq.push(make_pair(nx, ny));
                        }
                    }
                }
            }
        }
    }

    q.push(make_tuple(start.first, start.second, 0));
    visited[start.first][start.second] = true;
 
    while (!q.empty())   {
        int x, y,t ;
        tie(x,y,t) = q.front();

        q.pop();
        if (x == target.first && y == target.second) {
            cout << t << endl;
            return 0;
        }
 
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if (nx >= 0 && ny >= 0 && nx < n && ny < m)  {
                if (visited[nx][ny] == false && arr[nx][ny] != 'X' && water_visited[nx][ny] > t + 1) {
                    visited[nx][ny] = true;
                    q.push(make_tuple(nx,ny,t+1));
                }
            }
        }
    }
    cout << "KAKTUS" << endl; 

}
 
