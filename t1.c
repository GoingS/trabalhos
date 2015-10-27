#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct lista{
    char info[23];
    struct lista* prox;
    struct lista* ant;
};

typedef struct lista Lista;




Lista* cria(){
    return NULL;
}


Lista* insere(Lista* l, char login[]){
    Lista* p = l;
    Lista* novo = (Lista*) malloc(sizeof(Lista));
    Lista* anterior = NULL;
    Lista* aux;


    strcpy(novo->info, login);
    novo->prox = NULL;
    novo->ant = NULL;

    if(l == NULL)
        return novo;

    while((p != NULL) && (strcoll(p->info, novo->info) < 0)){ /*Procura o ponteiro p em que o novo nodo deve ser inserido antes */
        anterior = p;
        p = p->prox;
    }


    if(p == NULL){
        anterior->prox = novo; /* caso deva ser inserido antes do primeiro nodo */
        novo -> ant = anterior;
    }

    else if(p->ant == NULL){ /* no final */
        aux = l;
        l = novo;
        l->prox = aux;
        aux->ant = novo;
    }

    else{                   /* ou no meio */
        p->ant = novo;
        anterior->prox = novo;
        novo->prox = p;
        novo->ant = anterior;
    }

    return l;
}



void imprime(Lista* l){
    Lista* p;
    for(p=l;p!=NULL;p=p->prox){
        printf("%s\n", p->info);
    }
}

int tamanho(Lista* l){
    int i = 0;
    Lista* p = l;

    while(p){
        i++;
        p = p->prox;
    }
    return i;
}


Lista* obter_logins(Lista* l){
    char login[9];
    FILE *arq;

    arq = fopen("logins.txt", "r");

    while(!feof(arq)){
        fscanf(arq, "%s\n", login);

        if(strlen(login) < 8)
            l = insere(l, login); /* insere na lista os logins relevantes */
    }


    return l;
}

Lista* adiciona_sufixo(Lista* l){
    Lista* p = l;

    while(p != NULL){
        strcat(p->info, "@mycompany.com");
        p = p->prox;
    }
    return l;
}

int main(){

    Lista* l;

    l = cria(); /* inicialização da lista */
    l = obter_logins(l); /* obtem os logins com menos de 8 caractéres de um arquivo txt e passa para uma lista já ordernada */
    l = adiciona_sufixo(l); /* adiciona o sufixo @mycompany.com a lista */

    imprime(l);

}
