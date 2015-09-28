pred([],[]). 
pred([H|T],[H1|T1]) :­ H1 is H+1, pred(T,T1).
% adiciona +1 em cada elemento na outra lista


zipPlus([],[],[]).
zipPlus([H1|T1],[H2|T2],[H3|T3]) :- H3 is H1+H2,zipPlus(T1,T2,T3).


countdown(0,[0]).
countdown(X, [H|T]) :- H is X, X1 is X-1,countdown(X1,T).


potenciaX(N,X,[]) :- N == X.
potenciaX(N,X,[H|T]) :- H is 2^X, X1 is X+1, potenciaX(N,X1,T).

potencia(N,L) :- potenciaX(N,0,L).


positivos([],[]).
positivos([H1|T1],[H2|T2]) :- H1>=0, H2 is H1, positivos(T1,T2).
positivos([H1|T1],L2) :- H1<0, positivos(T1,L2). 


mesmaPosicao(X,L1,L2) :- nth0(A,L1,X), nth0(B,L2,X), A==B.


intercala2(_,[],[]).
intercala(X, [H1|T1], [H2|T2]) :- H2 = H1, intercala2(X,T1,T2).
intercala2(X, L1, [H2|T2]) :- H2 = X, intercala(X,L1,T2).


azulejos(1,0).
azulejos(0,0).
azulejos(NA,NQ) :- 
        L is floor(sqrt(NA)),
        write("Um quadrado de "), write(L), write(" de lado\n"),
        NA1 is NA-L^2,
        NQ1 is NQ-1,
        azulejos(NA1,NQ1).
       
