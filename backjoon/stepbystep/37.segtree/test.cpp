#include<stdio.h>
int n, m, t;
int arr[200001];

int main(){
    scanf("%d %d %d", &n, &m, &t);
    
    for(int i=1; i<=n;i++){
        scanf("%d", &arr[i]);
    }
    
    while(m--){
        int q;
        scanf("%d", &q);
        if(q <= n)
            printf("%d\n", arr[q]);
        else{
            q -= n;
        }
    }
    return 0;
}