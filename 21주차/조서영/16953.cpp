#include<iostream>
#include<queue>
#include<utility>

using namespace std;

int bfs(long long a, long long b){
    queue <pair <long long, long long> > q;
    q.push(make_pair(a, 1));
    while(!q.empty()){
        pair <long long,long long> now = q.front();
        q.pop();
        if(now.first == b){
            return now.second;
        }
        if(now.first*2 <= b){
            q.push(make_pair(now.first*2, now.second+1));
        }
        if(now.first*10+1 <= b){
            q.push(make_pair((now.first*10)+1, now.second+1));
        }
    }
    return -1;
}

int main(){
    long long a,b;

    cin >> a >> b;
    cout << bfs(a,b);
}